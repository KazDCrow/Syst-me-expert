/**
 *\file Joueur.cpp
 *\brief Implémentation de la classe Joueur
 *\author Jacob Rouleau
 *\version 1.0
 *\date Le 14 avril 2020
 */
#include "Joueur.h"
#include "Personne.h"
#include "ContratException.h"
#include "validationFormat.h"
#include <sstream>
#include <iostream>
#include <string>

using namespace std;


/**
 * \brief Constructeur avec paramètres
 * 		  On construit un objet Joueur à partir de valeurs passées en paramètres.
 *
 * \param[in] p_nom est un string constant qui représente le nom du joueur
 * \param[in] p_prenom est un string constant qui représente le prénom du joueur
 * \param[in] p_dateNaissance est un objet Date constant qui représente la date de naissance du joueur
 * \param[in] p_telephone est un string constant qui représente le numéro de téléphone du joueur
 * \param[in] p_position est un string constant qui représente la position du joueur
 *
 * \pre p_sex doit être égal à M ou F
 * \pre Le numéro de RAMQ doit être valide
 * \pre L'entraineur doit être âgé de minimum 18 ans
 * \post L'entraineur est initialisé avec les valeurs passées en paramètres
 */
Joueur::Joueur(const std::string p_nom, const std::string p_prenom,
		const util::Date p_dateNaissance, const std::string p_telephone,
		const std::string p_position) :
		Personne(p_nom, p_prenom, p_dateNaissance, p_telephone), m_position(
				p_position)
{
	PRECONDITION(util::validerAgeJoueur(p_dateNaissance) == true);
	PRECONDITION(util::validerPositionJoueur(p_position) == true);

	POSTCONDITION(reqNom() == p_nom);
	POSTCONDITION(reqPrenom() == p_prenom);
	POSTCONDITION(reqDate() == p_dateNaissance.reqDateFormatee());
	POSTCONDITION(reqTelephone() == p_telephone);
	POSTCONDITION(m_position == p_position);

	INVARIANTS();

}

/**
 * \brief Retourne la position du joueur
 * \return Un string constant contenant la position
 */
std::string Joueur::reqPosition() const
{
	return m_position;
}

/**
 * \brief Retourne les attributs de l'objets sous forme de chaîne de caractères (string)
 * \return Les attributs formatés sous forme de chaine de caractère
 */
std::string Joueur::reqPersonneFormate() const
{
	ostringstream os;
	os << Personne::reqPersonneFormate();
	os << "Position         :" << m_position << endl;
	os <<"---------------------" << endl;
	return os.str();
}

/**
 * \brief Fais une copie du joueur courant
 *
 */
Personne* Joueur::clone() const
{
	return new Joueur(*this);
}

/**
 * \brief pour vérifier l'invariant de classe dans l'implantation de la théorie du contrat
 */
void Joueur::verifieInvariant() const
{
	INVARIANT(util::validerPositionJoueur(m_position));

}
