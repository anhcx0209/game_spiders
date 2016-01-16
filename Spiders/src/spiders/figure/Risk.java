package spiders.figure;

import spiders.model.CobWeb;
import spiders.model.CobWebObject;

/**
 * Risk is non-movable object or movable objects
 * they can freeze bug or spider when they touched them.
 * @author anhcx
 */
public class Risk extends CobWebObject {

    /**
     * Constructor
     * @param cw = cobweb, where risk will appear.
     */
    public Risk(CobWeb cw) {
        super(cw);
    }
    
    // ----------------- TIME VALUE ------------------
    protected int _time;
    
}
