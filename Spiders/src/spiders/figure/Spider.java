package spiders.figure;

import java.util.ArrayList;
import spiders.events.GameEventListener;
import spiders.model.CobWeb;
import spiders.model.CobWebObject;
import spiders.navigations.Direction;
import spiders.navigations.Position;

/**
 * Spider - actor, can eat some bug, move, and be stunned by drop rain for a 
 * couple of seconds.
 * @author anhcx
 */
public class Spider extends CobWebObject  {

    /**
     * generate a spider with his cobweb
     * @param cw - cobweb, which spiders move on
     */
    public Spider(CobWeb cw) {
        super(cw);
        _position = cw.getFreePosition();
        _life = 10;
    }
    
    // ------------------- life ----------------
    private int _life;      // store life, when life = 0, spider die
    
    public int life() {
        return _life;
    }
            
    /**
     * decrease spider's life
     * @param amount - number of life is decreased
     */
    public void decreaseLife(int amount) {
        _life -= amount;
    }
    
    /**
     * increase spider's life
     * @param amount - number of life is added
     */
    public void increaseLife(int amount) {
        _life += amount;
    }
    
    /**
     * Spider move.
     * @param direct direction to moving.
     * @return moved or not
     */
    public boolean move(Direction direct) {
        if (this.life() > 0) {
            if (moveIsPossible(direct)) {
                Position newPos = position().next(direct);
                
                if (cobweb().have(TypeObject.FOOD, newPos)) {
                    SpiderFood food = cobweb().getFood(newPos);
                    
                    if (food.type() == TypeObject.WASP) {
                        Wasp w = (Wasp)food;
                        if (w.canBite()) {
                            System.err.println(this.name() + "is bited.");
                            decreaseLife(food.size());
                        }
                    } else 
                        increaseLife(food.size());
                    
                    cobweb().removeObject(food);
                }
                
                setPosition(newPos);
                decreaseLife(1);
                
                // Лог
                System.err.println(name() + " moved to: " + position().toString());
                
                for (GameEventListener gel : _gameListeners) {
                    gel.positionChanged();
                }
                
//                for (SpiderActionListener sal : _spiderListeners) {
//                    sal.spiderMoving(this);
//                }
                
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
    
//    // ------------------- SPIDER LISTENER -----------------------------
//    private ArrayList<SpiderActionListener> _spiderListeners = new ArrayList<>();
//    
//    public void addSAL(SpiderActionListener l) {
//        _spiderListeners.add(l);
//    }
//    
//    public void removeSAL(SpiderActionListener l) {
//        _spiderListeners.remove(l);
//    }
    
}
