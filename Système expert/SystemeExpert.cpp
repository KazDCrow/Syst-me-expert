/**
 * \file SystemeExpert.cpp
 * \brief Ce fichier contient une implantation des méthodes de la classe SystemeExpert
 * \author IFT-2008 & Jacob
 * \version 0.1
 * \date mai 2021
 *
 */

#include "SystemeExpert.h"
using namespace std;

namespace tp1
{
    // Compléter les méthodes demandées et corriger éventuellement la valeur de retour !
    // Ajouter aussi les balises Doxygen et commenter bien vos méthodes.

	/**
	 * \brief Constructeur de la classe SystemeExpert.
	 */
	SystemeExpert::SystemeExpert()
	{
	}

	/**
	 * \brief Destructeur de la classe SystemeExpert.
	 */
	SystemeExpert::~SystemeExpert()
	{

	}

	/**
	 * \brief Constructeur de copie de la classe SystemeExpert.
	 * \param[in] se Un objet SystemeExpert dont on veut une copie.
	 *
	 * \post Les baseRegles et baseFaits de l'objet courant son initialisé en copiant les baseRegles et baseFait de
	 * l'objet passé en paramètre
	 */
	SystemeExpert::SystemeExpert(const SystemeExpert & se)
	{
	    this->baseRegles = se.baseRegles;
	    this->baseFaits = se.baseFaits;
	}

	/**
	 * \brief Surcharge de l'opérateur d'égalité de manière à permettre au constructeur de copie de bien fonctionner.
	 * \param[in] se Un objet SystemeExpert dont on veut une copie
	 *
	 * \return L'objet SystemeExpert courant
	 */
	SystemeExpert & SystemeExpert::operator = (const SystemeExpert & se)
	{
        this->baseRegles = se.baseRegles;
        this->baseFaits = se.baseFaits;
		return *this;
	}

	/**
	 * \fn  void SystemeExpert::ajouterRegleSE(const Regle & tr).
	 * \brief   Ajout d'une règle à la base de règles
	 * \param   tr La règle à ajouter à la fin de la base de rèlges
	 *
	 * \post    La règle a été ajouté à la base de règles
	 */
	void SystemeExpert::ajouterRegleSE(const Regle & tr)
	{
	    bool est_present = false;
	    for(vector<Regle>::iterator it = this->baseRegles.begin(); it != this->baseRegles.end(); ++it)
        {
            if(*it == tr)
            {
                est_present = true;
            }
        }

	    if(est_present == false)
	    {
	        this->baseRegles.push_back(Regle(tr));
	    }

	}

	/**
	 * \fn void SystemeExpert::ajouterFaitSE(const TypeFait & tf).
	 * \brief Ajout d'un fait à la fin de la base de fait
	 * \param[in] tf le fait à ajouter
	 *
	 * \post Le fait a été ajouté à la base de faits
	 */
	void SystemeExpert::ajouterFaitSE(const TypeFait & tf)
	{
	    bool est_present = false;
        for(const TypeFait & typeFait : this->baseFaits)
        {
            if(typeFait == tf)
            {
                est_present = true;
                break;
            }
        }

        if(est_present == false)
        {
            this->baseFaits.push_back(tf);
        }
	}

    /**
    * \fn    void SystemeExpert::chargerSE(std::ifstream & EntreeFichier).
    * \brief     Chargement du système expert à partir d'une fichier texte.
    * \param[in]    EntreeFichier le fichier source.
    * \pre   EntreeFichier a déjà été ouvert à l'aide de la méthode "open" .

    * \post    La base de règles et la base de faits ont été chargées dans le système expert.
    */
	void SystemeExpert::chargerSE(std::ifstream & EntreeFichier)
	{
		Regle regle;		// Règle qui sera ajoutée au Système Expert.
		TypeFait faitLu;	// fait qui sera ajoutée soit à 'regle' soit à la base de faits.
		int section = 1;	// section 1: Premisses d'une règle.
							// section 2: Conclusions d'une règle.
							// section 3: Faits de base.

		// Vérifier si le fichier est ouvert en lecture
		if (!EntreeFichier.is_open()) throw std::logic_error("chargerSE: le fichier n'est pas ouvert");


		while(!EntreeFichier.eof()) // Jusqu'à ce qu'on ai atteint la fin du fichier
		{
			getline(EntreeFichier, faitLu);
			if (faitLu[0] == '!')	// Dans le format du fichier, le caractère '!'
			{						// indique un changement de section
				if (faitLu[1] == '>')	// "!>" indique que la prochaine section 
					section = 2;		// contient des conclusions.
				else
				{
					// La dernière section contenait des conclusions donc on
					// vient de finaliser une règle.  On l'ajoute maintenant au Système Expert
					ajouterRegleSE(regle);
					//Vider la règle en supprimant ses prémisses et ses conclusions
					regle.GetPremisses().clear();
					regle.GetConclusions().clear();
					
					section = (faitLu[1] == '%') ? 1 : 3; 
					// "!%" indique que la prochaine section contient 
					//	des premisses.
					// "!!" indique que la prochaine section contient 
					//	des faits.
				}

				getline(EntreeFichier, faitLu);
			}

			switch (section)
			{
				case 1:  // Ajout d'une premisse à une règle
					regle.GetPremisses().push_back(faitLu);
					break;

				case 2:	// Ajout d'une conclusion à une règle
					regle.GetConclusions().push_back(faitLu);
					break;
					
				case 3:	// Ajout d'un fait à la base de faits
					ajouterFaitSE(faitLu);
					break;
			}
		}
	}

