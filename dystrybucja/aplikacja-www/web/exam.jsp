<%-- 
    Document   : index
    Created on : 2010-06-12, 14:01:12
    Author     : yarpo
--%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
	<link href="inc/css/style.css" rel="stylesheet" type="text/css">
	<link href="inc/css/pager.css" rel="stylesheet" type="text/css">
	<script src="inc/js/jquery.js" type="text/javascript"></script>
	<script src="inc/js/jquery.pager.js" type="text/javascript"></script>
	<script src="inc/js/exam.js" type="text/javascript"></script>
        <meta http-equiv="content-type" content="text/html;charset=utf-8">
        <title>Egzamin: podaj odpowiedzi</title>
    </head>
    <body>
        <h1>Witaj na egzaminie</h1>

        <form action="student" method="post">
	    <input type="hidden" name="action" value="endExam" />
            <ol>
            <c:forEach var="question" items="${questionList}">
                <li>${question.content}<br />
                <c:choose>
                    <c:when test="${question.typeOpened == true}">
                        <textarea name="question${question.id}" cols="60" rows="20"></textarea>
                    </c:when>

                    <c:when test="${question.typeABCD == true}">
                        <ul>
                        <c:forEach var="answer" items="${question.answers.answersABCD}">
                            <li><input type="checkbox" name="question${question.id}" value="${answer.content}" /> ${answer.content}</li>
                        </c:forEach>
                        </ul>
                    </c:when>

                    <c:when test="${question.typeGap == true}">
                        <ul>
                        <c:forEach var="answer" items="${question.answers.answersGap}">
                            <li><input type="text" name="question${question.id}" /></li>
                        </c:forEach>
                        </ul>
                    </c:when>
                </c:choose>
                </li>
            </c:forEach>
            </ol>

            <div id="pager"></div>

            <input name="save" id="save" type="submit" value="zakończ egzamin" />
        </form>

        <div id="info">
            <p>Nie używaj entera.</p>
            <ul>
                <li>aby przejść do pytania wybierz za pomocą myszy jego numer</li>
                <li>aby zakończyć egzamin wybierz za pomocą myszy przycisk "zakończ egzamin"</li>
            </ul>
        </div>
    </body>
</html>
