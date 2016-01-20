package spider.factory;

import spiders.figure.Stone;
import spiders.model.GameModel;

/**
 * Factory to create stone.
 * @author anhcx
 */
public class StoneFactory extends ObjectFactory {

    /**
     * Constructor with a model.
     * @param gm game model.
     */
    public StoneFactory(GameModel gm) {
        super(gm);
    }
    
    /**
     * Create n stones with his position.
     * @param n number of stones will be created.
     */
    public void createStone(int n) {
        for (int i = 0; i < n; i++) {
            Stone stone = new Stone(model().field());
            stone.setPosition(model().field().getFreePosition());
        }
    }
    
}
