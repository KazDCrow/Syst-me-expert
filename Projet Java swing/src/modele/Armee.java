package modele;
import java.util.Arrays;
import java.util.List;

import modele.pkgDate.ClDate;

public class Armee extends ForceSecurite
{
    protected int nbrSortieExt;
    protected int nbrVictime;
    protected List<String> matriculeSubordonne = Arrays.asList(new String[5]);

    public Armee()
    {
        super();
        nbrSortieExt = 0;
        nbrVictime = 0;
    }

    public Armee(String _nom, String _prenom, String _matricule,char _sexe, ClDate _dateNaissance, String _grade, String _arme, String _poste, int _anneeService, int _nbrSorti, int _nbrVictime)
    {
        super(_nom, _prenom, _matricule, _sexe, _dateNaissance, _grade, _arme, _poste, _anneeService);
        nbrSortieExt = _nbrSorti;
        nbrVictime = _nbrVictime;
    }

    public Armee(String _nom, String _prenom,char _sexe, ClDate _dateNaissance, String _grade, String _arme, String _poste, int _anneeService, int _nbrSorti, int _nbrVictime)
    {
        super(_nom, _prenom, _sexe, _dateNaissance, _grade, _arme, _poste, _anneeService);
        nbrSortieExt = _nbrSorti;
        nbrVictime = _nbrVictime;
    }

    public int getNbrSortieExt() {
        return nbrSortieExt;
    }

    public void setNbrSortieExt(int nbrSortieExt) 
    {
        this.nbrSortieExt = nbrSortieExt;
    }

    public int getNbrVictime() {
        return nbrVictime;
    }

    public void setNbrVictime(int nbrVictime) 
    {
        this.nbrVictime = nbrVictime;
    }

    public void ajouterSubordonnee(String matricule)
    {

    }

    public void retirerSubordonnee(String matricule)
    {
        
    }

    public List<String> getMatriculeSubordonne() 
    {
        return matriculeSubordonne;
    }

    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + nbrSortieExt;
        result = prime * result + nbrVictime;
        result = prime * result + ((matriculeSubordonne == null) ? 0 : matriculeSubordonne.hashCode());
        return result;
    }

    public boolean equals(Armee uneArmee) 
    {
        boolean resultat = false;
        if (nbrVictime == uneArmee.getNbrVictime()) 
        {
            if (nbrSortieExt == uneArmee.getNbrSortieExt()) 
            {
                    for (String matricule : uneArmee.getMatriculeSubordonne()) 
                    {
                        if (matriculeSubordonne.contains(matricule)) 
                        {
                            resultat = true;    
                        }
                        else
                        {
                            resultat = false;
                        }
                    }
                    if(resultat)
                    {
                        if (super.equals(uneArmee)) 
                        {
                            resultat = true;    
                        }
                        else
                        {
                            resultat = false;
                        }
                    }
            }    
        }
        return resultat;
    }

    public String toString() 
    {
        return "Armee\n-----------------------------------------\n"+super.toString() + "\nNombre de sortie à l'extérieur : " + nbrSortieExt + "\t\tNombre de victimes : " + nbrVictime + "\nMatricule des subordonnés : "
                + matriculeSubordonne.toString()+"\n";
    }

    public String toString2() 
    {
        System.getProperty("/");
        return "Armee/"+super.toString2() + "/" + nbrSortieExt + "/" + nbrVictime + "/"
                + matriculeSubordonne.toString();
    }

    
}
