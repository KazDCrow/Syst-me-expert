/**
 * \file EntraineurTesteur.cpp
 * \brief Implantation des tests unitaires pour la classe Entraineur
 *        dérivée de Personne
 * \author Jacob Rouleau
 * \version 1.0
 * \date Le 14 avril 2020
 */

#include <gtest/gtest.h>
#include "Entraineur.h"
#include "ContratException.h"
#include "Date.h"
#include "validationFormat.h"
#include <sstream>
#include <iostream>
#include <string>

using namespace std;

util::Date uneDate(14,11,1998);
util::Date uneDateInvalide(14,11,2003);

/**
 * \brief Test du Constructeur Entraineur(const std::string p_nom, const std::string p_prenom,
		const util::Date p_dateNaissance, const std::string p_telephone,
		const std::string& p_numero, const char p_sex);
 *        cas valide ConstructeurAvecParametre: Entraineur avec des arguments valides
 *        cas invalide: Prochain test
 */
TEST(Entraineur, ConstructeurAvecParametre)
{
	Entraineur entraineurTest("Rouleau","Jacob",uneDate,"450 750-3367", "ROUJ 9811 1416", 'M');
	ASSERT_EQ("Rouleau", entraineurTest.reqNom());
	ASSERT_EQ("Jacob",entraineurTest.reqPrenom());
	ASSERT_EQ(uneDate.reqDateFormatee(),entraineurTest.reqDate());
	ASSERT_EQ("450 750-3367", entraineurTest.reqTelephone());
	ASSERT_EQ("ROUJ 9811 1416",entraineurTest.reqNumero());
	ASSERT_EQ('M', entraineurTest.reqSex());
}

/**
 * \brief Test du Constructeur Entraineur(const std::string p_nom, const std::string p_prenom,
		const util::Date p_dateNaissance, const std::string p_telephone,
		const std::string& p_numero, const char p_sex);
 *        cas invalideS: nom vide ou ne contenant pas que des lettres
 *        				 prénom vide ou ne contenant pas que des lettres
 *        				 date de naissance d'une personne non majeur
 *        				 téléphone dans un format invalide
 *        				 numéro de RAMQ non valide
 *        				 sexe non valide
 */
TEST(Entraineur,ConstructeurAvecParametreInvalide)
{
	ASSERT_THROW(Entraineur entraineurTestNom("","Jacob",uneDate,"450 750-3367", "ROUJ 9811 1416", 'M'), PreconditionException);
		ASSERT_THROW(Entraineur entraineurTestNom("Roul3au","Jacob",uneDate,"450 750-3367", "ROUJ 9811 1416", 'M'), PreconditionException);
		ASSERT_THROW(Entraineur entraineurTestNom("Rouleau","",uneDate,"450 750-3367", "ROUJ 9811 1416", 'M'), PreconditionException);
		ASSERT_THROW(Entraineur entraineurTestNom("Rouleau","1acob",uneDate,"450 750-3367", "ROUJ 9811 1416", 'M'), PreconditionException);
		ASSERT_THROW(Entraineur entraineurTestNom("Rouleau","Jacob",uneDateInvalide,"450 750-3367", "ROUJ 9811 1416", 'M'), PreconditionException);
		ASSERT_THROW(Entraineur entraineurTestNom("Rouleau","Jacob",uneDate,"450750-3367", "ROUJ 9811 1416", 'M'), PreconditionException);
		ASSERT_THROW(Entraineur entraineurTestNom("Rouleau","Jacob",uneDate,"450 7A0-3367", "ROUJ 9811 1416", 'M'), PreconditionException);
		ASSERT_THROW(Entraineur entraineurTestNom("Rouleau","Jacob",uneDate,"450 750-3367", "ROKJ 9811 1416", 'M'), PreconditionException);
		ASSERT_THROW(Entraineur entraineurTestNom("Rouleau","Jacob",uneDate,"450 750-3367", "ROUJ98111416", 'M'), PreconditionException);
		ASSERT_THROW(Entraineur entraineurTestNom("Rouleau","Jacob",uneDate,"450 750-3367", "ROUJ 9811 1416", 'K'), PreconditionException);
}




/**
 * \brief Test de la méthode Entraineur reqNumero() const;
 * 		cas valide: Personne avec des entrées valides. Vérification du retour sur le numéro
 * 		cas invalide: Aucun d'identifié
 */
TEST(Entraineur, ReqNumero)
{
	Entraineur entraineurTest("Rouleau","Jacob",uneDate,"450 750-3367", "ROUJ 9811 1416", 'M');
	ASSERT_EQ("ROUJ 9811 1416",entraineurTest.reqNumero());
}


/**
 * \brief Test de la méthode Entraineur reqSex() const;
 * 		cas valide: Personne avec des entrées valides. Vérification du retour sur le sexe
 * 		cas invalide: Aucun d'identifié
 */
TEST(Entraineur, ReqSex)
{
	Entraineur entraineurTest("Rouleau","Jacob",uneDate,"450 750-3367", "ROUJ 9811 1416", 'M');
	ASSERT_EQ('M',entraineurTest.reqSex());
}


/**
 * \brief Test de la méthode 	virtual std::string reqPersonneFormate() const;
 *        cas valide: Personne avec des entrés valides. Vérification du retour
 *        cas invalide: Aucun d'identifié
 */
TEST(Entraineur, reqPersonneFormate)
{
	Entraineur entraineurTest("Rouleau","Jacob",uneDate,"450 750-3367", "ROUJ 9811 1416", 'M');
	string afficherNom = "Nom              : ";
	string afficherPrenom = "Prénom           : ";
	string afficherDateNaissance = "Date de naissance: ";
	string afficherTelephone = "Téléphone        : ";

	ostringstream os;
	os << afficherNom << "Rouleau" << endl;
	os << afficherPrenom << "Jacob" << endl;
	os << afficherDateNaissance << uneDate.reqDateFormatee() << endl;
	os << afficherTelephone << "450 750-3367" << endl;
	os << "Numéro de RAMQ   :" << "ROUJ 9811 1416" << endl;
	os <<"---------------------" << endl;
	ASSERT_EQ(os.str(),entraineurTest.reqPersonneFormate());
}



