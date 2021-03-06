package pl.robert.api.app.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static pl.robert.api.app.shared.Constants.*;

@Entity
@Table(name = "user_details")
@Builder
@Getter @Setter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length = COL_LENGTH_LEVEL, nullable = false)
    String level;

    @Column(length = COL_LENGTH_EXPERIENCE, nullable = false)
    String expierience;

    @Column(name = "current_rank", length = COL_LENGTH_RANK, nullable = false)
    String currentRank;

    @Column(name = "number_of_wins", length = COL_LENGTH_CHALLENGE_SCORE, nullable = false)
    String numberOfWins;

    @Column(name = "number_of_loses", length = COL_LENGTH_CHALLENGE_SCORE, nullable = false)
    String numberOfLoses;

    @Column(name = "number_of_draws", length = COL_LENGTH_CHALLENGE_SCORE, nullable = false)
    String numberOfDraws;

    @Column(name = "number_of_posts", length = COL_LENGTH_FORUM_DETAILS, nullable = false)
    String numberOfPosts;

    @Column(name = "number_of_comments", length = COL_LENGTH_FORUM_DETAILS, nullable = false)
    String numberOfComments;

    @Column(name = "number_of_votes", length = COL_LENGTH_FORUM_DETAILS, nullable = false)
    String numberOfVotes;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Transient
    String leftExperienceToTheNextLevel;

    @Transient
    String currentExperienceInPercents;
}
