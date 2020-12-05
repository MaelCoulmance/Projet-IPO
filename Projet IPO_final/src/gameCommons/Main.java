package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import environment.EnvInf;
import frog.Frog;
import environment.Environment;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import frog.FrogInf;

import util.Menu;

import environment.Environment;

public class Main {

	public static final int DEFAULT_WIDTH = 26;
	public static final int DEFAULT_HEIGHT = 20;
	public static final int DEFAULT_TEMPO = 100;

	public static void main(String[] args) {

		//Caractéristiques du jeu
		int width = DEFAULT_WIDTH;
		int height = DEFAULT_HEIGHT;
		int tempo = DEFAULT_TEMPO;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.2;

		/**
		 * Mode:
		 * 		1. mode solo infini
		 * 		2. mode multijoueur infini
		 * 		3. mode solo classique
		 * 		4. mode multijoueur classique
		 */
		final int mode = MenuPrincipal();

		if (mode == 2 || mode == 4) {
			// modes multijoueur

			//Création de l'interface graphique
			IFroggerGraphics graphic = new FroggerGraphic(width, height);
			//Création de la partie
			Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity, 2);
			//Création et liason de la grenouille
			IFrog frog1 = (mode == 2) ? new FrogInf(game, width / 2) : new Frog(game, width /2);
			IFrog frog2 = (mode == 2) ? new FrogInf(game, width / 3) : new Frog(game, width /3);
			game.setFrog(frog1, frog2);
			graphic.setFrog(frog1, frog2);
			//Création et liaison de l'environnement
			IEnvironment env = (mode == 2) ? new EnvInf(game) : new Environment(game);
			game.setEnvironment(env);

			//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
			Timer timer = new Timer(tempo, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					game.update();
					graphic.repaint();
				}
			});
			timer.setInitialDelay(0);
			timer.start();
		} else if (mode == 1 || mode == 3) {
			// modes solo

			// Création de l'interface graphique
			IFroggerGraphics graphic = new FroggerGraphic(width, height);
			// Création de la partie
			Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity, 1);
			// Création et liaison de la grenouille
			IFrog frog = (mode == 3) ? new Frog(game, width / 2) : new FrogInf(game, width/2);
			game.setFrog(frog);
			graphic.setFrog(frog);
			// Création et liaison de l'environnement
			IEnvironment env = (mode == 3) ? new Environment(game) : new EnvInf(game);
			game.setEnvironment(env);

			// Boucle principale : l'environnement s'actualise tous les tempo milisecondes
			Timer timer = new Timer(tempo, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					game.update();
					graphic.repaint();
				}
			});
			timer.setInitialDelay(0);
			timer.start();
		}
	}

	public static int MenuPrincipal() {
		Menu menu = new Menu();
		int param = 1;

		while(!menu.isSet()) {
			menu.showMenu();
			param = menu.getParam();
		}
		return param;
	}


}
