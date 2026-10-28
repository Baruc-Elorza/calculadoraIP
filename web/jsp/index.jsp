<%-- 
    Document   : index
    Created on : 18 oct. 2026, 19:04:18
    Author     : Jobae
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculadora de subredes IPv4</title>
    </head>
    <body>
        <h1>Calculadora de subredes IPv4</h1>
 <h1>Calculadora de subredes IPv4</h1>

    <p>Introduce una dirección IPv4 y el prefijo de red.</p>

        <form action="${pageContext.request.contextPath}/Subred" method="post">

        <label for="ip">Dirección IPv4:</label>
        <input type="text"
               id="ip"
               name="ip"
               placeholder="192.168.1.25"
               required>

        <br><br>

        <label for="prefijo">Prefijo CIDR:</label>
        <input type="number"
               id="prefijo"
               name="prefijo"
               min="0"
               max="32"
               placeholder="24"
               required>

        <br><br>

        <input type="submit" value="Calcular subred">

    </form>

    </body>     
        
</html>
