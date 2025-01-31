package modele.pkgDate;

import java.io.Serializable;
import java.text.DecimalFormat;

public class ClDate implements Comparable <ClDate>, Serializable
{
	// Attributs
	private int annee;
    private int mois;
    private int jours;

	// Constructeurs
	public ClDate()
    {
        setAnnnee(2000);
        setMois(1);
        setJours(1);
    }

    public ClDate(int annee, int mois, int jours)
    {
        setAnnnee(annee);
        setMois(mois);
        setJours(jours);
    }

	// Accesseurs et mutateurs
	public int getAnnnee() 
    {
        return annee;
    }

    public void setAnnnee(int annnee) 
    {
        this.annee = annnee;
    }

    public int getMois() 
    {
        return mois;
    }

    public void setMois(int mois) 
    {
        this.mois = ((mois >0) && (mois <=12))? mois : 1;
    }

    public int getJours() 
    {
        return jours;
    }

    public void setJours(int jours) 
    {
        this.jours = (validerDateComplete(jours))? jours : 1;
    }
	
	// Méthodes de support (privées)
	private Boolean validerDateComplete(int jours)
    {
        // Ici on vérifie si l'année est bisextile
        Boolean bissextile;
        if (annee%4 == 0 && annee%100 != 0 ) 
        {
            bissextile = true;
        }
        else if (annee%400 == 0) 
        {
            bissextile = true;
        }
        else
        {
            bissextile = false;
        }

        // En fonction du mois, les conditions sur les nombres de jours possible varient
        switch (mois) 
        {
            // Les mois ayant 31 jours
            case 1 :case 3 : case 5 : case 7 : case 8 : case 10 : case 12:
                if (jours >= 1 && jours <= 31) 
                {
                    return true;
                }
                else
                {
                    return false;
                }

            // Les mois ayant 30 jours
            case 4 : case 6 : case 9 : case 11 :
                if (jours >= 1 && jours <= 30) 
                {
                    return true;
                }
                else 
                {
                    return false;
                }
            
            // Le mois de Février dont le nombre de jours varie en fonction de si l'année est bisextile
            case 2 :
                if (bissextile == true) 
                {
                    if (jours >= 1 && jours <= 29) 
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    if (jours >= 1 && jours <= 28) 
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            default:
                break;
        }
        return false;
    }

	// Méthodes de service (publiques)
    public String afficherDate()
    {
        DecimalFormat formatAnnee = new DecimalFormat("0000");
        DecimalFormat formatMoisEtJours = new DecimalFormat("00");
        return formatAnnee.format(annee) + "/" + formatMoisEtJours.format(mois) + "/" + formatMoisEtJours.format(jours);
    }

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + annee;
        result = prime * result + mois;
        result = prime * result + jours;
        return result; 
    }

    public boolean equals(ClDate dateComparer) 
    {
        if (this.annee == dateComparer.getAnnnee()) 
        {
            if(this.mois == dateComparer.getMois())
            {
                if(this.jours == dateComparer.getJours())
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public int compareTo(ClDate date)
    {
        if (this.annee < date.getAnnnee()) 
        {
            return -1;
        }
        else if (this.annee == date.getAnnnee()) 
        {
            if (mois < date.getMois()) 
            {
                return -1;
            }
            else if (this.mois == date.getMois()) 
            {
                if (this.jours < date.getJours()) 
                {
                    return -1;
                }
                else if (this.jours == date.getJours()) 
                {
                    return 0;
                }
                else 
                {
                    return 1;
                }  
            }
            else
            {
                return 1;
            }
        }
        else
        {
            return 1;
        }
    }

    // Méthode toString()
    public String toString()
    {
        return mois + "/" + jours + "/" + annee;
    }
}

