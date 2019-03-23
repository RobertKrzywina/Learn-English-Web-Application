package pl.robert.api.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.robert.api.security.dto.AuthorizationDTO;
import pl.robert.api.user.domain.Role;
import pl.robert.api.user.domain.UserFacade;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserDetailsServiceImpl implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthorizationDTO user = userFacade.findByUsername(username);

        if (user == null) throw new UsernameNotFoundException("User not found");

        if (!user.isVerified()) {
            user.setPassword(UUID.randomUUID().toString());
            logger.warn("User is not verified! Changing password to random UUID!");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                convertAuthorities(user.getRoles()));
    }

    private Set<GrantedAuthority> convertAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }
}
