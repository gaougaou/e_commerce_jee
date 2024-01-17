<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Votre Panier</title>
    <!-- Add your CSS styles here -->
</head>
<body>

    <h1>Votre Panier</h1>

    <%
    HttpSession mySession = request.getSession();
    List<String> cart = (List<String>) mySession.getAttribute("cart");

        if (cart != null && !cart.isEmpty()) {
    %>
            <table border="1">
                <thead>
                    <tr>
                        <th>Produit</th>
                        <th>Prix</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        String url = "jdbc:mysql://localhost:3306/e-commerce";
                        String user = "root";
                        String pwd = "";

                        try {
                            Connection con = DriverManager.getConnection(url, user, pwd);

                            // Display the details of products in the cart
                            for (String productId : cart) {
                                String query = "SELECT * FROM cmd ";
                                PreparedStatement pst = con.prepareStatement(query);
                                pst.setString(1, productId);
                                ResultSet rs = pst.executeQuery();

                                if (rs.next()) {
                    %>
                                    <tr>
                                        <td><%= rs.getString("") %></td>
                                        <td><%= rs.getDouble("prix") %></td>
                                    </tr>
                    <%
                                }

                                rs.close();
                                pst.close();
                            }

                            con.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    %>
                </tbody>
            </table>

            <!-- Add a button to redirect to Paiement.jsp -->
            <form action="<%= request.getContextPath() %>/Paiment.jsp" method="get">
                <button type="submit">Paiement</button>
            </form>
    <%
        } else {
    %>
            <p>Votre panier est vide.</p>
    <%
        }
    %>

</body>
</html>
