from tkinter import Tk, Canvas, messagebox, Frame, Label, filedialog, Button, E, W, Entry
from pipopipette import partie, planche

nb_chargement = 0
class CanvasPipopipette(Canvas):
    def __init__(self, planche, longueur_ligne=200):

        # attributs
        self.longueur_ligne = longueur_ligne
        self.largeur_ligne = longueur_ligne / 10
        self.dimension_boite = self.longueur_ligne + self.largeur_ligne
        self.planche = planche

        # attribut pour revenir à l'état d'origine (utiliser pour l'option rejouer)
        self.etat_origine_lignes = self.planche.lignes
        self.etat_origine_boites = self.planche.boites

        # nombre de boites
        self.nb_boite_v = self.planche.N_BOITES_H
        self.nb_boite_h = self.planche.N_BOITES_V

        # nombre de pixel par boite, peut varier
        self.nb_pixel_par_boite = longueur_ligne

        # Dictionnaires contenant les lignes et les boites du jeux
        # necessaires pour retrouver le coup jouee
        self.lignes = self.planche.lignes
        self.points = self.planche.boites

        # appel du constructeur de la classe (Canvas)
        super().__init__(
            width=self.nb_boite_h * self.dimension_boite + self.largeur_ligne,
            height=self.nb_boite_v * self.dimension_boite + self.largeur_ligne)

    def dessiner_boites(self):
        # itération sur le dictionnaires contenant l'ensemble des boites

        for position, boite in self.planche.boites.items():
            ligne, colonne = position

            # trouver les points d'encrages x et y du début et de la fin de la boite
            debut_boite_x = colonne * self.dimension_boite + self.largeur_ligne
            debut_boite_y = ligne * self.dimension_boite + self.largeur_ligne
            fin_boite_x = debut_boite_x + self.dimension_boite
            fin_boite_y = debut_boite_y + self.dimension_boite

            # création des boites de couleur
            self.create_rectangle(debut_boite_x, debut_boite_y, fin_boite_x, fin_boite_y, tags='boite',
                                  fill=boite.couleur_affichage())

    def dessiner_lignes(self):
        # itération sur le dictionnaire contenant l'ensemble des lignes

        for lco, ligne in self.planche.lignes.items():
            ligne_point, colone_point, orientation = lco

            # récupération des coordonnées de départ des lignes
            if orientation == "H":
                debut_ligne_x = colone_point * self.dimension_boite + self.largeur_ligne
                debut_ligne_y = ligne_point * self.dimension_boite
                fin_ligne_x = debut_ligne_x + self.longueur_ligne
                fin_ligne_y = debut_ligne_y + self.largeur_ligne
            else:
                debut_ligne_x = colone_point * self.dimension_boite
                debut_ligne_y = ligne_point * self.dimension_boite + self.largeur_ligne
                fin_ligne_x = debut_ligne_x + self.largeur_ligne
                fin_ligne_y = debut_ligne_y + self.longueur_ligne

            # création des lignes entre les différents points
            self.create_rectangle(debut_ligne_x,
                                  debut_ligne_y,
                                  fin_ligne_x,
                                  fin_ligne_y,
                                  tags='ligne',
                                  fill=ligne.couleur_affichage(),
                                  width=1)

    def dessiner_points(self):
        for col in range(self.planche.N_BOITES_V + 1):
            for ligne in range(self.planche.N_BOITES_H + 1):
                origine_cercle_x = col * self.dimension_boite
                origine_cercle_y = ligne * self.dimension_boite
                fin_cercle_x = origine_cercle_x + self.largeur_ligne
                fin_cercle_y = origine_cercle_y + self.largeur_ligne

                self.create_oval(origine_cercle_x,
                                 origine_cercle_y,
                                 fin_cercle_x,
                                 fin_cercle_y,
                                 tags='point',
                                 fill='black')

    def obtenir_coup_joue(self, event):
        '''
        Méthode qui retrouve si un clic est fait sur une ligne, une boîte ou sur un point, et surtout pour retrouver
        laquelle.

        Dans votre TP, vous pourrez vous débarasser des sections de code concernant les clics sur un
        point et sur une boîte pour conserver seulement les sections sur les lignes et retourner None
        quand le clic est sur un point ou une boîte.

        Args:
            event (Event): L'objet Event relié au clic fait sur le canvas

        Returns:
            None si le clic a été fait sur un point, (int, int, orientation) s'il
            a été fait sur une ligne et (int, int, 'Boite') si c'était une boîte
        '''

        # definir la valeur par defaut d'abord
        coup = None

        #recuperer les coordenés du clic gauche <Button-1>
        x_cliquee = event.x
        y_cliquee = event.y
        self.flag = 0

        # obtenir le mot correspondant dans le dictionnaire comme expliquee dans le Labo 11
        col = int(x_cliquee // self.dimension_boite)
        ligne = int(y_cliquee // self.dimension_boite)

        lower_limit = col * self.dimension_boite
        upper_limit = col * self.dimension_boite + self.largeur_ligne
        if x_cliquee > lower_limit and x_cliquee < upper_limit:
            coup = (ligne, col, 'V')
            self.flag += 1

        lower_limit = ligne * self.dimension_boite
        upper_limit = ligne * self.dimension_boite + self.largeur_ligne
        if y_cliquee > lower_limit and y_cliquee < upper_limit:
            coup = (ligne, col, 'H')
            self.flag += 1

        #gerer le cas des points
        if self.flag > 1:
            coup = None

        return coup

    def actualiser(self):
        # On supprime les anciennes boîtes et on ajoute les nouvelles.
        self.delete('boite')
        self.dessiner_boites()

        # On supprime les anciennes lignes et on ajoute les nouvelles.
        self.delete('ligne')
        self.dessiner_lignes()

        # On dessine les points
        self.dessiner_points()

    def valider_coup(self, coup):
        planche.Planche.valider_coup(self.planche,coup)


class Fenetre(Tk):
    def __init__(self):
        super().__init__()
        global nb_chargement
        # Figer la fenêtre
        self.resizable(0, 0)

        # Nom de la fenêtre.
        self.title('Pipopipette')

        # nouvelle partie
        if nb_chargement == 0:
            y_n = messagebox.askyesno("Charger une partie", "Voulez-vous charger une partie existante?")
            if y_n == True:
                nb_chargement += 1
                self.partie = partie.PartiePipopipette(filedialog.askopenfilename())
            else:
                nb_chargement += 1
                self.partie = partie.PartiePipopipette()
        else:
            self.partie = partie.PartiePipopipette()

        self.label = Label(self, text = f"C'est au joueur rouge de jouer")
        self.label.grid()

        self.frame = Frame(self, bd=20)
        self.frame.grid()

        self.initialiser_canvas()

        # On lie un clic sur le Canvas à une méthode.
        self.canvas_planche.bind('<Button-1>', self.selectionner)

        self.button = Button(self, text= "Sauvegarder la partie", command= self.sauvegarde_fermeture)
        self.button.grid(sticky=W+E)

    def initialiser_canvas(self):

        # Création du canvas grille.

        self.canvas_planche = CanvasPipopipette(self.partie.planche)

        self.canvas_planche.actualiser()
        self.canvas_planche.grid()

    def sauvegarde_fermeture(self):
        partie.PartiePipopipette.sauvegarder(self.partie, "sauvegarde1.txt")

        reponse = messagebox.askyesno("Sauvegarde effectué", "La sauvegarde a été effectué avec succès!\nSouhaitez-vous quitter le jeu?")
        if reponse == True:
            quit()

    def selectionner(self, event=None):
        '''
        Méthode appelée lorsqu'un clic est fait sur votre fenêtre.

        Par défaut, comme notre fenêtre contient seulement notre Canvas, on va chercher
        le coup associé au clic à l'aide de self.canvas_planche.obtenir_coup_joue(event).

        Ici, pour vous montrer la gestion des exceptions et l'affichage de messages avec
        messagebox, on lance ici une exception ErreurClicPoint et on affiche une erreur si
        le clic a été fait sur un point (associé à un retour None de obtenir_coup_joue()).

        Dans votre TP, le retour de obtenir_coup_joue() sera à None si et seulement si le clic
        N'a PAS été effectué une ligne. Ainsi, si le coup est None, on ne fera rien, sinon on le jouera
        avec self.partie.jouer_coup(). Aussi, si le coup est sur une ligne déjà jouée, on attrapera
        l'exception lancée dans Planche.valider_coup() et on affichera un message d'erreur correspondant.
        Enfin, on s'assurera aussi de faire appel à l'actualisation du canvas et à la logique de
        fin de partie.

        Args:
            event (Event): L'objet Event relié au clic fait sur le canvas
        '''

        coup = self.canvas_planche.obtenir_coup_joue(event)
        self.canvas_planche.valider_coup(coup)
        self.partie.jouer_coup(coup)

        # On actualise après chaque clic pour garder un canvas bien arrimé à l'état de la partie.
        self.label["text"] = f"C'est au joueur {self.partie.couleur_joueur_courant} de jouer"
        self.canvas_planche.actualiser()

        #On vérifie si la partie est terminé
        if self.partie.partie_terminee():
            self.gagnant = self.partie.gagnant_partie
            reponse = messagebox.askyesno("La partie est terminé",f"Le joueur {self.gagnant} a gagné!\nVoulez-vous rejouer?")
            if reponse == True:
                self.rejouer()
            else:
                quit()

    def rejouer(self):
        #methode pour rejouer une partie
        Fenetre.destroy(self)
        f = Fenetre()
        f.mainloop()
