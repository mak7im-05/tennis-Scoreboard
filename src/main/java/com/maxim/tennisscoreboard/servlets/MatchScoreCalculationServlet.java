package com.maxim.tennisscoreboard.servlets;

import com.maxim.tennisscoreboard.controllers.MatchScoreController;
import com.maxim.tennisscoreboard.models.Match;
import com.maxim.tennisscoreboard.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MatchScoreCalculationServlet", urlPatterns = "/match-score")
public class MatchScoreCalculationServlet extends HttpServlet {
    private final MatchScoreController matchScoreController = new MatchScoreController();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");

        if(uuid.isBlank()) {
            response.sendError(400,"Invalid uuid!");
            return;
        }
        if (OngoingMatchesService.get(uuid) == null){
            response.sendError(400,"No match with this uuid");
            return;
        }
        
        matchScoreController.handleGet(request, uuid);
        request.getRequestDispatcher("match-score.jsp").forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        int winPlayer = Integer.parseInt(request.getParameter("winPlayer"));
        int losePlayer = Integer.parseInt(request.getParameter("losePlayer"));
        Match match = OngoingMatchesService.get(uuid);
        match.getScore().winPlayerPoints(winPlayer, losePlayer);
    }

}
