package spiders.events;

import java.util.EventListener;
import spiders.figure.Spider;

/**
 * Listen all action from spider.
 * @author anhcx
 */
public interface SpiderActionListener extends EventListener {

    /**
     * Fire when spider successfully move his spider..
     * @param s
     */
    public void spiderMoved(Spider s);
}
