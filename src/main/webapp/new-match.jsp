<%-- Created by IntelliJ IDEA. User: Максим Date: 09.09.2024 Time: 7:59 To change this template use File | Settings |
  File Templates. --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>

    <head>
      <title>new-match</title>
      <link rel="stylesheet" href="style.css">
    </head>

    <body>
      <div class="container">
        <form class="new-match" action="new-match" method="post" name="newmatchform">
          <div class="form_container">
            <input type="text" class="player_name_input" name="player1" id="player1" placeholder="Имя второго игрока"
              required>
            <input type="text" class="player_name_input" name="player2" id="player2" placeholder="Имя первого игрока"
              required>
            <input type="submit" value="Начать" class="btn-start">
          </div>
        </form>
      </div>

    </body>

    </html>