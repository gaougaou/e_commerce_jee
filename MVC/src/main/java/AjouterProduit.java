import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AjouterProduit
 */
@WebServlet("/AjouterProduit")
public class AjouterProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterProduit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	     	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
	            // Retrieve product details from the request parameters
	            String productName = request.getParameter("productName");
	            String productDescription = request.getParameter("productDescription");
	            double productPrice = Double.parseDouble(request.getParameter("productPrice"));

	            // Database connection details
	            String url = "jdbc:mysql://localhost:3306/e-commerce";
	            String user = "root";
	            String pwd = "";

	            // Perform the insertion
	            try (Connection con = DriverManager.getConnection(url, user, pwd);
	                 PreparedStatement pst = con.prepareStatement("INSERT INTO produits (nom, description, prix) VALUES (?, ?, ?)")) {

	                pst.setString(1, productName);
	                pst.setString(2, productDescription);
	                pst.setDouble(3, productPrice);

	                int rowsAffected = pst.executeUpdate();

	                // Redirect back to the product list page after insertion
	                response.sendRedirect("Commande.jsp");

	            } catch (SQLException e) {
	                e.printStackTrace();
	                // Handle the exception, show an error message, or redirect to an error page as needed
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            // Handle the exception, show an error message, or redirect to an error page as needed
	        }

		doGet(request, response);
	}

}
