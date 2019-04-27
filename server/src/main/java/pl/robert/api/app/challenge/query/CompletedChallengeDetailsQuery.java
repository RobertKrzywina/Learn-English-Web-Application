package pl.robert.api.app.challenge.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompletedChallengeDetailsQuery {

    String id;
    String result;
    String score;
    String opponentScore;
    String username;
    String opponentUsername;
    String gainedXP;
    String answersStatus;
}
