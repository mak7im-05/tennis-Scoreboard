package com.maxim.tennisscoreboard.models;

import java.util.*;

public class Score {
    private final HashMap<Integer, List<Integer>> playerScore = new HashMap<>();
    private int playerNumber = 1;
    boolean isEnd;

    public void initPlayer() {
        List<Integer> put = new ArrayList<>();
        Collections.addAll(put, 0, 0, 0, 0);
        playerScore.put(playerNumber++, put);
    }

    public void winPointsPlayer(int winPlayerNumber, int losePlayerNumber) {
        int winPlayerPoints = playerScore.get(winPlayerNumber).getFirst();
        int losePlayerPoints = playerScore.get(losePlayerNumber).getFirst();

        if (!isEnd) {
            if (winPlayerNumber < 30) {
                winPlayerPoints += 15;
                playerScore.get(winPlayerNumber).set(0, winPlayerPoints);
            } else if (winPlayerNumber == 30) {
                winPlayerPoints += 10;
                playerScore.get(winPlayerNumber).set(0, winPlayerPoints);
            } else {
                if (winPlayerPoints != losePlayerPoints) {
                    winPlayerGame(winPlayerNumber, losePlayerNumber, winPlayerPoints, losePlayerPoints);
                } else {
                    int winExtraPoint = playerScore.get(winPlayerNumber).getLast();
                    int loseExtraPoint = playerScore.get(losePlayerNumber).getLast();
                    if (winExtraPoint == loseExtraPoint + 1) {
                        winPlayerGame(winPlayerNumber, losePlayerNumber, winPlayerPoints, losePlayerPoints);
                    } else {
                        winExtraPoint += 1;
                        playerScore.get(winPlayerNumber).set(3, winExtraPoint);
                    }
                }
            }
        }
    }

    private void winPlayerGame(int winPlayerNumber, int losePlayerNumber, int winPlayerPoints, int losePlayerPoints) {
        int winPlayerGame = playerScore.get(winPlayerNumber).get(1);
        int losePlayerGame = playerScore.get(losePlayerNumber).get(1);

        if (winPlayerGame < 5) {
            winPlayerGame++;
            playerScore.get(winPlayerNumber).set(1, winPlayerGame);
        } else {
            if (winPlayerGame == losePlayerGame + 1) {
                winPlayerSet(winPlayerNumber);
            } else {
                winPlayerGame++;
                playerScore.get(winPlayerNumber).set(1, winPlayerGame);
            }
        }
        clearScore();
    }

    private void winPlayerSet(int winPlayerNumber) {
        int winPlayerSet = playerScore.get(winPlayerNumber).get(2);

        if (winPlayerSet == 1) {
            winPlayerSet++;
            playerScore.get(winPlayerNumber).set(2, winPlayerSet);
        } else {
            winMatch();
        }
        clearMatch();
    }

    private void winMatch() {
        isEnd = true;
    }

    private void clearScore() {
        playerScore.get(1).set(0, 0);
        playerScore.get(2).set(0, 0);
        playerScore.get(1).set(3, 0);
        playerScore.get(2).set(3, 0);
    }

    private void clearMatch() {
        playerScore.get(1).set(1, 0);
        playerScore.get(2).set(1, 0);
    }

    public int getPlayerPoints(int playerNumber) {
        return playerScore.get(playerNumber).getFirst();
    }

    public int getPlayerGames(int playerNumber) {
        return playerScore.get(playerNumber).get(1);
    }

    public int getPlayerSets(int playerNumber) {
        return playerScore.get(playerNumber).get(2);
    }


}
