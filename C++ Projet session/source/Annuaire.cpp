/**
 *\file Annuaire.cpp
 *\brief Implémentation de la classe Annuaire
 *\author Jacob Rouleau
 *\version 1.0
 *\date Le 14 avril 2020
 */
#include <sstream>
#include <iostream>
#include <string>
#include <algorithm>
#include "Annuaire.h"
#include "Personne.h"
#include "ContratException.h"
#include "validationFormat.h"

using namespace hockey;
using namespace std;

/**
 * \brief Constructeur avec paramètres
 * 		  On construit un objet Annuaire à partir de la valeur passée en paramètres
 *
 * \param[in] p_nomClub est un string constant qui représente le nom du club et donc de l'annuaire
 *
 * \pre p_nomClub doit être non vide, mais peut contenir des nombres
 * \post L'annuaire est initialisé avec les valeurs passées en paramètres
 */
Annuaire::Annuaire(const std::string p_nomClub): m_nomClub(p_nomClub), m_vMembres()
{
	PRECONDITION(util::validerFormatNomClub(p_nomClub));

	POSTCONDITION(m_nomClub == p_nomClub);
	POSTCONDITION(m_vMembres.size() == 0);
}

/**
 * \brief Constructeur copie de la classe Annuaire
 */
Annuaire::Annuaire(const Personne& personneACopier)
{

}

/**
 * \brief Destructeur de la classe Annuaire qui vide le vecteur contenant les membres copiés
 */
Annuaire::~Annuaire()
{
	for(unsigned int i=0; i< m_vMembres.size();i++)
	{
		delete[] m_vMembres[i];
		m_vMembres.clear();
	}
}

/**
 * \brief Méthode qui ajoute, au vecteur de membres, une copie d'une personne (joueur ou entraineur)
 * \param[in] p_personne Un objet de type Personne donc soit un Joueur soit un Entraineur
 */
void Annuaire::ajouterPersonne(const Personne& p_personne)
{
	m_vMembres.push_back(p_personne.clone());
}

/**
 * \brief Retourne le nom du club
 * \return Un string constant contenant le nom du club
 */
string Annuaire::reqNomClub() const
{
	return m_nomClub;
}


/**
 * \brief Parcours le vecteurs des membres et retourne les attributs des membres sous forme de chaîne de caractères (string)
 * \return Les attributs des membres formatés sous forme de chaine de caractère
 */
std::string Annuaire::reqAnnuaireFormate()
{
	ostringstream os;
	os << "Club  : " <<m_nomClub << endl;
	os <<  "---------------------" << endl;

	for(unsigned int i = 0; i < m_vMembres.size(); i++)
	{
		os << m_vMembres[i]->reqPersonneFormate();
	}
	return os.str();
}



