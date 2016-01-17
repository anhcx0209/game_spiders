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
    }

    /**
     * Rain move down.
     */
    public void moveDown() {
        Direction dir = Direction.south();
        Position newPos = position().next(dir);
        if (newPos.isValid())
            setPosition(newPos);
    }
    
}
