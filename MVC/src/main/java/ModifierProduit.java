import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ModifierProduit")
public class ModifierProduit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve modified product details from the request parameters
            String productId = request.getParameter("productId");
            String productName = request.getParameter("productName");
            String productDescription = request.getParameter("productDescription");
            double productPrice = Double.parseDouble(request.getParameter("productPrice"));

            // Database connection details
            String url = "jdbc:mysql://localhost:3306/e-commerce";
            String user = "root";
            String pwd = "";

            // Perform the update
            try (Connection con = DriverManager.getConnection(url, user, pwd);
                 PreparedStatement pst = con.prepareStatement("UPDATE produits SET nom=?, description=?, prix=? WHERE id_produits=?")) {

                pst.setString(1, productName);
                pst.setString(2, productDescription);
                pst.setDouble(3, productPrice);
                pst.setString(4, productId);

                int rowsAffected = pst.executeUpdate();

                // Redirect back to the product list page after modification
                response.sendRedirect("Commande.jsp");

            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception, show an error message, or redirect to an error page as needed
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception, show an error message, or redirect to an error page as needed
        }
    }
}
