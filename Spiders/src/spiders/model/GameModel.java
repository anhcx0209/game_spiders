package spiders.model;

import java.util.ArrayList;
import spiders.events.GameEventListener;
import spiders.events.PlayerActionListener;
import spiders.figure.Computer;
import spiders.figure.Player;
import spiders.figure.Spider;
import spiders.navigations.Position;

/**
 * Game model, determine when player have to end game, control the computers...
 * @author anhcx
 */
public class GameModel implements PlayerActionListener {
    
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
        _step = 0;
        _field = new CobWeb(l.baseSize());
        Position.setHorizontalRange(1, l.baseSize());
        Position.setVerticalRange(1, l.baseSize());
        _level = l;
        
        // create new player
        _player = new Player(_field);
        _player.addPAL(this);
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
    
    // ------------------- STEP --------------------------
    private int _step;
    
    public int step() {
        return _step;
    }
    
    /**
     * Increase game step by 1.
     */
    public void increaseStep() {
        _step++;
    }

    @Override
    public void playerMoved() {
        if (player().life() == 0) {
            for (GameEventListener gel : _gameListeners)
                gel.gameOver();
        }
    }
    
    // ------------------- GAME LISTENER -----------------------------
    private ArrayList<GameEventListener> _gameListeners = new ArrayList<>();
    
    public void addGEL(GameEventListener l) {
        _gameListeners.add(l);
    }
    
    public void removeGEL(GameEventListener l) {
        _gameListeners.remove(l);
    }
}
