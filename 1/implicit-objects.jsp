<%--
 Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
    <title>Калькулятор</title>
</head>
<body>
    <div th:fragment="header">
        <div class="d-flex flex-column flex-md-row align-items-center pb-3 mb-4 border-bottom">
        <a href="http://127.0.0.1:8081" class="d-flex align-items-center link-body-emphasis text-decoration-none">
            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="32" class="me-2" viewBox="0 0 118 94" role="img"><title>Bootstrap</title><path fill-rule="evenodd" clip-rule="evenodd" d="M24.509 0c-6.733 0-11.715 5.893-11.492 12.284.214 6.14-.064 14.092-2.066 20.577C8.943 39.365 5.547 43.485 0 44.014v5.972c5.547.529 8.943 4.649 10.951 11.153 2.002 6.485 2.28 14.437 2.066 20.577C12.794 88.106 17.776 94 24.51 94H93.5c6.733 0 11.714-5.893 11.491-12.284-.214-6.14.064-14.092 2.066-20.577 2.009-6.504 5.396-10.624 10.943-11.153v-5.972c-5.547-.529-8.934-4.649-10.943-11.153-2.002-6.484-2.28-14.437-2.066-20.577C105.214 5.894 100.233 0 93.5 0H24.508zM80 57.863C80 66.663 73.436 72 62.543 72H44a2 2 0 01-2-2V24a2 2 0 012-2h18.437c9.083 0 15.044 4.92 15.044 12.474 0 5.302-4.01 10.049-9.119 10.88v.277C75.317 46.394 80 51.21 80 57.863zM60.521 28.34H49.948v14.934h8.905c6.884 0 10.68-2.772 10.68-7.727 0-4.643-3.264-7.207-9.012-7.207zM49.948 49.2v16.458H60.91c7.167 0 10.964-2.876 10.964-8.281 0-5.406-3.903-8.178-11.425-8.178H49.948z" fill="currentColor"></path></svg>
            <span class="fs-4" href>DataBase Workers</span>
            </a>
            <nav class="d-inline-flex mt-2 mt-md-0 ms-md-auto">
            <a class="me-3 py-2 link-body-emphasis text-decoration-none" href="http://127.0.0.1:8081">Главная</a>
            <a class="me-3 py-2 link-body-emphasis text-decoration-none" href="http://127.0.0.1:8081/workers">Работники</a>
            <a class="me-3 py-2 link-body-emphasis text-decoration-none" href="http://127.0.0.1:8081/workers/add">Добавить Работника</a>
            <a class="me-3 py-2 link-body-emphasis text-decoration-none" href="http://127.0.0.1:8080/examples/jsp/jsp2/el/implicit-objects.jsp?foo=bar">Калькулятор</a>
            </nav>
        </div>
    </div>
    <h1>Калькулятор</h1>

    
    <%
        double result = 0.0;
        String errorMsg = "";
        
        if (request.getMethod().equals("POST")) {
            try {
                double num1 = Double.parseDouble(request.getParameter("num1"));
                double num2 = Double.parseDouble(request.getParameter("num2"));
                String operator = request.getParameter("operator");
                
                if (operator.equals("+")) {
                    result = num1 + num2;
                } else if (operator.equals("-")) {
                    result = num1 - num2;
                } else if (operator.equals("*")) {
                    result = num1 * num2;
                } else if (operator.equals("/")) {
                    if (num2 == 0) {
                        errorMsg = "Деление на ноль";
                    } else {
                        result = num1 / num2;
                    }
                }
            } catch (NumberFormatException e) {
                errorMsg = "Некорректные входные данные";
            }
        }
    %>
    
    <form method="post">
        <input type="text" name="num1" placeholder="Число 1" value="${param.num1}" />
        <select name="operator">
            <option value="+" ${param.operator == "+" ? "selected" : ""}>+</option>
            <option value="-" ${param.operator == "-" ? "selected" : ""}>-</option>
            <option value="*" ${param.operator == "*" ? "selected" : ""}>*</option>
            <option value="/" ${param.operator == "/" ? "selected" : ""}>/</option>
        </select>
        <input type="text" name="num2" placeholder="Число 2" value="${param.num2}" />
        <input type="submit" value="Посчитать" />
    </form>
    
    <% if (!errorMsg.equals("")) { %>
        <p><font color="red"><%= errorMsg %></font></p>
    <% } else if (request.getMethod().equals("POST")) { %>
        <p>Результат: <%= result %></p>
    <% } %>
    
    <a href="basic-arithmetic.jsp?errorMsg=<%= errorMsg %>">Перейти к ошибке</a>
</body>
</html>