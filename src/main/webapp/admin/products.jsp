<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta charset="UTF-8">

    <title>Admin Ürün Yönetimi</title>

    <style>
        .logout-btn {

            position: absolute;
            top: 20px;
            right: 20px;

            background-color: red;
            color: white;

            padding: 10px 15px;

            border-radius: 6px;

            text-decoration: none;

            font-weight: bold;
        }

        .logout-btn:hover {

            background-color: darkred;
        }

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

        .top-bar {

            width: 90%;
            margin: auto;
            text-align: right;
        }

        .btn {

            padding: 8px 12px;
            text-decoration: none;
            border-radius: 5px;
            color: white;
            margin-right: 10px;
        }

        .add-btn {
            background-color: green;
        }

        .edit-btn {
            background-color: orange;
        }

        .delete-btn {
            background-color: red;
        }

    </style>

</head>

<body>
<a href="${pageContext.request.contextPath}/logout"
   class="logout-btn">

    Çıkış Yap

</a>

<h1>Admin Ürün Yönetimi</h1>

<div class="top-bar">

    <a href="${pageContext.request.contextPath}/admin/add-product"
       class="btn add-btn">
        Yeni Ürün Ekle
    </a>

    <a href="${pageContext.request.contextPath}/admin/orders"
       class="btn add-btn">

        Sipariş Yönetimi

    </a>

    <a href="${pageContext.request.contextPath}/admin/users"
       class="btn add-btn">
        Kullanıcı Yönetimi
    </a>

    <a href="${pageContext.request.contextPath}/admin/categories"
       class="btn add-btn">

        Kategori Yönetimi

    </a>

</div>

<table>

    <tr>

        <th>ID</th>
        <th>Ürün Adı</th>
        <th>Fiyat</th>
        <th>Stok</th>
        <th>İşlemler</th>

    </tr>

    <c:forEach var="product"
               items="${productList}">

        <tr>

            <td>${product.id}</td>

            <td>${product.name}</td>

            <td>${product.price}</td>

            <td>${product.stock}</td>

            <td>

                <a href="${pageContext.request.contextPath}/admin/edit-product?id=${product.id}"
                   class="btn edit-btn">
                    Düzenle
                </a>

                <a href="${pageContext.request.contextPath}/admin/delete-product?id=${product.id}"
                   class="btn delete-btn">
                    Sil
                </a>

            </td>

        </tr>

    </c:forEach>

</table>

</body>
</html>