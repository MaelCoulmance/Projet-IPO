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
     * @param frog
     */
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
     * @param timer un compteur qui permet de savoir le nombre d'execution de la fonction
     */
    public void endGameScreen(String message, int score, int timer);
}