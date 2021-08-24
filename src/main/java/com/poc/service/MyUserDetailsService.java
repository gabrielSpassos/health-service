package com.poc.service;

import com.poc.entity.UserEntity;
import com.poc.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        return Optional.ofNullable(userEntity)
                .map(UserRepositoryUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Usuário %s não existe.", email)));
    }

    private final static class UserRepositoryUserDetails extends UserEntity implements UserDetails {

        private UserRepositoryUserDetails(UserEntity userEntity) {
            super(userEntity);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return super.getRoles();
        }

        @Override
        public String getUsername() {
            return super.getEmail();
        }

        @Override
        public String getPassword() {
            return super.getPassword();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
