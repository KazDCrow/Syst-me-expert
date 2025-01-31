package modele;

import modele.pkgDate.ClDate;

public class Milice extends ForceSecurite
{
    protected int nbrPlainte;

    public Milice()
    {
        super();
        nbrPlainte = 0;
    }

    public Milice(String _nom, String _prenom, String _matricule,char _sexe, ClDate _dateNaissance, String _grade, String _arme, String _poste, int _anneeService, int _nbrPlainte)
    {
        super(_nom, _prenom, _matricule, _sexe, _dateNaissance, _grade, _arme, _poste, _anneeService);
        nbrPlainte = _nbrPlainte;
    }

    public Milice(String _nom, String _prenom, char _sexe, ClDate _dateNaissance, String _grade, String _arme, String _poste, int _anneeService, int _nbrPlainte)
    {
        super(_nom, _prenom, _sexe, _dateNaissance, _grade, _arme, _poste, _anneeService);
        nbrPlainte = _nbrPlainte;
    }

    public int getNbrPlainte() 
    {
        return nbrPlainte;
    }

    public void setNbrPlainte(int nbrPlainte) 
    {
        this.nbrPlainte = nbrPlainte;
    }

    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + nbrPlainte;
        return result;
    }

    public boolean equals(Milice unMilicien) 
    {
        boolean resultat = false;
        if (nbrPlainte == unMilicien.getNbrPlainte()) 
        {
            if(super.equals(unMilicien))
            {
                resultat = true;
            }    
        }
        return resultat;
    }

    public String toString() 
    {
        return "Milice\n-----------------------------------------\n" + super.toString() + "\nNombre de plaintes = " + nbrPlainte;
    }

    public String toString2() 
    {
        System.getProperty("/");
        return "Milice/"+super.toString2() + "/" + nbrPlainte;
    }

    
}
