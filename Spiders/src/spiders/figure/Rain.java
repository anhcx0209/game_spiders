/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.figure;

import spiders.events.SpiderActionListener;
import spiders.model.CobWeb;
import spiders.navigations.Direction;
import spiders.navigations.Position;

/**
 * Rain is risk, can move down and can stun food and spider.
 * Rain always start moving from row 1.
 * When go to last row, it will disappear.
 * @author anhcx
 */
public class Rain extends Risk {

    public Rain(CobWeb cw) {
        super(cw);
        _type = TypeObject.RAIN;
        _stunTime = 3;
        _spider = null;
        _food = null;
        _timeStunFood = 2;
    }

    protected SpiderFood _food;
    private int _timeStunFood;
    
    protected boolean busyWithFood() {
        return (_food != null);
    }
     
    
    /**
     * Rain move down.
     */
    public void moveDown() {
        
        if (!busy() && !busyWithFood()) {
            Direction dir = Direction.south();
            Position newPos = position().next(dir);
            if (newPos.isValid())
                setPosition(newPos);
            
            // if rain move to pos of player or computer - spider on web.
            if (cobweb().have(TypeObject.PLAYER, newPos) || cobweb().have(TypeObject.COMPUTER, newPos)) {
                // stun it;
                _spider = cobweb().getSpider(newPos);
                _spider.stun();
                System.err.println(_spider.name() + " is stuned at " + position().toString());
            }
            
            // if rain move to pos of food.
            if (cobweb().have(TypeObject.FOOD, newPos)) {
                // stun it;
                _food = cobweb().getFood(newPos);
                _food.stun();
                System.err.println(_food.name() + " is stuned at " + position().toString());
            }
        } else {
            if (busyWithFood()) {
                _timeStunFood--;
                System.err.println(_food.name() + "try to move but not....");
                System.err.println("remain time: " + _timeStunFood);

                if (_timeStunFood == 0) {
                    _food.unStun();
                    _food = null;
                    _timeStunFood = 2;
                }
            }
        }
    }
    
}
