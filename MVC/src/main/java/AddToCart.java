import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the product ID and quantity from the submitted form
        String productId = request.getParameter("productId");
        // You may have a quantity field in your form, retrieve it similarly

        // Retrieve the cart from the session
        HttpSession mySession = request.getSession();
        List<String> cart = (List<String>) mySession.getAttribute("cart");

        if (cart == null) {
            // If the cart doesn't exist in the session, create a new one
            cart = new ArrayList<>();
        }

        // Add the product ID to the cart
        cart.add(productId);

        // Update the cart in the session
        mySession.setAttribute("cart", cart);

        // Retrieve other product details from the database based on the product ID
        // For simplicity, this example assumes a table named "produits" with columns "nom" and "prix"
        // Replace it with your actual column names and table structure
        String productName = "";
        double productPrice = 0.0;

        String url = "jdbc:mysql://localhost:3306/e-commerce";
        String user = "root";
        String pwd = "";

        try {
            Connection con = DriverManager.getConnection(url, user, pwd);
            String query = "SELECT nom, prix FROM produits WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, productId);

            // Execute the query
            ResultSet rs = pst.executeQuery();

            // Process the results
            if (rs.next()) {
                productName = rs.getString("nom");
                productPrice = rs.getDouble("prix");
            }

            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Save the order details (product) in the database
        saveOrderDetails(productId, productName, productPrice);

        // Redirect to the cart page
        response.sendRedirect(request.getContextPath() + "/Cart.jsp");
    }

    private void saveOrderDetails(String productId, String productName, double productPrice) {
        String url = "jdbc:mysql://localhost:3306/e-commerce";
        String user = "root";
        String pwd = "";

        try {
            Connection con = DriverManager.getConnection(url, user, pwd);
            String query = "INSERT INTO cmd (produit_id, nom, prix) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, productId);
            pst.setString(2, productName);
            pst.setDouble(3, productPrice);

            // Execute the query to insert into the "commande" table
            pst.executeUpdate();

            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
