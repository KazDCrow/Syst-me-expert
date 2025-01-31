/////////////////////////////////////////////////////////////////////////////
//Fichier Regle.cpp
/////////////////////////////////////////////////////////////////////////////
/**
 * \file Regle.cpp
 * \brief Ce fichier contient une implantation des méthodes de la classe Regle
 * \author IFT-2008 & Jacob
 * \version 0.1
 * \date mai 2021
 *
 */

#include "Regle.h"
using namespace std;

namespace tp1
{
    // Compléter les méthodes demandées et corriger éventuellement la valeur de retour !
    // Ajouter aussi les balises Doxygen et commenter bien vos méthodes.

	/**
	 * \brief Constructeur de la classe Regle
	 */
	Regle::Regle()
	{
	}

	/**
	 * Constructeur de copie de la classe Regle
	 * \param r Un objet Regle dont on veut une copie
	 */
	Regle::Regle(const Regle & r)
	{
	    premisses = r.premisses;
	    conclusions = r.conclusions;
	}

	/**
	 * \brief Destructeur de la classe Regle
	 */
	Regle::~Regle()
	{

	}

	/**
	 * \brief Surcharge de l'opérateur d'égalité permettant au constructeur de copie de bien fonctionner
	 * \param r Un objet Regle dont on veut une copie
	 *
	 * \return L'objet Regle courant
	 */
	Regle & Regle::operator = (const Regle & r)
	{
	    this->premisses = r.premisses;
	    this->conclusions = r.conclusions;
		return *this;
	}

	/**
	 * \brief Surcharge de l'opérateur d'égalité (comparaison). Pour savoir si deux objets Regle sont pareil,
	 * on compare leurs prémisses respectives en itérant sur leur liste de prémisse. Si toutes les prémisses sont les
	 * même alors la conclusion est obligatoirement la même. Il n'est donc pas nécésaire de vérifier aussi leur conclusion.
	 * \param r Un objet Regle qu'on veut comparer avec l'objet Regle courant
	 * \return Un booléen dont la valeur est Vrai si les deux objets sont identiques et Faux sinon
	 */
	bool Regle::operator == (const Regle& r) const
	{
	    int nb_premisses_present = 0;
        for(const TypeFait & typeFait : this->premisses)
        {
            bool est_present = false;
            for(const TypeFait & typeFait1 : r.premisses)
            {
                if(est_present == true)
                {
                    continue;
                }
                else
                {
                    if(typeFait == typeFait1)
                    {
                        est_present = true;
                        nb_premisses_present++;
                    }
                }
            }
        }
        if(nb_premisses_present == this->premisses.size())
        {
            return true;
        }
	    return false;
	}

	/**
	 * \brief Surcharge de l'opérateur "n'est pas égal, pas équivalent". Pour savoir si les deux objets Regle sont différents,
	 * on appel la surcharge de l'opérateur d'égalité (comparaison) et on inverse le retour pour avoir Vrai si les deux objets sont
	 * différents et Faux sinon.
	 * \param r Un objet Regle qu'on veut comparer avec l'objet Regle courant pour savoir si les deux son bien différent.
	 * \return Un booléen dont la valeur est Faux si les deux objets sont identiques et Vrai sinon
	 */
	bool Regle::operator != (const Regle& r) const
	{
        bool egal = *this == r;
        if(egal == true)
        {
            return false;
        }
        else
        {
            return true;
        }
	}

}
