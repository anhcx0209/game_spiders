package spiders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import spiders.figure.Computer;
import spiders.figure.Player;
import spiders.figure.Spider;
import spiders.figure.SpiderFood;
import spiders.figure.Stone;
import spiders.model.CobWebObject;
import spiders.model.GameModel;
import spiders.navigations.Direction;
import spiders.navigations.Position;

/**
 * Game panel - where all components are placed.
 * @author anhcx
 */
public class GamePanel extends JPanel implements KeyListener {
    
    private GameModel _model;
    
    /**
     * Constructor
     * @param gm game model
     */
    public GamePanel(GameModel gm) {
        _model = gm;
        
        int size = 2 * GAP + CELL_SIZE * (_model.field().size() - 1);
        
        //setSize(new Dimension(size, size));
        setPreferredSize(new Dimension(size, size));
        readImage();
        
        addKeyListener(this);    
    }
    
    public GameModel gameModel() {
        return _model;
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
    
    private void readImage() {
        try {
            player_image = ImageIO.read(new File("D:\\AnhCX\\Documents\\NetBeansProjects\\Spiders\\media\\player.png"));
            spd_image = ImageIO.read(new File("D:\\AnhCX\\Documents\\NetBeansProjects\\Spiders\\media\\spider.png"));
            fly_image = ImageIO.read(new File("D:\\AnhCX\\Documents\\NetBeansProjects\\Spiders\\media\\fly.png"));
            mosquite_image = ImageIO.read(new File("D:\\AnhCX\\Documents\\NetBeansProjects\\Spiders\\media\\mosquito.png"));
            rain_image = ImageIO.read(new File("D:\\AnhCX\\Documents\\NetBeansProjects\\Spiders\\media\\drop.png"));
            rock_image = ImageIO.read(new File("D:\\AnhCX\\Documents\\NetBeansProjects\\Spiders\\media\\rock.png"));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // ---------------- PAINT COMPONENTS ------------------
    @Override
    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        
        // draw background
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.black);
        
        // draw grid
        drawGrid(g);
        
        // draw player
        ArrayList<CobWebObject> lists = _model.field().objects();
        for (CobWebObject obj : lists) {
            
            if (obj instanceof Spider) 
                drawSpider(g, (Spider)obj);
            
            if (obj instanceof SpiderFood)
                drawFood(g, (SpiderFood)obj);
            
            if (obj instanceof Stone)
                drawStone(g, (Stone)obj);
        }
    }
    
    private void drawStone(Graphics g, Stone s) {
        Point lefTop = coordPoint(s.position());
        g.setColor(Color.RED);
        
        g.drawImage(rock_image, lefTop.x, lefTop.y, 50, 30, this);
        
        g.setColor(Color.BLACK);   // восстанваливаем цвет пера
    }
    
    private void drawFood(Graphics g, SpiderFood f) {
        Point lefTop = coordPoint(f.position());
        g.setColor(Color.RED);
        String str = " " + f.size();
        
        g.drawImage(fly_image, lefTop.x, lefTop.y, 50, 30, this);
        
        g.drawString(str, lefTop.x + (5 * CELL_SIZE)/8, lefTop.y + (2 * CELL_SIZE)/4 + FONT_HEIGHT);
        
        g.setColor(Color.BLACK);   // восстанваливаем цвет пера
    }
    
    private void drawSpider(Graphics g, Spider s) {
        Point lefTop = coordPoint(s.position());
        g.setColor(Color.RED);
        String str = " " + s.life();
        
        if (s instanceof Player)
            g.drawImage(player_image, lefTop.x, lefTop.y, 50, 30, this);
        
        if (s instanceof Computer)
            g.drawImage(spd_image, lefTop.x, lefTop.y, 50, 30, this);
        
        g.drawString(str, lefTop.x + (5 * CELL_SIZE)/8, lefTop.y + (2 * CELL_SIZE)/4 + FONT_HEIGHT);
        
        g.setColor(Color.BLACK);   // восстанваливаем цвет пера
    }

    // ---------------- KEY EVENT-------------------------------------
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_UP) {         // move up
            System.err.println("Key up pressed!");
            _model.player().playerMove(Direction.north());
        }
        else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {  // move down
            System.err.println("Key down pressed!");
            _model.player().playerMove(Direction.south());
        }
        else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {  // move left
            System.err.println("Key left pressed!");
            _model.player().playerMove(Direction.west());
        }
        else if(ke.getKeyCode() == KeyEvent.VK_RIGHT) { // move right
            System.err.println("Key right pressed!");
            _model.player().playerMove(Direction.east());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    // ---------------------- GET LEFT TOP ----------------------
    private Point coordPoint(Position pos) {
        int left = GAP + CELL_SIZE * (pos.column()-1) - 25;
        int top = GAP + CELL_SIZE * (pos.row()-1) - 25;
        
        return new Point(left, top);
    }
    
    // ------------------- DRAW GRID ----------------------------
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
