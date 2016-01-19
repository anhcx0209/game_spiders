package spiders.model;

import java.util.ArrayList;
import java.util.Random;
import spiders.figure.Computer;
import spiders.figure.Rain;

/**
 * Factory to generate rain.
 * @author anhcx
 */
public class RainFactory {
    private GameModel _gameModel;
    
    /**
     * Constructor to create rain factory.
     * @param gm game model which factory will base on.
     */
    public RainFactory(GameModel gm) {
        _gameModel = gm;
    }
    
    /**
     * Create n drop rain on the first line of cobweb.
     * Position is randomly selected.
     * @param n number of rain will be created.
     * @return list of rains.
     */
    public ArrayList<Rain> createRains(int n) {
        ArrayList<Rain> ret = new ArrayList<>();
        
        Random rand = new Random();
        
        for (int i = 1; i <= n; i++) {
            // create rain
            Rain r = new Rain(_gameModel.field());
            r.setPosition(_gameModel.field().getFreePosition(1));
            
            // add spider listener
            _gameModel.player().addSAL(r);
            for (Computer com : _gameModel.computers()) {
                com.addSAL(r);
            }
            
            _gameModel.field().addObject(r);
            
            ret.add(r);
        }
        
        return ret;
    }
}