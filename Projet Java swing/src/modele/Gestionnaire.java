package modele;

import modele.pkgDate.ClDate;

public abstract class Gestionnaire extends Humain implements Secteur
{
    protected int nbrEmploye;
    protected String secteur;

    Gestionnaire()
    {
        super();
        setNbrEmploye(nbrEmploye);
        setSecteur(secteur);
    }

    Gestionnaire(String _nom, String _prenom, String _matricule, char _sexe, ClDate _dateNaissance, int _nbrEmploye, String _secteur)
    {
        super(_nom, _prenom, _matricule, _sexe, _dateNaissance);
        setNbrEmploye(_nbrEmploye);
        setSecteur(_secteur);
    }

    Gestionnaire(String _nom, String _prenom, char _sexe, ClDate _dateNaissance, int _nbrEmploye, String _secteur)
    {
        super(_nom, _prenom, _sexe, _dateNaissance);
        setNbrEmploye(_nbrEmploye);
        setSecteur(_secteur);
    }

    public int getNbrEmploye() {
        return nbrEmploye;
    }

    public void setNbrEmploye(int nbrEmploye) {
        this.nbrEmploye = nbrEmploye;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + nbrEmploye;
        result = prime * result + ((secteur == null) ? 0 : secteur.hashCode());
        return result;
    }

    public boolean equals(Gestionnaire unGestionnaire) 
    {
        boolean resultat = false;

        if (nbrEmploye == unGestionnaire.nbrEmploye) 
        {
            if (secteur == unGestionnaire.getSecteur()) 
            {
                if (super.equals(unGestionnaire)) 
                {
                    resultat = true;    
                }    
            }    
        }
        return resultat;
    }

    public String toString() {
        return super.toString() + "\nNombre d'emloyé : " + nbrEmploye + "\tSecteur d'attribution : " + secteur;
    }

    public String toString2() 
    {
        return super.toString2() + "/" + nbrEmploye + "/" + secteur;
    }

    

    
}
