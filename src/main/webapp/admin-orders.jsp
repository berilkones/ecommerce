<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta charset="UTF-8">

    <title>Admin Sipariş Yönetimi</title>

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

        .btn {

            padding: 6px 10px;
            text-decoration: none;
            color: white;
            border-radius: 5px;
        }

        .update-btn {
            background-color: orange;
        }

    </style>

</head>

<body>

<h1>Admin Sipariş Yönetimi</h1>

<table>

    <tr>

        <th>Sipariş ID</th>
        <th>Kullanıcı ID</th>
        <th>Tarih</th>
        <th>Toplam</th>
        <th>Durum</th>
        <th>İşlem</th>

    </tr>

    <c:forEach var="order"
               items="${orderList}">

        <tr>

            <td>${order.id}</td>

            <td>${order.userId}</td>

            <td>${order.orderDate}</td>

            <td>${order.totalAmount}</td>

            <td>

                <c:choose>

                    <c:when test="${order.status == 'Beklemede'}">

            <span style="color:gray; font-weight:bold;">
                    ${order.status}
            </span>

                    </c:when>

                    <c:when test="${order.status == 'Hazırlanıyor'}">

            <span style="color:orange; font-weight:bold;">
                    ${order.status}
            </span>

                    </c:when>

                    <c:when test="${order.status == 'Kargoya Verildi'}">

            <span style="color:blue; font-weight:bold;">
                    ${order.status}
            </span>

                    </c:when>

                    <c:when test="${order.status == 'Tamamlandı'}">

            <span style="color:green; font-weight:bold;">
                    ${order.status}
            </span>

                    </c:when>

                    <c:otherwise>

            <span style="color:red; font-weight:bold;">
                    ${order.status}
            </span>

                    </c:otherwise>

                </c:choose>

            </td>

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

        </tr>

    </c:forEach>

</table>

</body>

</html>