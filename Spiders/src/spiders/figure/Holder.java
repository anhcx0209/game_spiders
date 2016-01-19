package spiders.figure;

import spiders.events.SpiderActionListener;
import spiders.model.CobWeb;
import spiders.model.CobWebObject;
import spiders.views.HolderView;

/**
 * Holder is object can hold spider and bugs for a number of step
 * when they touch them or be touched by them.
 * @author anhcx
 */
public abstract class Holder extends CobWebObject implements SpiderActionListener {

    /**
     * Constructor, create new holder on the cobweb.
     * @param cw field, where risk will be placed.
     */
    protected Holder(CobWeb cw) {
        super(cw);
        _view = new HolderView(this);
    }
    
    // --------------- HOLDING SPIDER -------------------------
    protected int _timeHoldSpider;        // holding spider for how many steps
    protected Spider _spider;             // spider is holding by holder  
    
    /**
     * Return fact, if holder is holding a spider.
     * @return true if holder is holding, false if not.
     */
    public boolean isHoldingSpider() {
        return (_spider != null);
    }
    
    /**
     * Release spider, allow him to move.
     */
    protected void releaseSpider() {
        _spider.unStun();
        _spider = null;
    }
    
    /**
     * Capture a spider.
     * @param s spider will be captured.
     */
    protected void captureSpider(Spider s) {
        _spider = s;
        _spider.stun();
    }
    
    /**
     * This method will be acted when a spider has moved.
     * @param s spider just has move.
     */
    @Override
    public void spiderMoving(Spider s) {
        // if stone is busy to detaine someone
        if (isHoldingSpider()) {
            // if spider is "someone"
            if (s.equals(_spider)) {
                _timeHoldSpider--;
                System.err.println(s.name() + "try to move but not....");
                System.err.println("remain time: " + _timeHoldSpider);

                if (_timeHoldSpider == 0) {
                    releaseSpider();
                    _timeHoldSpider = 2;
                }
            }
        } else {
            if (s.position().equals(position()) && !s.isStuned()) {
                System.err.println(s.name() + " is stuned at " + position().toString());
                captureSpider(s);
            }
        }
    }
    
}
