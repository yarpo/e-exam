<%-- 
    Document   : login.jsp
    Created on : 2010-06-12, 14:09:17
    Author     : yarpo
--%>

<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Egzamin: rozpoczęcie</title>
    </head>
    <body>
        <h1>Podaj swój unikatowy klucz</h1>
        <c:if test="${sessionScope.success == false}">
            <p>${sessionScope.message}</p>
        </c:if>

        <form action="student?action=setKey" method="post">
            <input type="text" name="key" />
            <input type="submit" name="start_exam" value="Rozpocznij" />
        </form>
    </body>
</html>
