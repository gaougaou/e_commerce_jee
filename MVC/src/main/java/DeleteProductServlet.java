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

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve the product ID from the request parameters
            String productId = request.getParameter("id");

     

            // Database connection details
            String url = "jdbc:mysql://localhost:3306/e-commerce";
            String user = "root";
            String pwd = "";

            // Perform the deletion
            try (Connection con = DriverManager.getConnection(url, user, pwd);
            		
                 PreparedStatement pst = con.prepareStatement("DELETE FROM produits WHERE id_produits = ?")) {
            	

                pst.setString(1, productId);
                int rowsAffected = pst.executeUpdate();

                // Redirect back to the product list page after deletion
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
