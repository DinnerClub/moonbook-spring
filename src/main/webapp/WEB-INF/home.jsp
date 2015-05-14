<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<%
		response.sendRedirect(request.getContextPath() + "/index");
//response.sendRedirect(request.getContextPath() + "/views/pages/signin");
%>
</body>
</html>
