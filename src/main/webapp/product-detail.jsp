<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Ürün Detayı</title>
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
        .container {
            width: 60%;
            margin: auto;
            margin-top: 40px;
            border: 1px solid gray;
            padding: 20px;

        }
        h1 {
            color: darkblue;
        }
        p {
            font-size: 18px;
        }
    </style>
</head>
<body>

<a href="${pageContext.request.contextPath}/logout"
   class="logout-btn">

    Çıkış Yap

</a>

<h1>Ürün Listesi</h1>
<div class="container">
    <h1>${product.name}</h1>
    <p>
        <strong>Açıklama:</strong>
        ${product.description}
    </p>

    <p>
        <strong>Fiyat:</strong>
        ${product.price}
    </p>

    <p>
        <strong>Stok:</strong>
        ${product.stock}
    </p>
</div>
</body>
</html>