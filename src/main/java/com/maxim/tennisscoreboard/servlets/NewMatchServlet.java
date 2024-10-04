package com.maxim.tennisscoreboard.servlets;

import com.maxim.tennisscoreboard.dao.PlayerDao;
import com.maxim.tennisscoreboard.models.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "NewMatchServlet", urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private final PlayerDao playerDao;

    public NewMatchServlet() {
        this.playerDao = new PlayerDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("new-match.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("player1");

        Player p1 = new Player();
        p1.setName(firstName);

        playerDao.savePlayer(p1);
        Player p2 = playerDao.findByName(firstName);
        System.out.println(p2);
        response.setStatus(200);
    }
}
