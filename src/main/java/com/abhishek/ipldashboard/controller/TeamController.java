package com.abhishek.ipldashboard.controller;

import com.abhishek.ipldashboard.model.Team;
import com.abhishek.ipldashboard.repository.MatchRepository;
import com.abhishek.ipldashboard.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(path = "/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    @GetMapping("/name/{teamName}")
    public Team getTeam(@PathVariable String teamName,
                        @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(name = "page-size", required = false, defaultValue = "4") int pageSize) {
        log.info("Get Request to fetch the team details for team - {}", teamName);
        Team team = teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.getLatestMatchesByTeam(teamName, page, pageSize));
        return team;
    }
}
