<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03/05/2024
  Time: 10:48 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>danh sach</title>
</head>
<body>
<h1>Danh sach user</h1>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>price</th>
            <th>code</th>
            <th>Name</th>
            <th>branch</th>
            <th>description</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.price}"/></td>
                <td><c:out value="${user.code}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.branch}"/></td>
                <td><c:out value="${user.description}"/></td>
                <td>
                    <a href="/users?action=edit&id=${user.code}">Edit</a>
                    <a href="/users?action=delete&id=${user.code}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
