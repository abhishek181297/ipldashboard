package com.abhishek.ipldashboard.repository;

import com.abhishek.ipldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    default List<Match> getLatestMatchesByTeam(String teamName, int page, int count) {
        return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(page, count));
    }
}
