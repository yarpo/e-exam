<%-- 
    Document   : upload
    Created on : 2010-06-21, 19:50:08
    Author     : michal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Egzamin - podaj konfigurację egzaminu</title>
    </head>
    <body>
        <h1>Witaj na egzaminie</h1>
        <p>Proszę podać plik z konfiguracją egzaminu</p>
	<form action="/dystrybucja/examiner?action=uploaded" enctype="multipart/form-data" method="post">
	    Wybierz plik: <input type="file" name="secretDocument" /><br />
	    <input type="submit" value="Importuj" />
	</form>
    </body>
</html>
