package modele;

import modele.pkgDate.ClDate;

public class Ingenieur extends EmployeMaintenance
{
    protected String specialite;
    protected ClDate dateFinEtude;
    protected String niveauEtude;

    public Ingenieur()
    {
        super();
        specialite = "civil";
        dateFinEtude = new ClDate();
        niveauEtude = "Universitaire";
    }

    public Ingenieur(String _nom, String _prenom, String _matricule ,char _sexe, ClDate _dateNaissance, String _secteur, String _specialite, ClDate _dateFinEtude, String _niveauEtude)
    {
        super(_nom, _prenom, _matricule, _sexe, _dateNaissance, _secteur);
        specialite = _specialite;
        dateFinEtude = _dateFinEtude;
        niveauEtude = _niveauEtude;
    }

    public Ingenieur(String _nom, String _prenom, char _sexe, ClDate _dateNaissance, String _secteur, String _specialite, ClDate _dateFinEtude, String _niveauEtude)
    {
        super(_nom, _prenom, _sexe, _dateNaissance, _secteur);
        specialite = _specialite;
        dateFinEtude = _dateFinEtude;
        niveauEtude = _niveauEtude;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public ClDate getDateFinEtude() {
        return dateFinEtude;
    }

    public void setDateFinEtude(ClDate dateFinEtude) {
        this.dateFinEtude = dateFinEtude;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((specialite == null) ? 0 : specialite.hashCode());
        result = prime * result + ((dateFinEtude == null) ? 0 : dateFinEtude.hashCode());
        result = prime * result + ((niveauEtude == null) ? 0 : niveauEtude.hashCode());
        return result;
    }

    public boolean equals(Ingenieur unIngenieur) 
    {
        boolean resultat = false;
        if (specialite.equals(unIngenieur.getSpecialite())) 
        {
            if(dateFinEtude.equals(unIngenieur.getDateFinEtude()))
            {
                if (niveauEtude.equals(unIngenieur.getNiveauEtude()))
                {
                    if (super.equals(unIngenieur)) 
                    {
                        resultat = true;    
                    }
                }
            }    
        }
        return resultat;
    }

    public String toString() 
    {
        return "Ingenieur\n-----------------------------------------\n" + super.toString() + "\nSpécialité : " + specialite + "\nDate de fin d'étude : " + dateFinEtude + "\t\tNiveau d'étude : " + niveauEtude;
    }

    public String toString2() 
    {
        System.getProperty("/");
        return "Ingenieur/"+super.toString2() + "/" + specialite + "/" + dateFinEtude + "/" + niveauEtude;
    }

    
}
