package affichage;
import javax.swing.*;

import modele.Administrateur;
import modele.Armee;
import modele.BDHumain;
import modele.EmployeMaintenance;
import modele.ForceSecurite;
import modele.Gestionnaire;
import modele.Humain;
import modele.Ingenieur;
import modele.Milice;
import modele.Ouvrier;
import modele.Scientifique;
import modele.pkgDate.ClDate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Vue extends JFrame implements ActionListener
{
    protected JPanel centre;
    protected JButton retourAccueil;
    protected JButton survivantSuivant;
    protected int indexSurvivantAfficher = 0;
    protected JButton survivantPrecedent;
    protected JMenuBar mBarMenu;
    protected JMenu mEditer;
    protected JMenu mAjouter;
    protected JMenuItem miAjouterForce;
    protected JMenuItem miAjouterMaintenance;
    protected JMenuItem miAjouterGestionnaire;
    protected JMenuItem miModifier;
    protected JMenuItem miSupprimer;
    protected JMenu mAfficher;
    protected JMenuItem miSurvivantPrecis;
    protected JMenuItem miSecurite;
    protected JMenuItem miMaintenance;
    protected JMenuItem miGestionnaire;
    protected JButton bChoix1;
    protected JButton bChoix2;
    protected JPanel pMenuLeft;
    protected BackgroundPanel pCenter;
    protected JLabel lNbrSurvivants;
    protected JLabel lNbrEmployeMaintenance;
    protected JLabel lNbrForceSecurite;
    protected JLabel lNbrGestionnaire;
    protected JLabel lNbrDeces;
    protected BDHumain bd;
    protected JPanel pFormCenter;
    protected JTextField nom;
    protected JTextField prenom;
    protected JTextField matricule;
    protected JTextField sexe;
    protected JTextField annee;
    protected JTextField mois;
    protected JTextField jour;
    protected JTextField grade;
    protected JTextField arme;
    protected JTextField poste;
    protected JTextField service;
    protected JTextField secteur;
    protected JTextField sorti;
    protected JTextField victime;
    protected JTextField subordonne1;
    protected JTextField subordonne2;
    protected JTextField subordonne3;
    protected JTextField subordonne4;
    protected JTextField subordonne5;
    protected JTextField plainte;
    protected JTextField[] subordonnes = {subordonne1, subordonne2, subordonne3, subordonne4, subordonne5};
    protected int horaire;
    protected JTextField typeTravail;
    protected JTextField nbrSecteurDispo;
    protected char choix;
    protected JPanel pAffichageSurvivant;
    protected ArrayList<Humain> listePourAfficher;
    protected JLabel typeAffichage;
    protected JMenuItem miTous;
    protected JTextField specialite;
    protected JTextField niveau;
    protected JButton bAjouterSurvivant;
    protected JButton annulerAjout;
    protected JPanel dernierConteneur; 
    protected JTextField anneeEtude;
    protected JTextField moisEtude;
    protected JTextField jourEtude;
    protected JTextField nbEmploye;
    protected JTextField projet1;
    protected JTextField projet2;
    protected JTextField projet3;
    protected JTextField projetAffecter;
    protected JTextField titreAdmin;
    protected JTextField nomChercher;
    protected JTextField matriculeChercher;
    protected String choixRecherche;
    protected boolean supprimer = false;
    protected boolean modifier = false;
    protected JButton supprimerButton;
    protected JButton modifierButton;
    protected JTextArea survivant;
    JMenu mFile;
    JMenuItem mSauvegarderSecurite;
    JMenuItem mSauvegarderMaintenance;
    JMenuItem mSauvegarderGestionnaire;


    public Vue(BDHumain bd)
    {
        this.bd = bd;
        bd.chargementComplet();
        this.setSize(1150,700);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() 
        {
            public void windowClosing(java.awt.event.WindowEvent e) 
            {
                sauvegarderTout();
            }
        });
        draw();
    }

    //permet d'obtenir les information sur le nombre de résident du bunker dans les différentes catégories
    private void setNbrSurvivant()
    {
        lNbrSurvivants.setText("Survivant(s) : " + bd.getNbrSurvivant());
        lNbrEmployeMaintenance.setText("Maintenance(s) : " + bd.getNbrMaintenance());
        lNbrForceSecurite.setText("Sécurité(s) : " + bd.getNbrForceSecuriter());
        lNbrGestionnaire.setText("Gestionnaire(s) : " + bd.getNbrGestionnaire());
        lNbrDeces.setText("Décédé(s) : "+ bd.getNbrMort());
    }

    private void draw()
    {
        this.getContentPane().removeAll();
        drawMenu();
        pMenuLeft = new JPanel();

        drawLeftPanel();
        drawCenter();
        this.setJMenuBar(mBarMenu);
        this.add(pMenuLeft, BorderLayout.WEST);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    private void sauvegardeSecurite()
    {
        bd.sauvegardeListeSecurite();
    }

    private void sauvegardeMaintenance()
    {
        bd.sauvegardeListeMaintenance();
    }

    private void sauvegardeGestion()
    {
        bd.sauvegardeListeGestionnaire();
    }

    //permet d'afficher le menu se trouvant en haut de la fenetre
    private void drawMenu()
    {

        mBarMenu = new JMenuBar();
        mFile = new JMenu("Fichier");
        mSauvegarderSecurite = new JMenuItem("Sauvegarder liste securité");
        mSauvegarderSecurite.addActionListener(this);

        mSauvegarderMaintenance = new JMenuItem("Sauvegarder liste maintenance");
        mSauvegarderMaintenance.addActionListener(this);

        mSauvegarderGestionnaire = new JMenuItem("Sauvegarder liste gestionnaire");
        mSauvegarderGestionnaire.addActionListener(this);

        mFile.add(mSauvegarderSecurite);
        mFile.add(mSauvegarderMaintenance);
        mFile.add(mSauvegarderGestionnaire);

        mEditer = new JMenu("Édition des survivants");

        mAjouter = new JMenu("Ajouter un survivant");
        miAjouterForce = new JMenuItem("des forces de sécurité");
        miAjouterGestionnaire = new JMenuItem("gestionnaire");
        miAjouterMaintenance = new JMenuItem("employé de maintenance");
        mAjouter.add(miAjouterForce);
        mAjouter.add(miAjouterGestionnaire);
        mAjouter.add(miAjouterMaintenance);

        miAjouterForce.addActionListener(this);
        miAjouterGestionnaire.addActionListener(this);
        miAjouterMaintenance.addActionListener(this);

        miModifier = new JMenuItem("Modifier un survivant");
        miModifier.addActionListener(this);

        miSupprimer = new JMenuItem("Supprimer un survivant");
        miSupprimer.addActionListener(this);

        mEditer.add(mAjouter);
        mEditer.add(miModifier);
        mEditer.add(miSupprimer);

        mAfficher = new JMenu("Affichage des survivants");

        miSurvivantPrecis = new JMenuItem("Afficher surviant(s) précis");
        miSurvivantPrecis.addActionListener(this);

        miSecurite = new JMenuItem("Afficher les forces de sécurité");
        miSecurite.addActionListener(this);

        miGestionnaire = new JMenuItem("Afficher les gestionnaires");
        miGestionnaire.addActionListener(this);

        miMaintenance = new JMenuItem("Afficher les employés de maintenance");
        miMaintenance.addActionListener(this);

        miTous = new JMenuItem("Afficher tous les survivants");
        miTous.addActionListener(this);

        mAfficher.add(miSurvivantPrecis);
        mAfficher.add(miSecurite);
        mAfficher.add(miGestionnaire);
        mAfficher.add(miMaintenance);
        mAfficher.add(miTous);

        mBarMenu.add(mFile);
        mBarMenu.add(mEditer);
        mBarMenu.add(mAfficher);
    }

    //permet d'afficher le nombre de résident du bunker dans différentes catégories
    private void drawLeftPanel()
    {
        try 
        {
            pMenuLeft.removeAll();
        } catch (Exception e) {
        }
        lNbrSurvivants = new JLabel();
        lNbrEmployeMaintenance = new JLabel();
        lNbrForceSecurite = new JLabel();
        lNbrGestionnaire = new JLabel();
        lNbrDeces = new JLabel();

        setNbrSurvivant();

        pMenuLeft.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.LIGHT_GRAY));
        pMenuLeft.setPreferredSize(new Dimension(150,10));
        pMenuLeft.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        pMenuLeft.add(lNbrSurvivants);
        pMenuLeft.add(lNbrForceSecurite);
        pMenuLeft.add(lNbrEmployeMaintenance);
        pMenuLeft.add(lNbrGestionnaire);
        pMenuLeft.add(lNbrDeces);
        pMenuLeft.revalidate();
        pMenuLeft.repaint();
    }

    private void drawCenter()
    {
        pCenter = new BackgroundPanel();
        pCenter.setLayout(new BorderLayout());
        this.add(pCenter);
    }

    private void getListeAfficherNom()
    {
        listePourAfficher = new ArrayList<Humain>();

        ArrayList<Humain> temporaire = bd.afficherResidentAvecNom(nomChercher.getText());
        if (temporaire.size() >= 1) 
        {
            for (Humain personne : temporaire) 
            {
                listePourAfficher.add(personne);
            }
        }
                
        
        bd.resetNomDejaChercher();
        Collections.sort(listePourAfficher);
    }

    private void getListeAfficherMatricule()
    {
        listePourAfficher = new ArrayList<Humain>();

        ArrayList<Humain> temporaire = bd.afficherResident(matriculeChercher.getText());
        if (temporaire.size() > 1) 
        {
            for (Humain personne : temporaire) 
            {
                listePourAfficher.add(personne);
            }
        }
                

        bd.resetMatriculeDejaChercher();
        Collections.sort(listePourAfficher);
    }

    //permet d'obtenir la liste des survivants à afficher en fonction des matricules en entrée
    private void getListeAfficher(ArrayList<String> listeResident)
    {
        listePourAfficher = new ArrayList<Humain>();
        if (listeResident.size() != 0) 
        {
            for (String _matricule : listeResident) 
            {
                ArrayList<Humain> temporaire = bd.afficherResident(_matricule);
                if (temporaire.size() > 1) 
                {
                    for (Humain personne : temporaire) 
                    {
                        listePourAfficher.add(personne);
                    }
                }
                //Si le matricule a déjà été chercher une fois, le retour sera une liste vide c'est pour ça qu'on doit valider ici que ce n'est pas le cas
                else if(temporaire.size() != 0)
                {
                    listePourAfficher.add(temporaire.get(0));
                }
            }
            bd.resetMatriculeDejaChercher();
            Collections.sort(listePourAfficher);
        }
    }

    //affiche les survivants et leur information de la liste des survivants à afficher
    private void afficherSurvivant()
    {
        try 
        {
            this.remove(pCenter);
        } catch (Exception e) {}
        try 
        {
            this.remove(centre);
        } catch (Exception e) {}
        try 
        {
            this.remove(pFormCenter);
        } catch (Exception e) {}
        JPanel affichageSurvivant = new JPanel(new BorderLayout());

        centre = new JPanel(new BorderLayout());
        pAffichageSurvivant = new JPanel(new GridBagLayout());

        JPanel pPrecSuiv = new JPanel();
        JPanel pBoutons = new JPanel(new BorderLayout());
        retourAccueil = new JButton("Retour");
        retourAccueil.addActionListener(this);
        retourAccueil.setFocusable(false);

        survivantSuivant = new JButton("Suivant");
        survivantSuivant.setFocusable(false);
        survivantSuivant.addActionListener(this);

        survivantPrecedent = new JButton("Précedent");
        survivantPrecedent.addActionListener(this);

        survivantPrecedent.setFocusable(false);

        pPrecSuiv.add(survivantPrecedent);
        pPrecSuiv.add(survivantSuivant);
        pBoutons.add(pPrecSuiv, BorderLayout.NORTH);
        pBoutons.add(retourAccueil);
        survivant = new JTextArea();
        if (listePourAfficher.size() == 0) 
        {
            survivant.setText("Aucun survivant à afficher");
        }
        else
        {
            survivant.setText(listePourAfficher.get(indexSurvivantAfficher).toString());
        }

        
        survivant.setBackground(null);
        survivant.setFont(new Font("Arial Black", Font.BOLD, 15));
        affichageSurvivant.add(survivant, BorderLayout.NORTH);


        pAffichageSurvivant.add(affichageSurvivant);
        JLabel numeroSurvivant = new JLabel("Survivant #"+(indexSurvivantAfficher+1));
        numeroSurvivant.setFont(new Font("Arial Black", Font.BOLD, 15));
        JPanel typeNumero = new JPanel(new BorderLayout());
        typeNumero.add(typeAffichage, BorderLayout.NORTH);
        typeNumero.add(numeroSurvivant, BorderLayout.SOUTH);

        centre.add(typeNumero, BorderLayout.NORTH);
        centre.add(pAffichageSurvivant, BorderLayout.CENTER);
        JPanel pSupprimer = new JPanel(new BorderLayout());
        JPanel pModifier = new JPanel(new BorderLayout());
        if (supprimer) 
        {
            supprimerButton = new JButton("Supprimer le survivant");
            supprimerButton.setFocusable(false);
            supprimerButton.addActionListener(this);
            pSupprimer.add(supprimerButton, BorderLayout.SOUTH);
            pSupprimer.add(pBoutons, BorderLayout.NORTH);
            centre.add(pSupprimer, BorderLayout.SOUTH);
        }
        else
        {
            if (modifier) 
            {
                modifierButton = new JButton("Modifier ce survivant");
                modifierButton.setFocusable(false);
                modifierButton.addActionListener(this);
                pModifier.add(modifierButton, BorderLayout.SOUTH);
                pModifier.add(pBoutons, BorderLayout.NORTH);
                centre.add(pModifier, BorderLayout.SOUTH);
            }
            else
            {
                centre.add(pBoutons, BorderLayout.SOUTH);
            }
        }
        this.add(centre, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();  
    }

    private Component ajouterAdministrateur(Component conteneur)
    {
        pFormCenter = new JPanel(new GridBagLayout());
        JPanel projet_Titre = new JPanel();
        JPanel conteneurFinal = new JPanel(new BorderLayout());

        JPanel pAffecter = new JPanel();
        JLabel lProjetAffecter = new JLabel("Projet affecte");
        projetAffecter = new JTextField(10);
        pAffecter.add(lProjetAffecter);
        pAffecter.add(projetAffecter);

        JPanel ptitre = new JPanel();
        JLabel lTitre = new JLabel("Titre de gestionnaire");
        titreAdmin = new JTextField(10);
        ptitre.add(lTitre);
        ptitre.add(titreAdmin);

        projet_Titre.add(pAffecter);
        projet_Titre.add(ptitre);

        conteneurFinal.add(conteneur, BorderLayout.NORTH);
        conteneurFinal.add(projet_Titre, BorderLayout.SOUTH);
        pFormCenter.add(conteneurFinal);

        return pFormCenter;
    }

    private Component ajouterScientifique(Component conteneur)
    {
        pFormCenter = new JPanel(new GridBagLayout());
        JPanel contneurfinal = new JPanel(new BorderLayout());
        JPanel conteneurProjets = new JPanel();

        JPanel pPorjet1 = new JPanel(new BorderLayout());
        JPanel pProjet1Centrer = new JPanel(new GridBagLayout());
        JLabel lProjet1 = new JLabel("Nom du premier projet");
        projet1 = new JTextField(10);
        pPorjet1.add(lProjet1, BorderLayout.NORTH);
        pPorjet1.add(projet1, BorderLayout.SOUTH);
        pProjet1Centrer.add(pPorjet1);

        JPanel pPorjet2 = new JPanel(new BorderLayout());
        JPanel pProjet2Centrer = new JPanel(new GridBagLayout());
        JLabel lProjet2 = new JLabel("Nom du deuxieme projet");
        projet2 = new JTextField(10);
        pPorjet2.add(lProjet2, BorderLayout.NORTH);
        pPorjet2.add(projet2, BorderLayout.SOUTH);
        pProjet2Centrer.add(pPorjet2);

        JPanel pPorjet3 = new JPanel(new BorderLayout());
        JPanel pProjet3Centrer = new JPanel(new GridBagLayout());
        JLabel lProjet3 = new JLabel("Nom du troisieme projet");
        projet3 = new JTextField(10);
        pPorjet3.add(lProjet3, BorderLayout.NORTH);
        pPorjet3.add(projet3, BorderLayout.SOUTH);
        pProjet3Centrer.add(pPorjet3);

        conteneurProjets.add(pProjet1Centrer);
        conteneurProjets.add(pProjet2Centrer);
        conteneurProjets.add(pProjet3Centrer);

        contneurfinal.add(conteneur, BorderLayout.NORTH);
        contneurfinal.add(conteneurProjets, BorderLayout.SOUTH);
        pFormCenter.add(contneurfinal);
        return pFormCenter;
    }

    private void ajouterBoutonsSoumission(Component conteneur)
    {
        dernierConteneur = new JPanel(new BorderLayout());
        JPanel conteneurBoutons = new JPanel();

        bAjouterSurvivant = new JButton("Ajouter");
        bAjouterSurvivant.setFocusable(false);
        bAjouterSurvivant.addActionListener(this);

        annulerAjout = new JButton("Annuler");
        annulerAjout.setFocusable(false);
        annulerAjout.addActionListener(this);

        conteneurBoutons.add(annulerAjout);
        conteneurBoutons.add(bAjouterSurvivant);

        dernierConteneur.add(conteneur, BorderLayout.CENTER);
        dernierConteneur.add(conteneurBoutons, BorderLayout.SOUTH);

        this.add(dernierConteneur, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    private Component ajouterIngenieur(Component pConteneur)
    {
    /*protected String specialite;
    protected ClDate dateFinEtude;
    protected String niveauEtude; */
    pFormCenter = new JPanel(new GridBagLayout());

    JPanel specialiteNiveau = new JPanel();
    JPanel pSpecialite = new JPanel();
    JLabel lSpecialite = new JLabel("Spécialité");
    specialite = new JTextField(10);

    pSpecialite.add(lSpecialite);
    pSpecialite.add(specialite);
    specialiteNiveau.add(pSpecialite);

    JPanel pConteneurDate = new JPanel(new BorderLayout());
    JLabel lTitreDate = new JLabel("Date de fin d'étude :");
    lTitreDate.setHorizontalAlignment(JLabel.CENTER);
    pConteneurDate.add(lTitreDate, BorderLayout.NORTH);

    JPanel pDate = new JPanel();
    JPanel pAnnee = new JPanel();
    JLabel lAnnee = new JLabel("Année");
    anneeEtude = new JTextField(10);
    pAnnee.add(lAnnee);
    pAnnee.add(annee);

    JPanel pMois = new JPanel();
    JLabel lMois = new JLabel("Mois");
    moisEtude = new JTextField(10);
    pMois.add(lMois);
    pMois.add(mois);

    JPanel pJour = new JPanel();
    JLabel lJour = new JLabel("Jours");
    jourEtude = new JTextField(10);
    pJour.add(lJour);
    pJour.add(jour);
    pDate.add(pAnnee);
    pDate.add(pMois);
    pDate.add(pJour);
    pConteneurDate.add(pDate, BorderLayout.SOUTH);


    JPanel pNiveau = new JPanel();
    JLabel lNiveau = new JLabel("Niveau d'étude");
    niveau = new JTextField(10);

    pNiveau.add(lNiveau);
    pNiveau.add(niveau);
    specialiteNiveau.add(pNiveau);

    JPanel conteneurInfo = new JPanel(new BorderLayout());
    conteneurInfo.add(specialiteNiveau, BorderLayout.NORTH);
    conteneurInfo.add(pConteneurDate, BorderLayout.SOUTH);

    JPanel conteneurFin = new JPanel(new BorderLayout());
    conteneurFin.add(pConteneur, BorderLayout.NORTH);
    conteneurFin.add(conteneurInfo, BorderLayout.SOUTH);
    
    JLabel titre = new JLabel("Ajout d'un survivant ingénieur");
    titre.setHorizontalAlignment(JLabel.CENTER);
    titre.setFont(new Font("Arial Black", Font.BOLD, 15));

    JPanel conteneurFinal = new JPanel(new BorderLayout());
    conteneurFinal.add(titre, BorderLayout.NORTH);
    conteneurFinal.add(conteneurFin);
    
    pFormCenter.add(conteneurFinal);
    return pFormCenter; 

    }

    private Component ajouterOuvrier(Component pConteneur)
    {
        pFormCenter = new JPanel(new GridBagLayout());
        JPanel pHoraire = new JPanel(new BorderLayout());
        JLabel lHoraire = new JLabel("Horaire de travail du survivant");
        lHoraire.setHorizontalAlignment(JLabel.CENTER);
        pHoraire.add(lHoraire, BorderLayout.NORTH);

        ButtonGroup boutons = new ButtonGroup();
        JPanel pBoutons = new JPanel();
        JRadioButton horaire1 = new JRadioButton("minuit à 8h00");
        horaire1.addActionListener(e -> horaire = 0);
        JRadioButton horaire2 = new JRadioButton("8h00 à 16h00");
        horaire2.addActionListener(e -> horaire = 1);
        JRadioButton horaire3 = new JRadioButton("16h00 à minuit");
        horaire3.addActionListener(e -> horaire = 2);
        boutons.add(horaire1);
        boutons.add(horaire2);
        boutons.add(horaire3);

        pBoutons.add(horaire1);
        pBoutons.add(horaire2);
        pBoutons.add(horaire3);

        pHoraire.add(pBoutons, BorderLayout.SOUTH);

        JPanel pTravailSecteur = new JPanel();

        JPanel ptravail = new JPanel();
        JLabel lTravail = new JLabel("Type de travail/tâche");
        typeTravail = new JTextField(10);
        ptravail.add(lTravail);
        ptravail.add(typeTravail);

        JPanel pSecteurDispo = new JPanel();
        JLabel lSecteurDispo = new JLabel("Nombre de secteur accessible");
        nbrSecteurDispo = new JTextField(10);
        pSecteurDispo.add(lSecteurDispo);
        pSecteurDispo.add(nbrSecteurDispo);

        pTravailSecteur.add(ptravail);
        pTravailSecteur.add(pSecteurDispo);

        JPanel conteneurInfo = new JPanel(new BorderLayout());
        conteneurInfo.add(pHoraire, BorderLayout.NORTH);
        conteneurInfo.add(pTravailSecteur, BorderLayout.SOUTH);

        JPanel conteneurFin = new JPanel(new BorderLayout());
        conteneurFin.add(pConteneur, BorderLayout.NORTH);
        conteneurFin.add(conteneurInfo, BorderLayout.SOUTH);

        JLabel titre = new JLabel("Ajout d'un survivant ingénieur");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setFont(new Font("Arial Black", Font.BOLD, 15));

        JPanel conteneurFinal = new JPanel(new BorderLayout());
        conteneurFinal.add(titre, BorderLayout.NORTH);
        conteneurFinal.add(conteneurFin);
        
        pFormCenter.add(conteneurFinal);
        return pFormCenter;
    }

    private Component ajouterMilice(Component pConteneur)
    {
        pFormCenter = new JPanel(new GridBagLayout());
        JPanel pSecteur = new JPanel();
        JLabel lPlainte = new JLabel("Nombre de plainte contre le survivant");
        plainte = new JTextField(10);
        pSecteur.add(lPlainte);
        pSecteur.add(plainte);

        JPanel conteneur = new JPanel(new BorderLayout());
        conteneur.add(pConteneur, BorderLayout.NORTH);
        conteneur.add(pSecteur, BorderLayout.SOUTH);

        JLabel titre = new JLabel("Ajout d'un survivant faisant partie de la milice.");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setFont(new Font("Arial Black", Font.BOLD, 15));

        JPanel conteneurFin = new JPanel(new BorderLayout());
        conteneurFin.add(titre, BorderLayout.NORTH);
        conteneurFin.add(conteneur, BorderLayout.SOUTH);
        pFormCenter.add(conteneurFin);
        this.add(pFormCenter, BorderLayout.CENTER);
        return pFormCenter;
    }

    private Component ajouterArmee(Component pConteneur)
    {
        pFormCenter = new JPanel(new GridBagLayout());
        JPanel pConteneur0 = new JPanel(new BorderLayout());
        pConteneur0.add(pConteneur, BorderLayout.NORTH);

        JPanel	pConteneur1 = new JPanel(new BorderLayout());

        JPanel pSortiVictime = new JPanel();

        JPanel pSorti = new JPanel();
        JLabel lSorti = new JLabel("Nombre de sorti à l'extérieur");
        sorti = new JTextField(10);
        pSorti.add(lSorti);
        pSorti.add(sorti);

        JPanel pVictime = new JPanel();
        JLabel lVictime = new JLabel("Nombre total de zombie tué");
        victime = new JTextField(10);
        pVictime.add(lVictime);
        pVictime.add(victime);
        pSortiVictime.add(pSorti);
        pSortiVictime.add(pVictime);

        JPanel pSubordonnee1 = new JPanel();
        JPanel pSubordonnee2 = new JPanel();
        JPanel pSubordonnee3 = new JPanel();
        JPanel pSubordonnee4 = new JPanel(new BorderLayout());
        JPanel pSubordonneFinal = new JPanel(new BorderLayout());
        
        for (int i = 0; i < 5; i++) 
        {
            JPanel pSub = new JPanel();
            JLabel lSubordonne = new JLabel("Subordonné " + (i+1));
            pSub.add(lSubordonne);
            if (i == 0 || i == 1) 
            {
                if (i == 0) 
                {
                    subordonne1 = new JTextField(10);
                    pSub.add(subordonne1);
                }
                else
                {
                    subordonne2 = new JTextField(10);
                    pSub.add(subordonne2);
                }
                pSubordonnee1.add(pSub);
            }
            else if (i == 2 || i == 3) 
            {
                if (i == 0) 
                {
                    subordonne3 = new JTextField(10);
                    pSub.add(subordonne3);
                }
                else
                {
                    subordonne4 = new JTextField(10);
                    pSub.add(subordonne4);
                }
                pSubordonnee2.add(pSub); 
            }
            else
            {
                subordonne5 = new JTextField(10);
                pSub.add(subordonne5);
                pSubordonnee3.add(pSub);
            }
        }

        pSubordonnee4.add(pSubordonnee1, BorderLayout.NORTH);
        pSubordonnee4.add(pSubordonnee2,BorderLayout.SOUTH);
        pSubordonneFinal.add(pSubordonnee4, BorderLayout.NORTH);
        pSubordonneFinal.add(pSubordonnee3, BorderLayout.SOUTH);
        JLabel lSubordonneDemande = new JLabel("Veuillez entrer les matricules des subordonnés du survivant s'il/elle en a.");
        lSubordonneDemande.setHorizontalAlignment(JLabel.CENTER);
        JPanel pFinal = new JPanel(new BorderLayout());
        pFinal.add(lSubordonneDemande, BorderLayout.NORTH);
        pFinal.add(pSubordonneFinal, BorderLayout.SOUTH);

        pConteneur1.add(pSortiVictime, BorderLayout.NORTH);
        pConteneur1.add(pFinal, BorderLayout.SOUTH);
        pConteneur0.add(pConteneur1, BorderLayout.SOUTH);
        JLabel titre = new JLabel("Ajout d'un survivant faisant partie de l'armée.");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setFont(new Font("Arial Black", Font.BOLD, 15));
        JPanel conteneurFin = new JPanel(new BorderLayout());
        conteneurFin.add(titre, BorderLayout.NORTH);
        conteneurFin.add(pConteneur0, BorderLayout.SOUTH);
        pFormCenter.add(conteneurFin);
        return pFormCenter;
    }

    private Component ajouterGestionnaire(Component pConteneur)
    {
        JPanel employe = new JPanel();
        JLabel lNbrEmploye = new JLabel("Nombre d'employe");
        nbEmploye = new JTextField(10);
        employe.add(lNbrEmploye);
        employe.add(nbEmploye);

        JPanel pSecteur = new JPanel();
        JLabel lSecteur = new JLabel("Nom du secteur affecte");
        secteur = new JTextField(10);
        pSecteur.add(lSecteur);
        pSecteur.add(secteur);

        JPanel empSect = new JPanel();
        empSect.add(employe);
        empSect.add(pSecteur);

        JPanel conteneurJPanel = new JPanel(new BorderLayout());
        conteneurJPanel.add(pConteneur, BorderLayout.NORTH);
        conteneurJPanel.add(empSect, BorderLayout.SOUTH);
        return conteneurJPanel;
    }

    private Component ajouterMaintenance(Component pConteneur)
    {
        JPanel pSecteur = new JPanel();
        JLabel lSecteur = new JLabel("Secteur de travail");
        secteur = new JTextField(10);
        pSecteur.add(lSecteur);
        pSecteur.add(secteur);

        JPanel conteneur = new JPanel(new BorderLayout());
        conteneur.add(pConteneur, BorderLayout.NORTH);
        conteneur.add(pSecteur, BorderLayout.SOUTH);

        return conteneur;
    }

    private Component ajouterSecurite(Component pConteneur)
    {
        JPanel pConteneur0 = new JPanel(new BorderLayout());
        pConteneur0.add(pConteneur, BorderLayout.NORTH);

        JPanel pSecurite = new JPanel(new BorderLayout());

        JPanel pConteneur1 = new JPanel();

        JPanel pGrade = new JPanel();
        JLabel lGrade = new JLabel("Grade");
        grade = new JTextField(10);
        pGrade.add(lGrade);
        pGrade.add(grade);

        JPanel pArme = new JPanel();
        JLabel lArme = new JLabel("Arme");
        arme = new JTextField(10);
        pArme.add(lArme);
        pArme.add(arme);

        pConteneur1.add(pGrade);
        pConteneur1.add(pArme);


        JPanel pConteneur2 = new JPanel();
        JPanel pPoste = new JPanel();
        JLabel lPoste = new JLabel("Poste");
        poste = new JTextField(10);
        pPoste.add(lPoste);
        pPoste.add(poste);

        JPanel pService = new JPanel();
        JLabel lService = new JLabel("Année de service");
        service = new JTextField(10);
        pService.add(lService);
        pService.add(service);

        pConteneur2.add(pPoste);
        pConteneur2.add(pService);

        pSecurite.add(pConteneur1, BorderLayout.NORTH);
        pSecurite.add(pConteneur2, BorderLayout.SOUTH);
        pConteneur0.add(pSecurite, BorderLayout.SOUTH);

        
        return pConteneur0;
    }

    private Component ajouterSurvivant()
    {
        //Information de base que tous les survivants ont
        this.remove(pCenter);
        JPanel pConteneur = new JPanel(new BorderLayout());
        JPanel pConteneur2 = new JPanel(new BorderLayout());

        JPanel pNomComplet = new JPanel();
        JPanel pNom = new JPanel();
        JLabel lNom = new JLabel("Nom");
        nom = new JTextField(10);
        pNom.add(lNom);
        pNom.add(nom);

        JPanel pPrenom = new JPanel();
        JLabel lPrenom = new JLabel("Prenom");
        prenom = new JTextField(10);
        pPrenom.add(lPrenom);
        pPrenom.add(prenom);
        pNomComplet.add(pPrenom);
        pNomComplet.add(pNom);
        pConteneur2.add(pNomComplet, BorderLayout.NORTH);

        JPanel pMatSexe = new JPanel();
        JPanel pMatricule = new JPanel();
        JLabel lMatricule = new JLabel("Matricule");
        matricule = new JTextField(10);
        pMatricule.add(lMatricule);
        pMatricule.add(matricule);

        JPanel pSexe = new JPanel();
        JLabel lSexe = new JLabel("Sexe");
        sexe = new JTextField(10);
        pSexe.add(lSexe);
        pSexe.add(sexe);
        pMatSexe.add(pMatricule);
        pMatSexe.add(pSexe);
        pConteneur2.add(pMatSexe, BorderLayout.SOUTH);

        JPanel pConteneurDate = new JPanel(new BorderLayout());
        JLabel lTitreDate = new JLabel("Date de naissance :");
        lTitreDate.setHorizontalAlignment(JLabel.CENTER);
        pConteneurDate.add(lTitreDate, BorderLayout.NORTH);

        JPanel pDate = new JPanel();
        JPanel pAnnee = new JPanel();
        JLabel lAnnee = new JLabel("Année");
        annee = new JTextField(10);
        pAnnee.add(lAnnee);
        pAnnee.add(annee);

        JPanel pMois = new JPanel();
        JLabel lMois = new JLabel("Mois");
        mois = new JTextField(10);
        pMois.add(lMois);
        pMois.add(mois);

        JPanel pJour = new JPanel();
        JLabel lJour = new JLabel("Jours");
        jour = new JTextField(10);
        pJour.add(lJour);
        pJour.add(jour);
        pDate.add(pAnnee);
        pDate.add(pMois);
        pDate.add(pJour);
        pConteneurDate.add(pDate, BorderLayout.SOUTH);

        pConteneur.add(pConteneur2, BorderLayout.NORTH);
        pConteneur.add(pConteneurDate, BorderLayout.SOUTH);

        return pConteneur;
    }

    private void choixTypeSurvivant()
    {
        try 
        {
            this.remove(dernierConteneur);
            this.add(pCenter, BorderLayout.CENTER);
        } catch (Exception e) 
        {
        }
        try 
        {
            this.remove(centre);
            this.add(pCenter, BorderLayout.CENTER);
        } catch (Exception e) {
        }

        try 
        {
            this.remove(pAffichageSurvivant);
            this.add(pCenter, BorderLayout.CENTER);
        
        } catch (Exception e) {}
        pCenter.removeAll();
        bChoix1 = new JButton();
        bChoix2 = new JButton();
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titre = new JLabel();
        titre.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titre,BorderLayout.NORTH);
        JPanel panelBouton = new JPanel();
        panelBouton.add(bChoix1);
        panelBouton.add(bChoix2);
        panel.add(panelBouton, BorderLayout.SOUTH);
        
        switch (choix) 
        {
            case 'f':
                titre.setText("Le survivant fait partie de : ");
                bChoix1.setText("Armé du bunker");
                bChoix1.addActionListener(e -> 
                {
                    ajouterBoutonsSoumission(ajouterArmee(ajouterSecurite(ajouterSurvivant())));
                    choix = 'a';
                });
                bChoix2.setText("Milice Du bunker");
                bChoix2.addActionListener(e -> 
                {
                    ajouterBoutonsSoumission(ajouterMilice(ajouterSecurite(ajouterSurvivant())));
                    choix = 'm';
                });
                break;

            case 'm':
                titre.setText("Le survivant est un : ");
                bChoix1.setText("Ouvrier du bunker");
                bChoix1.addActionListener(e -> 
                {
                    ajouterBoutonsSoumission(ajouterOuvrier(ajouterMaintenance(ajouterSurvivant())));
                    choix = 'o';
                });
                bChoix2.setText("Ingénieur du bunker");
                bChoix2.addActionListener(e -> 
                {
                    ajouterBoutonsSoumission(ajouterIngenieur(ajouterMaintenance(ajouterSurvivant())));
                    choix = 'i';
                });
                break;

            case 'g':
                titre.setText("Le survivant est un : ");
                bChoix1.setText("Scientifique du bunker du bunker");
                bChoix1.addActionListener(e-> 
                {
                    ajouterBoutonsSoumission(ajouterScientifique(ajouterGestionnaire(ajouterSurvivant())));
                    choix = 's';
                });
                bChoix2.setText("Administrateur du bunker");
                bChoix2.addActionListener(e -> 
                {
                    choix = 'd';
                    ajouterBoutonsSoumission(ajouterAdministrateur(ajouterGestionnaire(ajouterSurvivant())));
                });
                break;
                
            default:
                break;
        }
        pCenter.add(panel, BorderLayout.NORTH);
        pCenter.revalidate();
        pCenter.repaint();
    }

    private void confirmerAjout(String _matricule)
    {
        pFormCenter = new JPanel(new GridBagLayout());
        JPanel confirmationPanel = new JPanel(new BorderLayout());
        JPanel conteneurLabel = new JPanel(new BorderLayout());
        JLabel lConfirmation = new JLabel("Le suvivant a bien ete ajoute. Son matricule est le :");
        lConfirmation.setHorizontalAlignment(JLabel.CENTER);
        lConfirmation.setFont(new Font("Arial Black", Font.BOLD, 15));
        JLabel matriculeLabel = new JLabel(_matricule);
        matriculeLabel.setHorizontalAlignment(JLabel.CENTER);
        matriculeLabel.setFont(new Font("Arial Black", Font.BOLD, 15));

        conteneurLabel.add(lConfirmation, BorderLayout.NORTH);
        conteneurLabel.add(matriculeLabel, BorderLayout.CENTER);

        confirmationPanel.add(conteneurLabel, BorderLayout.CENTER);
        
        JButton boutouConfirmer = new JButton("Confirmer");
        boutouConfirmer.setFocusable(false);
        boutouConfirmer.addActionListener(e -> 
        {
            this.remove(pFormCenter);
            drawCenter();
            this.revalidate();
            this.repaint();
        });

        confirmationPanel.add(boutouConfirmer, BorderLayout.SOUTH);
        pFormCenter.add(confirmationPanel);
        this.add(pFormCenter, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    private void afficherRecherche(Component conteneur)
    {
        centre.removeAll();
        centre.add(conteneur);
        centre.revalidate();
        centre.repaint();
    }

    private Component ajoutBoutonsConfirmerRecherche(Component conteneur)
    {
        JPanel total = new JPanel(new BorderLayout());
        JPanel conteneurBoutons = new JPanel();
        JButton boutonRecherche = new JButton("Rechercher");
        JButton boutonAnnuler = new JButton("Annuler");

        conteneurBoutons.add(boutonAnnuler);
        conteneurBoutons.add(boutonRecherche);

        total.add(conteneur, BorderLayout.NORTH);
        total.add(conteneurBoutons, BorderLayout.SOUTH);

        boutonRecherche.addActionListener(e -> 
        {
            if (choixRecherche.equals("n")) 
            {
                this.getListeAfficherNom();
            }
            else
            {
                this.getListeAfficherMatricule();
            }

            this.afficherSurvivant();
            bd.resetNomDejaChercher();
            bd.resetMatriculeDejaChercher();
        });

       return total;
    }

    private Component rechercheParNom()
    {
        JLabel lNomChercher = new JLabel("Nom chercher : ");
        nomChercher = new JTextField(20);
        lNomChercher.setFont(new Font("Arial Black", Font.BOLD, 15));

        JPanel pNom = new JPanel(new BorderLayout());
        pNom.add(lNomChercher, BorderLayout.NORTH);
        pNom.add(nomChercher, BorderLayout.SOUTH);
        return pNom;
    }

    private Component rechercheParMatricule()
    {
        JLabel lNomChercher = new JLabel("Matricule chercher : ");
        matriculeChercher = new JTextField(20);
        lNomChercher.setFont(new Font("Arial Black", Font.BOLD, 15));

        JPanel pNom = new JPanel(new BorderLayout());
        pNom.add(lNomChercher, BorderLayout.NORTH);
        pNom.add(matriculeChercher, BorderLayout.SOUTH);
        return pNom;
    }

    private void typeRecherche()
    {
        try 
        {
            this.remove(pCenter);
        } catch (Exception e) {}
        try 
        {
            this.remove(centre);
        } catch (Exception e) {}
        try 
        {
            this.remove(pFormCenter);
        } catch (Exception e) {}

        JPanel paneauCentreRecherche = new JPanel(new BorderLayout());
        JLabel typeRecherche = new JLabel("Choississez comment vous voulez effectuer la recherche");
        typeRecherche.setFont(new Font("Arial Black", Font.BOLD, 15));
        JPanel boutons = new JPanel();
        JButton choixNom = new JButton("Par le nom");
        choixNom.setFocusable(false);
        choixNom.addActionListener(e -> 
        {
            choixRecherche = "n";
            afficherRecherche(ajoutBoutonsConfirmerRecherche(rechercheParNom()));
        });
        JButton choixMatricule = new JButton("Par le matricule");
        choixMatricule.setFocusable(false);
        choixMatricule.addActionListener(e -> 
        {
            choixRecherche = "m";
            afficherRecherche(ajoutBoutonsConfirmerRecherche(rechercheParMatricule()));
        });
        boutons.add(choixMatricule);
        boutons.add(choixNom);

        paneauCentreRecherche.add(typeRecherche, BorderLayout.NORTH);
        paneauCentreRecherche.add(boutons, BorderLayout.SOUTH);

        centre = new JPanel(new GridBagLayout());
        centre.add(paneauCentreRecherche);

        this.add(centre, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    private void ajouterABaseDonneeSurvivant()
    {
        int iAnnee;
        int iMois;
        int iJour;
        int iService;
        int iSorti;
        int iVictime;
        char genre;
        int iNbEmploye;
        try 
        {
            iAnnee = Integer.parseInt(annee.getText());
            iMois = Integer.parseInt(mois.getText());
            iJour = Integer.parseInt(jour.getText());
        } catch (Exception ex) 
        {
            iAnnee = 2000;
            iJour = 1;
            iMois = 1;
        }
        ClDate date = new ClDate(iAnnee, iMois, iJour);
        System.out.println(sexe.getText().toCharArray());
        if (sexe.getText().toCharArray().length != 0 && sexe.getText() != "") 
        {
             genre= sexe.getText().toCharArray()[0];
        }
        else
        {
            genre = 'm'; 
        }

        try 
        {
            iService = Integer.parseInt(service.getText());
        } catch (Exception ex) {
            iService = 1;
        }

        try 
        {
            iSorti = Integer.parseInt(sorti.getText());
        } catch (Exception ex) {
            iSorti = 0;
        }

        try 
        {
            iVictime = Integer.parseInt(victime.getText());
        } catch (Exception ex) {
            iVictime = 0;
        }

        switch (choix) 
        {
            case 'a':
                Armee nouvelleArmee;
                nouvelleArmee= new Armee(nom.getText(), prenom.getText(), matricule.getText(),genre,date,grade.getText(), arme.getText(), poste.getText(), iService, iSorti, iVictime);
                bd.ajouter(nouvelleArmee);
                this.confirmerAjout(nouvelleArmee.getMatricule());
                break;
            case 'm':
                int iPlainte;
                try 
                {
                    iPlainte = Integer.parseInt(plainte.getText());
                } catch (Exception ex) {
                    iPlainte = 0;
                }
                Milice nouvellMilice;
                nouvellMilice = new Milice(nom.getText(), prenom.getText(), matricule.getText(),genre,date,grade.getText(), arme.getText(), poste.getText(), iService,iPlainte);
                bd.ajouter(nouvellMilice);
                this.confirmerAjout(nouvellMilice.getMatricule());
                System.out.println(bd.afficherResident(nouvellMilice.getMatricule()));
                break;
            case 'i':
                int iAnneeEtude;
                int iMoisEtude;
                int iJourEtude; 
                try 
                {
                    iAnneeEtude = Integer.parseInt(anneeEtude.getText());
                } catch (Exception ex) {
                    iAnneeEtude = 2024;
                }
                try {
                    iMoisEtude = Integer.parseInt(moisEtude.getText());
                } catch (Exception ex) {
                    iMoisEtude = 6;
                }
                try {
                    iJourEtude = Integer.parseInt(jourEtude.getText());
                } catch (Exception ex) {
                    iJourEtude = 21;
                }
                ClDate dateEtude = new ClDate(iAnneeEtude, iMoisEtude, iJourEtude);
                Ingenieur nouvelIngenieur = new Ingenieur(nom.getText(), prenom.getText(), matricule.getText(),genre,date,secteur.getText(),specialite.getText(),dateEtude,niveau.getText());
                bd.ajouter(nouvelIngenieur);
                this.confirmerAjout(nouvelIngenieur.getMatricule());
                break;
            case 'o':
                int iSecteurDispo;
                try {
                    iSecteurDispo = Integer.parseInt(nbrSecteurDispo.getText());
                } catch (Exception ex) {
                    iSecteurDispo = 1;
                }
                Ouvrier nouvelOuvrier = new Ouvrier(nom.getText(), prenom.getText(), matricule.getText(),genre,date,secteur.getText(),horaire,typeTravail.getText(),iSecteurDispo);
                bd.ajouter(nouvelOuvrier);
                this.confirmerAjout(nouvelOuvrier.getMatricule());
                break;
            case 's':
                try 
                {
                    iNbEmploye = Integer.parseInt(nbEmploye.getText());
                } catch (Exception e) {
                    iNbEmploye = 0;
                }
                int iNbrProjet = 0;
                String[] listeProjet = {projet1.getText(), projet2.getText(), projet3.getText()};
                for (String projet : listeProjet) {
                    if (projet.toCharArray().length != 0) 
                    {
                        iNbrProjet++;
                    }
                }
                Scientifique nouvScientifique = new Scientifique(nom.getText(), prenom.getText(), matricule.getText(),genre,date,iNbEmploye,secteur.getText(),iNbrProjet, listeProjet);
                bd.ajouter(nouvScientifique);
                this.confirmerAjout(nouvScientifique.getMatricule());
                break;
            case 'd':
                try 
                {
                    iNbEmploye = Integer.parseInt(nbEmploye.getText());
                } catch (Exception e) {
                    iNbEmploye = 0;
                }
                Administrateur nouvelAdministrateur = new Administrateur(nom.getText(), prenom.getText(), matricule.getText(),genre,date,iNbEmploye, secteur.getText(), projetAffecter.getText(), titreAdmin.getText());
                bd.ajouter(nouvelAdministrateur);
                this.confirmerAjout(nouvelAdministrateur.getMatricule());
                break;
            default:
                break;
                
        }
        this.remove(dernierConteneur);
        this.drawLeftPanel();
        this.revalidate();
        this.repaint();
    }

    private void remplirFormulaireAdministateur()
    {
        Administrateur unAdministrateur = (Administrateur) listePourAfficher.get(indexSurvivantAfficher);
        projetAffecter.setText(unAdministrateur.getProjetAffecter());
        titreAdmin.setText(unAdministrateur.getTitre());
    }

    private void remplirFormulaireScientifique()
    {
        Scientifique unScientifique = (Scientifique) listePourAfficher.get(indexSurvivantAfficher);
        projet1.setText(unScientifique.getListeProjet()[0]);
        projet2.setText(unScientifique.getListeProjet()[1]);
        projet3.setText(unScientifique.getListeProjet()[3]);
    }

    private void remplirFormulaireIngenieur()
    {
        Ingenieur unIngenieur = (Ingenieur) listePourAfficher.get(indexSurvivantAfficher);
        specialite.setText(choixRecherche);
        anneeEtude.setText(String.valueOf(unIngenieur.getDateFinEtude().getAnnnee()));
        moisEtude.setText(String.valueOf(unIngenieur.getDateFinEtude().getMois()));
        jourEtude.setText(String.valueOf(unIngenieur.getDateFinEtude().getJours()));
        niveau.setText(unIngenieur.getNiveauEtude());
    }

    private void remplirFormulaireOuvrier()
    {
        Ouvrier unOuvrier = (Ouvrier) listePourAfficher.get(indexSurvivantAfficher);
        horaire = unOuvrier.getIndexQuartTravail();
        typeTravail.setText(unOuvrier.getGenreTravail());
        nbrSecteurDispo.setText(String.valueOf(unOuvrier.getNbrSecteurDispo()));
    }

    private void remplirFormulaireMilice()
    {
        Milice uneMilice = (Milice) listePourAfficher.get(indexSurvivantAfficher);
        plainte.setText(String.valueOf(uneMilice.getNbrPlainte()));
    }

    private void remplirFormulaireArmee()
    {
        Armee uneArmee = (Armee) listePourAfficher.get(indexSurvivantAfficher);
        sorti.setText(String.valueOf(uneArmee.getNbrSortieExt()));
        victime.setText(String.valueOf(uneArmee.getNbrSortieExt()));
        subordonne1.setText(uneArmee.getMatriculeSubordonne().get(0));
        subordonne2.setText(uneArmee.getMatriculeSubordonne().get(1));
        subordonne3.setText(uneArmee.getMatriculeSubordonne().get(2));
        subordonne4.setText(uneArmee.getMatriculeSubordonne().get(3));
        subordonne5.setText(uneArmee.getMatriculeSubordonne().get(4));
    }

    private void remplirFormulaireGestionnaire()
    {
        Gestionnaire gestio = (Gestionnaire) listePourAfficher.get(indexSurvivantAfficher);
        secteur.setText(gestio.getSecteur());
        nbEmploye.setText(String.valueOf(gestio.getNbrEmploye()));
    }

    private void remplirFormulaireMaintenance()
    {
        EmployeMaintenance employe = (EmployeMaintenance) listePourAfficher.get(indexSurvivantAfficher);
        secteur.setText(employe.getSecteur());
    }

    private void remplirFormulaireSecurite()
    {
        ForceSecurite force = (ForceSecurite) listePourAfficher.get(indexSurvivantAfficher);
        grade.setText(force.getGrade());
        poste.setText(force.getPoste());
        arme.setText(force.getArme());
        service.setText(String.valueOf(force.getAnneeService()));
    }

    private void remplirFormulaireBase()
    {
        try 
        {
            this.remove(centre);
            this.add(pCenter, BorderLayout.CENTER);
        } catch (Exception e) {
        }

        try 
        {
            this.remove(pAffichageSurvivant);
            this.add(pCenter, BorderLayout.CENTER);
        } catch (Exception e) {}
        nom.setText(listePourAfficher.get(indexSurvivantAfficher).getNom());
        prenom.setText(listePourAfficher.get(indexSurvivantAfficher).getPrenom());
        matricule.setText(listePourAfficher.get(indexSurvivantAfficher).getMatricule());
        annee.setText(Integer.toString(listePourAfficher.get(indexSurvivantAfficher).getDateNaissance().getAnnnee()));
        mois.setText(Integer.toString(listePourAfficher.get(indexSurvivantAfficher).getDateNaissance().getMois()));
        jour.setText(Integer.toString(listePourAfficher.get(indexSurvivantAfficher).getDateNaissance().getJours()));
        sexe.setText(String.valueOf(listePourAfficher.get(indexSurvivantAfficher).getSexe()));
    }

    private void sauvegarderTout()
    {
        bd.sauvegardeComplet();

        System.exit(0);
    }

    private void modifierUnSurvivant()
    {
        if (survivant.getText().equals("Aucun survivant à afficher")) 
        {
            JOptionPane.showMessageDialog(centre, "Aucun survivant à modifier\nRetour.", "Erreur", JOptionPane.PLAIN_MESSAGE);
            modifier = false;
            this.remove(centre);
            drawCenter();
            drawLeftPanel();
            this.revalidate();
            this.repaint();
        }
        else
        {
            char position1 = survivant.getText().toLowerCase().charAt(0);
            char position2 = survivant.getText().toLowerCase().charAt(1);
            if (position1 == 'a' && position2 == 'r') 
            {
                choix = 'a';
                ajouterBoutonsSoumission(ajouterArmee(ajouterSecurite(ajouterSurvivant())));
                remplirFormulaireBase();
                remplirFormulaireSecurite();
                remplirFormulaireArmee();
            }
            else if (position1 == 'm') 
            {
                choix = 'm';
                ajouterBoutonsSoumission(ajouterMilice(ajouterSecurite(ajouterSurvivant())));
                remplirFormulaireBase();
                remplirFormulaireSecurite();
                remplirFormulaireMilice();
            }
            else if (position1 == 'o') 
            {
                choix = 'o';
                ajouterBoutonsSoumission(ajouterOuvrier(ajouterSecurite(ajouterSurvivant())));
                remplirFormulaireBase();
                remplirFormulaireMaintenance();
                remplirFormulaireOuvrier();
            }
            else if (position1 == 'i') 
            {
                choix = 'i';
                ajouterBoutonsSoumission(ajouterIngenieur(ajouterSecurite(ajouterSurvivant())));
                remplirFormulaireBase();
                remplirFormulaireMaintenance();
                remplirFormulaireIngenieur();
            }
            else if (position1 == 's') 
            {
                choix = 's';
                ajouterBoutonsSoumission(ajouterScientifique(ajouterSecurite(ajouterSurvivant())));
                remplirFormulaireBase();
                remplirFormulaireGestionnaire();
                remplirFormulaireScientifique();
            }
            else if (position1 == 'a' && position2 == 'd') 
            {
                choix = 'd';
                ajouterBoutonsSoumission(ajouterAdministrateur(ajouterSecurite(ajouterSurvivant())));
                remplirFormulaireBase();
                remplirFormulaireGestionnaire();
                remplirFormulaireAdministateur();
            }
            dernierConteneur.revalidate();
            dernierConteneur.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == miAjouterForce) 
        {
            choix = 'f';
            choixTypeSurvivant();
        }
        else if (e.getSource() == miAjouterGestionnaire) 
        {
            choix = 'g';
            choixTypeSurvivant();
        }
        else if (e.getSource() == miAjouterMaintenance) 
        {
            choix = 'm';
            choixTypeSurvivant();
        }
        else if (e.getSource() == miModifier) 
        {
            indexSurvivantAfficher = 0;
            typeAffichage = new JLabel("Survivant(s) à modifier :");
            typeAffichage.setFont(new Font("Arial Black", Font.BOLD, 15));
            modifier = true;
            supprimer = false;
            this.typeRecherche();
        }
        else if (e.getSource() == modifierButton) 
        {
           modifierUnSurvivant();  
        }
        else if (e.getSource() == miSupprimer) 
        {
            indexSurvivantAfficher = 0;
            typeAffichage = new JLabel("Survivant(s) à supprimer :");
            typeAffichage.setFont(new Font("Arial Black", Font.BOLD, 15));
            supprimer = true;
            modifier = false;
            this.typeRecherche();

        }
        else if (e.getSource() == supprimerButton) 
        {
            try 
            {
                bd.supprimer(listePourAfficher.get(indexSurvivantAfficher));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Aucun survivant a supprimer\nRetour.");
            }
            supprimer = false;
            this.remove(centre);
            drawCenter();
            drawLeftPanel();
            this.revalidate();
            this.repaint();

        }
        else if (e.getSource() == miSecurite) 
        {
            indexSurvivantAfficher = 0;
            supprimer = false;
            modifier = false;
            typeAffichage = new JLabel("Force de sécurité :");
            typeAffichage.setFont(new Font("Arial Black", Font.BOLD, 15));
            ArrayList<String> listeForceSecurite =bd.getMatriculeSecurite();
            getListeAfficher(listeForceSecurite);
            afficherSurvivant();    
        }
        else if (e.getSource() == miMaintenance) 
        {
            indexSurvivantAfficher = 0;
            supprimer = false;
            modifier = false;
            typeAffichage = new JLabel("Employé(s) de maintenance :");
            typeAffichage.setFont(new Font("Arial Black", Font.BOLD, 15));
            ArrayList<String> listeMaintenance =bd.getMatriculeMaintenance();
            getListeAfficher(listeMaintenance);
            afficherSurvivant(); 
        }
        else if (e.getSource() == miGestionnaire) 
        {
            indexSurvivantAfficher = 0;
            supprimer = false;
            modifier = false;
            typeAffichage = new JLabel("Gestionnaire(s) :");
            typeAffichage.setFont(new Font("Arial Black", Font.BOLD, 15));
            ArrayList<String> listeGestionnaire =bd.getMatriculeGestionnaire();
            getListeAfficher(listeGestionnaire);
            afficherSurvivant(); 
        }
        else if (e.getSource() == miTous) 
        {
            indexSurvivantAfficher = 0;
            supprimer = false;
            modifier = false;
            typeAffichage = new JLabel("Tous les survivants :");
            typeAffichage.setFont(new Font("Arial Black", Font.BOLD, 15));
            ArrayList<String> listeTous =bd.getTousMatricule();
            getListeAfficher(listeTous);
            afficherSurvivant(); 
        }
        else if (e.getSource() == miSurvivantPrecis) 
        {
            indexSurvivantAfficher = 0;
            supprimer = false;
            modifier = false;
            typeAffichage = new JLabel("Survivant(s) rechercher :");
            typeAffichage.setFont(new Font("Arial Black", Font.BOLD, 15));
            typeRecherche();
        }
        //Event listener sur les boutons de l'affichage des survivants
        else if(e.getSource() == retourAccueil)
        {
            supprimer = false;
            modifier = false;
            indexSurvivantAfficher = 0;
            this.remove(centre);
            drawCenter();
            this.revalidate();
            this.repaint();
        }
        else if(e.getSource() == survivantSuivant)
        {
            if (listePourAfficher.size() != 0) {
                if (indexSurvivantAfficher == (listePourAfficher.size()-1)) 
                {
                    indexSurvivantAfficher = 0;
                }
                else
                {
                    indexSurvivantAfficher++;
                }
            }
            afficherSurvivant();
        }
        else if(e.getSource() == survivantPrecedent)
        {
            if (listePourAfficher.size() != 0) 
            {
                if (indexSurvivantAfficher == 0) 
                {
                    indexSurvivantAfficher = (listePourAfficher.size()-1);
                }
                else
                {
                    indexSurvivantAfficher--;
                }
            }
            afficherSurvivant();
        }
        //Ajout d'un survivant
        else if (e.getSource() == bAjouterSurvivant) 
        {
           if (modifier) 
           {
                modifier = false;
                bd.supprimerCompletement(listePourAfficher.get(indexSurvivantAfficher));
                ajouterABaseDonneeSurvivant();
           }
           else
           {
                ajouterABaseDonneeSurvivant();
           }
        }
        else if (e.getSource() == annulerAjout) 
        {
            if (modifier) 
            {
                modifier = false;
            }
            System.out.println("test");
            this.remove(dernierConteneur);
            drawCenter();
            this.revalidate();
            this.repaint();
        }
        else if (e.getSource() == mSauvegarderSecurite) 
        {
            sauvegardeSecurite();
            JOptionPane.showMessageDialog(null, "La liste des force de sécurité a bien été enregistré.");
        }
        else if (e.getSource() == mSauvegarderMaintenance) 
        {
            sauvegardeMaintenance();
            JOptionPane.showMessageDialog(null, "La liste des employé de maintenance a bien été enregistré.");
        }
        else if (e.getSource() == mSauvegarderGestionnaire) 
        {
            sauvegardeGestion();
            JOptionPane.showMessageDialog(null, "La liste des gestionnaires a bien été enregistré.");
        }
    }
}
