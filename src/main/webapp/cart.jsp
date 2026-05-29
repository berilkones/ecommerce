<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta charset="UTF-8">

    <title>Sepet</title>

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-top: 30px;
        }

        .cart-container {
            width: 85%;
            margin: 30px auto;
            background: white;
            padding: 25px;
            border-radius: 14px;
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
        }

        td {
            padding: 14px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        input[type="number"] {
            width: 70px;
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

        .remove-btn {
            padding: 8px 12px;
            background-color: #d32f2f;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
        }

        .remove-btn:hover {
            background-color: #b71c1c;
        }

        .total-box {
            text-align: right;
            font-size: 22px;
            font-weight: bold;
            margin-top: 20px;
            color: #333;
        }

        .checkout-btn {
            margin-top: 20px;
            padding: 12px 20px;
            background-color: #ff9800;
        }
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
        table {
            width: 80%;
            border-collapse: collapse;
            margin: auto;
            margin-top: 40px;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: center;
        }
    </style>
</head>

<body>

<a href="${pageContext.request.contextPath}/logout"
   class="logout-btn">

    Çıkış Yap

</a>

<h1>Ürün Listesi</h1>
<h1 align="center">Sepetim</h1>

<c:if test="${not empty sessionScope.cartError}">
    <p style="color:red; text-align:center;">
            ${sessionScope.cartError}
    </p>

    <c:remove var="cartError" scope="session"/>
</c:if>

<div class="cart-container">

    <table>
        <tr>
            <th>Ürün</th>
            <th>Fiyat</th>
            <th>Adet</th>
            <th>Ara Toplam</th>
            <th>İşlem</th>
        </tr>

        <c:forEach var="item" items = "${cart}">
            <tr>
                <td>
                        ${item.product.name}
                </td>

                <td>
                        ${item.product.price}
                </td>

                <td>
                    <form action="update-cart" method="post">
                        <input type="hidden" name="id" value="${item.product.id}">

                        <input type="number"
                               name="quantity"
                               value="${item.quantity}"
                               min="1"
                               max="${item.product.stock}">

                        <button type="submit"
                                class="checkout-btn">
                            Güncelle
                        </button>
                    </form>
                </td>

                <td>
                        ${item.product.price * item.quantity}
                </td>

                <td>

                    <a href="remove-from-cart?id=${item.product.id}"
                       class="remove-btn">

                        Sepetten Çıkar

                    </a>

                </td>
            </tr>
        </c:forEach>
    </table>

    <div class="total-box">
        Genel Toplam: ${total} TL
    </div>

    <form action="checkout" method="post" style="text-align:right;">
        <button type="submit" class="checkout-btn">
            Siparişi Tamamla
        </button>
    </form>

</div>
</body>
</html>
