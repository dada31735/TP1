import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ObjectInputFilter;

public class ConfigurationTest {
    //region composants réel
    Composant gtx1070 = new Composant("GPU", "Nvidia", "gtx 1070", 500);
    Composant i5 = new Composant("CPU", "intel", "i5-9400f", 80);
    Composant core13600k = new Composant("CPU", "Intel", "Core i5-13600K", 330);
    Composant msiB550 = new Composant("Carte mère", "MSI", "B550 Gaming Wifi", 150);
    Composant tridentzDDR4 = new Composant("Ram", "GSkill", "Trident-Z DDR4 32GB", 135);
    Composant samsung980 = new Composant(" ssd", "Samsung", "980 Pro 2TB", 250);
    //endregion

    //region composants non réels (pour pouvoir créer une liste pleine)
    Composant comp1 = new Composant("1", "Samsung", "980 Pro 2TB", 250);
    Composant comp2 = new Composant("2", "Samsung", "980 Pro 2TB", 250);
    Composant comp3 = new Composant("3", "Samsung", "980 Pro 2TB", 250);
    Composant comp4 = new Composant("4", "Samsung", "980 Pro 2TB", 250);
    Composant comp5 = new Composant("5", "Samsung", "980 Pro 2TB", 250);
    Composant comp6 = new Composant("6", "Samsung", "980 Pro 2TB", 250);
    Composant comp7 = new Composant("7", "Samsung", "980 Pro 2TB", 250);
    Composant comp8 = new Composant("8", "Samsung", "980 Pro 2TB", 250);
    Composant comp9 = new Composant("9", "Samsung", "980 Pro 2TB", 250);
    Composant comp10 = new Composant("10", "Samsung", "980 Pro 2TB", 250);
    Composant comp11 = new Composant("11", "Samsung", "980 Pro 2TB", 250);
    Composant comp12 = new Composant("12", "Samsung", "980 Pro 2TB", 250);
    Composant comp13 = new Composant("13", "Samsung", "980 Pro 2TB", 250);
    Composant comp14 = new Composant("14", "Samsung", "980 Pro 2TB", 250);
    Composant comp15 = new Composant("15", "Samsung", "980 Pro 2TB", 250);
    Composant comp16 = new Composant("16", "Samsung", "980 Pro 2TB", 250);
    Composant comp17 = new Composant("17", "Samsung", "980 Pro 2TB", 250);
    Composant comp18 = new Composant("18", "Samsung", "980 Pro 2TB", 250);
    Composant comp19 = new Composant("19", "Samsung", "980 Pro 2TB", 250);
    Composant comp20 = new Composant("20", "Samsung", "980 Pro 2TB", 250);
    //endregion

    Composant[] listeMaxComposants = {comp1,comp2,comp3,comp4,comp5,comp6,comp7,comp8,comp9,comp10,comp11,comp12,comp13,comp14,comp15,comp16,comp17,comp18,comp19,comp20};

    //region configurations
    Configuration config1 = new Configuration("PC vide", 10000, new Composant[]{});
    Configuration config2 = new Configuration("PC complet 1", 10000, listeMaxComposants);
    Configuration config3 = new Configuration("PC complet 2", 500, new Composant[]{gtx1070});
    //endregion

    //region Tests
    @Test
    public void ajoutComposantValide(){
        assertEquals(config1.ajouter(i5), true);
    }

    @Test
    public void ajoutComposantInvalideMemeCategorie(){
        config1.ajouter(i5);
        assertEquals(config1.ajouter(core13600k),false);
    }

    @Test
    public void ajoutComposantInvalidePrixDepasser(){
        assertEquals(config3.ajouter(i5), false);
    }

    @Test
    public void ajoutComposantInvalideNbComposantsMaxAtteint() {
        assertEquals(config2.ajouter(i5), false);
    }
    //endregion
}