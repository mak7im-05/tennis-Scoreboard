package com.maxim.tennisscoreboard.servlets;

import com.maxim.tennisscoreboard.models.Match;
import com.maxim.tennisscoreboard.service.FinishedMatchesPersistenceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.maxim.tennisscoreboard.service.FinishedMatchesPersistenceService.MATCHES_PER_PAGE;

@WebServlet(name = "MatchesServlet", urlPatterns = "/matches")
public class MatchesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        String filterByPlayerName = request.getParameter("filterByPlayerName");
        List<Match> matches;

        if(request.getParameter("pageNumber") != null) {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }

        matches = findMatchesByName(filterByPlayerName, pageNumber);

        int totalPages = (int) (Math.ceil(matches.size() * 1.0 /MATCHES_PER_PAGE));

        request.setAttribute("matches", matches);
        request.setAttribute("size", matches.size());
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("matches.jsp").forward(request,response);
     }

    private List<Match> findMatchesByName(String filterByPlayerName, int pageNumber) {
        List<Match> matches;
        if(isFilterByPlayerNameNull(filterByPlayerName)) {
            matches = FinishedMatchesPersistenceService.getMatchesByName(filterByPlayerName, pageNumber);
        } else {
            matches = FinishedMatchesPersistenceService.getAllMatches(pageNumber);
        }
        return matches;
    }

    private boolean isFilterByPlayerNameNull(String filterByPlayerName) {
        return filterByPlayerName != null && !filterByPlayerName.isBlank();
    }
}
