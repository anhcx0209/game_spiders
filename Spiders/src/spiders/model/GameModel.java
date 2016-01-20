package spiders.model;

import spider.factory.RainFactory;
import spider.factory.FoodFactory;
import java.util.ArrayList;
import spiders.events.GameListener;
import spiders.events.GameModelActionListener;
import spiders.events.PlayerActionListener;
import spiders.figure.Computer;
import spiders.figure.Player;
import spiders.figure.Stone;
import spiders.navigations.Position;

/**
 * Game model, save all info about game and control logic game.
 * @author anhcx
 */
public class GameModel implements PlayerActionListener {
    
    private CobWeb _field;  // field - where player play on.
    private Level _level;   // level of game.
    
    /**
     * This is getter, return field, where spiders are moving on.
     * @return cobweb where game is based on.
     */
    public CobWeb field() {
        return _field;
    }
    
    /**
     * This is getter, return level of game is based on.
     * @return level - difficulty of game.
     */
    public Level level() {
        return _level;
    }
    
    // ---------------------- FACTORY ------------------------
    private RainFactory _rainFact = new RainFactory(this);
    
    /**
     * This is constructor, create a game model with pre-defined level
     * hard, easy or medium.
     * @param l pre-define level for game model.
     */
    public GameModel(Level l) {
        // create cobweb to play.
        _step = 0;
        _field = new CobWeb(l.baseSize());
        _level = l;
        this.addGML(_field);
        
        // set range for position.
        Position.setHorizontalRange(1, l.baseSize());
        Position.setVerticalRange(1, l.baseSize());
        
        // create player 
        _player = new Player(_field);
        
        for (int i = 0; i < _level.numberOfComputers(); i++) {
            Computer com = new Computer(_field);
            com.setName("Com" + i);
            _coms.add(com);
        }
        
        setUpField();
    }
    
    private void setUpField() {
        _rainFact.createRains(level().numberOfRain());
        
        // create food
        _field.captureFoods(_foodFact.createFood(_level.numberOfFoods()));
        
        // create stone
        for (int i = 0; i < _level.numberOfStone(); i++) {
            Stone s = new Stone(_field);
            _field.addObject(s);
            
            // add listener
            _player.addSAL(s);
            for (Computer com : _coms)
                com.addSAL(s);
        }
        
        // set position player and computer
        _player.setName("player");
        _player.setPosition(field().getFreePosition());
        field().addObject(_player);
        
        for (Computer com : _coms) {
            com.setPosition(field().getFreePosition());
            _player.addPAL(com);
            field().addObject(com);
        }
        
        _player.addPAL(this);
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
    
    private void checkGameOver() {
        
        // check computers
        for (Computer com : _coms) {
            if (com.life() == 0)
                field().removeObject(com);
        }
        
        // check player
        if (player().life() == 0) {
            field().removeObject(_player);
            for (GameListener gel : _gameListeners)
                gel.gameOver();
        }
    }
    
    // ------------------- FOOD FACTORY ------------------
    private FoodFactory _foodFact = new FoodFactory(this);
    
    /**
     * Generate more food and rain with a frequency.
     */
    public void makeMoreFoodAndRain() {
        // generate more food
        if (_level.spin() && field().foodPerSpider() < 1.0) {
            _field.captureFoods(_foodFact.createFood(_level.numberOfFoods()));
        }
        
        // generate more rain
        if (level().spin())
            _rainFact.createRains(level().numberOfRain());
    }
    
    // ------------------- STEP --------------------------
    private int _step;
    
    public int step() {
        return _step;
    }
    
    /**
     * Increase game step by 1.
     */
    private void increaseStep() {
        _step++;
    }

    @Override
    public void playerMoved() {
        
        // check computer die and remove it
        checkGameOver();
        
        for (GameModelActionListener gml : _gamemodelListeners) 
            gml.stepIncrease();
        
        increaseStep();
        
        for (GameListener gel : _gameListeners)
            gel.needRepaint();
    }
    
    // ------------------- GAME LISTENER -----------------------------
    private ArrayList<GameListener> _gameListeners = new ArrayList<>();
    
    public void addGEL(GameListener l) {
        _gameListeners.add(l);
    }
    
    // --------------------- Game model action listener ----------------
    private ArrayList<GameModelActionListener> _gamemodelListeners = new ArrayList<>();
    
    public void addGML(GameModelActionListener l) {
        _gamemodelListeners.add(l);
    }
}
