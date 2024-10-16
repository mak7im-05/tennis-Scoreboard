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
    <title>Finish Match score</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Победил ${winner}</h1>
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
        <td>${firstPlayerName}</td>
        <td>${firstPlayerPoints}</td>
        <td>${firstPlayerGames}</td>
        <td>${firstPlayerSets}</td>
    </tr>
    <tr>
        <td>${secondPlayerName}</td>
        <td>${secondPlayerPoints}</td>
        <td>${secondPlayerGames}</td>
        <td>${secondPlayerSets}</td>
    </tr>
    </tbody>
</table>
</body>
</html>
