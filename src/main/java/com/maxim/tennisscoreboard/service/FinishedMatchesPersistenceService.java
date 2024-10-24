package com.maxim.tennisscoreboard.service;

import com.maxim.tennisscoreboard.dao.MatchDao;
import com.maxim.tennisscoreboard.models.Match;

import java.util.List;

public class FinishedMatchesPersistenceService {
    public static final int MATCHES_PER_PAGE = 6;
    private static final MatchDao matchDao = new MatchDao();

    public static void save(Match match, String uuid) {
        OngoingMatchesService.delete(uuid);
        matchDao.save(match);
    }

    public static List<Match> getAllMatches() {
        return matchDao.getMatches();
    }

    public static List<Match> getMatchesByName(String filterByPlayerName) {
        return matchDao.getMatchesByName(filterByPlayerName);
    }
}
