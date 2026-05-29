<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Giriş</title>

    <style>
        body {
            font-family: Arial;
            background-color: #f4f4f4;
        }

        .login-container {
            width: 400px;
            margin: 80px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        h2 {
            text-align: center;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
        }

        button {
            width: 100%;
            padding: 10px;
            margin-top: 15px;
            background: darkblue;
            color: white;
            border: none;
        }

        .error {
            color: red;
            text-align: center;
        }
    </style>
</head>

<body>

<div class="login-container">

    <h2>Admin Giriş</h2>

    <%
        String error = (String) request.getAttribute("error");
    %>

    <% if (error != null) { %>
    <p class="error"><%= error %></p>
    <% } %>

    <form action="${pageContext.request.contextPath}/admin-login" method="post">

        <input type="email"
               name="email"
               placeholder="Admin E-posta"
               required>

        <input type="password"
               name="password"
               placeholder="Şifre"
               required>

        <button type="submit">
            Admin Giriş Yap
        </button>

    </form>

</div>

</body>
</html>