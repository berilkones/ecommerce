<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Sipariş Yönetimi</title>

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
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

        .logout-btn {
            background-color: #d32f2f;
        }

        h1 {
            text-align: center;
            font-size: 38px;
            color: #333;
        }

        table {
            width: 90%;
            margin: 35px auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 8px 20px rgba(0,0,0,0.08);
        }

        th {
            background-color: #2196F3;
            color: white;
            padding: 14px;
        }

        td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        select {
            padding: 8px;
            border-radius: 6px;
            border: 1px solid #ccc;
        }

        button {
            padding: 8px 12px;
            border: none;
            border-radius: 6px;
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
            cursor: pointer;
        }

        button:hover {
            background-color: #388E3C;
        }


        .status-btn {

            display: inline-block;
            padding: 8px 14px;
            border-radius: 6px;
            text-decoration: none;
            color: white;
            font-size: 14px;
            font-weight: bold;
            margin: 4px;
            transition: 0.2s;
        }

        .status-btn:hover {

            opacity: 0.85;
        }

        .preparing-btn {

            background-color: orange;
        }

        .cargo-btn {

            background-color: #2196F3;
        }

        .delivered-btn {

            background-color: #4CAF50;
        }


        h1 {
            text-align: center;
        }
    </style>
</head>

<body>
<div class="top-menu">

    <a href="${pageContext.request.contextPath}/admin/dashboard"
       class="menu-btn">
        Dashboard
    </a>

    <a href="${pageContext.request.contextPath}/logout"
       class="logout-btn">
        Çıkış Yap
    </a>

</div>


<table>
    <tr>
        <th>Sipariş No</th>
        <th>Kullanıcı ID</th>
        <th>Tarih</th>
        <th>Toplam Tutar</th>
        <th>Durum</th>
        <th>Detay</th>
    </tr>

    <c:forEach var="order" items="${orderList}">
        <tr>
            <td>${order.id}</td>
            <td>${order.userId}</td>
            <td>${order.orderDate}</td>
            <td>${order.totalAmount}</td>
            <td>
                <form action="${pageContext.request.contextPath}/admin/update-order-status"
                      method="post">

                    <input type="hidden"
                           name="id"
                           value="${order.id}">

                    <select name="status">
                        <option value="Beklemede">Beklemede</option>
                        <option value="Hazırlanıyor">Hazırlanıyor</option>
                        <option value="Kargoya Verildi">Kargoya Verildi</option>
                        <option value="Tamamlandı">Tamamlandı</option>
                        <option value="İptal Edildi">İptal Edildi</option>
                    </select>

                    <button type="submit">
                        Güncelle
                    </button>

                </form>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/order-detail?id=${order.id}">
                    Detay
                </a>
            </td>
        </tr>
    </c:forEach>
</table>



</body>
</html>