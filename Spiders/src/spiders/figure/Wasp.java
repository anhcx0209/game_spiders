package spiders.figure;

import java.util.Random;
import spiders.model.CobWeb;

/**
 *
 * @author anhcx
 */
public class Wasp extends SpiderFood {

    public Wasp(CobWeb cw) {
        super(cw);
        _type = TypeObject.WASP;
        randomSize(SizeFood.MEDIUM);
        makeFactorEscape(SpiderFood.SizeFood.MEDIUM);
        
        // random wasp can bite   
//        Random rn = new Random();
//        int factor = rn.nextInt(2);
//        if (factor == 1)
//            _bite = true;
//        else 
//            _bite = false;
        _bite = true;
    }
    
    private boolean _bite;
    
    public boolean canBite() {
        return _bite;
    }
    
}
