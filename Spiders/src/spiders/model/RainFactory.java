package spiders.model;

import java.util.ArrayList;
import spiders.figure.Rain;
import spiders.navigations.Position;

/**
 * Factory to generate drop rain.
 * @author anhcx
 */
public class RainFactory {
    private GameModel _gameModel;
    
    public RainFactory(GameModel gm) {
        _gameModel = gm;
    }
    
    public ArrayList<Rain> createRains() {
        ArrayList<Rain> ret = new ArrayList<>();
        for (int i = 1; i <= _gameModel.field().size(); i++) {
            Rain r = new Rain(_gameModel.field());
            r.setPosition(new Position(1, i));
            ret.add(r);
        }
        
        return ret;
    }
}
