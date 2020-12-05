package gameCommons;

import java.awt.Color;
import java.util.Random;

import environment.EnvInf;
import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

import javax.swing.text.Position;

import util.Case;
import util.Direction;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	private int timer;
	public boolean endGameLose;

	private final int mode;  // 1: mode solo, 2: mode multijoueur

	// Lien aux objets utilisés
	private IEnvironment environment;
	private IFrog frog1;
	private IFrog frog2;
	private IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant déplacement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity, int mode) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.timer = 0;
		this.endGameLose = false;
		this.mode = mode;
	}

	/**
	 * Lie l'objet frog à la partie
	 * 
	 * @param frog1
	 */
	public void setFrog(IFrog frog1, IFrog frog2) {
		this.frog1 = frog1;
		this.frog2 = frog2;
	}

	public void setFrog(IFrog frog) {
		this.frog1 = frog;
		this.frog2 = null;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		boolean res;
		if (frog2 != null) {
			Case c = frog1.getPosition();
			Case c2 = frog2.getPosition();
			res = (!this.environment.isSafe(c)) || (!this.environment.isSafe(c2));
		}
		else {
			Case c = frog1.getPosition();
			res = !this.environment.isSafe(c);
		}

		return res;
	}

	/**
	 * Teste si la partie est gagnee et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagnée
	 */
	public boolean testWin() {
		boolean res;
		Case c = frog1.getPosition();
		if (frog2 != null) {
			Case c2 = frog2.getPosition();
			res = (c.ord == this.height-1) || (c2.ord == this.height - 1);
		}
		else {
			res = c.ord == (this.height-1);
		}
		return res;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		timer++;
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog1.getPosition(), Color.GREEN));
		if (frog2 != null) {
			this.graphic.add(new Element(frog2.getPosition(), Color.MAGENTA));
		}
		/*
		testLose();
		testWin();
		 */


		if (testLose() || this.endGameLose) {
			graphic.clear();
			if (frog1.getDist(1) == -1)
				graphic.endGameScreen("GAME OVER");
			else if (mode == 1)
				graphic.endGameScreen("GAME OVER", frog1.getDist(2), timer);
			else
				graphic.endGameScreen2("GAME OVER", frog1.getDist(2), frog2.getDist(2), timer);
			return;
		}


		if (testWin()) {
			graphic.clear();
			if (frog1.getDist(1) == -1)
				graphic.endGameScreen("YOU WIN !");
			else
				graphic.endGameScreen("YOU WIN", frog1.getDist(2), timer);
			return;
		}
	}

	public Direction getFrog1Direction() {
		return frog1.getDirection();
	}

	public Direction getFrog2Direction() {
		if (frog2 != null)
			return frog2.getDirection();
		else
			return Direction.nulldir;
	}

	public void resetFrog1Dir() {
		this.frog1.resetDir();
	}

	public void resetFrog2Dir() {
		if (frog2 != null)
			this.frog2.resetDir();
	}

	public Case getFrog1Pos() {
		if (frog2 != null)
			return frog1.getPosition();
		else
			return new Case(0,0);
	}

	public Case getFrog2Pos() {
		if (frog2 != null)
			return frog2.getPosition();
		else
			return new Case(0,0);
	}

	public int getFrog1Dist() {
		return frog1.getDist(1);
	}

	public int getFrog2Dist() {
		if (frog2 != null)
			return frog2.getDist(1);
		else
			return -1;
	}

	public void setFrog1Dist(int new_dist) {
		frog1.setDist(new_dist);
	}

	public void setFrog2Dist(int new_dist) {
		if (frog2 != null)
			frog2.setDist(new_dist);
	}

	public Case getFrog1NextPos() {
		return frog1.getNextPosition();
	}

	public Case getFrog2NextPos() {
		if (frog2 != null)
			return frog2.getNextPosition();
		else return new Case(0,0);
	}
}
