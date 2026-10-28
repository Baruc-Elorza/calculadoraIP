<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Resultado de la subred</title>
    </head>
    <body>
        <h1>Resultado de la subred</h1>
        <p>IP ingresada: ${ip}</p>
        <p>Prefijo: /${prefijo}</p>
        <p>Máscara: ${subred.obtenerMascara()}</p>
        <p>Dirección de red: ${subred.obtenerDireccionRed()}</p>
        <p>Broadcast: ${subred.obtenerBroadcast()}</p>
        <p>Primera IP utilizable: ${subred.obtenerPrimeraIP()}</p>
        <p>Última IP utilizable: ${subred.obtenerUltimaIP()}</p>
        <p>Total de direcciones: ${subred.obtenerTotalDirecciones()}</p>
        <p>Hosts utilizables: ${subred.obtenerHostsUtilizables()}</p>
        <a href="${pageContext.request.contextPath}/jsp/index.jsp">Volver</a>
    </body>
</html>
