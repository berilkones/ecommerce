<%@ page contentType ="text/html;charset=UTF-8" language ="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">


    <title>Ürünler</title>
    <style>
        .search-input {

            padding: 10px;
            width: 250px;

            border-radius: 8px;
            border: 1px solid #ccc;

            font-size: 15px;
        }

        .search-btn {

            padding: 10px 16px;

            border: none;
            border-radius: 8px;

            background-color: #4CAF50;
            color: white;

            font-weight: bold;
        }
        .top-menu {
            display: flex;
            justify-content: flex-end;
            align-items: center;
            gap: 12px;
            padding: 18px 40px;
        }

        .welcome-text {
            font-weight: bold;
            font-size: 16px;
            color: #222;
        }

        .menu-btn,
        .logout-btn {
            text-decoration: none;
            padding: 10px 14px;
            border-radius: 8px;
            font-weight: bold;
            color: white;
        }

        .menu-btn {
            background-color: #2196F3;
        }

        .logout-btn {
            background-color: #d32f2f;
        }

        .menu-btn:hover {
            background-color: #1976D2;
        }

        .logout-btn:hover {
            background-color: #b71c1c;
        }



        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
        }

        h1 {
            text-align: center !important;
            font-size: 40px !important;
            font-family: 'Segoe UI', sans-serif !important;
            color: #333 !important;
        }



        .product-grid {
            width: 90%;
            margin: 30px auto;
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
        }

        .product-card {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 15px;
            text-align: center;
            background-color: white;
            transition: 0.2s;
        }
        .product-card:hover {

            transform: translateY(-5px);

            box-shadow: 0 8px 20px rgba(0,0,0,0.15);
        }

        .product-image {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-radius: 8px;
        }

        .price {
            font-weight: bold;
            color: darkgreen;
        }

        .detail-btn,
        .cart-btn {
            display: inline-block;
            margin: 5px;
            padding: 8px 12px;
            text-decoration: none;
            color: white;
            border-radius: 5px;
        }

        .detail-btn {
            background-color: #2196F3;
        }

        .cart-btn {
            background-color: #4CAF50;
        }
    </style>
</head>
<body>
<div class="top-menu">

    <div class="welcome-text">
        Hoşgeldin, ${sessionScope.loggedInUser.fullName}
    </div>

    <a href="${pageContext.request.contextPath}/orders" class="menu-btn">
        Siparişlerim
    </a>

    <a href="${pageContext.request.contextPath}/logout" class="logout-btn">
        Çıkış Yap
    </a>

</div>

<h1>Ürün Listesi</h1>

<form action="products" method="get" style="text-align:center; margin-bottom:20px;">

    <input type="text"
           name="keyword"
           placeholder="Ürün ara..."
           class="search-input">

    <button type="submit" class="search-btn">
        Ara
    </button>

</form>

<c:if test="${not empty sessionScope.cartError}">

    <div style="text-align:center; margin-bottom:20px;">

        <a href="products">Tüm Ürünler</a>
        |
        <a href="products?categoryId=1">Telefon</a>
        |
        <a href="products?categoryId=2">Bilgisayar</a>
        |
        <a href="products?categoryId=3">Aksesuar</a>
        |
        <a href="products?categoryId=4">Kitap</a>
        |
        <a href="products?categoryId=5">Giyim</a>

    </div>

    <p style="
        color:red;
        text-align:center;
        font-weight:bold;">

            ${sessionScope.cartError}

    </p>

    <c:remove var="cartError"
              scope="session"/>

</c:if>
<c:if test="${not empty sessionScope.loggedInUser}">

    <a href="cart" class="menu-btn">
        Sepete Git
    </a>
</c:if>
<div class="product-grid">

    <c:forEach var="product" items="${productList}">

        <div class="product-card">

            <img src="${pageContext.request.contextPath}/images/${product.imageUrl}"
                 alt="${product.name}"
                 class="product-image">

            <h3>${product.name}</h3>

            <p>${product.description}</p>

            <p class="price">${product.price} TL</p>

            <p>Stok: ${product.stock}</p>

            <a href="product-detail?id=${product.id}" class="detail-btn">
                Detay
            </a>
            <c:choose>

                <c:when test="${not empty sessionScope.loggedInUser}">

                    <a href="add-to-cart?id=${product.id}"
                       class="cart-btn">

                        Sepete Ekle

                    </a>

                </c:when>

                <c:otherwise>

                    <a href="login"
                       class="cart-btn">

                        Sepete Ekle

                    </a>

                </c:otherwise>

            </c:choose>



        </div>

    </c:forEach>

</div>
</body>
</html>