package spiders.model;

import java.util.ArrayList;
import spiders.figure.Computer;
import spiders.figure.Player;
import spiders.figure.Spider;

/**
 * Game model, determine when player have to end game, control the computers...
 * @author anhcx
 */
public class GameModel {
    
    private CobWeb _field;  // cobweb - where player play on
    private Level _level;
    
    /**
     * getter for _field
     * @return cobweb - where game is based on
     */
    public CobWeb field() {
        return _field;
    }
    
    /**
     * @return level of game
     */
    public Level level() {
        return _level;
    }
    
    /**
     * create a game model with pre-defined level
     * level = hard, medium and easy
     * @param l pre-define level for game model
     */
    public GameModel(Level l) {
        _field = new CobWeb(l.baseSize());
        _level = l;
        
        // create new player
        _player = new Player(_field);
        _field.addObject(_player);
        
        // create computer 
        for (int i = 0; i < _level.numberCom(); i++) {
            Computer com = new Computer(_field);
            _coms.add(com);
            _field.addObject(com);
        }
            
        // create food 
        _field.captureMoreFood(_foodFact.createFood(_level.numberBug()));
    }
    
    // ------------------ COMPUTER -------------------------
    private ArrayList<Computer> _coms = new ArrayList<>();
    
    public ArrayList<Computer> computers() {
        return _coms;
    }
    
    // ------------------ PLAYER -------------------------
    private Player _player;
    
    public Player player() {
        return _player;
    }
    
    // ------------------- FOOD FACTORY ------------------
    private FoodFactory _foodFact = new FoodFactory(this);
    
}
