package spiders.figure;

import spiders.events.SpiderActionListener;
import spiders.model.CobWeb;
import spiders.model.CobWebObject;

/**
 * Risk is all objects can stun spider and bugs when they touch them or be touched by them.
 * @author anhcx
 */
public class Risk extends CobWebObject implements SpiderActionListener {

    public Risk(CobWeb cw) {
        super(cw);
    }
    
    // --------------- TIME STUN -------------------------
    protected int _stunTime;
    
    public int stunTime() {
        return _stunTime;
    }
    
    // ---------------- SPIDER ---------------------------
    protected boolean busy() {
        return (_spider != null);
    }
    
    protected Spider _spider;
   
    
    /**
     * Spider s move to risk.
     * @param s who is moving to.
     */
    @Override
    public void spiderMoving(Spider s) {
        // if stone is busy to detaine someone
        if (busy()) {
            // if spider is "someone"
            if (s.equals(_spider)) {
                _stunTime--;
                System.err.println(s.name() + "try to move but not....");
                System.err.println("remain time: " + _stunTime);

                if (_stunTime == 0) {
                    _spider = null;
                    s.unStun();
                    _stunTime = 2;
                }
            }
        } else {
            // if stone is free, 
            if (s.position().equals(position()) && !s.isStuned()) {
                System.err.println(s.name() + " is stuned at " + position().toString());
                _spider = s;
                s.stun();
            }
        }
    }
    
}
