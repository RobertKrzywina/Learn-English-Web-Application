package pl.robert.api.app.challenge.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

import static pl.robert.api.app.shared.Constants.M_DEFENDER_USERNAME_EMPTY;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) @ToString
public class CreateChallengeDto {

    String attackerUsername;

    @NotEmpty(message = M_DEFENDER_USERNAME_EMPTY)
    String defenderUsername;
}