	/**
	 * \fn void SystemeExpert::chainageAvant(std::vector<Regle> & er).
	 * \brief Saturation de la base de faits en y ajoutant les conclusions des règles dont les prémisses sont présente dans
	 * la base de faits et cela jusqu'à ce que plus aucun fait ne puisse être déduit.
	 * \param[in] er Un vecteur de règle où mettre les règle utilisé lors du chaînage
	 * \throw Une exception de type logic_error mentionnant que la conclusion de la règle est déjà présente dans la base de faits
	 * \post La base de faits à été saturé avec succès et plus aucun fait ne peut être ajouté grâce aux règles de
	 * la base de règles actuelles.
	 *
	 */
	void SystemeExpert::chainageAvant(std::vector<Regle> & er)
	{
        bool bNouvelle_assertion;
        do{
            bNouvelle_assertion = false;
            /*Première boucle permettant de passer à travers toutes les règles*/
            for(auto regle = this->baseRegles.begin(); regle != this->baseRegles.end(); ++regle)
            {
                int nb_premisses_present = 0;
                /*Deuxième boucle permettant de passer au travers des prémisses de la règle*/
                for(const TypeFait & premisses : regle->GetPremisses())
                {
                    /*
                     * Troisième boucle permettant de passer au travers des faits de baseFaits et de les comparés à la prémisse en cours
                     * */
                    for(const TypeFait & faits : this->baseFaits)
                    {
                        /*
                         * Si toutes les prémisses de la règle en cours sont présentes dans fait,
                         * alors on regarde si la conclusion de la règle fait déjà partie de la base de faits.
                         *
                         * Sinon, on regarde si la prémisse en cours fait partie de la base de faits
                         */
                        if(nb_premisses_present == regle->GetPremisses().size())
                        {
                            bool regle_utilise = false;
                            /*
                             * Quatrième boucle qui passe au travers de toutes les conclusions de la règle en cours
                             */
                            for(const TypeFait & conclusions : regle->GetConclusions())
                            {
                                int nb_faits_compare = 0;
                                for(const TypeFait & faits : this->baseFaits)
                                {
                                    if(conclusions != faits)
                                    {
                                        nb_faits_compare++;
                                    }
                                    else
                                    {
                                        throw logic_error( "La conclusion de la règle est déjà présente dans la base de faits");
                                    }
                                }
                                /*
                                 * On regarde si la conclusion de la règle en cours se trouvait déjà
                                 * dans la base de faits. Si ce n'est pas le cas, on l'ajoute à la base de faits
                                 * et on met regle_utilise à vrai.
                                 *
                                 * Si ce n'est pas le cas, on affiche que la conclusion est déjà présente dans la base de faits
                                 */
                                if(nb_faits_compare == this->baseFaits.size())
                                {
                                    this->baseFaits.push_back(conclusions);
                                    regle_utilise = true;
                                }
                                else
                                {
                                    throw logic_error( "La conclusion de la règle est déjà présente dans la base de faits");
                                }
                            }
                            /*
                             * Si la règle a été utilisé, alors on l'ajoute au vecteur de règle er passé en paramètre
                             */
                            if(regle_utilise == true)
                            {
                                bNouvelle_assertion = true;
                                er.push_back(*regle);
                            }
                        }

                        else
                        {
                            if(premisses == faits)
                            {
                                nb_premisses_present++;
                            }
                        }
                    }
                }
            }
        }while(bNouvelle_assertion == true);

	}


