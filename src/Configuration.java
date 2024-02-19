/**
 * @author Dumitru Gliga
 * @version 1.0.0
 */
public class Configuration {
    //region donnés membres
    private String description;
    private double prixMaximal;
    private Composant[] listeComposants;
    private int nbComposants;
    private final int MAX_COMPOSANTS = 20;
    //endregion

    //region Constructeur(s)
    public Configuration(String description, double prixMaximal, Composant[] composants){
        setDescription(description);
        setPrixMaximal(prixMaximal);
        setListeComposants(composants);
    }
    //endregion

    //region Accesseurs et Mutateurs

    /**
     * Accesseur de la donné membre 'description'
     * @return la donné membre 'description'
     */
    public String getDescription() {
        return description;
    }

    /**
     * mutateur de la donné membre 'description'. Mutateur privé parce que la valeur de 'description' devrait seulement être
     * affectée une fois dans le constructeur.
     * @param description valeur qu'on désire attribuer à 'description' ne peut pas être vide ou null
     */
    private void setDescription(String description) {
        if (description.isBlank() || description.isEmpty())
            return;
        this.description = description.toUpperCase();
    }

    /**
     * Accesseur de la donné membre 'prixMaximal'
     * @return la donné membre 'prixMaximal'
     */
    public double getPrixMaximal() {
        return prixMaximal;
    }

    /**
     * Mutateur de la donné membre 'prixMaximal'
     * Aucune modification sera effectué si le prixMaximal passer en paramétre
     * est négatif.
     * @param prixMaximal prix qu'on désire attribuer à la donné membre 'prixMaximal' ne peut pas être négatif
     */
    public void setPrixMaximal(double prixMaximal) {
        if (prixMaximal <= 0)
            return;
        this.prixMaximal = prixMaximal;
    }

    /**
     * Accesseur de la donné membre 'listeComposants'
     * @return la donné membre 'listeComposants'
     */
    public Composant[] getListeComposants() {
        return listeComposants;
    }

    public void setListeComposants(Composant[] listeComposants) { // <------ QUESTION  (copie d'adresse ou de qui est-ce qui est à l'intérieure du tableau)
        this.listeComposants = listeComposants;
    }

    /**
     * Accesseur de la donné membre 'nbComposants'
     * @return la donné membre 'nbComposants'
     */
    public int getNbComposants() {
        return nbComposants;
    }

    /**
     * mutateur de la donné membre 'nbComposants'
     * @param nbComposants valeur qu'on désire attribuer à 'nbComposants' ne peut pas être négatif ou 0
     */
    public void setNbComposants(int nbComposants) {
        if (nbComposants <= 0)
            return;
        this.nbComposants = nbComposants;
    }

    //endregion

}
