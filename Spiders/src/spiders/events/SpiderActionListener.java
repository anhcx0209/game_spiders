package spiders.events;

import java.util.EventListener;
import spiders.figure.Spider;
import spiders.navigations.Position;

/**
 * Listen all action from spider.
 * @author anhcx
 */
public interface SpiderActionListener extends EventListener {
    public void spiderMoving(Spider s);
}
