package spiders.figure;

import spiders.events.SpiderActionListener;
import spiders.model.CobWeb;

/**
 * Stone is object, which is can not move.
 * Spider move to stone will be turn back and stun for 2 system step.
 * @author anhcx
 */
public class Stone extends Risk implements SpiderActionListener {

    public Stone(CobWeb cw) {
        super(cw);
        _type = TypeObject.STONE;
        _stunTime = 2;
        _position = cobweb().getFreePosition();
        _spider = null;
    }
    
    public boolean busy() {
        return _spider != null;
    }
    
    private Spider _spider;

    /**
     * Spider s move to stone.
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
