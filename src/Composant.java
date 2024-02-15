public class Composant {
    //region déclaration des donnés membres
    private String categorie, nom, marque;
    private double prix, rabais;
    //endregion

    //region constructeur(s)
    public Composant(String categorie, String marque, String nom, double prix){
        setCategorie(categorie);
        setMarque(marque);
        setNom(nom);
        setPrix(prix);
        rabais = 0.00;
    }
    //endregion

    //region Accesseurs et Mutateurs

    /**
     * Accesseur de la donné membre 'categorie'
     * @return la donné membre 'categorie'
     */
    public String getCategorie() { return categorie;}

    /**
     * Mutateur de la donné membre 'categorie'
     * si l'argument est vide ou null
     * la donnée membre ne sera pas modifiée.
     *
     * Méthode privé parce que cette donné membre
     * devrait être constante et alors on ne veut pas
     * permettre de constamment modifier la donné
     * membre.
     * @param categorie valeur qu'on veut attribuer à la donné membre 'categorie'
     */
    private void setCategorie(String categorie) {
        if (categorie.isEmpty() || categorie.isBlank()){
            System.out.println("Catégorie invalide!");
            return;
        }
        this.categorie = categorie.toUpperCase();
    }

    /**
     * Accesseur de la donné membre 'nom'
     * @return la donné membre 'nom'
     */
    public String getNom() { return nom;}

    /**
     * Mutateur de la donné membre 'nom'
     * si l'argument est vide ou null
     * la donnée membre ne sera pas modifiée.
     * @param nom valeur qu'on veut attribuer à la donné membre 'nom'
     */
    public void setNom(String nom) {
        if (nom.isEmpty() || nom.isBlank()){
            System.out.println("nom invalide!");
            return;
        }
        this.nom = nom;
    }

    /**
     * Accesseur de la donné membre 'marque'
     * @return la donné membre 'marque'
     */
    public String getMarque() {
        return marque;
    }

    /**
     * Mutateur de la donné membre 'marque'
     * si l'argument est vide ou null
     * la donnée membre ne sera pas modifiée.
     * @param marque valeur qu'on veut attribuer à la donné membre marque
     */
    public void setMarque(String marque) {
        if (marque.isEmpty() || marque.isBlank()){
            System.out.println("marque invalide!");
            return;
        }
        this.marque = marque;
    }

    /**
     * Accesseur de la donné membre 'prix'
     * @return la donné membre 'prix' - le rabais
     */
    public double getPrix() {
        return prix - rabais*100;
    }

    /**
     * Mutateur de la donné membre 'prix'
     * si l'argument est un nombre inférieur ou égal à 0.00
     * la donné membre ne sera pas modifié.
     * @param prix valeur qu'on veut attribuer à la donné membre prix
     */
    public void setPrix(double prix) {
        if (prix <= 0.00 ) {
            System.out.println("Prix négatif ou null");
            return;
        }
        this.prix = prix;
    }

    /**
     * Accesseur de la donné membre 'rabais'
     * @return la donné membre 'rabais'
     */
    public double getRabais() {
        return rabais;
    }

    /**
     * Mutateur de la donné membre 'rabais'
     * si l'argument est inférieur ou égal à 0.00
     * la donné membre ne sera pas modifié
     * @param rabais valeur qu'on veut attribuer à la donné membre
     */
    public void setRabais(double rabais) {
        if (rabais <= 0.00 ) {
            System.out.println("rabais négatif ou null");
            return;
        }
        this.rabais = rabais;
    }

    //endregion

    //region méthodes de la classe

    /**
     * Méthode qui créer une copie profonde
     * de l'objet
     * @return une copie profonde de l'objet
     */
    public Composant copier(){
        return new Composant(getCategorie(), getMarque(), getNom(), getPrix());
    }

    /**
     * vérifie si deux composants sont identiques
     * en regardant leurs catégorie, marque et nom
     * @param autre Composant avec lequel on désire comparer l'objet
     * @return true si les objets sont pareil false si non
     */
    public boolean estIdentique(Composant autre){
        if (autre.getCategorie().equals(this.getCategorie()) && autre.getMarque().equals(this.getMarque()) && autre.getNom().equals(this.getNom()))
            return true;
        return false;
    }

    /**
     * Créer une représentation String de la classe
     * @return la classe sous ce format "[catégorie] marque nom"
     */
    @Override
    public String toString(){
        return "["+categorie+"] "+marque+" "+nom;
    }

    //endregion
}
