<%-- 
    Document   : ok
    Created on : 2010-06-21, 23:02:49
    Author     : michal
--%>

<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Egzamin: upload konfiguracji</title>
        <script src="inc/js/jquery.js" type="text/javascript"></script>
        <script src="inc/js/examiner.js" type="text/javascript"></script>
    </head>
    <body>
        <c:if test="${requestScope.status == true}">
            <h1>Udało się zaimportować (${requestScope.exam.name})</h1>
            <p>Wczytano ${requestScope.size} pytań. <b>Egzamin trwa.</b></p>
            <ul>
            <c:forEach var="question" items="${requestScope.questions}">
                <li>${question.content}</li>
            </c:forEach>
            </ul>
            <p><a id="finish" href="/dystrybucja/mergeAnswers">Zakończ egzamin</a></p>
	    </c:if>
	   <c:if test="${requestScope.status == false}">
               <h1>Nie udało się zaimportować</h1>
               <p>Spróbuj ponownie</p>
	   </c:if>

    </body>
</html>
