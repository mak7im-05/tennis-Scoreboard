package com.maxim.tennisscoreboard.servlets;

import com.maxim.tennisscoreboard.dao.MatchDao;
import com.maxim.tennisscoreboard.models.Match;
import com.maxim.tennisscoreboard.service.OngoingMatchesService;

public class FinishedMatchesPersistenceService {
    private static final MatchDao dao = new MatchDao();
    public static void save(Match match, String uuid) {
        OngoingMatchesService.delete(uuid);
        dao.save(match);
    }
}
