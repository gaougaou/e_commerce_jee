

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChercherProduit
 */
@WebServlet("/ChercherProduit")
public class ChercherProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChercherProduit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String productName = request.getParameter("productName");

	        List<Product> productList = new ArrayList<>();

	        String url = "jdbc:mysql://localhost:3306/e-commerce";
	        String user = "root";
	        String pwd = "";

	        try {
	            Connection con = DriverManager.getConnection(url, user, pwd);

	            // Construct the SQL query
	            String query = "SELECT * FROM produits WHERE name LIKE ?";
	            PreparedStatement pst = con.prepareStatement(query);

	            // Set the search parameter
	            pst.setString(1, "%" + productName + "%");

	            // Execute the query
	            ResultSet rs = pst.executeQuery();

	            // Process the results
	            while (rs.next()) {
	                int productId = rs.getInt(1);
	                String name = rs.getString(2);
	                String description = rs.getString(3);
	                double price = rs.getDouble(4);

	                Product product = new Product(productId, name, description, price);
	                productList.add(product);
	            }

	            rs.close();
	            pst.close();
	            con.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        // Set the search results in the request attribute
	        request.setAttribute("productList", productList);

	        // Forward the request to the JSP page for displaying the search results
	        request.getRequestDispatcher("/yourJspFile.jsp").forward(request, response);
	    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
