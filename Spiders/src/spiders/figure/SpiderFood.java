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
    
    /**
     *
     * @return
     */
    public boolean escape() {
        return true;
    }
}
