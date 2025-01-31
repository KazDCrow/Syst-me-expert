package modele;
import modele.pkgDate.ClDate;

public class Test extends OutilCreationMatricule
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


        /***********************************************************************************************/
        /**********************************Employé de maintenance***************************************/
        /***********************************************************************************************/
        //Création d'objet de type Ouvrier et ajout de ceux-ci à la base de donnée
        matricule = OutilCreationMatricule.setMatricule("Morry", "jamie");
        Ouvrier unOuvrier = new Ouvrier();
        Ouvrier deuxOuvrier = new Ouvrier();
        Ouvrier troisOuvrier = new Ouvrier("Morry", "jamie",  'f', cdate, "Salle à manger", 0, "Nettoyer", 5);
        Ouvrier quatreOuvrier = new Ouvrier("Morry", "jamie",matricule,  'f', cdate, "Salle à manger", 0, "Nettoyer", 5);
        Ouvrier cinqOuvrier = new Ouvrier("Morry", "jamie",matricule,  'f', cdate, "Salle à manger", 0, "Nettoyer", 5);

        baseDonnee.ajouter(cinqOuvrier);
        baseDonnee.ajouter(quatreOuvrier);
        baseDonnee.ajouter(troisOuvrier);
        baseDonnee.ajouter(deuxOuvrier);
        baseDonnee.ajouter(unOuvrier);

        //Création d'objet de type Ingenieur et ajout de ceux-ci à la base de donnée
        matricule = OutilCreationMatricule.setMatricule("denver", "carole");
        Ingenieur unIngenieur = new Ingenieur();
        Ingenieur deuxIngenieur = new Ingenieur("denver", "carole", 'f', new ClDate(1975,5,20), "Salle du generateur", "Soudure", new ClDate(2000,6,20), "Universitaire");
        Ingenieur troisIngenieur = new Ingenieur("denver", "carole", matricule,'f', new ClDate(1975,5,20), "Salle du generateur", "Soudure", new ClDate(2000,6,20), "Universitaire");
        Ingenieur quatreIngenieur = new Ingenieur();
        Ingenieur cinqIngenieur = new Ingenieur("denver", "carole", matricule, 'f', new ClDate(1975,5,20), "Salle du generateur", "Soudure", new ClDate(2000,6,20), "Universitaire");

        baseDonnee.ajouter(cinqIngenieur);
        baseDonnee.ajouter(quatreIngenieur);
        baseDonnee.ajouter(troisIngenieur);
        baseDonnee.ajouter(deuxIngenieur);
        baseDonnee.ajouter(unIngenieur);

        System.out.println(baseDonnee.getMatriculeSecurite());

        //Test de la recherche d'un resident et affichage de ce dernier en fonction du matricule
        System.out.println(baseDonnee.afficherResident(matricule)); // Fonctionne
        //Le triage fonctionne, puisque le retour est toujours en ordre de date et de nom


    }
}
