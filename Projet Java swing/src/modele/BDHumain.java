package modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class BDHumain implements Serializable
{
    protected ArrayList<Humain> bdCentralHumain = new ArrayList<Humain>();
    protected ArrayList<ForceSecurite> listeForceSecurites = new ArrayList<ForceSecurite>();
    protected ArrayList<Gestionnaire> listeGestionnaires = new ArrayList<Gestionnaire>();
    protected ArrayList<EmployeMaintenance> listeEmployeMaintenances = new ArrayList<EmployeMaintenance>();
    protected ArrayList<String> matriculeDejaChercher = new ArrayList<String>();
    protected ArrayList<String> nomDejaChercher = new ArrayList<String>();

    public BDHumain ()
    {
    }

    private void ajouterAuxListeSurvivant()
    {
        listeForceSecurites.removeAll(listeForceSecurites);
        listeEmployeMaintenances.removeAll(listeEmployeMaintenances);
        listeGestionnaires.removeAll(listeGestionnaires);
        for (Humain humain : bdCentralHumain) 
        {
            if (humain instanceof ForceSecurite) 
            {
                listeForceSecurites.add((ForceSecurite)humain);    
            }
            else if (humain instanceof Gestionnaire) 
            {
                System.out.println("ajout gestionnaire");
                listeGestionnaires.add((Gestionnaire) humain);
            }
            else if (humain instanceof EmployeMaintenance) 
            {
                listeEmployeMaintenances.add((EmployeMaintenance) humain);
            }
        }
    }

    public void sauvegardeListeSecurite()
    {
        File forceTxt = new File("src/app","Forces de sécurité.txt");
        FileWriter sortie = null;

        try 
        {
            File forceTxt2 = new File("src/app","Forces de sécurité.txt");
            forceTxt2.delete();
        } catch (Exception e) 
        {
            System.out.println("Échec de supressionn du fichier Forces de sécurité.txt");
        }

        try 
        {
            sortie = new FileWriter(forceTxt);
        } catch (IOException e) 
        {
            System.out.println("creation : " + e.getMessage());
        }

        try 
        {

            sortie.write("Forces de securite.txt en date du " + new Date()+"\r\n");    
            for (ForceSecurite humain : listeForceSecurites) 
            {
                sortie.write(humain.toString2()+"\r\n");
            }

            sortie.close();


        } catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }

    public void sauvegardeListeMaintenance()
    {
        File forceTxt = new File("src/app","Employé de maintenance.txt");
        FileWriter sortie = null;

        try 
        {
            File forceTxt2 = new File("src/app","Employé de maintenance.txt");
            forceTxt2.delete();
        } catch (Exception e) 
        {
            System.out.println("Échec de supressionn du fichier Employé de maintenance.txt");
        }

        try 
        {
            sortie = new FileWriter(forceTxt);
        } catch (IOException e) 
        {
            System.out.println("creation : " + e.getMessage());
        }

        try 
        {

            sortie.write("Empoloye de maintenance.txt en date du " + new Date()+"\r\n");    
            for (EmployeMaintenance humain : listeEmployeMaintenances) 
            {
                sortie.write(humain.toString2()+"\r\n");
            }

            sortie.close();


        } catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }

    public void sauvegardeListeGestionnaire()
    {
        File forceTxt = new File("src/app","Gestion.txt");
        FileWriter sortie = null;

        try 
        {
            File forceTxt2 = new File("src/app","Gestion.txt");
            forceTxt2.delete();
        } catch (Exception e) 
        {
            System.out.println("Échec de supressionn du fichier Gestion.txt");
        }

        try 
        {
            sortie = new FileWriter(forceTxt);
        } catch (IOException e) 
        {
            System.out.println("creation : " + e.getMessage());
        }

        try 
        {

            sortie.write("Gestion.txt en date du " + new Date()+"\r\n");    
            for (Gestionnaire humain : listeGestionnaires) 
            {
                sortie.write(humain.toString2()+"\r\n");
            }

            sortie.close();


        } catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }

    public void sauvegardeComplet()
    {
        System.out.println("Sauvegarde");
        File fichierSortie = null;
        FileOutputStream fmtBinaires = null;
        ObjectOutputStream sortie = null;

        try 
        {
            File fichierSortie2 = new File("src/app","bunker.ser");
            fichierSortie2.delete();
        } catch (Exception e) {
            System.out.println("Erreur de supression du fichier bunker.ser");
        }

        try 
        {
            fichierSortie = new File("src/app","bunker.ser");
            fmtBinaires = new FileOutputStream(fichierSortie);
            sortie = new ObjectOutputStream(fmtBinaires);


        } catch (IOException e) {
            System.out.println("Creation bunker.ser erreur\n : " + e.getMessage());
        }

        try 
        {
            sortie.writeObject(bdCentralHumain);
            sortie.close();    
            
        } catch (Exception e) {
            System.out.println("Erreur dans l'écriture du fichier bunker.ser\n : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void chargementComplet()
    {
        File fichierEntree = null;
        FileInputStream fmtBinairesE = null;
        ObjectInputStream entree = null;

        try 
        {
            fichierEntree = new File("src/app","bunker.ser");
            fmtBinairesE = new FileInputStream(fichierEntree);
            entree = new ObjectInputStream(fmtBinairesE);
        } catch (IOException e) {
            System.out.println("Chargement du fichier bunker.ser erreur\n : " + e.getMessage());
        }

        try 
        {
            bdCentralHumain = (ArrayList<Humain>) entree.readObject();
            ajouterAuxListeSurvivant();
            entree.close();
        } catch (Exception e) {
            System.out.println("Erreur chargement des informations du fichier bunker.ser erreur\n : " + e.getMessage());
        }

        

    }


    public ArrayList<Humain> getTListHumain()
    {
        return bdCentralHumain;
    }

    public int getNbrMort()
    {
        int compteur = 0;

        for (Humain humain : bdCentralHumain) 
        {
            if (humain.getVivant() == false) 
            {
                compteur++;    
            }
        }

        return compteur;
    }

    public int getNbrSurvivant()
    {
        return (bdCentralHumain.size()-getNbrMort());
    }

    public int getNbrForceSecuriter()
    {
        return listeForceSecurites.size();
    }

    public int getNbrMaintenance()
    {
        return listeEmployeMaintenances.size();
    }

    public int getNbrGestionnaire()
    {
        return listeGestionnaires.size();
    }

    public void ajouter(Humain resident)
    {
        bdCentralHumain.add(resident);
        if (resident instanceof ForceSecurite) 
        {
            ForceSecurite unMilitaire = (ForceSecurite) resident;    
            listeForceSecurites.add(unMilitaire);
        }
        else if (resident instanceof Gestionnaire) 
        {
            Gestionnaire unGestionnaire = (Gestionnaire) resident;
            listeGestionnaires.add(unGestionnaire);
        }
        else if (resident instanceof EmployeMaintenance) 
        {
            EmployeMaintenance unEmploye = (EmployeMaintenance) resident;
            listeEmployeMaintenances.add(unEmploye);    
        }
    }

    public void supprimerCompletement(Humain resident)
    {
        Humain unHumain = resident;
        if (bdCentralHumain.contains(unHumain)) 
        {
            bdCentralHumain.remove(unHumain);
            if (unHumain instanceof ForceSecurite) 
            {
                ForceSecurite unMilitaire = (ForceSecurite) unHumain;    
                listeForceSecurites.remove(unMilitaire);
            }
            else if (unHumain instanceof Gestionnaire) 
            {
                Gestionnaire unGestionnaire = (Gestionnaire) unHumain;
                listeGestionnaires.remove(unGestionnaire);
            }
            else if (unHumain instanceof EmployeMaintenance) 
            {
                EmployeMaintenance unEmploye = (EmployeMaintenance) unHumain;
                listeEmployeMaintenances.remove(unEmploye);    
            }
        }
    }

    public void supprimer(Humain resident)
    {
        Humain unHumain = resident;
        if (bdCentralHumain.contains(unHumain)) 
        {
            int position = bdCentralHumain.indexOf(unHumain);
            bdCentralHumain.get(position).setVivant(false);
            if (unHumain instanceof ForceSecurite) 
            {
                ForceSecurite unMilitaire = (ForceSecurite) unHumain;    
                listeForceSecurites.remove(unMilitaire);
            }
            else if (unHumain instanceof Gestionnaire) 
            {
                Gestionnaire unGestionnaire = (Gestionnaire) unHumain;
                listeGestionnaires.remove(unGestionnaire);
            }
            else if (unHumain instanceof EmployeMaintenance) 
            {
                EmployeMaintenance unEmploye = (EmployeMaintenance) unHumain;
                listeEmployeMaintenances.remove(unEmploye);    
            }
        }
    }

    public ArrayList<Integer> rechercheResidentNom(String _nom)
    {
        ArrayList<Integer> listeIndex = new ArrayList<Integer>();
        for (int index = 0; index < bdCentralHumain.size(); index++) 
        {
            if (bdCentralHumain.get(index).getNom().equals(_nom)) 
            {
                
                listeIndex.add(index);
            }
        }
        return listeIndex;
    }

    public ArrayList<Integer> rechercheResidentMatricule(String matricule)
    {
        ArrayList<Integer> listeIndex = new ArrayList<Integer>();
        for (int index = 0; index < bdCentralHumain.size(); index++) 
        {
            if (bdCentralHumain.get(index).getMatricule() == matricule) 
            {
                listeIndex.add(index);
            }
        }
        return listeIndex;
    }

    public ArrayList<Humain> afficherResident(String matricule)
    {
        ArrayList<Humain> listeResident = new ArrayList<Humain>();
        ArrayList<Integer> listeIndex = rechercheResidentMatricule(matricule);  
        if (listeIndex.size() != 0) 
        {
            Collections.sort(matriculeDejaChercher);
            if (Collections.binarySearch(matriculeDejaChercher, matricule) < 0) 
            {
                for (Integer index : listeIndex) 
                {
                    listeResident.add(bdCentralHumain.get(index));
                }
                matriculeDejaChercher.add(matricule);
            }
        }
        return listeResident;
    }

    public ArrayList<Humain> afficherResidentAvecNom(String nom)
    {
        ArrayList<Humain> listeResident = new ArrayList<Humain>();
        ArrayList<Integer> listeIndex = rechercheResidentNom(nom); 
        if (listeIndex.size() != 0) 
        {
            Collections.sort(nomDejaChercher);
            if (Collections.binarySearch(nomDejaChercher, nom) < 0) 
            {
                for (Integer index : listeIndex) 
                {
                    if (bdCentralHumain.get(index).getVivant()) 
                    {
                        listeResident.add(bdCentralHumain.get(index));
                    }
                }
                nomDejaChercher.add(nom);
            }
        }
        return listeResident;
    }

    public void resetMatriculeDejaChercher()
    {
        matriculeDejaChercher.removeAll(matriculeDejaChercher);
    }

    public void resetNomDejaChercher()
    {
        nomDejaChercher.removeAll(nomDejaChercher);
    }

    public ArrayList<String> getMatriculeSecurite()
    {
        ArrayList<String> listeMatricule = new ArrayList<String>();
        
        for (ForceSecurite force : listeForceSecurites) 
        {
            listeMatricule.add(force.getMatricule());
        }
        if (listeMatricule.size() == 0) 
        {
            listeMatricule.add("1");
        }
        return listeMatricule;
    }

    public ArrayList<String> getMatriculeMaintenance()
    {
        ArrayList<String> listeMatricule = new ArrayList<String>();
        Collections.sort(listeEmployeMaintenances);
        for (EmployeMaintenance maitenance : listeEmployeMaintenances) 
        {
            listeMatricule.add(maitenance.getMatricule());
        }
        if (listeMatricule.size() == 0) 
        {
            listeMatricule.add("1");
        }
        return listeMatricule;
    }

    public ArrayList<String> getMatriculeGestionnaire()
    {
        ArrayList<String> listeMatricule = new ArrayList<String>();
        Collections.sort(listeGestionnaires);
        for (Gestionnaire gestionnaire : listeGestionnaires) 
        {
            listeMatricule.add(gestionnaire.getMatricule());
        }
        if (listeMatricule.size() == 0) 
        {
            listeMatricule.add("1");
        }
        return listeMatricule;
    }

    public ArrayList<String> getTousMatricule()
    {
        ArrayList<String> listeMatricule = new ArrayList<String>();
        Collections.sort(bdCentralHumain);
        for (Humain humain : bdCentralHumain) 
        {
            listeMatricule.add(humain.getMatricule());
        }
        if (listeMatricule.size() == 0) 
        {
            listeMatricule.add("1");
        }
        return listeMatricule;
    }


    public String toString()
    {
        return bdCentralHumain.toString();
    }

    public String toStringForce()
    {
        return "###############################################################################\n"+
                "Force de sécurité\n"+
                "###############################################################################\n"+
                listeForceSecurites.toString()+"\nLe nombre de résident dans les forces de sécurité est : "+ listeForceSecurites.size() +"\n\n";
    }

    public String toStringGestionnaire()
    {
        return  "###############################################################################\n"+
                "Employé de maintenance\n"+
                "###############################################################################\n"+
                listeGestionnaires.toString()+"\n\n";
    }

    public String toStringEmploye()
    {
        return "###############################################################################\n"+
                "Employé de maintenance\n"+
                "###############################################################################\n"+
                listeEmployeMaintenances.toString()+"\n\n";
    }
}
