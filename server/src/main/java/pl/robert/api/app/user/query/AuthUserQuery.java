package pl.robert.api.app.user.query;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthUserQuery {

    String username;
    String roles;
    String isAuthenticated;
}
