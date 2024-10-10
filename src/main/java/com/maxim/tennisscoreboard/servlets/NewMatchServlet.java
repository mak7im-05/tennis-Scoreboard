package com.maxim.tennisscoreboard.servlets;

import com.maxim.tennisscoreboard.dao.PlayerDao;
import com.maxim.tennisscoreboard.models.Player;
import com.maxim.tennisscoreboard.service.MatchGeneratorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "NewMatchServlet", urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private final PlayerDao playerDao;
    private final MatchGeneratorService matchGeneratorService;

    public NewMatchServlet() {
        this.matchGeneratorService = new MatchGeneratorService();
        this.playerDao = new PlayerDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("new-match.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstPlayerName = request.getParameter("player1");
        String secondPlayerName = request.getParameter("player2");

        if(isInvalidParameters(firstPlayerName, secondPlayerName)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameters is invalid");
            return;
        }

        if(firstPlayerName.equals(secondPlayerName)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "First player name is same as second player");
            return;
        }

        Player firstPlayer = playerDao.findByName(firstPlayerName);
        Player secondPlayer = playerDao.findByName(secondPlayerName);

        if(firstPlayer == null) {
            firstPlayer = new Player();
            firstPlayer.setName(firstPlayerName);
            playerDao.savePlayer(firstPlayer);
            firstPlayer = playerDao.findByName(firstPlayerName);
        }

        if(secondPlayer == null) {
            secondPlayer = new Player();
            secondPlayer.setName(secondPlayerName);
            playerDao.savePlayer(secondPlayer);
            secondPlayer = playerDao.findByName(secondPlayerName);
        }

        String generatedUUID = matchGeneratorService.generateMatch(firstPlayer, secondPlayer);
        String redirect = String.format("match-score?uuid=%s",generatedUUID);
        response.sendRedirect(redirect);
    }

    private boolean isInvalidParameters(String firstPlayerName, String secondPlayerName) {
        return firstPlayerName == null || secondPlayerName == null || firstPlayerName.isBlank() || secondPlayerName.isBlank();
    }
}
