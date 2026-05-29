<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>Yeni Ürün Ekle</title>

  <style>
    body {
      font-family: Arial;
      background-color: #f4f4f4;
    }

    .form-container {
      width: 450px;
      margin: 50px auto;
      background: white;
      padding: 25px;
      border-radius: 10px;
    }

    input, textarea, select {
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

    .error {
      color: red;
      text-align: center;
    }
  </style>
</head>

<body>

<div class="form-container">

  <h2>Yeni Ürün Ekle</h2>

  <%
    String error = (String) request.getAttribute("error");
  %>

  <% if (error != null) { %>
  <p class="error"><%= error %></p>
  <% } %>

  <form action="${pageContext.request.contextPath}/admin/add-product"
        method="post">

    <select name="categoryId" required>
      <option value="">Kategori Seçiniz</option>
      <option value="1">Telefon</option>
      <option value="2">Bilgisayar</option>
      <option value="3">Aksesuar</option>
      <option value="4">Kitap</option>
      <option value="5">Giyim</option>
    </select>

    <input type="text"
           name="name"
           placeholder="Ürün Adı"
           required>

    <textarea name="description"
              placeholder="Ürün Açıklaması"
              required></textarea>

    <input type="number"
           name="price"
           placeholder="Fiyat"
           min="1"
           step="0.01"
           required>

    <input type="number"
           name="stock"
           placeholder="Stok"
           min="0"
           required>

    <input type="text"
           name="imageUrl"
           placeholder="Görsel Yolu">

    <button type="submit">
      Ürün Ekle
    </button>

  </form>

</div>

</body>
</html>