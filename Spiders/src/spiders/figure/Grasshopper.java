/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.figure;

import spiders.model.CobWeb;

/**
 *
 * @author anhcx
 */
public class Grasshopper extends SpiderFood {

    public Grasshopper(CobWeb cw) {
        super(cw);
        _type = TypeObject.GRASSHOPPER;
        randomSize(SizeFood.HUGE);
    }
    
}
