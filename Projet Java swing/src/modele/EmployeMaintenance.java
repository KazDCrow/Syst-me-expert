package modele;
import java.util.ArrayList;

import modele.pkgDate.ClDate;
public abstract class EmployeMaintenance extends Humain
{
    protected String secteur;

    static ArrayList<EmployeMaintenance> arrayMaintenance;
    static 
    {
        arrayMaintenance = new ArrayList<EmployeMaintenance>();
    }    
    EmployeMaintenance()
    {
        super();
        secteur = "unSecteur";
        arrayMaintenance.add(this);
    }

    EmployeMaintenance(String _nom, String _prenom, String _matricule, char _sexe, ClDate _dateNaissance, String _secteur)
    {
        super(_nom, _prenom, _matricule ,_sexe, _dateNaissance);
        secteur = _secteur;
        arrayMaintenance.add(this);
    }

    EmployeMaintenance(String _nom, String _prenom, char _sexe, ClDate _dateNaissance, String _secteur)
    {
        super(_nom, _prenom, _sexe, _dateNaissance);
        secteur = _secteur;
        arrayMaintenance.add(this);
    }

    public String getSecteur() 
    {
        return secteur;
    }
    public void setSecteur(String secteur) 
    {
        this.secteur = secteur;
    }
    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((secteur == null) ? 0 : secteur.hashCode());
        return result;
    }

    public boolean equals(EmployeMaintenance unEmploye) 
    {
        boolean resultat = false;
        if (secteur.equals(unEmploye.getSecteur())) 
        {
            if (super.equals(unEmploye)) 
            {
                resultat = true;    
            }
        }
        return resultat;
    }

    public String toString() 
    {
        return super.toString() + "\nSecteur d'attribution : " + secteur;
    }

    public String toString2() 
    {
        return super.toString2() + "/" + secteur;
    }

    
}
