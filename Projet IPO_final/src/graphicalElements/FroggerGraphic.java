package graphicalElements;

import javax.swing.*;

import gameCommons.IFrog;
import util.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	private final ArrayList<Element> elementsToDisplay;
	private final int pixelByCase = 16;
	private final int width;
	private final int height;
	private IFrog frog1;
	private IFrog frog2;
	private final JFrame frame;


	private double time = -1;
	private int score = -1;
	private int scoreJ2 = -1;

	boolean isTimeSet = false;
	boolean isScoreSet = false;

	public FroggerGraphic(int width, int height) {
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>();

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));

		JFrame frame = new JFrame("Frogger");
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Element e : elementsToDisplay) {
			g.setColor(e.color);
			g.fillRect(pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), pixelByCase, pixelByCase - 1);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					frog1.move(Direction.up);
					break;
				case KeyEvent.VK_DOWN:
					frog1.move(Direction.down);
					break;
				case KeyEvent.VK_LEFT:
					frog1.move(Direction.left);
					break;
				case KeyEvent.VK_RIGHT:
					frog1.move(Direction.right);
			}
			if (frog2 != null) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_Z:
						frog2.move(Direction.up);
						break;
					case KeyEvent.VK_S:
						frog2.move(Direction.down);
						break;
					case KeyEvent.VK_Q:
						frog2.move(Direction.left);
						break;
					case KeyEvent.VK_D:
						frog2.move(Direction.right);
						break;
				}
			}
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}

	public void setFrog(IFrog frog1, IFrog frog2) {
		this.frog1 = frog1;
		this.frog2 = frog2;
	}

	public void setFrog(IFrog frog) {
		this.frog1 = frog;
		this.frog2 = null;
	}

	public void endGameScreen(String s) {
		frame.remove(this);
		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		frame.repaint();

	}

	public void endGameScreen(String s, int z, int timer) {
		this.setTimeScore(timer, z);

		frame.remove(this);
		JLabel message = new JLabel(s);
		message.setFont(new Font("Verdana", 1, 20));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setVerticalAlignment(SwingConstants.CENTER);
		message.setSize(this.getSize());

		JLabel score = new JLabel("score: " + this.score + "     chrono: " + this.time + " sec");
		score.setFont(new Font("Verdana", 1, 15));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setVerticalAlignment(message.getVerticalAlignment() + 1);
		score.setSize(this.getSize());

		frame.getContentPane().add(message);
		frame.getContentPane().add(score);
		frame.repaint();
	}

	public void endGameScreen2(String s, int j1, int j2, int timer) {
		this.setTimeScore(timer, j1, j2);

		frame.remove(this);
		JLabel message = new JLabel(s);
		message.setFont(new Font("Verdana", 1, 20));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setVerticalAlignment(SwingConstants.CENTER);
		message.setSize(this.getSize());

		JLabel score = new JLabel("score: J1 " + this.score + ", J2 " + this.scoreJ2 + "     chrono: " + this.time + " sec");
		score.setFont(new Font("Verdana", 1, 15));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setVerticalAlignment(message.getVerticalAlignment() + 1);
		score.setSize(this.getSize());

		frame.getContentPane().add(message);
		frame.getContentPane().add(score);
		frame.repaint();
	}

	private void setTimeScore(int timer, int score)
	{
		if (!isTimeSet) {
			this.time = timer * 0.1;   // l'écran s'actualise toute les 100ms (1ms = 0.001s, 100ms = 0.1s)
			this.time = (double)(Math.round(time * 100)/100); // on arrondis à deux chiffres après la virgule.
			isTimeSet = true;
		}
		if (!isScoreSet) {
			this.score = score;
			isScoreSet = true;
		}
	}

	private void setTimeScore(int timer, int scoreJ1, int scoreJ2) {
		if (!isTimeSet) {
			this.time = timer * 0.1;
			this.time = (double)(Math.round(timer * 100) / 100);
			isTimeSet = true;
		}
		if (!isScoreSet) {
			this.score = scoreJ1;
			this.scoreJ2 = scoreJ2;
			isScoreSet = true;
		}
	}

}
