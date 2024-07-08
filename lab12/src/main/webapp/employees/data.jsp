<%@ page contentType="application/json; charset=UTF-8" %>
<%@ page import="java.sql.*, org.json.*" %>

<%
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String url = "jdbc:sqlite:D:\\OneDrive\\Documents\\proga\\backend\\lab12\\src\\main\\resources\\sample.db";
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(url);
        statement = connection.prepareStatement("SELECT * FROM employees WHERE firstname LIKE ?;");
		String name = request.getParameter("name");
		statement.setString(1, "%" + name + "%");
        resultSet = statement.executeQuery();

        JSONArray jsonArray = new JSONArray();

        while (resultSet.next()) {
            int total_columns = resultSet.getMetaData().getColumnCount();
            JSONObject jsonObject = new JSONObject();

            for (int i = 0; i < total_columns; i++) {
                jsonObject.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
            }
            jsonArray.put(jsonObject);
        }

        out.print(jsonArray.toString());
    } catch (Exception e) {
        // Обработка исключения
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().print("{\"error\":\"Произошла ошибка при получении данных\"}");
    } finally {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>
