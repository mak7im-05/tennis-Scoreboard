<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <h1>Сыгранные матчи</h1>

        <form class="findForm" action="matches" method="get" name="filterByPlayerName">
            <input type="text" placeholder="Имя игрока" name="filterByPlayerName">
            <button type="submit">Найти</button>
        </form>

        <a href="index.jsp">На главную</a>

        <c:if test="${size>0}">
            <table class="tennis_table-score">
                <thead>
                    <tr>
                        <th>№ Match</th>
                        <th>Player 1</th>
                        <th>Player 2</th>
                        <th>Winner</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="match" items="${matches}">
                        <tr>
                            <td>${match.getId()}</td>
                            <td>${match.getPlayer1().getName()}</td>
                            <td>${match.getPlayer2().getName()}</td>
                            <td>${match.getWinner().getName()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="paginator">
                <c:if test="${currentPage > 1}">
                    <a href="${pageContext.request.contextPath}/matches?pageNumber=${currentPage - 1}&filterByPlayerName=${filterByPlayerName}">Предыдущая</a>
                </c:if>
                <span>Страница ${currentPage} из ${totalPages}</span>
                <c:if test="${currentPage < totalPages}">
                    <a href="${pageContext.request.contextPath}/matches?pageNumber=${currentPage + 1}&filterByPlayerName=${filterByPlayerName}">Следующая</a>
                </c:if>
            </div>

        </c:if>
        <c:if test="${size <= 0}">
            <p class="notFound">Матчей не найдено</p>
        </c:if>
    </body>
</html>