    /**
     * \fn std::vector<Regle> SystemeExpert::chainageArriere (const TypeFait & hypothese, bool & demontre).
     * \brief Évaluation d'un énoncé, une hypothèse, de manière à évaluer si elle est vrai ou fausse en se basant
     * sur les faits et règles des bases de règles et de faits du système expert. L'hypothèse de départ est comparé
     * aux conclusions des règles de la base de règles et si on trouve une règle avec comme conclusion notre hypothèse et
     * on regarde alors si les prémisses de cette règle sont toute présente dans la base de faits. Si une prémisse n'y est pas,
     * alors cette prémisse devient une hypothèse qu'on vérifie alors par appel récursif.
     * \param[in] hypothese Le fait dont on veut connaître si il est vrai ou faux.
     * \param[in] demontre Un booléen qui sera modifié en fonction de si l'hypothèse est vrai ou fausse.
     *
     * \return Un vecteur de règle contenant les règles ayant été utilisé pour établir si l'hypothèse est vrai. Il sera vide
     * si l'hypothèse est fausse.
     */
    std::vector<Regle> SystemeExpert::chainageArriere (const TypeFait & hypothese, bool & demontre)
    {
        std::vector<Regle> vregles;
        demontre = false;
        /*
         * Première boucle permettant de passer au travers de toutes les règles
         */
        for(auto regle = this->baseRegles.begin(); regle != this->baseRegles.end(); regle++)
        {
            /*
             * Deuxième boucle permettant de passer au travers des conclusions de la règle en cours
             */
            for(const TypeFait & conclusion : regle->GetConclusions())
            {
                string conclusion_rectifier = conclusion;
                enleverCharactereSuperfluPingouin(conclusion_rectifier);

                /*
                 * Si la conclusion concorde avec l'hypothèse en entrée, alors on procède sinon on continue
                 */
                if(conclusion_rectifier == hypothese)
                {
                    int nb_premisse_present = 0;

                    /*
                     * Troisième boucle permettant de passer au travers des prémisses de la règle en cours
                     */
                    for(const TypeFait  & premisse : regle->GetPremisses())
                    {
                        string premisse_rectifier = premisse;
                        enleverCharactereSuperfluPingouin(premisse_rectifier);
                        bool est_present = false;

                        /*
                         * Quatrième boucle permettant de passer au travers les faits de la base de faits du système expert
                         */
                        for(const TypeFait & fait : this->baseFaits)
                        {
                            string fait_rectifier = fait;
                            enleverCharactereSuperfluPingouin(fait_rectifier);

                            /*
                             * Si le fait et la prémisse en cours concorde, alors on indique qu'un fait et une prémisse
                             * ont eu une concordance en mettant est_present à vrai et en incrémentant le nombre de prémisse
                             * de la règle qui sont présente dans la base de faits de 1.
                             * Si ce n'est pas le cas alors on continue.
                             */
                            if(premisse_rectifier == fait_rectifier)
                            {
                                est_present = true;
                                nb_premisse_present++;
                            }
                        }

                        /*
                         * Si aucun des faits de la base de faits n'a concordé avec la prémisse en cours,
                         * alors est_present est resté à faux et la prémisse en cours devient une hypothèse à démontrer
                         * et on fait donc un appel récursif en passant en paramètre la prémisse comme hypothèse et
                         * premisse_valider comme variable booléenne nous permettant de savoir si la prémisse en cours est
                         * vrai ou fausse.
                         */
                        if(est_present == false)
                        {
                            bool premisse_valider = false;
                            vector<Regle> vregle2 = chainageArriere(premisse_rectifier, premisse_valider);

                            /*
                             * Si la prémisse a été valider et est donc vrai, premisse_valider est a vrai et on incrémente donc le
                             * nombre de prémisses présentes dans la base de faits de 1.
                             */
                            if(premisse_valider == true)
                            {
                                nb_premisse_present++;

                                /*
                                 * Cinquième boucle permetttant de passer au travers des règles utilisé lors de l'appel récursif pour
                                 * les ajouter au vecteur de règle courante soit vregles
                                 */
                                for(auto regle_utiliser = vregle2.begin(); regle_utiliser != vregle2.end(); regle_utiliser++)
                                {
                                    vregles.push_back(*regle_utiliser);
                                }
                            }
                        }

                        /*
                         * Si toutes les prémisses sont présente dans la base de faits du système expert, alors
                         * l'hypothèse est démontré et demontre est mis a vrai.
                         * On ajoute ensuite la conclusion en cours à la base de faits.
                         * On ajoute la règle en cours au vecteur des règles utilisé pour la validation et on fini
                         * par retourné ce même vecteur
                         */
                        if(nb_premisse_present == regle->GetPremisses().size())
                        {
                            demontre = true;
                            this->baseFaits.push_back(conclusion);
                            vregles.push_back(*regle);
                            return vregles;
                        }
                    }
                }
            }
        }
        /*
         * Si l'hypothèse est fausse alors on retourne tout de même le vecteur qui sera cependant vide
         */
        return vregles;
    }


    /**
     * \fn void SystemeExpert::enleverCharactereSuperfluPingouin(TypeFait &chaine).
     * \brief Supprime les charactères \r qui se trouve à la fin des chaines de charactère que
     * contient le fichier pingouin.
     * \param[in] chaine La chaine de charactère dont il faut enlever les charactères indésirables
     */
    void SystemeExpert::enleverCharactereSuperfluPingouin(TypeFait &chaine)
    {
        /*
         * On itère sur la chaine de charactères et si le charactère \r est présent, on le supprime
         */
        for(int i = 0; i < chaine.size();i++)
        {
            if(chaine[i] == '\r')
            {
                chaine.erase(i,1);
            }
        }
    }
}
