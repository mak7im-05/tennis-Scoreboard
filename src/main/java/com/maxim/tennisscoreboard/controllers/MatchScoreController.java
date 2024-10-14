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
        request.setAttribute("firstPlayerGames", match.getScore().getPlayerGames(1));
        request.setAttribute("firstPlayerSets", match.getScore().getPlayerSets(1));

        request.setAttribute("secondPlayerName", secondPlayerName);
        request.setAttribute("secondPlayerGames", match.getScore().getPlayerGames(2));
        request.setAttribute("secondPlayerSets", match.getScore().getPlayerSets(2));

        if(match.getScore().getPlayerPoints(1) == match.getScore().getPlayerPoints(2) && match.getScore().getPlayerPoints(1) == 40) {
            if(match.getScore().getPlayerExtraPoints(1) - match.getScore().getPlayerExtraPoints(2) == 1) {
                request.setAttribute("firstPlayerPoints", "Больше");
                request.setAttribute("secondPlayerPoints", "Меньше");
            } else if(match.getScore().getPlayerExtraPoints(1) - match.getScore().getPlayerExtraPoints(2) == -1) {
                request.setAttribute("firstPlayerPoints", "Меньше");
                request.setAttribute("secondPlayerPoints", "Больше");
            } else {
                request.setAttribute("firstPlayerPoints", "Ровно");
                request.setAttribute("secondPlayerPoints", "Ровно");
            }
        } else {
            request.setAttribute("firstPlayerPoints", match.getScore().getPlayerPoints(1));
            request.setAttribute("secondPlayerPoints", match.getScore().getPlayerPoints(2));

        }
    }
}
