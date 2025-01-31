package modele;

import modele.pkgDate.ClDate;

public class Ouvrier extends EmployeMaintenance implements QuartTravailListe
{
    protected int indexQuartTravail;
    protected String genreTravail;
    protected int nbrSecteurDispo;

    public Ouvrier()
    {
        super();
        indexQuartTravail = 0;
        genreTravail = "nettoyage";
        nbrSecteurDispo =1;
    }

    public Ouvrier(String _nom, String _prenom, String _matricule,char _sexe, ClDate _dateNaissance, String _secteur, int _indexQuartTravail, String _genreTravail, int _nbrSecteurDispo)
    {
        super(_nom, _prenom, _matricule,_sexe, _dateNaissance, _secteur);
        setQuartTravail(_indexQuartTravail);
        genreTravail = _genreTravail;
        nbrSecteurDispo = _nbrSecteurDispo;
    }

    public Ouvrier(String _nom, String _prenom, char _sexe, ClDate _dateNaissance, String _secteur, int _indexQuartTravail, String _genreTravail, int _nbrSecteurDispo)
    {
        super(_nom, _prenom, _sexe, _dateNaissance, _secteur);
        setQuartTravail(_indexQuartTravail);
        genreTravail = _genreTravail;
        nbrSecteurDispo = _nbrSecteurDispo;
    }

    public int getIndexQuartTravail()
    {
        return indexQuartTravail;
    }

    public String getQuartTravail() 
    {
        return listeQuartTravail[indexQuartTravail];
    }

    public void setQuartTravail(int indexQuartTravail) 
    {
        if (indexQuartTravail < 3 && indexQuartTravail >=0) 
        {
            this.indexQuartTravail = indexQuartTravail;
        }
        else
        {
            this.indexQuartTravail = 0;
        }
    }

    public String getGenreTravail() {
        return genreTravail;
    }

    public void setGenreTravail(String genreTravail) {
        this.genreTravail = genreTravail;
    }

    public int getNbrSecteurDispo() {
        return nbrSecteurDispo;
    }

    public void setNbrSecteurDispo(int nbrSecteurDispo) {
        this.nbrSecteurDispo = nbrSecteurDispo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + indexQuartTravail;
        result = prime * result + ((genreTravail == null) ? 0 : genreTravail.hashCode());
        result = prime * result + nbrSecteurDispo;
        return result;
    }

    public boolean equals(Ouvrier unOuvrier) 
    {
        boolean resulat = false;
        if (getQuartTravail().equals(unOuvrier.getQuartTravail())) 
        {
            if (genreTravail.equals(unOuvrier.getGenreTravail())) 
            {
                if (nbrSecteurDispo == unOuvrier.getNbrSecteurDispo()) 
                {
                    if (super.equals(unOuvrier)) 
                    {
                        resulat = true;    
                    }    
                }    
            }
        }
        return resulat;
    }

    public String toString() 
    {
        return "Ouvrier\n-----------------------------------------\n"+ super.toString() + "\nQuart de travail : " + getQuartTravail() + "\tGenre de travail : " + genreTravail + 
                "\nNombre de secteur disponible : " + nbrSecteurDispo;
    }

    public String toString2() 
    {
        System.getProperty("/");
        return "Ouvrier/"+super.toString2() + "/" + getQuartTravail() + "/" + genreTravail + 
                "/" + nbrSecteurDispo;
    }

    

    
}
