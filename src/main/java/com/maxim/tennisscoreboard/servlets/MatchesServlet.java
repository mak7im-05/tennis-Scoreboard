package com.maxim.tennisscoreboard.servlets;

import com.maxim.tennisscoreboard.models.Match;
import com.maxim.tennisscoreboard.service.FinishedMatchesPersistenceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MatchesServlet", urlPatterns = "/matches")
public class MatchesServlet extends HttpServlet {
    private static final int MATCHES_PER_PAGE = 6;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        String filterByPlayerName = request.getParameter("filterByPlayerName");
        List<Match> allMatches;

        if(request.getParameter("pageNumber") != null) {
            String s = request.getParameter("pageNumber");
            pageNumber = Integer.parseInt(s);
        }

        if(filterByPlayerName != null && !filterByPlayerName.isBlank()) {
            filterByPlayerName = request.getParameter("filterByPlayerName");
            allMatches = FinishedMatchesPersistenceService.getMatchesByName(filterByPlayerName);
        } else {
            allMatches = FinishedMatchesPersistenceService.getAllMatches();
        }

        List<Match> matches = getMatchesPerPage(allMatches, pageNumber);
        int totalPages = (int) (Math.ceil(allMatches.size() * 1.0 /MATCHES_PER_PAGE));

        request.setAttribute("matches", matches);
        request.setAttribute("size", matches.size());
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);


        request.getRequestDispatcher("matches.jsp").forward(request,response);
     }

    private List<Match> getMatchesPerPage(List<Match> allMatches, int pageNumber) {
        List<Match> matches = new ArrayList<>();
        if(!allMatches.isEmpty()) {
            for (int i = (pageNumber-1)*MATCHES_PER_PAGE; i < pageNumber*MATCHES_PER_PAGE && i < allMatches.size(); i++) {
                matches.add(allMatches.get(i));
            }
        }

        return matches;
    }
}
