package spiders.figure;

import spiders.model.CobWeb;

/**
 * Stone is object, which is can not move.
 * Spider move to stone will be turn back and stun for 2 system step.
 * @author anhcx
 */
public class Stone extends Risk {

    public Stone(CobWeb cw) {
        super(cw);
        _type = TypeObject.STONE;
        _stunTime = 2;
        _position = cobweb().getFreePosition();
    }
    
}
