/*
 * PersonneTesteur.cpp
 *
 *  Created on: 2020-04-13
 *      Author: etudiant
 */

#include <gtest/gtest.h>
#include "Personne.h"
#include "ContratException.h"
#include "Date.h"
#include <sstream>
#include <iostream>
#include <string>

using namespace hockey;
using namespace std;


util::Date uneDate(14,11,1998);

/**
 * \class PolygoneDeTest
 * \brief classe de test permettant de tester la classe abstraite Polygone
 */
class PersonneDeTest: public Personne
{
public:
	PersonneDeTest(const std::string p_nom, const std::string p_prenom,
			const util::Date p_dateNaissance, const std::string p_telephone) :
		Personne(p_nom,p_prenom,p_dateNaissance,p_telephone)
	{
	}
	;
	virtual Personne* clone() const
	{
		return 0;
	};
	virtual std::string reqPersonneFormate() const
	{
		return Personne::reqPersonneFormate();
	}
};


/**
 * \class UnePersonneValide
 * \brief Fixture  UnePersonneValide pour la création d'un objet Polygone pour les tests
 */
class UnePersonneValide: public ::testing::Test
{
public:
	UnePersonneValide() :
		f_personne("rouleau","jacob",uneDate,"450 750-3367")
	{
	}
	;

	PersonneDeTest f_personne;
};


/**
 * \brief Test du constructeur Personne(const std::string p_nom, const std::string p_prenom,
			const util::Date p_dateNaissance, const std::string p_telephone);
 * 		cas valide:
 * 		 ConstructeurAvecParamètres: Personne avec un nom, prénom et numéro de téléphone, i.e que les membres
 * 		 doivent être égal aux paramètres en entrés
 * 		cas invalide:
 * 		 ConstructeurAvecParamètres: Personne avec un nom et/ou prénom vide ou ne contenant pas que des lettres.
 * 		 							 Personne avec une téléphone au format invalide
 */
TEST(Personne, ConstructeurAvecParametres)
{
	PersonneDeTest personneTest("rouleau","jacob",uneDate,"450 750-3367");
	ASSERT_EQ("rouleau",personneTest.reqNom());
	ASSERT_EQ("jacob", personneTest.reqPrenom());
	ASSERT_EQ("450 750-3367", personneTest.reqTelephone());
	ASSERT_THROW(PersonneDeTest personneTest2("roul2au","",uneDate,"450 70-3367"), PreconditionException);
	ASSERT_THROW(PersonneDeTest personneTest2("","",uneDate,"450 70-3367"), PreconditionException);
	ASSERT_THROW(PersonneDeTest personneTest2("rouleau","",uneDate,"450 70-3367"), PreconditionException);
	ASSERT_THROW(PersonneDeTest personneTest2("rouleau","j5b",uneDate,"450 70-3367"), PreconditionException);
	ASSERT_THROW(PersonneDeTest personneTest2("rouleau","jacob",uneDate,"4X0 750-3367"), PreconditionException);
	ASSERT_THROW(PersonneDeTest personneTest2("rouleau","jacob",uneDate,"450 750 3367"), PreconditionException);
	ASSERT_THROW(PersonneDeTest personneTest2("rouleau","jacob",uneDate,"450 790-33k7"), PreconditionException);
}

/**
 * \Test de la méthode Personne reqNom() const;
 * 		cas valide: Personne avec des entrées valides. Vérification du retour sur le nom
 * 		cas invalide: Aucun d'identifié
 */
TEST_F(UnePersonneValide, ReqNom)
{
	ASSERT_EQ("rouleau",f_personne.reqNom());
}


/**
 * \Test de la méthode Personne reqPrénom() const;
 * 		cas valide: Personne avec des entrées valides. Vérification du retour sur le prénom
 * 		cas invalide: Aucun d'identifié
 */
TEST_F(UnePersonneValide, ReqPrenom)
{
	ASSERT_EQ("jacob",f_personne.reqPrenom());
}

/**
 * \Test de la méthode Personne reqDate() const;
 * 		cas valide: Personne avec des entrées valides. Vérification du retour sur la date
 * 		cas invalide: Aucun d'identifié
 */
TEST_F(UnePersonneValide, ReqDate)
{
	ASSERT_EQ(uneDate.reqDateFormatee(),f_personne.reqDate());
}


/**
 * \Test de la méthode Personne reqTelephone() const;
 * 		cas valide: Personne avec des entrées valides. Vérification du retour sur le téléphone
 * 		cas invalide: Aucun d'identifié
 */
TEST_F(UnePersonneValide, ReqTelephone)
{
	ASSERT_EQ("450 750-3367",f_personne.reqTelephone());
}


/**
 * \Test de la méthode Personne asgTelephone();
 * 		cas valide: Personne avec des entrées valides. Changement du téléphone et vérification de mise à jour
 * 		cas invalide: Le numéro de téléphone n'est pas dans un format valide.
 * 					  Il contient des lettres ou est trop court ou trop long ou
 * 					  code régional invalide ou  ne correspond pas au format
 * 					  XXX XXX-XXXX où X= un chiffre
 */
TEST_F(UnePersonneValide, AsgTelephone)
{
	f_personne.asgTelephone("514 831-1052");
	ASSERT_EQ("514 831-1052", f_personne.reqTelephone());
	ASSERT_THROW(f_personne.asgTelephone("514 83h-1052"),PreconditionException);
	ASSERT_THROW(f_personne.asgTelephone("514 834-105"),PreconditionException);
	ASSERT_THROW(f_personne.asgTelephone("514 8366-1052"),PreconditionException);
	ASSERT_THROW(f_personne.asgTelephone("124 834-1052"),PreconditionException);
	ASSERT_THROW(f_personne.asgTelephone("514x831-1052"),PreconditionException);
}


/**
 * \brief Test de la méthode 	virtual std::string reqPersonneFormate() const;
 *        cas valide: Personne avec des entrés valides. Vérification du retour
 *        cas invalide: Aucun d'identifié
 */
TEST_F(UnePersonneValide, reqPersonneFormate)
{
	string afficherNom = "Nom              : ";
	string afficherPrenom = "Prénom           : ";
	string afficherDateNaissance = "Date de naissance: ";
	string afficherTelephone = "Téléphone        : ";

	ostringstream os;
	os << afficherNom << "rouleau" << endl;
	os << afficherPrenom << "jacob" << endl;
	os << afficherDateNaissance << uneDate.reqDateFormatee() << endl;
	os << afficherTelephone << "450 750-3367" << endl;
	ASSERT_EQ(os.str(),f_personne.reqPersonneFormate());
}


/**
 * \brief Test de la méthode bool operator==(const Personne& p_personne) const;
 * 		  Cas valide: Faux si les deux objets sont différents basé sur leur nom, prénom et date de naissance
 * 		  			  Vrai si les deux objets ont le même nom, prénom et date de naissance
 * 		  Cas invalide: Aucun d'identifié
 */
TEST_F(UnePersonneValide, OperateurEgalite)
{
	PersonneDeTest secondePersonne("thibault","sarah",uneDate,"450 750-3367");
	ASSERT_FALSE(f_personne.operator ==(secondePersonne));
	PersonneDeTest personnePareil("rouleau","jacob",uneDate,"450 750-3367");
	ASSERT_TRUE(f_personne.operator ==(personnePareil));
}





