/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Game panel - where all components are placed.
 * @author anhcx
 */
public class GamePanel extends JPanel implements KeyListener {
    
    /**
     * Constructor
     */
    public GamePanel() {
        int size = 2 * GAP + CELL_SIZE * 10;    // size = 10
        
        setPreferredSize(new Dimension(size, size));
        setBackground(Color.RED);
        
        addKeyListener(this);
    }
    
    // --------------------SIZE----------------------------
    private static final int CELL_SIZE = 50;
    private static final int GAP = 2;
    private static final int FONT_HEIGHT = 15;
    
    // --------------------COLOR---------------------------
    private static final Color BG_COLOR = new Color(175, 225, 175);
    private static final Color GRID_COLOR = Color.GREEN;
    
    // --------------------IMAGE---------------------------
    public static BufferedImage player_image;
    public static BufferedImage spd_image;
    public static BufferedImage fly_image;
    public static BufferedImage mosquite_image;
    public static BufferedImage rain_image;
    public static BufferedImage rock_image;
    

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
