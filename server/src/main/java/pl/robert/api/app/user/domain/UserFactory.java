package pl.robert.api.app.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.robert.api.app.user.domain.dto.CreateUserDTO;

import java.util.Collections;
import java.util.HashSet;

class UserFactory {

    static User create(CreateUserDTO dto) {
        return User
                .builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder().encode(dto.getPassword()))
                .roles(new HashSet<>(Collections.singleton(new Role(1L, "ROLE_USER"))))
                .isEnabled(false)
                .build();
    }

    @Bean
    static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    static UserDetails createDetails(User user) {
        return UserDetails
                .builder()
                .level("1")
                .expierience("0")
                .currentRank(String.valueOf(UserRanks.BRONZE))
                .user(user)
                .build();
    }
}
