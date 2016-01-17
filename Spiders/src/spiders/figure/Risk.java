package spiders.figure;

import spiders.model.CobWeb;
import spiders.model.CobWebObject;

/**
 * Risk is all objects can stun spider and bugs when they touch them or be touched by them.
 * @author anhcx
 */
public class Risk extends CobWebObject {

    public Risk(CobWeb cw) {
        super(cw);
    }
    
    
    // ------------ TIME STUN -------------------------
    protected int _stunTime;
    
    public int stunTime() {
        return _stunTime;
    }
    
}
