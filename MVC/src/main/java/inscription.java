

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/inscription")
public class inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		        String name = request.getParameter("name");
		        String prenom = request.getParameter("prenom");
		        int age = Integer.parseInt(request.getParameter("age"));
		        String login = request.getParameter("login");
		        String email = request.getParameter("email");
		        String password = request.getParameter("password");
		        String confPassword = request.getParameter("confpassword");
		        String tel = request.getParameter("tel");

		  

		        String url = "jdbc:mysql://localhost:3306/e-commerce";
		        String username = "your_username";
		        String dbPassword = "your_password";

		        try {
		            Class.forName("com.mysql.jdbc.Driver");
		            Connection connection = DriverManager.getConnection(url, username, dbPassword);

		            String query = "INSERT INTO users (name, prenom, age, login, email, password, tel) VALUES (?, ?, ?, ?, ?, ?, ?)";
		            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		                preparedStatement.setString(1, name);
		                preparedStatement.setString(2, prenom);
		                preparedStatement.setInt(3, age);
		                preparedStatement.setString(4, login);
		                preparedStatement.setString(5, email);
		                preparedStatement.setString(6, password);
		                preparedStatement.setString(7, tel);

		                int rowsInserted = preparedStatement.executeUpdate();
		                if( rowsInserted >0)
		                {
		                	response.sendRedirect("Commande.jsp");
		                }
		                else {
		                	response.sendRedirect("inscription.jsp");
		                	
		                }
		              
		            }
		        } catch (ClassNotFoundException | SQLException e) {
		            e.printStackTrace();
		         
		        }

				doGet(request, response);
		    }
		

	

}
