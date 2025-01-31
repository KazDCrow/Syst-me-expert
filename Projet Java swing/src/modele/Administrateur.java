package modele;

import modele.pkgDate.ClDate;

public class Administrateur extends Gestionnaire
{
    protected String projetAffecter;
    protected String titre;

    public Administrateur()
    {
        super();
        projetAffecter = "Aucun";
        titre = "Bureaucrate";
    }

    public Administrateur(String _nom, String _prenom, String _matricule, char _sexe, ClDate _dateNaissance, int _nbrEmploye, String _secteur, String _projet, String _titre)
    {
        super(_nom, _prenom, _matricule, _sexe, _dateNaissance,_nbrEmploye, _secteur);
        projetAffecter = _projet;
        titre = _titre;
    }

    public Administrateur(String _nom, String _prenom, char _sexe, ClDate _dateNaissance, int _nbrEmploye, String _secteur, String _projet, String _titre)
    {
        super(_nom, _prenom, _sexe, _dateNaissance, _nbrEmploye, _secteur);
        projetAffecter = _projet;
        titre = _titre;
    }

    public String getProjetAffecter() {
        return projetAffecter;
    }

    public void setProjetAffecter(String projetAffecter) {
        this.projetAffecter = projetAffecter;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((projetAffecter == null) ? 0 : projetAffecter.hashCode());
        result = prime * result + ((titre == null) ? 0 : titre.hashCode());
        return result;
    }

    public boolean equals(Administrateur adm) 
    {
        boolean resultat = false;
        if (titre == adm.getTitre()) 
        {
            if (projetAffecter == adm.getProjetAffecter()) 
            {
                if (super.equals(adm)) 
                {
                    resultat = true;
                }
            }
        }
        return resultat;
    }

    public String toString() {
        return "Administrateur\n-----------------------------------------\n" + super.toString() + 
                "\nTitre : " + titre + 
                "\nProjet affecté : " + projetAffecter;
    }

    public String toString2() 
    {
        System.getProperty("/");
        return "Administrateur/"+super.toString2() + 
                "/" + titre + 
                "/" + projetAffecter;
    }

    

    

    
}
