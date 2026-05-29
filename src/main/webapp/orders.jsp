<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Siparişlerim</title>

    <style>

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
        }

        h1 {
            text-align: center;
            font-size: 38px;
            color: #333;
            margin-top: 30px;
        }

        table {
            background-color: white;
            border-radius: 10px;
            overflow: hidden;
        }

        .continue-btn {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 12px 18px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: bold;
        }

        .continue-btn:hover {
            background-color: #388E3C;
        }
        .logout-btn {

            background-color: #d32f2f;
        }
        .logout-btn:hover {

            background-color: darkred;
        }
        .top-menu {

            display: flex;
            justify-content: flex-end;
            gap: 10px;

            padding: 20px;
        }

        .menu-btn,
        .logout-btn {

            padding: 10px 14px;

            border-radius: 8px;

            color: white;

            text-decoration: none;

            font-weight: bold;
        }

        .menu-btn {

            background-color: #2196F3;
        }



        table {
            width: 80%;
            border-collapse: collapse;
            margin: 40px auto;
        }

        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: center;
        }

        h1 {
            text-align: center;
        }
    </style>
</head>

<body>
<div class="top-menu">

    <a href="${pageContext.request.contextPath}/logout"
       class="logout-btn">

        Çıkış Yap

    </a>

</div>

<h1>Siparişlerim</h1>

<table>
    <tr>
        <th>Sipariş No</th>
        <th>Tarih</th>
        <th>Toplam Tutar</th>
        <th>Durum</th>
        <th>Detay</th>
    </tr>

    <c:forEach var="order" items="${orderList}">
        <tr>
            <td>${order.id}</td>
            <td>${order.orderDate}</td>
            <td>${order.totalAmount}</td>
            <td>${order.status}</td>
            <td>
                <a href="order-detail?id=${order.id}">
                    Detay
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

<div style="text-align:center; margin-top:25px;">
    <a href="${pageContext.request.contextPath}/products"
       class="continue-btn">
        Alışverişe Devam Et
    </a>
</div>
</body>
</html>