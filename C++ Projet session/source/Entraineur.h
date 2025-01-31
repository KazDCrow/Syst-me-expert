/**
 *\file Entraineur.h
 *\brief Fichier qui contient l'interface de la classe Entraineur qui sert au maintient et à la manipulation des entraineurs.
 *\author Jacob Rouleau
 *\version 1.0
 *\date Le 14 avril 2020
 */

#ifndef ENTRAINEUR_H_
#define ENTRAINEUR_H_
#include "Personne.h"
#include "ContratException.h"
#include <string>

using namespace hockey;

/**
 * \class Entraineur
 * \brief Cette classe est dérivé de la classe Personne
 *
 * 		  		Cette classe gère des objets de types Entraineur créés avec les paramètres en entré qui sont le nom, le prénom,
 * 				la date de naissance, le numero de téléphone, le numéro de RAMQ et le sexe.
 *
 * 				Les informations sont vérifiées par le contrat
 *
 * 				Cette classe permet d'afficher les informations formatées d'un entraineur,
 * 				de changer le numéro de telephone pour ensuite afficher la modification de maniere formaté
 * 				et elle permet enfin de comparer deux objets personnes grâce à l'operateur d'égalité.
 *
 * 				La validité d'un numéro de téléphone, dan le cas d'un changement, peut être vérifié à l'aide de
 * 				la fonction bool util::validerTelephone(telephone).
 *
 * 				Attributs:	const std::string m_nom: Le nom de l'entraineur
 * 							const std::string m_prenom: Le prénom de l'entraineur
 * 							std::string m_telephone: Le numéro de téléphone de l'entraineur
 * 							const util::Date m_dateNaissance: La date de naissance de l'entraineur
 * 							const std::string& m_numero: Le numéro de RAMQ de l'entraineur
 * 							const char m_sex: Le sexe de l'entraineur
 *
 */
class Entraineur: public Personne
{
public:
	Entraineur(const std::string p_nom, const std::string p_prenom,
			const util::Date p_dateNaissance, const std::string p_telephone, const std::string &p_numero, const char p_sex);

	virtual ~Entraineur() {} ;
	std::string reqNumero() const;
	char reqSex() const;

	virtual std::string reqPersonneFormate() const;
	virtual Personne* clone() const;

private:
	void verifieInvariant() const;
	const std::string m_numero;
	const char m_sex;

};



#endif /* ENTRAINEUR_H_ */
