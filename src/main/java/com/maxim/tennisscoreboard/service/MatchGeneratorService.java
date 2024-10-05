package com.maxim.tennisscoreboard.service;

import com.maxim.tennisscoreboard.models.Match;
import com.maxim.tennisscoreboard.models.Player;

public class MatchGeneratorService {
    public String generateMatch(Player firstPlayer, Player secondPlayer) {
        Match newMatch = createNewMatch(firstPlayer, secondPlayer);
        String uuid = OngoingMatchesService.put(newMatch);
        return uuid;
    }

    private Match createNewMatch(Player firstPlayer, Player secondPlayer) {
        Match newMatch = new Match();
        newMatch.setPlayer1(firstPlayer.getId());
        newMatch.setPlayer2(secondPlayer.getId());

        return newMatch;
    }
}
