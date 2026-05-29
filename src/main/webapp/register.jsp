<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta charset="UTF-8">

    <title>Kayıt Ol</title>

    <style>

        body {
            font-family: Arial;
            background-color: #f4f4f4;
        }

        .register-container {

            width: 400px;
            margin: 50px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        h2 {
            text-align: center;
        }

        input, textarea {

            width: 100%;
            padding: 10px;
            margin-top: 10px;
        }

        button {

            width: 100%;
            padding: 10px;
            margin-top: 15px;
            background: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        .error {

            color: red;
            text-align: center;
        }

    </style>

</head>

<body>

<div class="register-container">

    <h2>Kayıt Ol</h2>

    <%
        String error =
                (String) request.getAttribute("error");
    %>

    <% if (error != null) { %>

    <p class="error">
        <%= error %>
    </p>

    <% } %>

    <form action="register"
          method="post">

        <input type="text"
               name="fullName"
               placeholder="Ad Soyad"
               required>

        <input type="email"
               name="email"
               placeholder="E-posta"
               required>

        <input type="password"
               name="password"
               placeholder="Şifre"
               required>

        <input type="text"
               name="phone"
               placeholder="Telefon">

        <textarea name="address"
                  placeholder="Adres"></textarea>

        <button type="submit">
            Kayıt Ol
        </button>

    </form>

</div>

</body>
</html>