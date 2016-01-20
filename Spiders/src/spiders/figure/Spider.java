package spiders.figure;

import java.util.ArrayList;
import spiders.events.GameListener;
import spiders.events.SpiderActionListener;
import spiders.model.CobWeb;
import spiders.model.CobWebObject;
import spiders.navigations.Direction;
import spiders.navigations.Position;
import spiders.views.SpiderView;

/**
 * Spider - actor, can eat some bug, move, and be stunned by drop rain for a 
 * couple of seconds.
 * @author anhcx
 */
public abstract class Spider extends CobWebObject {

    /**
     * Constructor with a cobweb.
     * @param cw cobweb, where object will play on.
     */
    public Spider(CobWeb cw) {
        super(cw);
        _life = 10;
        _view = new SpiderView(this);
    }
    
    // ------------------- LIFE ------------------------
    private int _life;      
    
    /**
     * Get life of spider.
     * @return current life of spider.
     */
    public int life() {
        return _life;
    }
            
    /**
     * Decrease spider's life.
     * @param amount - number of life is decreased.
     */
    protected void decreaseLife(int amount) {
        _life -= amount;
    }
    
    /**
     * Increase spider's life.
     * @param amount - number of life is added.
     */
    private void increaseLife(int amount) {
        _life += amount;
    }
    
    /**
     * Spider try to move a position.
     * @param direct direction to moving.
     * @return moved or not.
     */
    public boolean tryMovingTo(Direction direct) {
        if (this.life() > 0) {
            if (moveIsPossible(direct)) {
                Position newPos = position().next(direct);
                boolean eatStunFood = false;
                
                // eating
                CobWebObject obj = cobweb().have(SpiderFood.class, newPos);
                if (obj != null) {
                    SpiderFood food = (SpiderFood)obj;
                    if (!food.isStuned()) {
                        increaseLife(food.value());
                        cobweb().removeObject(food);
                    } else {
                        eatStunFood = true;
                    }
                }
                
                // only realy move when isn't stuned or 
                if (!isStuned() && !eatStunFood)
                    setPosition(newPos);
                
                decreaseLife(1);
                
                // Лог
                System.err.println(name() + " moved to: " + position().toString());
                
                for (GameListener gel : _gameListeners) {
                    gel.needRepaint();
                }
                
                for (SpiderActionListener sal : _actionListeners) {
                    sal.spiderMoved(this);
                }
                
                return true;
            }
        }
        return false;
    }
    
    /**
     * Check this move can be realize or not.
     * @param direct - direction of move.
     * @return true if move possible, otherwise false.
     */
    protected boolean moveIsPossible(Direction direct) {
        Position newPos = position().next(direct);
        if (newPos.isValid()) {
            boolean haveCom = cobweb().have(Computer.class, newPos) != null;
            boolean havePlayer = cobweb().have(Player.class, newPos) != null;
            if (!haveCom && !havePlayer)
                return true;
        }
        
        return false;
    }
    
    // ------------------- GAME LISTENER -----------------------------
    private ArrayList<GameListener> _gameListeners = new ArrayList<>();
    
    public void addGEL(GameListener l) {
        _gameListeners.add(l);
    }
    
    public void removeGEL(GameListener l) {
        _gameListeners.remove(l);
    }
    
    // ------------------- Spider LISTENER -----------------------------
    private ArrayList<SpiderActionListener> _actionListeners = new ArrayList<>();
    
    public void addSAL(SpiderActionListener l) {
        _actionListeners.add(l);
    }
    
}
