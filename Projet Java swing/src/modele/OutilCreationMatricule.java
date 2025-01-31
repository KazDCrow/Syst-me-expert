package modele;

public abstract class OutilCreationMatricule 
{
    static String getNombreMatricule(String nbr)
    {
        String strNbr = nbr;
        if (strNbr.length() < 5) 
        {
            strNbr += (int) Math.floor(1+Math.random()*9);
            return getNombreMatricule(strNbr);
        }
        else
        {
            return nbr;
        }
    }

    public static String setMatricule(String nom, String prenom) 
    {
        String mat;
        String matricule = "";
        if (nom.length() <= 3) 
        {
            mat = nom.toUpperCase();    
        }
        else
        {
            mat = nom.substring(0,3).toUpperCase();
        }

        mat += prenom.substring(0,1).toUpperCase();
        String nbr = ""; 
        mat += getNombreMatricule(nbr);
        matricule = mat;

        return matricule;
    }
}