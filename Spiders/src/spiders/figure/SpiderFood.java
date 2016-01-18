package spiders.figure;

import java.util.Random;
import spiders.model.CobWeb;
import spiders.model.CobWebObject;

/**
 * Bug, which is food of spider.
 * They can fall in to cobweb and escape from it.
 * They can be eaten by spider, spider will convert their value to life.
 * @author anhcx
 */
public abstract class SpiderFood extends CobWebObject {
    
    /**
     * Generate a spider's food
     * @param cw cobweb which food can be fall into.
     */
    public SpiderFood(CobWeb cw) {
        super(cw);
    }
    
    // ------------------ size ----------------------
    // size of food, it is value life will be increase when spider eat
    private int _size;
    
    public int size() {
        return _size;
    }
    
    public enum SizeFood {
        SMALL, MEDIUM, HUGE
    }
    
    public SizeFood sizeFood() {
        if (_size >= 1 && _size <= 10)
            return SizeFood.SMALL;
        else
            if (_size >= 11 && _size <= 13)
                return SizeFood.MEDIUM;
            else
                return SizeFood.HUGE;
            
    }
    
    protected void randomSize(SizeFood cls) {
        Random rnd = new Random();
        int min = 0;
        int max = 0;
        switch (cls) {
            case SMALL:
                min = 1;
                max = 10;
                break;
            case MEDIUM:
                min = 11;
                max = 13;
                break;
            case HUGE:
                min = 15;
                max = 14;
                break;
            default:
                throw new AssertionError(cls.name());
        }
        
        _size = min + rnd.nextInt(max - min + 2);
    }
    
    /**
     * bug fall in to web an be sticked there.
     * @return action successful or not
     */
    public boolean failIntoWeb() {
//        Random rn = new Random();
//        int factor = rn.nextInt(10) + 1;
//        if (factor == 2 || factor == 3) {
//            Position pos = cobweb().getFreePosition();
//            if (pos != null) {
//                setPosition(pos);
//                return true;
//            } else 
//                return false;
//        }
//        else 
//            return false;
        setPosition(cobweb().getFreePosition());
        return true;
    }
    
    // --------------------- FACTOR TO ESCAPE -------------------

    int[] _factorEscape;
    
    protected void makeFactorEscape(SizeFood cls) {
        int percent = 0;
        
        switch (cls) {
            case SMALL:
                percent = 10;
                break;
            case MEDIUM:
                percent = 20;
                break;
            case HUGE:
                percent = 30;
                break;
            default:
                throw new AssertionError(cls.name());
            
        }
        
        _factorEscape = new int[percent]; // 50 % escape
        
        boolean[] flag = new boolean[100];
        Random rn = new Random();
        for (int i = 0; i < percent; i++) {
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
            if (_factorEscape[i] == x) {
                
                return true;
            }
                
        
        return false;
    }
}
