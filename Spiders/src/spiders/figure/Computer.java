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
public class Computer extends Spider {

    public Computer(CobWeb cw) {
        super(cw);
        _type = TypeObject.COMPUTER;
    }
    
}
