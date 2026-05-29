<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<html>

<head>
    <meta charset="UTF-8">

    <title>Kategori Ekle</title>

    <style>

        body {
            font-family: Arial;
            background-color: #f4f4f4;
        }

        .form-container {

            width: 400px;
            margin: 50px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        input, textarea {

            width: 100%;
            padding: 10px;
            margin-top: 10px;
        }

        button {

            width: 100%;
            padding: 10px;
            margin-top: 15px;
            background-color: green;
            color: white;
            border: none;
        }

    </style>

</head>

<body>

<div class="form-container">

    <h2>Yeni Kategori Ekle</h2>

    <form action="${pageContext.request.contextPath}/admin/add-category"
          method="post">

        <input type="text"
               name="name"
               placeholder="Kategori Adı"
               required>

        <textarea name="description"
                  placeholder="Kategori Açıklaması"
                  required></textarea>

        <button type="submit">

            Kategori Ekle

        </button>

    </form>

</div>

</body>

</html>