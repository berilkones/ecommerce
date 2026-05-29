<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>

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


        .dashboard-btn {
            display: inline-block;
            background-color: #008000;
            color: white;
            text-decoration: none;
            padding: 12px 18px;
            margin: 8px;
            border-radius: 8px;
            font-weight: bold;
        }

        .dashboard-btn:hover {
            background-color: #006400;
        }

        body {
            font-family: Arial;
            background-color: #f4f4f4;
        }

        h1 {
            text-align: center;
        }

        .dashboard {
            width: 80%;
            margin: 40px auto;
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }

        .number {
            font-size: 32px;
            font-weight: bold;
            color: darkblue;
        }

        .links {
            text-align: center;
            margin-top: 30px;
        }

        .links a {
            margin: 10px;
        }
    </style>
</head>

<body>

<a href="${pageContext.request.contextPath}/logout"
   class="logout-btn">

    Çıkış Yap

</a>

<h1>Ürün Listesi</h1>

<h1>Admin Dashboard</h1>

<div class="dashboard">

    <div class="card">
        <h3>Toplam Ürün</h3>
        <p class="number">${productCount}</p>
    </div>

    <div class="card">
        <h3>Toplam Kategori</h3>
        <p class="number">${categoryCount}</p>
    </div>

    <div class="card">
        <h3>Toplam Kullanıcı</h3>
        <p class="number">${userCount}</p>
    </div>

    <div class="card">
        <h3>Toplam Sipariş</h3>
        <p class="number">${orderCount}</p>
    </div>

    <div class="card">
        <h3>Bekleyen Sipariş</h3>
        <p class="number">${pendingOrderCount}</p>
    </div>

</div>

<div class="links">
    <a href="${pageContext.request.contextPath}/admin/products" class="dashboard-btn">
        Ürün Yönetimi
    </a>

    <a href="${pageContext.request.contextPath}/admin/categories" class="dashboard-btn">
        Kategori Yönetimi
    </a>

    <a href="${pageContext.request.contextPath}/admin/orders" class="dashboard-btn">
        Sipariş Yönetimi
    </a>

    <a href="${pageContext.request.contextPath}/admin/users" class="dashboard-btn">
        Kullanıcı Yönetimi
    </a>
</div>

</body>
</html>