package modele;
import java.io.Serializable;
import java.util.Comparator;

import modele.pkgDate.ClDate;

public class Humain implements Comparable <Humain>, Comparator<Humain>, Serializable
{
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected char sexe;
    protected ClDate dateNaissance;
    protected Boolean vivant;

    protected Humain()
    {
        nom = "Inconnu";
        prenom = "Inconnu";
        matricule = OutilCreationMatricule.setMatricule(nom, prenom);
        sexe = 'm';
        dateNaissance = new ClDate();
        vivant = true;
    }

    protected Humain(String _nom, String _prenom, String _matricule, char _sexe, ClDate _dateNaissance)
    {
        setNom(_nom);
        setPrenom(_prenom);
        setMatricule(_matricule);
        setSexe(_sexe);
        dateNaissance = _dateNaissance;
        vivant = true;
    }

    protected Humain(String _nom, String _prenom,char _sexe, ClDate _dateNaissance)
    {
        setNom(_nom);
        setPrenom(_prenom);
        setMatricule();
        setSexe(_sexe);
        dateNaissance = _dateNaissance;
        vivant = true;
    }


    public String getMatricule() 
    {
        return matricule;
    }

    public void setMatricule(String _matricule) 
    {
        if (_matricule.toCharArray().length != 0) 
        {
            this.matricule = _matricule;
        }
        else 
        {
            setMatricule();
        }
    }

    public void setMatricule()
    {
        this.matricule = OutilCreationMatricule.setMatricule(nom, prenom);
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String _nom) 
    {
        if (_nom.toCharArray().length != 0) 
        {
            this.nom = _nom;
        }
        else
        {
            this.nom = "Inconnu";
        }
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String _prenom) {
        if (_prenom.toCharArray().length != 0) 
        {
            this.prenom = _prenom;
        }
        else
        {
            this.prenom = "Inconnu";
        }
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char _sexe) {
        this.sexe = _sexe;
    }

    public ClDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(ClDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Boolean getVivant() {
        return vivant;
    }

    public void setVivant(Boolean vivant) {
        this.vivant = vivant;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matricule == null) ? 0 : matricule.hashCode());
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
        result = prime * result + sexe;
        result = prime * result + ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
        result = prime * result + ((vivant == null) ? 0 : vivant.hashCode());
        return result;
    }

    public boolean equals(Humain unHumain) 
    {
        boolean resultat = false;

        if (matricule.equals(unHumain.getMatricule())) 
        {
            if (nom == unHumain.getNom() && prenom == unHumain.getPrenom()) 
            {
                if (sexe == unHumain.getSexe()) 
                {
                    if (dateNaissance.equals(unHumain.getDateNaissance())) 
                    {
                        if (vivant == unHumain.getVivant()) 
                        {
                            resultat = true;    
                        }
                    }    
                }    
            }    
        }
        return resultat;
    }

    public int compareTo(Humain unHumain)
    {
        int resultat =  this.dateNaissance.compareTo(unHumain.getDateNaissance());
        if (resultat == 0) 
        {
            resultat = unHumain.compare(this, unHumain);
        }
        return resultat;
    }

    public int compare(Humain unHumain, Humain deuxHumain) 
    {
        String nomMajuscule1 = unHumain.getNom().toLowerCase();
        String nomMajuscule2 = deuxHumain.getNom().toLowerCase();
        int minLength = Math.min(nomMajuscule1.length(), nomMajuscule2.length());
        Boucle:
        for (int i = 0; i < minLength; i++) 
        {
            if (nomMajuscule1.charAt(i) < nomMajuscule2.charAt(i)) 
            {
                return -1;    
            }
            else if(nomMajuscule1.charAt(i) == nomMajuscule2.charAt(i))
            {
                if (i == (minLength-1)) 
                {
                    return 0;
                }
                else
                {
                    continue Boucle;
                }
            }
            else 
            {
                return 1;
            }
        }
        return 0;
    }

    public String toString() 
    {
        return "\nMatricule : " + matricule + "\nNom : " + nom + "\t\tPrénom =" + prenom + "\nSexe : " + sexe
                + "\nDate de naissance : " + dateNaissance + "\nVivant : " + vivant;
    }

    public String toString2()
    {
        return matricule + "/" + nom + "/" + prenom + "/" + sexe
                + "/" + dateNaissance + "/" + vivant;
    }

    
}
