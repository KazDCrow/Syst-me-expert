/**
 *\file Personne.h
 *\brief Fichier qui contient l'interface de la classe Personne qui sert au maintien et à la manipulation des personnes.
 *\author Jacob Rouleau
 *\version 1.0
 *\date Le 27 février 2020
 */
#ifndef PERSONNE_H_
#define PERSONNE_H_
#include <string>
#include "Date.h"

/**
 * \namespace hockey
 */
namespace hockey
{
/**
 * \class Personne
 * \brief Cette classe gère des objets de types Personne créés avec les paramètres en entré qui sont le nom, le prénom,
 * 				la date de naissance et le numero de téléphone.
 *
 * 				Les informations doivent être préalablement vérifié par le programme principal pour éviter tout problème.
 *
 * 				Cette classe permet d'afficher les informations formatées d'une personne,
 * 				de changer le numéro de telephone pour ensuite afficher la modification de maniere formaté
 * 				et elle permet enfin de comparer deux objets personnes grâce à l'operateur d'égalité.
 *
 * 				La validité d'un numéro de téléphone, dan le cas d'un changement, peut être vérifié à l'aide de
 * 				la fonction bool util::validerTelephone(telephone).
 *
 * 				Attributs:	const std::string m_nom: Le nom de la personne
 * 							const std::string m_prenom: Le prénom de la personne
 * 							std::string m_telephone: Le numéro de téléphone de la personne
 * 							const util::Date m_dateNaissance: La date de naissance de la personne
 *
 */
class Personne
{
public:
	Personne(const std::string p_nom, const std::string p_prenom,
			const util::Date p_dateNaissance, const std::string p_telephone);
	virtual ~Personne() {} ;
	std::string reqNom() const;
	std::string reqPrenom() const;
	std::string reqDate() const;
	util::Date reqDateObjet() const;
	std::string reqTelephone() const;

	void asgTelephone(const std::string p_telephone);

	virtual std::string reqPersonneFormate() const;
	virtual Personne* clone() const=0;

	bool operator==(const Personne& p_personne) const;

private:
	void verifieInvariant() const;
	const std::string m_nom;
	const std::string m_prenom;
	std::string m_telephone;
	const util::Date m_dateNaissance;
};
} //namespace hockey

#endif /* PERSONNE_H_ */
