/**
 *\file Personne.cpp
 *\brief Implémentation de la classe Personne
 *\author Jacob Rouleau
 *\version 1.0
 *\date Le 27 février 2020
 */

#include "Personne.h"
#include "ContratException.h"
#include "Date.h"
#include "validationFormat.h"
#include <sstream>
#include <iostream>
#include <string>

using namespace std;

namespace hockey
{
/**
 * \brief Constructeur avec paramètres
 * 		  On construit un objet Personne à partir de valeurs passées en paramètres.
 * 		  On affiche ensuite un message pour signifié que l'objet est crée et on appelle la méthode
 * 		  reqPersonneFormate() pour formater les informations de la personnes.
 *
 * \param[in] p_nom est un string constant qui représente le nom de la personne
 * \param[in] p_prenom est un string constant qui représente le prénom de la personne
 * \param[in] p_dateNaissance est un objet Date constant qui représente la date de naissance de la personne
 * \param[in] p_telephone est un string constant qui représente le numéro de téléphone de la personne
 * \pre Le nom ne doit contenir que des lettres et être non vide
 * \pre Le prenom ne doit contenir que des lettres et êtres non vide
 * \pre Le téléphone de la personne doit être dans un format valide
 * \post La Personne est initialisé avec les valeurs passées en paramètres
 */
Personne::Personne(const std::string p_nom, const std::string p_prenom,
		const util::Date p_dateNaissance, const std::string p_telephone) :
		m_nom(p_nom), m_prenom(p_prenom), m_telephone(p_telephone), m_dateNaissance(
				p_dateNaissance)
{
	PRECONDITION(util::validerFormatNom(p_nom) == true);
	PRECONDITION(util::validerFormatNom(p_prenom) == true);
	PRECONDITION(util::validerTelephone(p_telephone) == true);

	POSTCONDITION(m_nom == p_nom);
	POSTCONDITION(m_prenom == p_prenom);
	POSTCONDITION(m_telephone == p_telephone);
	POSTCONDITION(m_dateNaissance == p_dateNaissance);

	INVARIANTS();
}

/**
 * \brief Retourne le nom de la Personne
 * \return Un string constant représentant le nom soit m_nom
 */
string Personne::reqNom() const
{
	return m_nom;
}
/**
 * \brief Retourne le prénom de la Personne
 * \return Un string constant représentant le prénom soit m_prenom
 */
string Personne::reqPrenom() const
{
	return m_prenom;
}
/**
 * \brief Retourne la date de naissance formaté de la Personne
 * 			Fait appel à la fonction reqDateFormatee() de la classe Date
 * \return Un string représentant la date de naissance de la personne soit dateFormater
 */
string Personne::reqDate() const
{
	string dateFormater;
	dateFormater = m_dateNaissance.reqDateFormatee();
	return dateFormater;
}

/**
 * \brief Retourne la date de naissance tel quel soit un objet Date
 * \return Une date constante représentant la date de naissance de la personne
 */
util::Date Personne::reqDateObjet() const
{
	return m_dateNaissance;
}


/**
 * \brief Retourne le numéro de téléphone de la personne
 * \return Un string représentant le numéro de téléphone de la personne soit m_telephone
 */
string Personne::reqTelephone() const
{
	return m_telephone;
}
/**
 * \brief Permet de changer le numéro de téléphone de la personne
 * 			Commence par demander un numéro de téléphone à l'utilisateur et recommence tant que le numéro n'est pas valide.
 *
 * 			La validité du numéro de téléphone est effectué grâce à la fonction
 * 			util::validerTelephone(p_telephone).
 *
 * 			On affiche ensuite un message de réussite de la modification et on fait appel à la fonction
 * 			Personne::reqPersonneFormate() pour formater les nouvelles information de l'objet en vue d'un affichage
 * \param[in] p_telephone est un string constant representant le numero de telephone de la personne
 */
void Personne::asgTelephone(const std::string p_telephone)
{
	PRECONDITION(util::validerTelephone(p_telephone) == true);
	m_telephone = p_telephone;
	POSTCONDITION(m_telephone == p_telephone);
	INVARIANTS();
}
/**
 * \brief Retourne les attributs de l'objets sous forme de chaîne de caractères (string)
 * \return Les attributs formatés sous forme de chaine de caractère
 */
string Personne::reqPersonneFormate() const
{
	string afficherNom = "Nom              : ";
	string afficherPrenom = "Prénom           : ";
	string afficherDateNaissance = "Date de naissance: ";
	string afficherTelephone = "Téléphone        : ";

	ostringstream os;

	os << afficherNom << m_nom << endl;
	os << afficherPrenom << m_prenom << endl;
	os << afficherDateNaissance << m_dateNaissance.reqDateFormatee() << endl;
	os << afficherTelephone << m_telephone << endl;

	return os.str();
}
/**
 * \brief Surchage de l'opérateur ==
 * 			En fonction du nom, prénom et date de naissance
 * \param[in] p_personne qui est un objet de type Personne
 * \return Un booléen indiquant si les deux Personne sont égal ou pas
 */
bool Personne::operator==(const Personne& p_personne) const
{
	if (m_nom == p_personne.m_nom)
	{
		if (m_prenom == p_personne.m_prenom)
		{
			if (m_dateNaissance == p_personne.m_dateNaissance)
			{
				return true;
			}
		}
	}
	return false;
}

/**
 * \brief pour vérifier les invariants de classe dans l'implantation de la théorie du contrat
 */
void Personne::verifieInvariant() const
{
	INVARIANT(util::validerFormatNom(m_nom)==true);
	INVARIANT(util::validerFormatNom(m_prenom)==true);
	INVARIANT(util::validerTelephone(m_telephone)==true);
}

} //namespace hockey
