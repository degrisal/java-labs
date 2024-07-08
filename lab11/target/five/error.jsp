<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="styles/error.css">
    <title>Error Page</title>
</head>
<body>
    <div id="container">
        <h2>Something went wrong...</h2>
        <p id="description"> [<%=response.getStatus()%>] Error: <%=exception.getMessage() %></p>
    </div>
</body>
</html>