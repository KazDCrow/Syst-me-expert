from tkinter import messagebox
class ErreurPositionCoup(Exception):
    def __init__(self, jouer):
        super().__init__()

        if jouer == True:
            messagebox.showerror("Attention", "Cette ligne a déjà été joué")

        elif jouer == None:
            messagebox.showerror("Attention", "Veiller à choisir une ligne pour jouer un coup")