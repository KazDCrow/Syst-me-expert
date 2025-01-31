/**
 *\file Joueur.h
 *\brief Fichier qui contient l'interface de la classe Joueur qui sert au maintient et à la manipulation des joueurs.
 *\author Jacob Rouleau
 *\version 1.0
 *\date Le 14 avril 2020
 */


#ifndef JOUEUR_H_
#define JOUEUR_H_

#include "Personne.h"
#include "ContratException.h"

using namespace hockey;

class Joueur:public Personne
{
public:
	Joueur(const std::string p_nom, const std::string p_prenom,
				const util::Date p_dateNaissance, const std::string p_telephone, const std::string p_position);
	virtual ~Joueur() {} ;
	std::string reqPosition() const;

	virtual std::string reqPersonneFormate() const;
	virtual Personne* clone() const;

private:
	void verifieInvariant() const;
	const std::string m_position;
};



#endif /* JOUEUR_H_ */
