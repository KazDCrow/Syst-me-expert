/**
 * \file AnnuaireTesteur.cpp
 * \brief Implantation des tests unitaires pour la classe Annuaire
 * \author Jacob Rouleau
 * \version 1.0
 * \date Le 14 avril 2020
 */

#include <gtest/gtest.h>
#include "Annuaire.h"
#include "ContratException.h"
#include "Date.h"
#include "Entraineur.h"
#include "Joueur.h"
#include "validationFormat.h"
#include <sstream>
#include <iostream>
#include <string>

using namespace std;


util::Date uneDateJoueur(14,11,2004);
util::Date uneDateEntraineur(14,11,1998);
Joueur joueurTest("Rouleau","Jacob",uneDateJoueur,"450 750-3367","centre");
Entraineur entraineurTest("Rouleau","Jacob",uneDateEntraineur,"450 750-3367", "ROUJ 9811 1416", 'M');

/**
 * \brief Test du Constructeur Annuaire(const std::string p_nomClub)
 *        cas valide ConstructeurAvecParametre: Annuaire avec un nom de club non vide
 *        cas invalideS: nom de club vide
 */
TEST(Annuaire, ConstructeurAvecParametre)
{
	Annuaire club("Les petits");
	ASSERT_EQ("Les petits", club.reqNomClub());
	ASSERT_THROW(Annuaire club(""),PreconditionException);
}

/**
 * \brief Test de la methode ajouterPersonne(const Personne& p_personne)
 * 			cas valide: Ajout d'une personne valide
 */
TEST(Annuaire, AjouterPersonne)
{
	ostringstream os;
	os << "Club  : " <<"Les petits" << endl;
	os <<  "---------------------" << endl;
	os << joueurTest.reqPersonneFormate();
	Annuaire club("Les petits");
	club.ajouterPersonne(joueurTest);
	ASSERT_EQ(os.str(),club.reqAnnuaireFormate());
}
