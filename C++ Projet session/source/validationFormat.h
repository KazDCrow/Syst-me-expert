/**
 *\file validationFormat.h
 *\brief Fichier qui contient les prototypes des fonctions de validation
 *\author Jacob Rouleau
 *\version 1.0
 *\date Le 27 février 2020
 */
#include <iostream>
#include <string>
#include "Date.h"
#ifndef VALIDATIONFORMAT_H_
#define VALIDATIONFORMAT_H_

/**
 * \namespace util
 */
namespace util
{
/**
 *\def longueur_telephone
 *\brief Longueur d'un numéro de téléphone valide (nombre de caractère du string)
 */
static const int longueur_telephone = 12;

/**
 * \brief Valide si le numéro en entré est un numéro de téléphone valide
 * \param[in] p_telephone est un string constant qui représente le numéro à valider
 * \return Un booléen true si le numéro est valide et false sinon
 */
bool validerTelephone(const std::string &p_telephone);

/**
 * \brief Valide le numéro RAMQ grâce à l'aide des information en entré
 * 			3 premières lettres = 3 premières lettres du nom
 * 			4e lettre = 1ère lettre du prénom
 * 			5e et 10e lettre = un espace
 * 			6e et 7e lettres = 2 dernier nombre de l'année de naissance
 * 			8e et 9e lettres = le mois de naissance (+50 si une femme)
 * 			11e et 12e lettres = jour de naissance
 *\param[in] p_numero un string constant qui représente le numéro RAMQ
 *\param[in] p_nom un string constant représentant le nom de la personne
 *\param[in] p_prenom un string constant représentant le prénom de la personne
 *\param[in] p_jourNaissance un entier signé représentant le jour de naissance
 *\param[in] p_moisNaissance un entier signé représentant le mois de naissance
 *\param[in] p_anneeNaissance un entier signé représentant l'année de naissance
 *\param[in] p_sex un char représentant le sexe de la personne (M ou F)
 *\return un booléen soit true si le numéro RAMQ est valide et false sinon
 */
bool validerNumRAMQ(const std::string &p_numero, const std::string &p_nom,
		const std::string& p_prenom, int p_jourNaissance, int p_moisNaissance,
		int p_anneeNaissance, char p_sex);

/**
 *\brief Valide si le paramètre en entré est un nom ou prénom valide soit non vide
 * 			et ne contenant que des lettres
 *\param[in] p_nom un string constant représentant un nom ou un prénom à vérifié
 *\return Un booléen true si le paramètre est valide et false sinon
 */
bool validerFormatNom(const std::string& p_nom);


bool validerFormatSexe(const char& p_sex);

bool validerPositionJoueur(const std::string& p_position);

bool validerAgeJoueur(const util::Date p_dateNaissance);

bool validerFormatNomClub(const std::string& p_club);

} // namespace util

#endif /* VALIDATIONFORMAT_H_ */
