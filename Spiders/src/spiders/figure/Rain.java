package spiders.figure;

import spiders.events.GameModelActionListener;
import spiders.model.CobWeb;
import spiders.model.CobWebObject;
import spiders.navigations.Direction;
import spiders.navigations.Position;

/**
 * Rain is holder, can move down and can capture food and spider.
 * Rain always start moving from row 1.
 * When go to last row, it will disappear.
 * @author anhcx
 */
public class Rain extends Holder implements GameModelActionListener {

    /**
     * Create a drop of rain.
     * @param cw cobweb where rain move on.
     */
    public Rain(CobWeb cw) {
        super(cw);
        _timeHoldSpider = 3;
        _spider = null;
        _timeHoldFood = 2;
        _food = null;
    }

    // -------------------- HOLDING FOOD -----------------------
    private SpiderFood _food;
    private int _timeHoldFood;
    
    public boolean isHoldingFood() {
        return (_food != null);
    }
    
    private void releaseFood() {
        _food.unStun();
        _food = null;
    }
    
    private void captureFood(SpiderFood f) {
        _food = f;
        _food.stun();
    }
    
    /**
     * Rain move down and capture every spider or food they can.
     * If rain is holding some food, it will decrease time and let move on 
     * when out of time.
     */
    public void moveDown() {
        
        if (!isHoldingSpider()&& !isHoldingFood()) {
            Direction dir = Direction.south();
            Position newPos = position().next(dir);
            if (newPos.isValid())
                setPosition(newPos);
            
            // if rain move to pos of player or computer - spider on web.
            CobWebObject c = cobweb().have(Computer.class, newPos);
            if (c != null) {
                captureSpider((Computer)c);
                System.err.println(_spider.name() + " is stuned at " + position().toString());
            }
            
            CobWebObject p = cobweb().have(Spider.class, newPos);
            if (p != null) {
                captureSpider((Player)p);
                System.err.println(_spider.name() + " is stuned at " + position().toString());
            }
            
            // if rain move to pos of food.
            CobWebObject f = cobweb().have(SpiderFood.class, newPos);
            if (f != null) {
                // stun it;
                captureFood((SpiderFood)f);
                System.err.println(_food.name() + " is stuned at " + position().toString());
            }
        } else {
            if (isHoldingFood()) {
                _timeHoldFood--;
                System.err.println(_food.toString() + "try to move but not....");
                System.err.println("remain time: " + _timeHoldFood);

                if (_timeHoldFood == 0) {
                    releaseFood();
                    _timeHoldFood = 2;
                }
            }
        }
    }

    @Override
    public void stepIncrease() {
        moveDown();
        if (position().row() == cobweb().size())
            stopWork();
    }
    
}
