package graphicalElements;

import gameCommons.IFrog;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'�l�ment aux �l�ments � afficher
	 * @param e
	 */
    public void add(Element e);
    
    /**
     * Enl�ve tous les �l�ments actuellement affich�s
     */
    public void clear();
    
    /***
     * Actualise l'affichage
     */
    public void repaint();
    
    /**
     * Lie la grenouille � l'environneemnt graphique
     * @param frog1
     */
    public void setFrog(IFrog frog1, IFrog frog2);

    public void setFrog(IFrog frog);
    
    /**
     * Lance un �cran de fin de partie
     * @param message le texte � afficher
     */
    public void endGameScreen(String message);

    /**
     * Nouvelle version de engGameScreen pour le mode de jeu infini, permet un
     * affichage plus propre du score
     * @param message le texte � afficher
     * @param score la plus haute ligne atteinte par le joueur
     * @param timer un compteur qui permet de savoir le nombre d'execution de la fonction update
     */
    public void endGameScreen(String message, int score, int timer);

    /**
     * Nouvelle version de endGameScreen pour le mode de jeu infini � deux joueurs.
     * @param message le texte � afficher
     * @param j1 la plus haute ligne atteinte par le joueur 1
     * @param j2 la plus haute ligne atteinte par le joueur 2
     * @param timer un compteur qui permet de connaitre le nombre d'execution de la fonction update
     */
    public void endGameScreen2(String message, int j1, int j2, int timer);
}
