package spiders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import spiders.model.GameModel;
import spiders.navigations.Position;

/**
 * Game panel - where all components are placed.
 * @author anhcx
 */
public class GamePanel extends JPanel implements KeyListener {
    
    private GameModel _model;
    
    /**
     * Constructor
     */
    public GamePanel() {
        _model = new GameModel();
        
        int size = 2 * GAP + CELL_SIZE * (_model.field().size() - 1);
        
        //setSize(new Dimension(size, size));
        setPreferredSize(new Dimension(size, size));
        
        addKeyListener(this);    
    }
    
    // --------------------SIZE----------------------------
    private static final int CELL_SIZE = 50;
    private static final int GAP = 30;
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
    public void paintComponent(Graphics g) {
        // draw background
        int width = getWidth();
        int height = getHeight();
        
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.black);
        
        // draw grid
        drawGrid(g);
    }

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
    
    private Point coordPoint(Position pos) {
        int left = GAP + CELL_SIZE * (pos.column()-1);
        int top = GAP + CELL_SIZE * (pos.row()-1);
        
        return new Point(left, top);
    }

    private void drawGrid(Graphics g) {
        int width  = getWidth();
        int height = getHeight();

        g.setColor(GRID_COLOR);
        
        // vertical line
        for(int i = 1; i <= _model.field().size() + 1; i++) {
            int x = GAP + CELL_SIZE*(i-1);
            g.drawLine(x, 0, x, height);
        }
        // horizontal line
        for(int i = 1; i <= _model.field().size() + 1; i++) {
            int y = GAP + CELL_SIZE*(i-1);
            g.drawLine(0, y, width, y);
        }
    }
    
}
