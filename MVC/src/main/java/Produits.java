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

@WebServlet("/Produits")
public class Produits extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Produits() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Produit> listProduits = getAllProduitsFromDatabase();

        // Ajouter la liste des produits à la requête
        request.setAttribute("listProduits", listProduits);

        // Dispatcher vers la page JSP
        request.getRequestDispatcher("/votre-page.jsp").forward(request, response);
    }

    // Méthode pour récupérer la liste de produits depuis la base de données
    private List<Produit> getAllProduitsFromDatabase() {
        List<Produit> produits = new ArrayList<>();
        String DBURL = "jdbc:mysql://localhost:3306/e-commerce";
        String un = "root";
        String ps = "";

        try {
            Connection con = DriverManager.getConnection(DBURL, un, ps);
            String query = "SELECT  nom, description, prix FROM produits";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Créer un objet Produit pour chaque ligne de résultat
                Produit produit = new Produit();
                produit.setId_produits(resultSet.getInt("id_produits"));
                produit.setNom(resultSet.getString("nom"));
                produit.setDescription(resultSet.getString("description"));
                produit.setPrix(resultSet.getDouble("prix"));

                // Ajouter le produit à la liste
                produits.add(produit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
