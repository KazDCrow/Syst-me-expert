/**
 *\file Entraineur.cpp
 *\brief Implémentation de la classe Entraineur
 *\author Jacob Rouleau
 *\version 1.0
 *\date Le 14 avril 2020
 */

#include "Entraineur.h"
#include "Personne.h"
#include "ContratException.h"
#include "validationFormat.h"
#include <sstream>
#include <iostream>
#include <string>

using namespace hockey;
using namespace std;

/**
 * \brief Constructeur avec paramètres
 * 		  On construit un objet Entraineur à partir de valeurs passées en paramètres.
 *
 * \param[in] p_nom est un string constant qui représente le nom de l'entraîneur
 * \param[in] p_prenom est un string constant qui représente le prénom de l'entraîneur
 * \param[in] p_dateNaissance est un objet Date constant qui représente la date de naissance de l'entraîneur
 * \param[in] p_telephone est un string constant qui représente le numéro de téléphone de l'entraîneur
 * \param[in] p_numero est un string constant qui représente le numéro de RAMQ de l'entraîneur
 * \param[in] p_sex est un char constant qui représente le sexe de l'entraîneur
 *
 * \pre p_sex doit être égal à M ou F
 * \pre Le numéro de RAMQ doit être valide
 * \pre L'entraineur doit être âgé de minimum 18 ans
 * \post L'entraineur est initialisé avec les valeurs passées en paramètres
 */
Entraineur::Entraineur(const std::string p_nom, const std::string p_prenom,
		const util::Date p_dateNaissance, const std::string p_telephone,
		const std::string& p_numero, const char p_sex) :
		Personne(p_nom, p_prenom, p_dateNaissance, p_telephone),m_numero(p_numero),m_sex(p_sex)
{
	PRECONDITION(util::validerNumRAMQ(p_numero, p_nom, p_prenom,
					p_dateNaissance.reqJour(), p_dateNaissance.reqMois(),
					p_dateNaissance.reqAnnee(), p_sex) == true);
	PRECONDITION(p_dateNaissance.reqAnnee() <= 2002);
	PRECONDITION(util::validerFormatSexe(p_sex) == true);

	POSTCONDITION(reqNom() == p_nom);
	POSTCONDITION(reqPrenom() == p_prenom);
	POSTCONDITION(reqDate() == p_dateNaissance.reqDateFormatee());
	POSTCONDITION(reqTelephone() == p_telephone);
	POSTCONDITION(m_numero == p_numero);
	POSTCONDITION(m_sex == p_sex);
	INVARIANTS();
}

/**
 * \brief Retourne le numéro de RAMQ de l'Entraineur
 * \return Un string constant représentant le numéro soit m_numéro
 */
string Entraineur::reqNumero() const
{
	return m_numero;
}

/**
 * \brief Retourne le sexe de l'Entraineur
 * \return Un char constant représentant le sexe soit m_sex
 */
char Entraineur::reqSex() const
{
	return m_sex;
}

/**
 * \brief Retourne les attributs de l'objets sous forme de chaîne de caractères (string)
 * \return Les attributs formatés sous forme de chaine de caractère
 */
string Entraineur::reqPersonneFormate() const
{
	ostringstream os;
	os << Personne::reqPersonneFormate();
	os << "Numéro de RAMQ   :" << m_numero << endl;
	os <<"---------------------" << endl;
	return os.str();

}

/**
 * \brief Fais une copie de l'entraineur courant
 *
 */
Personne* Entraineur::clone() const
{
	return new Entraineur(*this);
}

/**
 * \brief pour vérifier l'invariant de classe dans l'implantation de la théorie du contrat
 */
void Entraineur::verifieInvariant() const
{

	INVARIANT(m_sex == 'M' || m_sex == 'F');

}
