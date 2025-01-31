package modele;

import modele.pkgDate.ClDate;

public class TestForceSecurite 
{
    public static void main(String[] args) 
    {
        BDHumain baseDonnee = new BDHumain();

        ClDate cdate = new ClDate();
        /***********************************************************************************************/
        /***************************************Force de sécurité***************************************/
        /***********************************************************************************************/
        // Création d'objets de type Armee et ajout de ceux-ci à la base de donnée
        String matricule = OutilCreationMatricule.setMatricule("r", "j");

        Armee uneArmee = new Armee("r", "j", matricule, 'm', cdate,  "soldat", "m16", "garde", 2, 1, 35);
        Armee deuxArmee = new Armee();
        Armee troisArmee = new Armee("r", "j", matricule, 'm', cdate, "soldat", "m16", "garde", 2, 1, 35);
        Armee quatreArmee = new Armee();

        baseDonnee.ajouter(quatreArmee);
        baseDonnee.ajouter(troisArmee);
        baseDonnee.ajouter(deuxArmee);
        baseDonnee.ajouter(uneArmee);

        //Création d'objet de type milice et ajout de ceux-ci à la base de donnée
        Milice uneMilice = new Milice();
        Milice deuxMilice = new Milice("rouleau", "jacob", matricule, 'm' , cdate, "guarde", "aucune", "guarde", 2, 1);
        Milice troisMilice = new Milice("doe", "john",'m', cdate, "milicien", "batte", "porte", 1, 0);
        Milice quatreMilice = new Milice("thibault", "cedric", 'm', new ClDate(1976,8,24), "recrue", "aucune", "guarde manger", 0, 0);
        Milice cinqMilice = new Milice("rouleau", "jacob", matricule, 'm' , cdate, "guarde", "aucune", "guarde", 2, 1);
        Milice sixMilice = new Milice("rouleau", "jacob", matricule, 'm' , new ClDate(1980,8,24), "guarde", "aucune", "guarde", 2, 1);

        baseDonnee.ajouter(deuxMilice);
        baseDonnee.ajouter(uneMilice);
        baseDonnee.ajouter(troisMilice);
        baseDonnee.ajouter(quatreMilice);
        baseDonnee.ajouter(cinqMilice);
        baseDonnee.ajouter(sixMilice);

        // Test de la méthode equals sur des objets Armee
        System.out.println("\nuneArmee pareil que deuxArmee : " + uneArmee.equals(deuxArmee)); // Donne faux, c'est bon
        System.out.println("\nuneArmee pareil que troisArmee : " + uneArmee.equals(troisArmee)); // Donne vrai, c'est bon
        System.out.println("\ndeuxArmee pareil que quatreArmee : " + deuxArmee.equals(quatreArmee)); // Donne faux, c'est bon

        //Test de la méthode equals des objets Milice
        System.out.println("\nuneMilice pareil que deuxMilice : " + uneMilice.equals(deuxMilice)); // Donne faux, c'est bon
        System.out.println("\nuneMilice pareil que troisMilice : " + uneMilice.equals(troisMilice)); // Donne faux, c'est bon
        System.out.println("\ncinqMilice pareil que deuxMilice : " + cinqMilice.equals(deuxMilice)); // Donne vrai, c'est bon
        System.out.println("\nsixMilice pareil que cinqMilice : " + sixMilice.equals(cinqMilice)); // Donne faux, c'est bon


        // Test de l'affichage de la liste des force de sécurité
        System.out.println(baseDonnee.toStringForce());  // Tout fonctionne
    }
}
