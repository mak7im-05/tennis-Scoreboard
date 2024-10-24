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

        matches = findMatchesByName(filterByPlayerName);

        int totalPages = (int) (Math.ceil(matches.size() * 1.0 /MATCHES_PER_PAGE));

        List<Match> matchesPerPage = getMatchesPerPage(matches, pageNumber);

        request.setAttribute("matches", matchesPerPage);
        request.setAttribute("size", matches.size());
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("matches.jsp").forward(request,response);
     }

    private List<Match> findMatchesByName(String filterByPlayerName) {
        List<Match> matches;
        if(isFilterByPlayerNameNull(filterByPlayerName)) {
            matches = FinishedMatchesPersistenceService.getMatchesByName(filterByPlayerName);
        } else {
            matches = FinishedMatchesPersistenceService.getAllMatches();
        }
        return matches;
    }

    private boolean isFilterByPlayerNameNull(String filterByPlayerName) {
        return filterByPlayerName != null && !filterByPlayerName.isBlank();
    }

    private static List<Match> getMatchesPerPage(List<Match> allMatches, int pageNumber) {
        List<Match> matches = new ArrayList<>();
        if(!allMatches.isEmpty()) {
            for (int i = (pageNumber-1)*MATCHES_PER_PAGE; i < pageNumber*MATCHES_PER_PAGE && i < allMatches.size(); i++) {
                matches.add(allMatches.get(i));
            }
        }
        return matches;
    }
}
