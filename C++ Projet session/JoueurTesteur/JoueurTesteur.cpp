/**
 * \file JoueurTesteur.cpp
 * \brief Implantation des tests unitaires pour la classe Joueur
 *        dérivée de Personne
 * \author Jacob Rouleau
 * \version 1.0
 * \date Le 14 avril 2020
 */

#include <gtest/gtest.h>
#include "Joueur.h"
#include "ContratException.h"
#include "Date.h"
#include "validationFormat.h"
#include <sstream>
#include <iostream>
#include <string>

using namespace std;

util::Date uneDate(14,11,2004);
util::Date uneDateInvalide(14,11,1998);
util::Date uneDateInvalide2(6,4,2002);


/**
 * \brief Test du Constructeur Joueur(const std::string p_nom, const std::string p_prenom,
		const util::Date p_dateNaissance, const std::string p_telephone,
		const std::string p_position);
 *        cas valide ConstructeurAvecParametre: Joueur avec des arguments valides
 *        cas invalideS: Prochain test
 */
TEST(Joueur, ConstructeurAvecParametre)
{
	Joueur joueurTest("Rouleau","Jacob",uneDate,"450 750-3367","centre");
	ASSERT_EQ("Rouleau", joueurTest.reqNom());
	ASSERT_EQ("Jacob",joueurTest.reqPrenom());
	ASSERT_EQ(uneDate.reqDateFormatee(),joueurTest.reqDate());
	ASSERT_EQ("450 750-3367", joueurTest.reqTelephone());
	ASSERT_EQ("centre", joueurTest.reqPosition());
}

/**
 * \brief Test du Constructeur Joueur(const std::string p_nom, const std::string p_prenom,
		const util::Date p_dateNaissance, const std::string p_telephone,
		const std::string p_position);
 *        cas invalideS: nom vide ou ne contenant pas que des lettres
 *        				 prénom vide ou ne contenant pas que des lettres
 *        				 date de naissance d'une personne de plus de 17 ans ou de moins de 15 ans
 *        				 téléphone dans un format invalide
 */
TEST(Joueur,ConstructeurAvecParametreInvalide)
{
	ASSERT_THROW(Joueur joueurTest("","Jacob",uneDate,"450 750-3367", "centre"), PreconditionException);
		ASSERT_THROW(Joueur joueurTest("Roul3au","Jacob",uneDate,"450 750-3367", "centre"), PreconditionException);
		ASSERT_THROW(Joueur joueurTest("Rouleau","",uneDate,"450 750-3367", "centre"), PreconditionException);
		ASSERT_THROW(Joueur joueurTest("Rouleau","1acob",uneDate,"450 750-3367", "centre"), PreconditionException);
		ASSERT_THROW(Joueur joueurTest("Rouleau","Jacob",uneDateInvalide,"450 750-3367", "centre"), PreconditionException);
		ASSERT_THROW(Joueur joueurTest("Rouleau","Jacob",uneDateInvalide2,"450 750-3367", "centre"), PreconditionException);
		ASSERT_THROW(Joueur joueurTest("Rouleau","Jacob",uneDate,"450 7A0-3367", "centre"), PreconditionException);
		ASSERT_THROW(Joueur joueurTest("Rouleau","Jacob",uneDate,"450-750-3367", "centre"), PreconditionException);
		ASSERT_THROW(Joueur joueurTest("Rouleau","Jacob",uneDate,"450 750-3367", "droite"), PreconditionException);
}


/**
 * \brief Test de la méthode Joueur reqPosition() const;
 * 		cas valide: Personne avec des entrées valides. Vérification du retour sur la position
 * 		cas invalide: Aucun d'identifié
 */
TEST(Joueur, ReqPosition)
{
	Joueur joueurTest("Rouleau","Jacob",uneDate,"450 750-3367","centre");
	Joueur joueurTest2("Rouleau","Jacob",uneDate,"450 750-3367","ailier");
	Joueur joueurTest3("Rouleau","Jacob",uneDate,"450 750-3367","défenseur");
	Joueur joueurTest4("Rouleau","Jacob",uneDate,"450 750-3367","gardien");
	ASSERT_EQ("centre", joueurTest.reqPosition());
	ASSERT_EQ("ailier", joueurTest2.reqPosition());
	ASSERT_EQ("défenseur",joueurTest3.reqPosition());
	ASSERT_EQ("gardien",joueurTest4.reqPosition());
}


/**
 * \brief Test de la méthode virtual std::string reqPersonneFormate() const;
 *        cas valide: Personne avec des entrés valides. Vérification du retour
 *        cas invalide: Aucun d'identifié
 */
TEST(Joueur, reqPersonneFormate)
{
	Joueur joueurTest("Rouleau","Jacob",uneDate,"450 750-3367","centre");
	string afficherNom = "Nom              : ";
	string afficherPrenom = "Prénom           : ";
	string afficherDateNaissance = "Date de naissance: ";
	string afficherTelephone = "Téléphone        : ";

	ostringstream os;
	os << afficherNom << "Rouleau" << endl;
	os << afficherPrenom << "Jacob" << endl;
	os << afficherDateNaissance << uneDate.reqDateFormatee() << endl;
	os << afficherTelephone << "450 750-3367" << endl;
	os << "Position         :" << "centre" << endl;
	os <<"---------------------" << endl;

	ASSERT_EQ(os.str(),joueurTest.reqPersonneFormate());
}









