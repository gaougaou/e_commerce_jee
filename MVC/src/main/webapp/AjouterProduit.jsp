<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Add Product</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WySr4q7BR7FjqiP0qD2SawfLV/LZoiwe1iS"
          crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Add a New Product</h2>

    <form action="AjouterProduit" method="post">
        <div class="form-group">
            <label for="productName">Product Name:</label>
            <input type="text" class="form-control" id="productName" name="productName" required>
        </div>

        <div class="form-group">
            <label for="productDescription">Product Description:</label>
            <textarea class="form-control" id="productDescription" name="productDescription" required></textarea>
        </div>

        <div class="form-group">
            <label for="productPrice">Product Price:</label>
            <input type="number" class="form-control" id="productPrice" name="productPrice" step="0.01" required>
        </div>

        <button type="submit" class="btn btn-primary">Add Product</button>
    </form>
</div>

<!-- Bootstrap JS and Popper.js -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WySr4q7BR7FjqiP0qD2SawfLV/LZoiwe1iS"
        crossorigin="anonymous"></script>

</body>
</html>
