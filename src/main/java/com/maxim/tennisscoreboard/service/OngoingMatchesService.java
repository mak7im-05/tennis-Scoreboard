package com.maxim.tennisscoreboard.service;

import com.maxim.tennisscoreboard.models.Match;

import java.util.HashMap;
import java.util.UUID;

public class OngoingMatchesService {
    private static final HashMap<String, Match> ongoingMatches = new HashMap<>();


    public static String put(Match newMatch) {
        String uuid = UUID.randomUUID().toString();
        while (ongoingMatches.containsKey(uuid)) {
            uuid = UUID.randomUUID().toString();
        }
        ongoingMatches.put(uuid, newMatch);

        return uuid;
    }
}
