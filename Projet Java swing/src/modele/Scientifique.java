package modele;

import java.util.Arrays;

import modele.pkgDate.ClDate;

public class Scientifique extends Gestionnaire
{
    protected int nbrProjet;
    protected String[] listeProjet = new String[3];

    public Scientifique()
    {
        super();
        nbrProjet = 0;
    }

    public Scientifique(String _nom, String _prenom, String _matricule, char _sexe, ClDate _dateNaissance, int _nbrEmploye, String _secteur, int _nbrProjet)
    {
        super(_nom, _prenom, _matricule, _sexe, _dateNaissance, _nbrEmploye, _secteur);
        nbrEmploye = _nbrProjet;
    }

    public Scientifique(String _nom, String _prenom, char _sexe, ClDate _dateNaissance, int _nbrEmploye, String _secteur, int _nbrProjet)
    {
        super(_nom, _prenom, _sexe, _dateNaissance, _nbrEmploye, _secteur);
        nbrProjet = _nbrProjet;
    }

    public Scientifique(String _nom, String _prenom, String _matricule, char _sexe, ClDate _dateNaissance, int _nbrEmploye, String _secteur, int _nbrProjet, String[] _listeProjets)
    {
        super(_nom, _prenom, _matricule, _sexe, _dateNaissance, _nbrEmploye, _secteur);
        nbrEmploye = _nbrProjet;
        listeProjet = _listeProjets;
    }

    public Scientifique(String _nom, String _prenom, char _sexe, ClDate _dateNaissance, int _nbrEmploye, String _secteur, int _nbrProjet, String[] _listeProjets)
    {
        super(_nom, _prenom, _sexe, _dateNaissance, _nbrEmploye, _secteur);
        nbrProjet = _nbrProjet;
        listeProjet = _listeProjets;
    }

    public int getNbrProjet() {
        return nbrProjet;
    }

    public void setNbrProjet(int nbrProjet) {
        this.nbrProjet = nbrProjet;
    }

    public String[] getListeProjet() 
    {
        return listeProjet;
    }

    public void setListeProjet(String[] listeProjet) 
    {
        this.listeProjet = listeProjet;
    }

    public void supprimerUnProjet(String _projet)
    {
        for (int i = 0; i < listeProjet.length; i++) 
        {
            if (listeProjet[i] == _projet) 
            {
                listeProjet[i] = null;
            }
        }
    }

    public void ajouterUnProjet(String unProjet)
    {
        int compteur = 0;
        int index = 0;
        for (String projet : listeProjet) 
        {
            if (projet == null) 
            {
                index = compteur;
                compteur++;
            }
        }

        if (compteur > 0) 
        {
            listeProjet[index] = unProjet;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + nbrProjet;
        result = prime * result + Arrays.hashCode(listeProjet);
        return result;
    }

    public boolean equals(Scientifique unScientifique) 
    {
        boolean resultat = false;
        if (nbrProjet == unScientifique.getNbrProjet()) 
        {
            if (listeProjet == unScientifique.getListeProjet()) 
            {
                if (super.equals(unScientifique)) 
                {
                    resultat = true;
                } 
            }
        }
        return resultat;
    }

    public String toString() 
    {
        return "Scientifique\n-----------------------------------------\n" + super.toString() + 
                "\nNombre de projet lié : " + nbrProjet + 
                "\nListe des projets : " + "\nProjet 1 : " +listeProjet[0] +
                "\nProjet 2 : " + listeProjet[1] + 
                "\nProjet 3 : " + listeProjet[2];
    }

    public String toString2() 
    {
        System.getProperty("/");
        return "Scientifique/"+super.toString2() + 
                "/" + nbrProjet + 
                "/" +listeProjet[0] +
                "/" + listeProjet[1] + 
                "/" + listeProjet[2];
    }
}
