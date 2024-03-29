<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>E-Commerce Cart</title>
    <style>
        /* Styles are unchanged for brevity */
            <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .container {
            margin-top: 30px;
            flex-grow: 1;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        .d-flex {
            justify-content: space-between;
            align-items: center;
        }

        .btn-primary {
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 5px;
        }

        .message {
            background-color: #28a745;
            color: #fff;
            padding: 10px;
            margin-bottom: 20px;
            text-align: center;
        }

        .btn-danger {
            background-color: #dc3545;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 5px;
        }

        .center-container {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .finish-btn-container {
            margin-top: 20px;
        }
    </style>
    </style>
</head>
<body>

    <div class="container my-3">
        <div class="message">
            <p>Voici vos produits </p>
        </div>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price</th>
                    <th scope="col">Action</th>
                    <th scope="col">Modifier</th> <!-- Added a new column for Modifier button -->
                </tr>
            </thead>
            <tbody>
            <% 
                String url = "jdbc:mysql://localhost:3306/e-commerce";
                String user = "root";
                String pwd = "";
                int number = 0;
                try {
                    Connection con = DriverManager.getConnection(url, user, pwd);
                    PreparedStatement pst = con.prepareStatement("SELECT * FROM produits");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
            %>
                        <tr>
                            <td><%= rs.getString(2) %></td>
                            <td><%= rs.getString(3) %></td>
                            <td><%= rs.getString(4) %></td>
                            <td><a href="DeleteProductServlet?id=<%= rs.getString(1) %>" class="btn">Supprimer</a></td>

                            <td><a href="ModifierProduit.jsp?id=<%= rs.getString(1) %>" class="btn">Modifier</a></td> <!-- Added Modifier button -->
                        </tr>
            <%
                    }
                    // Correction de la requ�te SQL
                    pst = con.prepareStatement("SELECT COUNT(*) FROM produits");
                    rs = pst.executeQuery();
                    rs.next();
                    number = rs.getInt(1);
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            %>  
                
            </tbody>
        </table>
    </div>
    <div class="finish-btn-container center-container">
        <a href="AjouterProduit.jsp" class="btn btn-primary">Ajouter un produit</a> <!-- Replaced "Finir la commande" with "Ajouter un produit" -->
    </div>

</body>
</html>
