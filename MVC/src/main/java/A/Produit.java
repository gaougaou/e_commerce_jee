package A;


public class Produit {
    private int id_produits;
    private String nom;
    private String description;
    private double prix;

    // Constructeur par défaut
    public Produit() {
    }

    // Constructeur avec paramètres
    public Produit(int id_produits, String nom, String description, double prix) {
        this.id_produits = id_produits;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    // Getters et setters
    public int getId_produits() {
        return id_produits;
    }

    public void setId_produits(int id_produits) {
        this.id_produits = id_produits;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
