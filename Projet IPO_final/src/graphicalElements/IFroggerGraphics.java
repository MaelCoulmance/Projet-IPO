package graphicalElements;

import gameCommons.IFrog;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'élément aux éléments à afficher
	 * @param e
	 */
    public void add(Element e);
    
    /**
     * Enlève tous les éléments actuellement affichés
     */
    public void clear();
    
    /***
     * Actualise l'affichage
     */
    public void repaint();
    
    /**
     * Lie la grenouille à l'environneemnt graphique
     * @param frog1
     */
    public void setFrog(IFrog frog1, IFrog frog2);

    public void setFrog(IFrog frog);
    
    /**
     * Lance un écran de fin de partie
     * @param message le texte à afficher
     */
    public void endGameScreen(String message);

    /**
     * Nouvelle version de engGameScreen pour le mode de jeu infini, permet un
     * affichage plus propre du score
     * @param message le texte à afficher
     * @param score la plus haute ligne atteinte par le joueur
     * @param timer un compteur qui permet de savoir le nombre d'execution de la fonction update
     */
    public void endGameScreen(String message, int score, int timer);

    /**
     * Nouvelle version de endGameScreen pour le mode de jeu infini à deux joueurs.
     * @param message le texte à afficher
     * @param j1 la plus haute ligne atteinte par le joueur 1
     * @param j2 la plus haute ligne atteinte par le joueur 2
     * @param timer un compteur qui permet de connaitre le nombre d'execution de la fonction update
     */
    public void endGameScreen2(String message, int j1, int j2, int timer);
}
