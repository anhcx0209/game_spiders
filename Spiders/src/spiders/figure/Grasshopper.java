/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.figure;

import java.util.Random;
import spiders.model.CobWeb;
import spiders.navigations.Position;

/**
 *
 * @author anhcx
 */
public class Grasshopper extends SpiderFood {

    public Grasshopper(CobWeb cw) {
        super(cw);
        _type = TypeObject.GRASSHOPPER;
        _percentJump = 50;
        randomSize(SizeFood.HUGE);
        makeFactorEscape(SpiderFood.SizeFood.HUGE);
        makeFactorJump();
    }
    
    // --------------------- FACTOR TO ESCAPE -------------------
    private int[] _factorJump;
    private int _percentJump = 40;
    
    private void makeFactorJump() {
        
        _factorJump = new int[_percentJump]; // 40 % jump
        
        boolean[] flag = new boolean[100];
        Random rn = new Random();
        for (int i = 0; i < _percentJump; i++) {
            boolean ok = true;
            while (ok) {
                int k = rn.nextInt(100);
                if (!flag[k]) {
                    flag[k] = true;
                    _factorJump[i] = k;
                    ok = false;
                }
            }
        }
    }
    
    /**
     * Bug try to jump.
     */
    public void jump() {
        Random rand = new Random();
        int x = rand.nextInt(100);
        for (int i = 0; i < _factorJump.length; i++)
            if (_factorJump[i] == x) {
                Position oldPos = _position;
                Position newPos = cobweb().getFreePosition();
                setPosition(newPos);
                
                System.err.println("Grasshopper in " + oldPos.toString()
                        + " is jumped to " + newPos.toString());
            }
    }
}
