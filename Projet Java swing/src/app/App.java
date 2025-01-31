package app;
import modele.Administrateur;
import modele.Armee;
import modele.BDHumain;
import modele.Ingenieur;
import modele.Milice;
import modele.OutilCreationMatricule;
import modele.Ouvrier;
import modele.Scientifique;
import modele.pkgDate.ClDate;
import affichage.Vue;

public class App 
{
    public static void main(String[] args) 
    {
        BDHumain bd = new BDHumain();
        ClDate cdate = new ClDate();
        /***********************************************************************************************/
        /***************************************Force de sécurité***************************************/
        /***********************************************************************************************/
        // Création d'objets de type Armee et ajout de ceux-ci à la base de donnée
        String matricule = OutilCreationMatricule.setMatricule("t", "g");

        Armee uneArmee = new Armee("tremblay", "richard", matricule, 'm', cdate,  "soldat", "m16", "garde", 2, 1, 35);
        Armee deuxArmee = new Armee();
        Armee troisArmee = new Armee("tremblay", "guy", matricule, 'm', new ClDate(1950,1,1), "soldat", "m16", "premiere ligne", 2, 1, 26);
        Armee quatreArmee = new Armee();

        bd.ajouter(uneArmee);
        bd.ajouter(deuxArmee);
        bd.ajouter(troisArmee);
        bd.ajouter(quatreArmee);

        //Création d'objet de type milice et ajout de ceux-ci à la base de donnée
        // Milice uneMilice = new Milice();
        Milice deuxMilice = new Milice("rouleau", "jacob", matricule, 'm' , cdate, "guarde", "aucune", "guarde", 2, 1);
        Milice troisMilice = new Milice("doe", "john",'m', cdate, "milicien", "batte", "porte", 1, 0);
        Milice quatreMilice = new Milice("thibault", "cedric", 'm', new ClDate(1976,8,24), "recrue", "aucune", "guarde manger", 0, 0);
        Milice cinqMilice = new Milice("rouleau", "jacob", matricule, 'm' , cdate, "guarde", "aucune", "guarde", 2, 1);
        Milice sixMilice = new Milice("stevenson", "Jason", matricule, 'm' , new ClDate(1980,8,24), "guarde", "aucune", "guarde", 2, 1);

        bd.ajouter(deuxMilice);
        bd.ajouter(troisMilice);
        bd.ajouter(quatreMilice);
        bd.ajouter(cinqMilice);
        bd.ajouter(sixMilice);


        /***********************************************************************************************/
        /**********************************Employé de maintenance***************************************/
        /***********************************************************************************************/
        //Création d'objet de type Ouvrier et ajout de ceux-ci à la base de donnée
        matricule = OutilCreationMatricule.setMatricule("Morry", "jamie");
        Ouvrier unOuvrier = new Ouvrier();
        Ouvrier deuxOuvrier = new Ouvrier();
        Ouvrier troisOuvrier = new Ouvrier("potter", "james",  'm', cdate, "Salle à manger", 0, "Nettoyer", 5);
        Ouvrier quatreOuvrier = new Ouvrier("potter", "harry",matricule,  'm', cdate, "Salle à manger", 0, "Nettoyer", 5);
        Ouvrier cinqOuvrier = new Ouvrier("Simpson", "Lisa",  'f', cdate, "Salle musique", 0, "(Nettoyer)", 5);

        bd.ajouter(cinqOuvrier);
        bd.ajouter(quatreOuvrier);
        bd.ajouter(troisOuvrier);
        bd.ajouter(deuxOuvrier);
        bd.ajouter(unOuvrier);

        //Création d'objet de type Ingenieur et ajout de ceux-ci à la base de donnée
        matricule = OutilCreationMatricule.setMatricule("denver", "carole");
        Ingenieur unIngenieur = new Ingenieur();
        Ingenieur deuxIngenieur = new Ingenieur("harrington", "pinguoin", 'f', new ClDate(1975,5,20), "Salle du generateur", "Soudure", new ClDate(2000,6,20), "Universitaire");
        Ingenieur troisIngenieur = new Ingenieur("denver", "carole", matricule,'f', new ClDate(1975,5,20), "Salle du generateur", "Soudure", new ClDate(2000,6,20), "Universitaire");
        Ingenieur quatreIngenieur = new Ingenieur();
        Ingenieur cinqIngenieur = new Ingenieur("Trioport", "Charles", matricule, 'f', new ClDate(1975,5,20), "Salle du generateur", "Soudure", new ClDate(2000,6,20), "Universitaire");

        bd.ajouter(cinqIngenieur);
        bd.ajouter(quatreIngenieur);
        bd.ajouter(troisIngenieur);
        bd.ajouter(deuxIngenieur);
        bd.ajouter(unIngenieur);

        //Création d'objet de type Scientifique et ajout de ceux-ci à la base de donnée
        //String _nom, String _prenom, char _sexe, ClDate _dateNaissance, int _nbrEmploye, String _secteur, int _nbrProjet
        Scientifique unScientifique = new Scientifique("Science", "wilson", 'm', new ClDate(1995,5,31), 3, "Bibliotheque", 0);
        Scientifique deuxScientifique = new Scientifique("Librarian", "Wickerbottom", 'f', new ClDate(1990,6,20), 1, "Laboratoire 1", 0);
        Scientifique troisScientifique = new Scientifique("Aby", "Wendy", 'f', new ClDate(1995,5,31), 0, "Bibliotheque", 0);
        Scientifique quatreScientifique = new Scientifique("Moore", "Woody", 'm', new ClDate(1996,9,1), 0, "Laboratoire 1", 0);

        bd.ajouter(unScientifique);
        bd.ajouter(deuxScientifique);
        bd.ajouter(troisScientifique);
        bd.ajouter(quatreScientifique);

        //Création d'objet de type Administrateur et ajout de ceux-ci à la base de donnée
        //String _nom, String _prenom, String _matricule, char _sexe, ClDate _dateNaissance, int _nbrEmploye, String _secteur, String _projet, String _titre
        Administrateur unAdmin = new Administrateur("Gourou", "Louise", 'f', new ClDate(1981,10,31), 5,"bureau","aucun","admin");
        Administrateur deuxAdmin = new Administrateur("Garou", "Pinut", 'm', new ClDate(1989,11,14), 0,"bureau","aucun","admin");
        Administrateur troisAdmin = new Administrateur("Tirant", "lache", 'm', new ClDate(), 0,"partout","aucun","admin");
        Administrateur quatreAdmin = new Administrateur("personne", "hercula", 'f', new ClDate(2012,10,31), 0,"bureau","aucun","admin");

        bd.ajouter(unAdmin);
        bd.ajouter(deuxAdmin);
        bd.ajouter(troisAdmin);
        bd.ajouter(quatreAdmin);


       new Vue(bd);
        
    }
}
