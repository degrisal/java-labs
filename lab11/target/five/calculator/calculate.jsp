<%@ page errorPage="/error.jsp" %>
<%
    try { 
        Double.parseDouble(request.getParameter("x"));
    } catch (NumberFormatException exc) {
        response.setContentType("text/plain");
        throw new ServletException("`x` variable is empty.");
    }
    
    try {
        Double.parseDouble(request.getParameter("y"));
    } catch (NumberFormatException exc) {
        response.setContentType("text/plain");
        throw new ServletException("`y` variable is empty.");
    }
    
    double x = Double.parseDouble(request.getParameter("x"));
    double y = Double.parseDouble(request.getParameter("y"));
    double action = 0;
    String result = "";

    String operation = request.getParameter("op");

    if ("+".equals(operation)) {
        action = x + y;
        result = x + " + " + y + " = " + action;
    } else if ("-".equals(operation)) {
        action = x - y;
        response.setContentType("text/plain");
        result = x + " - " + y + " = " + action;
    } else if ("*".equals(operation)) {
        action = x * y;
        result = x + " * " + y + " = " + action;
    } else if ("/".equals(operation) && y != 0) {
        action = x / y;
        result = x + " / " + y + " = " + action;
    } else {
        throw new ServletException("Can't divide by zero");
    }

    request.setAttribute("result", result);
    
    request.getRequestDispatcher("/calculator").forward(request, response);
%>