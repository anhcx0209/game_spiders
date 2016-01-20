package spider.factory;

import spiders.model.GameModel;

/**
 * Factory to create object.
 * @author anhcx
 */
public abstract class ObjectFactory {
    private GameModel _gameModel;
    
    protected GameModel model() {
        return _gameModel;
    }

    public ObjectFactory(GameModel gm) {
        _gameModel = gm;
    }
}
