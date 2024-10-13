<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 09.10.2024
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match score</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
  <table class="tennis_table-score">
    <caption>Теннисное табло</caption>
    <thead>
      <tr>
        <th>Игроки</th>
        <th>Очки</th>
        <th>Геймы</th>
        <th>Сеты</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><%= request.getParameter("firstPlayerName") %></td>
        <td><%= request.getParameter("firstPlayerPoints") %></td>
        <td><%= request.getParameter("firstPlayerGames") %></td>
        <td><%= request.getParameter("firstPlayerSets") %></td>
      </tr>
      <tr>
        <td><%= request.getParameter("secondPlayerName") %></td>
        <td><%= request.getParameter("secondPlayerPoints") %></td>
        <td><%= request.getParameter("secondPlayerGames") %></td>
        <td><%= request.getParameter("secondPlayerSets") %></td>
      </tr>
    </tbody>
  </table>
  <div class="btn-container">
    <button class="btn">
      победил игрок 1
    </button>
    <button class="btn">
      победил игрок 2
    </button> 
  </div>
</body>
</html>
