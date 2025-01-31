/**
 *\file Annuaire.h
 *\brief Fichier qui contient l'interface de la classe Annuaire qui sert au maintient et à la manipulation des membres.
 *\author Jacob Rouleau
 *\version 1.0
 *\date Le 14 avril 2020
 */
#include <vector>
#include "Personne.h"
#include <iostream>
#ifndef ANNUAIRE_H_
#define ANNUAIRE_H_

using namespace hockey;

class Annuaire
{
public:
	Annuaire(const std::string p_nomClub);
	Annuaire(const Personne& personneACopier);
	~Annuaire();

	std::string reqNomClub() const;
	void ajouterPersonne (const Personne& p_personne);
	std::string reqAnnuaireFormate();


private:
	const std::string m_nomClub;
	std::vector<Personne*> m_vMembres;

};



#endif /* ANNUAIRE_H_ */
