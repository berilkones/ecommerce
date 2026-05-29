<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>E-Ticaret Portalı</title>

    <style>
        .admin-btn {
            background-color: #333;
        }
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f5f5f5;
        }

        .hero {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
            background: linear-gradient(135deg, #e3f2fd, #ffffff);
        }

        .hero-box {
            background: white;
            padding: 50px;
            border-radius: 20px;
            box-shadow: 0 8px 25px rgba(0,0,0,0.1);
            width: 500px;
        }

        h1 {
            font-size: 42px;
            color: #333;
            margin-bottom: 15px;
        }

        p {
            color: #666;
            font-size: 18px;
            margin-bottom: 30px;
        }

        .btn {
            display: inline-block;
            padding: 12px 22px;
            margin: 8px;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            color: white;
        }

        .products-btn {
            background-color: #2196F3;
        }

        .login-btn {
            background-color: #4CAF50;
        }

        .register-btn {
            background-color: #ff9800;
        }
    </style>
</head>

<body>

<div class="hero">

    <div class="hero-box">

        <h1>Temel E-Ticaret Portalı</h1>

        <p>
            Ürünleri inceleyebilir, sepete ekleyebilir ve sipariş oluşturabilirsiniz.
        </p>

        <a href="products" class="btn products-btn">
            Ürünleri Gör
        </a>

        <a href="login" class="btn login-btn">
            Giriş Yap
        </a>

        <a href="register" class="btn register-btn">
            Kayıt Ol
        </a>
        <a href="${pageContext.request.contextPath}/admin-login"
           class="btn admin-btn">
            Admin Girişi
        </a>
    </div>

</div>

</body>
</html>