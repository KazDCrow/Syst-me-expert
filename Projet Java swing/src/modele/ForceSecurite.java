package modele;
import modele.pkgDate.ClDate;

public abstract class ForceSecurite extends Humain
{
    protected String grade;
    protected String arme;
    protected String poste;
    protected int anneeService;

    ForceSecurite()
    {
        super();
        grade = "soldat";
        arme = "m16";
        poste = "garde";
        anneeService = 1;
    }

    ForceSecurite(String _nom, String _prenom, String _matricule ,char _sexe, ClDate _dateNaissance, String _grade, String _arme, String _poste, int _anneeService)
    {
        super(_nom, _prenom, _matricule ,_sexe, _dateNaissance);
        grade = _grade;
        arme = _arme;
        poste = _poste;
        anneeService = _anneeService;
    }

    ForceSecurite(String _nom, String _prenom,char _sexe, ClDate _dateNaissance, String _grade, String _arme, String _poste, int _anneeService)
    {
        super(_nom, _prenom,_sexe, _dateNaissance);
        grade = _grade;
        arme = _arme;
        poste = _poste;
        anneeService = _anneeService;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getArme() {
        return arme;
    }

    public void setArme(String arme) {
        this.arme = arme;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public int getAnneeService() {
        return anneeService;
    }

    public void setAnneeService(int anneeService) 
    {
        this.anneeService = anneeService;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((grade == null) ? 0 : grade.hashCode());
        result = prime * result + ((arme == null) ? 0 : arme.hashCode());
        result = prime * result + ((poste == null) ? 0 : poste.hashCode());
        result = prime * result + anneeService;
        return result;
    }

    public boolean equals(ForceSecurite uneForce) {
        boolean resultat = false;
        if (grade.equals(uneForce.getGrade())) 
        {
            if (arme.equals(uneForce.getArme())) 
            {
                if (poste.equals(uneForce.getPoste())) 
                {
                    if (anneeService == uneForce.getAnneeService()) 
                    {
                        if (super.equals(uneForce)) 
                        {
                            resultat = true;
                        }    
                    }    
                }    
            }    
        }
        return resultat;
    }

    public String toString() {
        return super.toString() + "\nGrade : " + grade + "\t\tArme : " + arme + "\nPoste : " + poste + "\t\tAnnee de service : "
                + anneeService;
    }

    public String toString2 ()
    {
        return super.toString2() + "/" + grade + "/" + arme + "/" + poste + "/"
                + anneeService;
    }

    
}
