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

    public void winPlayerPoints(int winPlayerNumber, int losePlayerNumber) {
        int winPlayerPoints = playerScore.get(winPlayerNumber).getFirst();
        int losePlayerPoints = playerScore.get(losePlayerNumber).getFirst();

        if (!isEnd) {
            if (playerScore.get(winPlayerNumber).get(1).equals(playerScore.get(losePlayerNumber).get(1)) && playerScore.get(winPlayerNumber).get(1) == 6) {
                if(winPlayerPoints == 6) {
                    winPlayerGame(winPlayerNumber, losePlayerNumber);
                } else {
                    winPlayerPoints++;
                    playerScore.get(winPlayerNumber).set(0, winPlayerPoints);
                }
            } else if (winPlayerPoints < 30) {
                winPlayerPoints += 15;
                playerScore.get(winPlayerNumber).set(0, winPlayerPoints);
            } else if (winPlayerPoints == 30) {
                winPlayerPoints += 10;
                playerScore.get(winPlayerNumber).set(0, winPlayerPoints);
            } else {
                if (winPlayerPoints != losePlayerPoints) {
                    winPlayerGame(winPlayerNumber, losePlayerNumber);
                } else {
                    int winExtraPoint = playerScore.get(winPlayerNumber).getLast();
                    int loseExtraPoint = playerScore.get(losePlayerNumber).getLast();
                    if (winExtraPoint == loseExtraPoint + 1) {
                        winPlayerGame(winPlayerNumber, losePlayerNumber);
                    } else {
                        winExtraPoint += 1;
                        playerScore.get(winPlayerNumber).set(3, winExtraPoint);
                    }
                }
            }
        }
    }

    private void winPlayerGame(int winPlayerNumber, int losePlayerNumber) {
        int winPlayerGame = playerScore.get(winPlayerNumber).get(1);
        int losePlayerGame = playerScore.get(losePlayerNumber).get(1);

        if(winPlayerGame >= 5 && Math.abs(winPlayerGame + 1 - losePlayerGame) > 1) {
            winPlayerSet(winPlayerNumber);
        } else {
            winPlayerGame++;
            playerScore.get(winPlayerNumber).set(1, winPlayerGame);
        }
        clearScore();
    }

    private void winPlayerSet(int winPlayerNumber) {
        int winPlayerSet = playerScore.get(winPlayerNumber).get(2);
        if (winPlayerSet == 1) {
            winPlayerSet++;
            playerScore.get(winPlayerNumber).set(2, winPlayerSet);
            winMatch();
        } else {
            winPlayerSet++;
            playerScore.get(winPlayerNumber).set(2, winPlayerSet);
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

    public boolean isEnd() {
        return isEnd;
    }


    public int getPlayerExtraPoints(int playerId) {
        return playerScore.get(playerId).get(3);
    }
}
//if (winPlayerGame == 5 && losePlayerGame < 5) {
//winPlayerSet(winPlayerNumber);
//            } else if(winPlayerGame == 5 && losePlayerGame == 6) {
//winPlayerGame++;
//        playerScore.get(winPlayerNumber).set(1, winPlayerGame);
//            } else {
//winPlayerGame++;
//        playerScore.get(winPlayerNumber).set(1, winPlayerGame);
//            }