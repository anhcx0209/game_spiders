package spiders.figure;

import java.util.Random;
import spiders.model.CobWeb;
import spiders.model.CobWebObject;
import spiders.navigations.Position;

/**
 * Bug, which is food of spider.
 * They can fall in to cobweb and escape from it.
 * They can be eaten by spider, spider will convert their value to life.
 * @author anhcx
 */
public class SpiderFood extends CobWebObject {

    /**
     * Generate a spider's food
     * @param cw cobweb which food can be fall into.
     */
    public SpiderFood(CobWeb cw) {
        super(cw);
        _type = TypeObject.FOOD;
        makeFactor();
    }
    
    
    // ------------------ size ----------------------
    // size of food, it is value life will be increase when spider eat
    private int _size = 6;          
    
    public int size() {
        return _size;
    }
    
    /**
     * bug fall in to web an be sticked there.
     * @return action successful or not
     */
    public boolean failIntoWeb() {
        Random rn = new Random();
        int factor = rn.nextInt(10) + 1;
        if (factor == 1 || factor == 2 || factor == 3) {
            Position pos = cobweb().getFreePosition();
            if (pos != null) {
                setPosition(pos);
                return true;
            } else 
                return false;
        }
        else 
            return false;
    }
    
    // --------------------- FACTOR TO ESCAPE -------------------

    int[] _factorEscape;
    
    private void makeFactor() {
        _factorEscape = new int[_size]; // 10 % gen food
        
        boolean[] flag = new boolean[100];
        Random rn = new Random();
        for (int i = 0; i < _size; i++) {
            boolean ok = true;
            while (ok) {
                int k = rn.nextInt(100);
                if (!flag[k]) {
                    flag[k] = true;
                    _factorEscape[i] = k;
                    ok = false;
                }
            }
        }
    }
    
    /**
     * Bug try to escape.
     * @return successful or not.
     */
    public boolean escape() {
        Random rand = new Random();
        int x = rand.nextInt(100);
        for (int i = 0; i < _factorEscape.length; i++)
            if (_factorEscape[i] == x)
                return true;
        
        return false;
    }
}
