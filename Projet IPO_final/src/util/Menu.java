package util;

import gameCommons.Main;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Menu extends JPanel implements KeyListener {
    private JFrame frame;
    private JLabel mode;
    private int param;
    private boolean isSet = false;
    private JTextField typingArea = new JTextField(20);

    public Menu() {
        this.frame = new JFrame("Frogger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(Main.DEFAULT_WIDTH * 16, Main.DEFAULT_HEIGHT * 16));
        frame.pack();


        this.mode = new JLabel("<-     Mode 1 Joueur Infini     ->");
        mode.setFont(new Font("Verdana", 1, 20));
        mode.setHorizontalAlignment(SwingConstants.CENTER);
        mode.setSize(new Dimension(20, 20));
        mode.setVisible(true);

        this.param = 1;

        frame.add(mode);
        frame.setVisible(true);
        frame.addKeyListener(this);
        typingArea.addKeyListener(this);

    }

    // p = true -> vers la droite
    // p = false -> vers la gauche
    private void switchMode(boolean p) {
        if (p)
            param ++;
        else
            param--;

        if (param < 1)
            param = 4;

        if (param > 4)
            param = 1;

        //this.showMenu();
    }

    public int getParam()
    {
        return this.param;
    }

    public void showMenu() {
        if (param == 1) {
            mode.setText("<-     Mode 1 Joueur Infini     ->");
            mode.setVisible(true);
        }
        else if (param == 2) {
            mode.setText("<-    Mode 2 Joueurs Infini     ->");
            mode.setVisible(true);
        }
        else if (param == 3) {
            mode.setText("<-   Mode 1 Joueur Classique    ->");
            mode.setVisible(true);
        }
        else if (param == 4) {
            mode.setText("<-   Mode 2 Joueurs Classique   ->");
            mode.setVisible(true);
        }
    }

    public boolean isSet() {
        return this.isSet;
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.switchMode(false);
                break;
            case KeyEvent.VK_RIGHT:
                this.switchMode(true);
                break;
            case KeyEvent.VK_ENTER:
                this.isSet = true;
                break;
        }
    }
}
