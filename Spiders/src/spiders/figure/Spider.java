package spiders.figure;

import java.util.ArrayList;
import spiders.events.GameEventListener;
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
     * generate a spider with his cobweb
     * @param cw - cobweb, which spiders move on
     */
    public Spider(CobWeb cw) {
        super(cw);
        _life = 10;
        _stuned = false;
        _view = new SpiderView(this);
    }
    
    // ------------------- life ------------------------
    private int _life;      // store life, when life = 0, spider die
    
    public int life() {
        return _life;
    }
            
    /**
     * decrease spider's life
     * @param amount - number of life is decreased
     */
    private void decreaseLife(int amount) {
        _life -= amount;
    }
    
    /**
     * increase spider's life
     * @param amount - number of life is added
     */
    private void increaseLife(int amount) {
        _life += amount;
    }
    
    // --------------- Stuned time ------------------------
    private boolean _stuned;
    
    public boolean isStuned() {
        return _stuned;
    }
    
    public void unStun() {
        _stuned = false;
    }
    
    public void stun() {
        _stuned = true;
    }
    
    /**
     * Spider try to move a position.
     * @param direct direction to moving.
     * @return moved or not.
     */
    public boolean moveTo(Direction direct) {
        if (this.life() > 0) {
            if (moveIsPossible(direct)) {
                Position newPos = position().next(direct);
                boolean eatStunFood = false;
                
                // eating
                if (cobweb().have(TypeObject.FOOD, newPos)) {
                    SpiderFood food = cobweb().getFood(newPos);
                    if (!food.isStuned()) {
                        increaseLife(food.size());
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
                
                for (GameEventListener gel : _gameListeners) {
                    gel.positionChanged();
                }
                
                for (SpiderActionListener sal : _actionListeners) {
                    sal.spiderMoving(this);
                }
                
                return true;
            }
        }
        return false;
    }
    
    private boolean moveIsPossible(Direction direct) {
        Position newPos = position().next(direct);
        if (newPos.isValid()) {
            boolean haveCom = cobweb().have(TypeObject.COMPUTER, newPos);
            boolean havePlayer = cobweb().have(TypeObject.PLAYER, newPos);
            if (!haveCom && !havePlayer)
                return true;
        }
        
        return false;
    }
    
    // ------------------- GAME LISTENER -----------------------------
    private ArrayList<GameEventListener> _gameListeners = new ArrayList<>();
    
    public void addGEL(GameEventListener l) {
        _gameListeners.add(l);
    }
    
    public void removeGEL(GameEventListener l) {
        _gameListeners.remove(l);
    }
    
    // ------------------- Spider LISTENER -----------------------------
    private ArrayList<SpiderActionListener> _actionListeners = new ArrayList<>();
    
    public void addSAL(SpiderActionListener l) {
        _actionListeners.add(l);
    }
    
}
