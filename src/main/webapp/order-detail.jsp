<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Sipariş Detayı</title>

    <style>
        body {

            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
        }

        h1 {

            text-align: center;
            font-size: 42px;
            color: #333;

            margin-top: 30px;
        }

        .detail-container {

            width: 85%;

            margin: 40px auto;

            background-color: white;

            padding: 30px;

            border-radius: 16px;

            box-shadow: 0 8px 20px rgba(0,0,0,0.08);
        }

        table {

            width: 100%;

            border-collapse: collapse;
        }

        th {

            background-color: #2196F3;
            color: white;

            padding: 14px;

            font-size: 17px;
        }

        td {

            padding: 14px;

            text-align: center;

            border-bottom: 1px solid #ddd;
        }

        .back-btn {

            display: inline-block;

            margin-top: 25px;

            padding: 12px 18px;

            background-color: #4CAF50;

            color: white;

            text-decoration: none;

            border-radius: 8px;

            font-weight: bold;
        }

        .back-btn:hover {

            background-color: #388E3C;
        }

    </style>
</head>

<body>

<h1>Sipariş Detayı - #${orderId}</h1>

<div class="detail-container">
    <table>
        <tr>
            <th>Ürün</th>
            <th>Açıklama</th>
            <th>Birim Fiyat</th>
            <th>Adet</th>
            <th>Ara Toplam</th>
        </tr>

        <c:forEach var="item" items="${itemList}">
            <tr>
                <td>${item.product.name}</td>
                <td>${item.product.description}</td>
                <td>${item.unitPrice}</td>
                <td>${item.quantity}</td>
                <td>${item.subtotal}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<div style="text-align:center;">

    <a href="${pageContext.request.contextPath}/orders"
       class="back-btn">

        Siparişlerime Dön

    </a>

</div>

</body>
</html>