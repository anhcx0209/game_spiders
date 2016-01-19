package spiders.figure;

import java.util.Random;
import spiders.events.SpiderActionListener;
import spiders.model.CobWeb;
import spiders.model.CobWebObject;
import spiders.views.FoodView;

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
        Random rn = new Random();
        _size = rn.nextInt(11) + 1;
        _stuned = false;
        makeFactor();
        _view = new FoodView(this);
    }
    
    // ------------------ stun ----------------------
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
    
    // ------------------ size ----------------------
    // size of food, it is value life will be increase when spider eat
    private int _size;          
    
    public int size() {
        return _size;
    }
    
    /**
     * Bug can fall in to web an be sticked there.
     * @return action successful or not.
     */
    public boolean failIntoWeb() {
        Random rn = new Random();
        int factor = rn.nextInt(10) + 1;
        if (factor == 1 || factor == 2 || factor == 3 || factor == 5 || factor == 6) 
            return true;
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
