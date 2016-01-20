package spiders.figure;

import java.util.Random;
import spiders.events.GameModelActionListener;
import spiders.model.CobWeb;
import spiders.model.CobWebObject;
import spiders.views.FoodView;

/**
 * Bug, which is food of spider.
 * They can fall in to cobweb and escape from it.
 * They can be eaten by spider, spider will convert their value to life.
 * @author anhcx
 */
public class SpiderFood extends CobWebObject implements GameModelActionListener {

    /**
     * Generate a spider's food
     * @param cw cobweb which food can be fall into.
     */
    public SpiderFood(CobWeb cw) {
        super(cw);
        makeFactor();
        _view = new FoodView(this);
    }
    
    // ------------------ SIZE ----------------------
    // size of food, it is value life will be increase when spider eat
    private int _value;          
    
    /**
     * Get size of spider foods.
     * @return
     */
    public int value() {
        return _value;
    }
    
    // ------------------ ACTION ---------------------
    
    /**
     * Bug can fall in to web an be sticked there.
     * @return action successful or not.
     */
    public boolean failIntoWeb() {
        return rollDice(_factorFallToWeb);
    }
    
    /**
     * When game step has increased, try to escape from the cobweb.
     */
    @Override
    public void stepIncrease() {
        if (rollDice(_factorEscape)) {
            stopWork();
        }
    }
    
    // ----------------- FACTOR TO ESCAPE AND FALL TO WEB ----------------
    int[] _factorFallToWeb;
    int[] _factorEscape;
    
    private void makeFactor() {
        Random rn = new Random();
        _value = rn.nextInt(11) + 1;
        
        _factorEscape = randomArray(_value); // 10 % gen food.
        _factorFallToWeb = randomArray(100 - _value); // 90% fall in to web.
    }

    private int[] randomArray(int n) {
        int[] array = new int[n];
        Random rn = new Random();
        boolean[] isChoosen = new boolean[100];
        
        for (int i = 0; i < n; i++) {
            boolean ok = true;
            
            while (ok) {
                int x = rn.nextInt(100);
                if (!isChoosen[x]) {
                    isChoosen[x] = true;
                    array[i] = x;
                    ok = false;
                }
            }
        }
        
        return array;
    }
    
    private boolean rollDice(int[] array) {
        Random rand = new Random();
        int x = rand.nextInt(100);
        for (int i = 0; i < array.length; i++)
            if (array[i] == x) {
                return true;
            }
        
        return false;
    }
    
}
