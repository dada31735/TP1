import java.util.Objects;

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

    public Configuration(Configuration originale){
        Composant[] listeCopie = new Composant[listeComposants.length];
        for (int i = 0; i < listeComposants.length; i++) {
            listeCopie[i] = listeComposants[i].copier();
        }
        new Configuration(description, prixMaximal, listeCopie);
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
    public Composant[] getComposants() {
        return listeComposants;
    }

    public void setListeComposants(Composant[] listeComposants) { // <------ QUESTION  (copie d'adresse ou de qui est-ce qui est à l'intérieure du tableau)
        for (int i = 0; i < listeComposants.length; i++) {
            this.listeComposants[i] = listeComposants[i];
        }
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

    //region autres methodes
    public double calculerTotal(double taxe){
        double total = 0.0;
        for (int i = 0; i < listeComposants.length; i++) {
            total += listeComposants[i].getPrix();
        }
        return (total*(1+taxe));
    }

    public Composant rechercher(String categorie){
        for (int i = 0; i < listeComposants.length; i++) {
            if (categorie.toUpperCase().equals(listeComposants[i].getCategorie())){ //see if toUpper is needed
                return listeComposants[i];
            }
        }
        return null;
    }

    public boolean ajouter(Composant composant){
        if (composant != null){
            for (int i = 0; i < listeComposants.length; i++) {
                if (composant.getCategorie().toUpperCase().equals(listeComposants[i].getCategorie())){ //see if toUpper is needed again !
                    return false;
                }
                else if (nbComposants == MAX_COMPOSANTS) {
                    return false;
                }
                else{
                    double prixTotal = 0.0;
                    for (int j = 0; j < listeComposants.length; j++) {
                        prixTotal+=listeComposants[i].getPrix();
                    }
                    prixTotal+=composant.getPrix();
                    if (prixTotal >= prixMaximal)
                        return false;
                }
            }
        }
        listeComposants[nbComposants] = composant;
        nbComposants++;
        return true;
    }

    public boolean retirer(Composant composant){
        Composant composantARetirer = rechercher(composant.getCategorie());

        for (int i = 0; i < listeComposants.length; i++) {
            if (composantARetirer == null){
                return false;
            }
            if (listeComposants[i].equals(composantARetirer)){
                for (int j = i; j < listeComposants.length; j++) {
                    listeComposants[j] = listeComposants[j+1];
                }
                return true;
            }
        }
        return false;
    }

    public boolean remplacer(Composant composant){
        if (retirer(composant)){
            ajouter(composant);
            return true;
        } else return false;
    }

    public String toString(){

        String resultat = ""+description+" ("+prixMaximal+") :/n";

        for (int i = 0; i < listeComposants.length; i++) {
            if (listeComposants[i] != null){
                resultat += "1 : "+listeComposants[i]+" ("+listeComposants[i].getPrix()+")";
            }
        }
        return resultat;
    }
    //endregion

}
