package spiders.figure;

import spiders.model.CobWeb;

/**
 * Stone can not move, and freeze spider when they touch them.
 * @author anhcx
 */
public class Stone extends Risk {

    public Stone(CobWeb cw) {
        super(cw);
        _type = TypeObject.STONE;
        _time = 2;
    }
    
}
