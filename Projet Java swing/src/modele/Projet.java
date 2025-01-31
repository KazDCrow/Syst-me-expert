package modele;

public class Projet 
{
    private String nomProjet;
    private int nbrPersonnelLier;

    Projet(String nom)
    {
        nomProjet = nom;
        nbrPersonnelLier = 0;
    }

    public String getNomProjet() 
    {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) 
    {
        this.nomProjet = nomProjet;
    }

    public int getnbrPersonnelLier() 
    {
        return nbrPersonnelLier;
    }

    public void setnbrPersonnelLier(int nbrPersonnelLier) 
    {
        this.nbrPersonnelLier = nbrPersonnelLier;
    }

    public void ajouterDuPersonnel(int nbr)
    {
        nbrPersonnelLier += nbr;
    }

    public void enleverDuPersonnel(int nbr)
    {
        nbrPersonnelLier -= nbr;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nomProjet == null) ? 0 : nomProjet.hashCode());
        result = prime * result + nbrPersonnelLier;
        return result;
    }

    public boolean equals(Projet unProjet) 
    {
        boolean resultat = false;
        if(nomProjet == unProjet.getNomProjet())
        {
            if(nbrPersonnelLier == unProjet.getnbrPersonnelLier())
            {
                resultat = true;
            }
        }
        return resultat;
    }

    public String toString() 
    {
        return "Nom du projet :\t" + nomProjet + "\nNombre de personnel lié au projet :\t" + nbrPersonnelLier;
    }
}
