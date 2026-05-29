<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Kullanıcı Yönetimi</title>

    <style>
        body {
            font-family: Arial;
        }

        h1 {
            text-align: center;
        }

        table {
            width: 90%;
            margin: 30px auto;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: center;
        }
    </style>
</head>

<body>

<h1>Admin Kullanıcı Yönetimi</h1>

<table>
    <tr>
        <th>ID</th>
        <th>Ad Soyad</th>
        <th>E-posta</th>
        <th>Telefon</th>
        <th>Rol</th>
    </tr>

    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.fullName}</td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
            <td>${user.role}</td>
        </tr>
    </c:forEach>
</table>

<div style="text-align:center;">
    <a href="${pageContext.request.contextPath}/admin/products">
        Ürün Yönetimine Dön
    </a>
</div>

</body>
</html>