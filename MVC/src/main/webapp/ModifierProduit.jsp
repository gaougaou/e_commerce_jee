<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Modify Product</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WySr4q7BR7FjqiP0qD2SawfLV/LZoiwe1iS"
          crossorigin="anonymous">
</head>
<body>

<%
String url = "jdbc:mysql://localhost:3306/e-commerce";
String user = "root";
String pwd = "";
String productId = request.getParameter("id");
String productName = ""; // Initialize variables for product details
String productDescription = "";
double productPrice = 0.0;

try {
    Connection con = DriverManager.getConnection(url, user, pwd);
    PreparedStatement pst = con.prepareStatement("SELECT * FROM produits WHERE id_produits = ?");
    pst.setString(1, productId);
    ResultSet rs = pst.executeQuery();

    if (rs.next()) {
        // Retrieve values from the database
        productName = rs.getString("nom");
        productDescription = rs.getString("description");
        productPrice = rs.getDouble("prix");
    }

    rs.close();
    pst.close();
    con.close();
} catch (SQLException e) {
    e.printStackTrace();
}
%>  

<div class="container mt-5">
    <h2 class="mb-4">Modify Product</h2>

    <form action="ModifierProduit" method="post">
        <input type="hidden" name="productId" value="<%= productId %>">

        <div class="form-group">
            <label for="productName">Product Name:</label>
            <input type="text" class="form-control" id="productName" name="productName" required value="<%= productName %>">
        </div>

        <div class="form-group">
            <label for="productDescription">Product Description:</label>
            <textarea class="form-control" id="productDescription" name="productDescription" required><%= productDescription %></textarea>
        </div>

        <div class="form-group">
            <label for="productPrice">Product Price:</label>
            <input type="number" class="form-control" id="productPrice" name="productPrice" step="0.01" required value="<%= productPrice %>">
        </div>

        <button type="submit" class="btn btn-primary">Update Product</button>
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
