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

@WebServlet("/Controlleur")
public class Controlleur extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Controlleur() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String DBURL = "jdbc:mysql://localhost:3306/e-commerce";

        String un = "root";
        String ps = "";

        try {
            Connection con = DriverManager.getConnection(DBURL, un, ps);
            String req = "SELECT * FROM clients WHERE email=? AND password=?";
            PreparedStatement ST = con.prepareStatement(req);
            ST.setString(1, request.getParameter("email"));
            ST.setString(2, request.getParameter("password"));
            ResultSet RS = ST.executeQuery();
            if (RS.next()) {
                response.sendRedirect("Acceuil.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        }
    }

