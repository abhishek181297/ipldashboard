package com.abhishek.ipldashboard.batchprocessing;

import com.abhishek.ipldashboard.data.MatchInput;
import com.abhishek.ipldashboard.model.Match;
import com.abhishek.ipldashboard.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class MatchInputProcessor implements ItemProcessor<MatchInput, Match> {
//    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);
//
//    @Override
//    public Match process(final Person person) throws Exception {
//        final String firstName = person.getFirstName().toUpperCase();
//        final String lastName = person.getLastName().toUpperCase();
//
//        final Person transformedPerson = new Person(firstName, lastName);
//
//        log.info("Converting (" + person + ") into (" + transformedPerson + ")");
//
//        return transformedPerson;
//    }

    @Override
    public Match process(MatchInput matchInput) throws Exception {
        String firstInningsTeam, secondInningsTeam;
        if (matchInput.getToss_decision().equals("bat")) {
            firstInningsTeam = matchInput.getToss_winner();
            secondInningsTeam = matchInput.getTeam1().equals(matchInput.getToss_winner()) ?
                    matchInput.getTeam2() : matchInput.getTeam1();
        } else {
            secondInningsTeam = matchInput.getToss_winner();
            firstInningsTeam = matchInput.getTeam1().equals(matchInput.getToss_winner()) ?
                    matchInput.getTeam2() : matchInput.getTeam1();
        }


        Match match = Match.builder()
                .id(Long.parseLong(matchInput.getId()))
                .city(matchInput.getCity())
                .date(
                        LocalDate.parse(matchInput.getDate(),
                                DateTimeFormatter.ofPattern(Constants.YEAR_FIRST_HYPHENATED_DATE_PATTERN)))
                .tossDecision(matchInput.getToss_decision())
                .tossWinner(matchInput.getToss_winner())
                .team1(firstInningsTeam)
                .team2(secondInningsTeam)
                .matchWinner(matchInput.getWinner())
                .playerOfMatch(matchInput.getPlayer_of_match())
                .result(matchInput.getResult())
                .resultMargin(matchInput.getResult_margin())
                .umpire1(matchInput.getUmpire1())
                .umpire2(matchInput.getUmpire2())
                .venue(matchInput.getVenue())
                .build();
        log.info("Converting (" + matchInput + ") into (" + match + ")");
        return match;
    }
}
