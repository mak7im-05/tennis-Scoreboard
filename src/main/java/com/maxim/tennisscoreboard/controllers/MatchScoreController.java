package com.maxim.tennisscoreboard.controllers;

import com.maxim.tennisscoreboard.models.Match;
import com.maxim.tennisscoreboard.service.OngoingMatchesService;
import jakarta.servlet.http.HttpServletRequest;

public class MatchScoreController {

    public void handleGet(HttpServletRequest request, String uuid) {
        Match match = OngoingMatchesService.get(uuid);

        String firstPlayerName = match.getPlayer1().getName();
        String secondPlayerName = match.getPlayer2().getName();

        request.setAttribute("uuid", uuid);

        request.setAttribute("firstPlayerName", firstPlayerName);
        request.setAttribute("firstPlayerPoints", match.getScore().getPlayerPoints(1));
        request.setAttribute("firstPlayerGames", match.getScore().getPlayerGames(1));
        request.setAttribute("firstPlayerSets", match.getScore().getPlayerSets(1));

        request.setAttribute("secondPlayerName", secondPlayerName);
        request.setAttribute("secondPlayerPoints", match.getScore().getPlayerPoints(2));
        request.setAttribute("secondPlayerGames", match.getScore().getPlayerGames(2));
        request.setAttribute("secondPlayerSets", match.getScore().getPlayerSets(2));
    }
}
