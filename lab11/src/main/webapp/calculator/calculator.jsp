<%@ page errorPage="/error.jsp" %>
<% 
	String x = request.getParameter("x");
	String y = request.getParameter("y");
	String result = "";

	if (request.getAttribute("result") != null) {
		result = request.getAttribute("result").toString();
	}

	if (x == null) { x = ""; }
	if (y == null) { y = ""; }
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="styles/calculator.css">
		<title>Calculator</title>
	</head>
    <body>
        <div class="container">
            <form action="/five/calculate" method="POST">
                <div>
                    <label> Input number X </label> 
					<input name="x" value="<%=x%>" required>
				</div>
				<div>
                    <label> Input number Y </label>
					<input name="y" value="<%=y%>" required>
                </div>
                <div class="buttons">
                    <button type="submit" name="op" value="+">+</button>
                    <button type="submit" name="op" value="-">-</button>
                    <button type="submit" name="op" value="*">*</button>
                    <button type="submit" name="op" value="/">/</button>
                </div>
                <div class="result">
					<span id="result"><%=result%></span>
				</div>
            </form>
        </div>
    </body>
</html>
