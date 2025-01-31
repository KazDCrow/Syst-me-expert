package modele;
import modele.pkgDate.ClDate;

public class TestEmployeMaintenance 
{
    public static void main(String[] args) 
    {
        BDHumain baseDonnee = new BDHumain();
        ClDate cdate = new ClDate();
        String matricule;
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

        //Test de la méthode equals() des objets de type Ouvrier

        //Création d'objet de type Ingenieur et ajout de ceux-ci à la base de donnée
        matricule = OutilCreationMatricule.setMatricule("denver", "carole");
        Ingenieur unIngenieur = new Ingenieur();
        Ingenieur deuxIngenieur = new Ingenieur("denver", "carole", 'f', new ClDate(1975,5,20), "Salle du generateur", "Soudure", new ClDate(2000,6,20), "Universitaire");
        Ingenieur troisIngenieur = new Ingenieur("denver", "carole", matricule,'f', new ClDate(1975,5,20), "Salle du generateur", "Soudure", new ClDate(2000,6,20), "Universitaire");
        Ingenieur quatreIngenieur = new Ingenieur();
        Ingenieur cinqIngenieur = new Ingenieur("denver", "carole", matricule, 'f', new ClDate(1975,5,20), "Salle du generateur", "Soudure", new ClDate(2000,6,20), "Universitaire");
        Ingenieur sixIngenieur = new Ingenieur("Rouleau", "Jacob", 'm', new ClDate(1998,11,14), "", "Reparation", new ClDate(2018,6,20), "Collégial");

        baseDonnee.ajouter(cinqIngenieur);
        baseDonnee.ajouter(quatreIngenieur);
        baseDonnee.ajouter(troisIngenieur);
        baseDonnee.ajouter(deuxIngenieur);
        baseDonnee.ajouter(unIngenieur);
        baseDonnee.ajouter(sixIngenieur);

        //Test de la méthode equals() des objets de type Ingénieur
        System.out.println("unIngenieur pareil que deuxIngenieur : " + unIngenieur.equals(deuxIngenieur)); //Donne faux, c'est bon
        System.out.println("deuxIngenieur pareil que troisIngenieur : " + deuxIngenieur.equals(troisIngenieur)); //Donne faux, c'est bon
        System.out.println("unIngenieur pareil que quatreInggenieur : " + unIngenieur.equals(quatreIngenieur)); //Donne faux, c'est bon
        System.out.println("troisIngenieur pareil que cinqIngenieur : " + troisIngenieur.equals(cinqIngenieur)); //Donne vrai, c'est bon


        // Test d'affichage de l'array des employés de maintenance
        System.out.println(baseDonnee); //Tout fonctionne, c'est bon
        
        // Test de suppretion d'un resident de la base de donner
        baseDonnee.supprimer(sixIngenieur);
        System.out.println("\n*************************Après suppression - Liste des employés************************************************");
        System.out.println(baseDonnee.listeEmployeMaintenances); // L'objet a bien été supprimé de la liste des employe de maintenance
        System.out.println("\n****************************Après suppression - Base de donnée centrale**************************************************");
        System.out.println(baseDonnee); // L'objet est encore présent dans la base de donné centrale, mais son attribut vivant est à false


    }
}
