package spiders.figure;

import spiders.model.CobWeb;

/**
 * Stone is non-moving holder, it can not move.
 * Spider move to position of stone, must spend 2 more step before get out of it.
 * @author anhcx
 */
public class Stone extends Holder {

    /**
     * Constructor to generate a Stone with pre-defined position.
     * @param cw cobweb stone will be placed.
     */
    public Stone(CobWeb cw) {
        super(cw);
        _timeHoldSpider = 2;
        _position = cobweb().getFreePosition();
        _spider = null;
    }
    
}
