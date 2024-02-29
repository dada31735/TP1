import java.util.Objects;

/**
 * @author Dumitru Gliga
 * @version 1.0.0
 */
public class Configuration {
    //region donnés membres
    private String description;
    private double prixMaximal;
    private int nbComposants;
    private final int MAX_COMPOSANTS = 20;
    private Composant[] listeComposants = new Composant[MAX_COMPOSANTS];
    //endregion

    //region Constructeur(s)

    /**
     * Constructeur
     * @param description phrase descriptif de la configuration
     * @param prixMaximal coût maximal que la configuration devrait couter avant taxe.
     * @param composants tableaux de composants qu'on désire ajouter à la configuration
     */
    public Configuration(String description, double prixMaximal, Composant[] composants){
        setDescription(description);
        setPrixMaximal(prixMaximal);
        setListeComposants(composants);
    }

    /**
     * Constructeur par copie (copie profonde)
     * @param originale Configuration qu'on desire copier
     */
    public Configuration(Configuration originale){
        Composant[] listeCopie = new Composant[originale.listeComposants.length];
        for (int i = 0; i < originale.listeComposants.length; i++) {
            if (originale.listeComposants[i] != null)
                listeCopie[i] = originale.listeComposants[i].copier();
        }

        setDescription(originale.getDescription() + " (copie)");
        setListeComposants(listeCopie);
        setPrixMaximal(originale.getPrixMaximal());
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
        if (description == null || description.equals(""))
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

    /**
     * Mutateur de la donné membre 'listeComposants'.
     * @param listeComposants
     */
    public void setListeComposants(Composant[] listeComposants) {
        if (listeComposants == null)
            return;

        double prixTotal = 0.0;

        for (int j = 0; j < listeComposants.length; j++) {
            this.listeComposants[j] = listeComposants[j];
            nbComposants++;
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

    /**
     * calcule le prix totale de tout les composants incluant la taxe
     * @param taxe taxe a aplliquer sur le prix total
     * @return le prix totale incluant la taxe
     */
    public double calculerTotal(double taxe){
        double total = 0.0;

        if (listeComposants == null)
            return 0.0;

        for (int i = 0; i < listeComposants.length; i++) {
            if (listeComposants[i] != null)
                total += listeComposants[i].getPrix();
        }
        return (total*(1+taxe));
    }

    /**
     * recherche un composant en fonction de sa categorie
     * @param categorie categorie a partir de laquelle on veut faire la recherche
     * @return null si le composant n'est pas trouvé retourne le composant si il est trouvé
     */
    public Composant rechercher(String categorie){
        if (listeComposants == null)
            return null;

        for (int i = 0; i < listeComposants.length; i++) {
            if (listeComposants[i] != null) {
                if (categorie.toUpperCase().equals(listeComposants[i].getCategorie())) {
                    return listeComposants[i];
                }
            }
        }
        return null;
    }

    /**
     * Permet d'ajouter un composant à la liste de composants
     * @param composant composant qu'on désire d'ajouter
     * @return true si l'ajout c'est effectuer avec succés false si non
     */
    public boolean ajouter(Composant composant){
        if (composant != null){
            if (rechercher(composant.getCategorie()) != null){
                System.out.println("Il y a déjà un composant de cette catégorie: "+rechercher(composant.getCategorie()));
                return false;
            } else {
                for (int i = 0; i < listeComposants.length; i++) {
                    if (nbComposants == MAX_COMPOSANTS) {
                        System.out.println("nombre de composants maximum atteint");
                        return false;
                    } else {
                        double prixTotal = 0.0;
                        for (int j = 0; j < listeComposants.length; j++) {
                            if (listeComposants[j] != null)
                                prixTotal += listeComposants[j].getPrix();
                        }
                        prixTotal += composant.getPrix();
                        if (prixTotal >= prixMaximal) {
                            System.out.println("L'ajout de ce composant ferait dépasser le prix maximum: " + composant);
                            return false;
                        } else {
                            listeComposants[nbComposants] = composant;
                            nbComposants++;
                            System.out.println(composant + " ajouté à la configuration (total=" + prixTotal + "$)");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Permet de retirer un composant de la liste de composants
     * @param composant composant qu'on désire retirer
     * @return true si le composant a était retirer avec succés false si non
     */
    public boolean retirer(Composant composant){
        Composant composantARetirer = rechercher(composant.getCategorie());
        if (composantARetirer == null){
            System.out.println("composant introuvable: "+composant);
            return false;
        }

        for (int i = 0; i < listeComposants.length; i++) {
            if (listeComposants[i] != null) {
                if (listeComposants[i].equals(composant)) {
                    //System.out.println("composant trouver : "+composant);
                    for (int j = i; j < listeComposants.length; j++) {
                        if (j != MAX_COMPOSANTS - 1)
                            listeComposants[j] = listeComposants[j + 1];
                    }
                    nbComposants--;
                    System.out.println(composant + " retiré de la configuration");
                    return true;
                }
            }
        }
        System.out.println("composant introuvable: "+composant);
        return false;
    }

    /**
     * remplace un composant par un autre
     * @param composant composant qu'on désire ajouter(celui qui remplacera un autre)
     * @return true si le composant a était remplacé false si non
     */
    public boolean remplacer(Composant composant){
        if (retirer(rechercher(composant.getCategorie()))){
            ajouter(composant);
            return true;
        } else return false;
    }

    /**
     * Créer une représentation String de l'objet appelant
     * @return représentation String de l'objet appelant
     */
    @Override
    public String toString(){

        String resultat = ""+description+" ("+prixMaximal+"$) :\n";

        if (listeComposants == null)
            return "\t Configuration Vide";

        int nbComposants = 1;

        for (int i = 0; i < listeComposants.length; i++) {
            if (listeComposants[i] != null){
                resultat += nbComposants + " : "+listeComposants[i]+" ("+listeComposants[i].getPrix()+"$)\n";
                nbComposants++;
            }
        }
        return resultat;
    }
    //endregion

}
