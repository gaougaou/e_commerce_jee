<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil - E-Commerce</title>
    <style>
body {
    font-family: 'Helvetica Neue', Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}

header {
    background-color: #333;
    color: #fff;
    padding: 20px;
    text-align: center;
}

h1 {
    font-size: 28px;
}

nav {
    background-color: #007bff;
    padding: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

nav a {
    color: #fff;
    text-decoration: none;
    padding: 10px;
}

.container {
    margin: 20px;
    flex-grow: 1;
}

.product-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 20px;
}

.product-card {
    background-color: #fff;
    border: 1px solid #ddd;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s ease-in-out;
}

.product-card:hover {
    transform: scale(1.03);
}

.product-image {
    max-width: 100%;
    height: auto;
    border-radius: 10px;
}

.product-details {
    margin-top: 15px;
}

.product-name {
    font-size: 18px;
    font-weight: bold;
    color: #333;
}

.product-description {
    font-size: 14px;
    color: #666;
}

.product-price {
    font-size: 16px;
    color: #007bff;
    margin-top: 10px;
}

.search-container {
    margin-bottom: 20px;
    text-align: center;
}

.search-form {
    display: flex;
    justify-content: center;
    align-items: center;
}

.search-input {
    padding: 10px;
    margin-right: 10px;
    width: 200px;
}

.search-button {
    padding: 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}



    </style>
</head>
<body>

    <header>
        <h1>E-Commerce</h1>
    </header>

    <nav>
        <a href="#">Accueil</a>
        <a href="#">Produits</a>
        <!-- Add more navigation links as needed -->
    </nav>

    <div class="container">
        <div class="search-container">
            <form action="" method="get" class="search-form">
                <label for="productName">Recherche par nom:</label>
                <input type="text" id="productName" name="productName" class="search-input">
                <button type="submit" class="btn btn-primary search-button">Rechercher</button>
            </form>
        </div>

        <h2>Nos Produits</h2>

        <div class="product-grid">
           <%
                    String productName = request.getParameter("productName");

                    String url = "jdbc:mysql://localhost:3306/e-commerce";
                    String user = "root";
                    String pwd = "";
                    int number = 0;
                    try {
                        Connection con = DriverManager.getConnection(url, user, pwd);

                        // Construct the SQL query
                        String query = "SELECT * FROM produits";
                        
                        // If there is a search parameter, modify the query
                        if (productName != null && !productName.isEmpty()) {
                            query += " WHERE nom LIKE ?";
                        }

                        PreparedStatement pst = con.prepareStatement(query);

                        // Set the search parameter if it exists
                        if (productName != null && !productName.isEmpty()) {
                            pst.setString(1, "%" + productName + "%");
                        }

                        ResultSet rs = pst.executeQuery();
                        while (rs.next()) {
                %>
                        <div class="product-card">
                            <img class="product-image" src="<%= rs.getString(1) %>" alt="<%= rs.getString(2) %>">
                            <h3><%= rs.getString(2) %></h3>
                            <p><%= rs.getString(3) %></p>
                            <p>Prix: <%= rs.getString(4) %></p>
                            <a href="<%= request.getContextPath() %>/AddToCart?productId=<%= rs.getString(1) %>">Ajouter au panier</a>
                        </div>
            <%
                    }
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            %>
        </div>
    </div>

</body>
</html>
