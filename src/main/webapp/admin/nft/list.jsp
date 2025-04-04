<%--
  Created by IntelliJ IDEA.
  User: Quang Minh
  Date: 4/4/2025
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>NFT List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>All NFTs</h2>

    <c:if test="${not empty msg}">
        <div class="alert alert-success">
                ${msg}
        </div>
    </c:if>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Image</th>
            <th>Name</th>
            <th>Price</th>
            <th>Artist</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="nft" items="${nfts}">
            <tr>
                <td><img src="${nft.imageUrl}" alt="${nft.name}" width="100"></td>
                <td>${nft.name}</td>
                <td>${nft.price}</td>
                <td>${nft.artistName}</td>
                <td>${nft.status}</td>
                <td>
                    <a href="NFTServlet?action=edit&nftId=${nft.nftId}" class="btn btn-warning">Edit</a>
                    <button class="btn btn-danger" onclick="confirmDelete(${nft.nftId})">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="pagination">
        <!-- Pagination logic goes here -->
        <c:if test="${not empty nfts}">
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${pageCount}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${i}&limit=${limit}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </div>

    <a href="NFTServlet?action=add" class="btn btn-primary">Add New NFT</a>
</div>

<script>
    function confirmDelete(nftId) {
        if (confirm("Are you sure you want to delete this NFT?")) {
            window.location.href = 'NFTServlet?action=delete&nftId=' + nftId;
        }
    }
</script>
</body>
</html>


