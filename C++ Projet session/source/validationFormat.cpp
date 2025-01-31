/**
 *\file validationFormat.cpp
 *\brief Implémentation des fonctions de validation
 *\author Jacob Rouleau
 *\version 1.0
 *\date Le 27 février 2020
 */

#include <string>
#include <cctype>
#include <iostream>
#include <algorithm>
#include "validationFormat.h"

using namespace std;
namespace util
{
/**
 * \var const char alphabet[52]
 * \brief Un tableau constant contenant des char représentant l'ensemble des
 * 			lettres de l'alphabet sous leur forme minuscule et majuscule
 */
const char alphabet[52] =
	{ 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h',
			'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N',
			'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T', 'u',
			'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z' };

/**
 * \brief Valide si le numéro en entré est un numéro de téléphone valide
 * \param[in] p_telephone est un string constant qui représente le numéro à valider
 * \return Un booléen true si le numéro est valide et false sinon
 */
bool validerTelephone(const string &p_telephone)
{
	string code_regional[33] =
	{ "403", "780", "604", "236", "250", "778", "902", "204", "506", "905",
			"519", "289", "705", "613", "807", "416", "647", "438", "514",
			"450", "579", "418", "581", "819", "873", "306", "709", "867",
			"800", "866", "877", "888", "855" };

	if (p_telephone.size() != longueur_telephone)
	{
		cout << "Le numero de téléphone est invalide (trop long ou trop court)"
				<< endl;
		return false;
	}
	char separation = '-';
	if (p_telephone[7] != separation)
	{
		cout << "Le numero de téléphone n'est pas au bon format (XXX CCC-CCCC)"<<endl;
		return false;
	}
	char espace = ' ';
	if (p_telephone[3] != espace)
	{
		cout << "Le numero de téléphone n'est pas au bon format (XXX CCC-CCCC)"<<endl;
		return false;
	}
	for (int k = 3; k < 12; k++)
	{
		char numero_telephone = p_telephone[k];

		for (int p = 0; p < 52; p++)
		{
			char lettre = alphabet[p];
			if (lettre == numero_telephone)
			{
				cout << "Le numero de téléphone contient des lettres" << endl;
				return false;
			}
		}
	}
	for (int k = 0; k < 3; k++)
	{
		string numero_telephone;
		numero_telephone.append(p_telephone, 0, 3);

		for (int i = 0; i < 33; i++)
		{
			string code = code_regional[i];
			if (numero_telephone == code)
			{
				return true;
			}
		}

	}

	char numero_telephone = p_telephone[0];
	if (numero_telephone == '9')
	{
		return true;
	}
	cout
			<< "Le numero de telephone est invalide (aucun code régional ne correspond)"
			<< endl;
	return false;
}
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
bool validerNumRAMQ(const string &p_numero, const string &p_nom,
		const string& p_prenom, int p_jourNaissance, int p_moisNaissance,
		int p_anneeNaissance, char p_sex)
{
	unsigned int longueur = 14;
	static string annee_naissance = to_string(p_anneeNaissance);
	int annee = 2;
	int mois = 0;
	int jour = 0;

	struct my_toupper
	{
	    char operator()(char c) const
	    {
	        return std::toupper(static_cast<unsigned char>(c));
	    }
	};

	string nom = p_nom;
	string prenom = p_prenom;
	transform(nom.begin(), nom.end(), nom.begin(), my_toupper());
	transform(prenom.begin(), prenom.end(), prenom.begin(), my_toupper());

	if (p_numero.size() != longueur)
	{
		return false;
	}
	for (int i = 0; i < 14; i++)
	{
		char lettre_numero;
		lettre_numero = p_numero[i];

		if (i < 3)
		{
			char lettre_nom = nom[i];
			if (lettre_nom != lettre_numero)
			{
				cout
						<< "Les premieres lettre de votre numéro ne correspondent pas aux premières de votre nom"
						<< endl;
				return false;

			}
		}
		if (i == 3)
		{
			char lettre_prenom = prenom[0];
			if (lettre_prenom != lettre_numero)
			{
				cout
						<< "La 4e lettre de votre numéro d'assurance social ne correspond pas à la première de votre prénom"
						<< endl;
				return false;
			}
		}
		if (i == 4)
		{
			char espace = ' ';
			if (lettre_numero != espace)
			{
				cout
						<< "Le numero d'assurance social n'est pas au bon format (XXXX XXXX XXXX)"
						<< endl;
				return false;
			}
		}
		if (5 <= i && i < 7)
		{

			char lettre_annee = annee_naissance[annee];
			annee++;
			if (lettre_numero != lettre_annee)
			{
				cout
						<< "L'année de naissance ne concorde pas avec celle du numéro d'assurance social"
						<< endl;
				return false;
			}
		}
		if (7 <= i && i < 9)
		{
			char lettre_mois;
			if (p_sex == 'F')
			{
				string mois_naissance;
				mois_naissance.append(to_string(p_moisNaissance + 50));

				lettre_mois = mois_naissance[mois];
				mois++;
				if (lettre_mois != lettre_numero)
				{
					cout
							<< "Le mois de naissance ne correspond pas au numero d'assurance social"
							<< endl;
					return false;
				}
			}
			else
			{
				if (p_moisNaissance < 10)
				{
					lettre_numero = p_numero[7];
					if (lettre_numero != '0')
					{
						cout << "Les mois ne correspondent pas" << endl;
						return false;
					}
					lettre_numero = p_numero[8];
					string mois = to_string(p_moisNaissance);
					lettre_mois = mois[0];
					if (lettre_numero != lettre_mois)
					{
						cout
								<< "Le mois de naissance ne correspond pas au numero d'assurance social"
								<< endl;
						return false;
					}
				}
				else
				{
					string mois_naissance = to_string(p_moisNaissance);
					lettre_mois = mois_naissance[mois];
					mois++;
					if (lettre_mois != lettre_numero)
					{
						cout
								<< "Le mois de naissance ne correspond pas au numero d'assurance social"
								<< endl;
						return false;
					}
				}
			}
		}
		if (i == 9)
		{
			char espace = ' ';
			if (lettre_numero != espace)
			{
				cout << "Le numero n'est pas au bon format (XXXX XXXX XXXX)"
						<< endl;
				return false;
			}
		}
		if (10 <= i && i < 12)
		{
			char lettre_jour;

			if (p_jourNaissance < 10)
			{
				lettre_numero = p_numero[10];
				if (lettre_numero != '0')
				{
					cout << "Les jours ne correspondent pas" << endl;
					return false;
				}
				lettre_numero = p_numero[11];
				string jour_naissance = to_string(p_jourNaissance);
				lettre_jour = jour_naissance[0];
				if (lettre_jour != lettre_numero)
				{
					cout << "Les jours ne correspondent pas" << endl;
					return false;
				}

			}
			else
			{
				string jour_naissance = to_string(p_jourNaissance);
				lettre_jour = jour_naissance[jour];
				jour++;
				if (lettre_jour != lettre_numero)
				{
					cout << "Les jours ne correspondent pas" << endl;
					return false;
				}

			}

		}
	}
	return true;
}
/**
 *\brief Valide si le paramètre en entré est un nom ou prénom valide soit non vide
 * 			et ne contenant que des lettres
 *\param[in] p_nom un string constant représentant un nom ou un prénom à vérifié
 *\return Un booléen true si le paramètre est valide et false sinon
 */
bool validerFormatNom(const std::string& p_nom)
{
	if (p_nom.size() == 0)
	{
		cout << "Le nom et le prénom ne peuvent être vide" << endl;
		return false;
	}

	unsigned int valide = 0;
	for (unsigned int i = 0; i < p_nom.size(); i++)
	{
		char lettre;
		lettre = p_nom[i];
		int egal =0;

		for (int j = 0; j < 52 && egal != 1; j++)
		{
			if (lettre == alphabet[j])
			{
				egal = 1;
				valide++;
			}
		}
	}
	if(valide == p_nom.size())
	{
		return true;
	}
	cout << "Le nom et le prénom ne peuvent comporter que des lettres"<<endl;
	return false;
}

/**
 *\brief Valide si le paramètre en entré est un nom valide n'etant pas vide
 *\param[in] p_club un string constant représentant un nom de club a verifier
 *\return Un booléen true si le paramètre est valide et false sinon
 */
bool validerFormatNomClub(const std::string& p_club)
{
	if (p_club.size() == 0)
		{
			cout << "Le nom et le prénom ne peuvent être vide" << endl;
			return false;
		}

	return true;
}


/**
 *\brief Valide si le paramètre en entré est un sexe valide soit M ou F
 *\param[in] p_sex un char constant représentant un sexe à vérifier
 *\return Un booléen true si le paramètre est valide et false sinon
 */
bool validerFormatSexe(const char& p_sex)
{
	char masculin = 'M';
	char feminin = 'F';
	if(p_sex == masculin)
	{
		return true;
	}
	if(p_sex == feminin)
	{
		return true;
	}
	else
	{
		cout << "Le sexe est invalide (M ou F)" << endl;
		return false;
	}
}

/**
 *\brief Valide si le paramètre en entré est une position valide
 *\param[in] p_position un string constant représentant une position a verifier
 *\return Un booléen true si le paramètre est valide et false sinon
 */
bool validerPositionJoueur(const std::string& p_position)
{
	string position[4] = {"ailier","centre","défenseur","gardien"};
	for(int i = 0; i < 4; i++)
	{
		if(p_position == position[i])
		{
			return true;
		}
	}
	cout << "La position n'est pas valide (ailier, centre, défenseur ou gardien)" << endl;
	return false;
}

/**
 *\brief Valide si le paramètre en entré est une date de naissance valide pour un joueur
 *\param[in] p_dateNaissance un objet Date a etre verifier
 *\return Un booléen true si le paramètre est valide et false sinon
 */
bool validerAgeJoueur(const util::Date p_dateNaissance)
{
	int moisNaissance = p_dateNaissance.reqMois();
	int jourNaissance = p_dateNaissance.reqJour();
	int anneeNaissance = p_dateNaissance.reqAnnee();
	int anneeMaximum = 2002;
	int anneeMinimum = 2005;
	if(p_dateNaissance.validerDate(jourNaissance,moisNaissance,anneeNaissance) != true)
	{
		cout << "La date de naissance n'est pas valide" << endl;
		return false;
	}
	if(anneeNaissance < anneeMinimum && anneeNaissance > anneeMaximum)
	{
		return true;
	}
	if(anneeNaissance == anneeMaximum)
	{
		if(moisNaissance<4)
		{
			return false;
		}
		if(moisNaissance == 4)
		{
			if(jourNaissance < 15)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		if(moisNaissance>4)
		{
			return true;
		}
	}
	if(anneeNaissance == anneeMinimum)
	{
		if(moisNaissance>4)
		{
			return false;
		}
		if(moisNaissance == 4)
		{
			if(jourNaissance > 15)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		if(moisNaissance < 4)
		{
			return true;
		}
	}
	return false;
}
} // namespace util


