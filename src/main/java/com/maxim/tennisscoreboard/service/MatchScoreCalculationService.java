package com.maxim.tennisscoreboard.service;

import com.maxim.tennisscoreboard.models.Match;

public class MatchScoreCalculationService {
    public static void winPoints(Match match, int playerNameId) {
        if (playerNameId == 1) {
            match.getScore().winPlayerPoints(1, 2);
        } else {
            match.getScore().winPlayerPoints(2, 1);
        }
    }
}
