/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.figure;

import java.util.ArrayList;
import spiders.model.CobWeb;
import spiders.model.CobWebObject;
import spiders.navigations.Direction;
import spiders.navigations.Position;

/**
 *
 * @author anhcx
 */
public class Computer extends Spider {

    public Computer(CobWeb cw) {
        super(cw);
        _type = TypeObject.COMPUTER;
    }
    
    // AI when be here
    public Direction think() {
        Position myPos = position();
        Position bestPos = null;
        int min = 999999;
        // get all object is food
        ArrayList<CobWebObject> listFood = cobweb().objects(TypeObject.FOOD);
        
        // find the min lenght
        for (CobWebObject e : listFood) {
            int cur_length = Position.lengthPath(myPos, e.position());
            if (cur_length < min) 
                bestPos = e.position();
        }
        
        // get direction to best pos
        if (bestPos != null) {
            System.err.println(name() + " dertemined target: " + bestPos.toString());
            return Position.wayTo(myPos, bestPos);
        } else {
            System.err.println(name() + " moved random!");
            return Direction.randomDirection();
        }
    }
    
}
