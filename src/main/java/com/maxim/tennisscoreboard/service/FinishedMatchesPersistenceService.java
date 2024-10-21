package com.maxim.tennisscoreboard.service;

import com.maxim.tennisscoreboard.dao.MatchDao;
import com.maxim.tennisscoreboard.models.Match;

import java.util.ArrayList;
import java.util.List;

public class FinishedMatchesPersistenceService {
    public static final int MATCHES_PER_PAGE = 6;
    private static final MatchDao matchDao = new MatchDao();

    public static void save(Match match, String uuid) {
        OngoingMatchesService.delete(uuid);
        matchDao.save(match);
    }

    public static List<Match> getAllMatches(int pageNumber) {
        List<Match>matches = matchDao.getMatches();
        return getMatchesPerPage(matches, pageNumber);
    }

    public static List<Match> getMatchesByName(String filterByPlayerName, int pageNumber) {
        List<Match> matches = matchDao.getMatchesByName(filterByPlayerName);
        return getMatchesPerPage(matches, pageNumber);
    }

    private static List<Match> getMatchesPerPage(List<Match> allMatches, int pageNumber) {
        List<Match> matches = new ArrayList<>();
        if(!allMatches.isEmpty()) {
            for (int i = (pageNumber-1)*MATCHES_PER_PAGE; i < pageNumber*MATCHES_PER_PAGE && i < allMatches.size(); i++) {
                matches.add(allMatches.get(i));
            }
        }
        return matches;
    }
}
