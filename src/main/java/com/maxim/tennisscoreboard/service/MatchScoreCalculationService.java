package com.maxim.tennisscoreboard.service;

import com.maxim.tennisscoreboard.dao.MatchDao;
import com.maxim.tennisscoreboard.models.Match;

public class MatchScoreCalculationService {
    private static final MatchDao matchDao = new MatchDao();

    public static void winPoints(Match match, int playerNameId) {
        if(playerNameId == 1) {
            match.getScore().winPlayerPoints(1, 2);
        } else {
            match.getScore().winPlayerPoints(2, 1);
        }
    }
}
