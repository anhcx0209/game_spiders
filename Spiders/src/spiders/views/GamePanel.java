package spiders.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
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
        
        setPreferredSize(new Dimension(size, size));
        addKeyListener(this);    
    }
    
    public GameModel gameModel() {
        return _model;
    }
    
    // --------------------SIZE----------------------------
    private static final int CELL_SIZE = 50;
    private static final int GAP = 30;
    private static final int FONT_HEIGHT = 15;
    
    int cellSize() {
        return CELL_SIZE;
    }
    
    int fontHeight() {
        return FONT_HEIGHT;
    }
    
    // --------------------COLOR---------------------------
    private static final Color BG_COLOR = new Color(175, 225, 175);
    private static final Color GRID_COLOR = Color.GREEN;
    
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
            obj.view().drawOn(g, this);
        }
    }
    
    // ---------------- KEY EVENT-------------------------------------
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_UP) {         // move up
            System.err.println("Key up pressed!");
            _model.giveMoreFood();
            _model.player().playerMove(Direction.north());
        }
        else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {  // move down
            System.err.println("Key down pressed!");
            _model.giveMoreFood();
            _model.player().playerMove(Direction.south());
        }
        else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {  // move left
            System.err.println("Key left pressed!");
            _model.giveMoreFood();
            _model.player().playerMove(Direction.west());
        }
        else if(ke.getKeyCode() == KeyEvent.VK_RIGHT) { // move right
            System.err.println("Key right pressed!");
            _model.giveMoreFood();
            _model.player().playerMove(Direction.east());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    // ---------------------- GET LEFT TOP ----------------------
    Point coordPoint(Position pos) {
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
