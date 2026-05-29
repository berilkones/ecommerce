<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>Admin Kategori Yönetimi</title>

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
      width: 80%;
      margin: 30px auto;
      border-collapse: collapse;
    }

    th, td {
      border: 1px solid black;
      padding: 10px;
      text-align: center;
    }

    .top-bar {
      width: 80%;
      margin: auto;
      text-align: right;
    }

    .btn {
      padding: 8px 12px;
      text-decoration: none;
      background-color: green;
      color: white;
      border-radius: 5px;
    }
  </style>
</head>

<body>

<a href="${pageContext.request.contextPath}/logout"
   class="logout-btn">

  Çıkış Yap

</a>

<h1>Admin Kategori Yönetimi</h1>

<div class="top-bar">
  <a href="category-form.jsp" class="btn">
    Yeni Kategori Ekle
  </a>
</div>

<table>
  <tr>
    <th>ID</th>
    <th>Kategori Adı</th>
    <th>Açıklama</th>
    <th>Aktif mi?</th>
    <th>İşlem</th>
  </tr>

  <c:forEach var="category" items="${categoryList}">
    <tr>
      <td>${category.id}</td>
      <td>${category.name}</td>
      <td>${category.description}</td>
      <td>${category.active}</td>
      <td>
        <a href="${pageContext.request.contextPath}/admin/delete-category?id=${category.id}">
          Pasif Yap
        </a>
      </td>
    </tr>
  </c:forEach>
</table>

</body>
</html>