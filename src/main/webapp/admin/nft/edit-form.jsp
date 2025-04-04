<%--
  Created by IntelliJ IDEA.
  User: Quang Minh
  Date: 4/4/2025
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit NFT</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Edit NFT</h2>

    <c:if test="${not empty errors}">
        <div class="alert alert-danger">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <form action="NFTServlet?action=update" method="POST">
        <input type="hidden" name="nftId" value="${nft.nftId}">

        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="${nft.name}" required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3" required>${nft.description}</textarea>
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" class="form-control" id="price" name="price" value="${nft.price}" required step="0.01">
        </div>

        <div class="mb-3">
            <label for="imageUrl" class="form-label">Image URL</label>
            <input type="text" class="form-control" id="imageUrl" name="imageUrl" value="${nft.imageUrl}" required>
        </div>

        <div class="mb-3">
            <label for="artistName" class="form-label">Artist Name</label>
            <input type="text" class="form-control" id="artistName" name="artistName" value="${nft.artistName}" required>
        </div>

        <div class="mb-3">
            <label for="categoryId" class="form-label">Category</label>
            <select class="form-select" id="categoryId" name="categoryId" required>
                <option value="1" ${nft.categoryId == 1 ? 'selected' : ''}>Digital Art</option>
                <option value="2" ${nft.categoryId == 2 ? 'selected' : ''}>Sound</option>
                <option value="3" ${nft.categoryId == 3 ? 'selected' : ''}>Video</option>
                <option value="4" ${nft.categoryId == 4 ? 'selected' : ''}>GIF</option>
                <option value="5" ${nft.categoryId == 5 ? 'selected' : ''}>3D Model</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="ownerWalletAddress" class="form-label">Owner Wallet Address</label>
            <input type="text" class="form-control" id="ownerWalletAddress" name="ownerWalletAddress" value="${nft.ownerWalletAddress}" required>
        </div>

        <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <select class="form-select" id="status" name="status" required>
                <option value="available" ${'available'.equals(nft.status) ? 'selected' : ''}>Available</option>
                <option value="sold" ${'sold'.equals(nft.status) ? 'selected' : ''}>Sold</option>
                <option value="not_for_sale" ${'not_for_sale'.equals(nft.status) ? 'selected' : ''}>Not for Sale</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Update NFT</button>
        <a href="NFTServlet?action=list" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>

