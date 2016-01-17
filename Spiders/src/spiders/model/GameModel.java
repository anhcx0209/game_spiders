package spiders.model;

import java.util.ArrayList;
import spiders.events.GameEventListener;
import spiders.events.PlayerActionListener;
import spiders.figure.Computer;
import spiders.figure.Player;
import spiders.figure.Rain;
import spiders.figure.Stone;
import spiders.navigations.Direction;
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
        _player.setName("player");
        _player.addPAL(this);
        _field.addObject(_player);
        
        // create computer 
        for (int i = 0; i < _level.numberCom(); i++) {
            Computer com = new Computer(_field);
            com.setName("Com" + i);
            _coms.add(com);
            _field.addObject(com);
        }
            
        // create food
        _field.captureMoreFood(_foodFact.createFood(_level.numberBug()));
        
        // create stone
        for (int i = 0; i < _level.numberStone(); i++) {
            Stone s = new Stone(_field);
            _field.addObject(s);
            
            // add listener
            _player.addSAL(s);
            for (Computer com : _coms)
                com.addSAL(s);
        }
        
        // create rain
        ArrayList<Rain> rains = _rainFact.createRains();
        for (Rain rain : rains) {
            _rains.add(rain);
            field().addObject(rain);
            
            // add listener
            _player.addSAL(rain);
            for (Computer com : _coms)
                com.addSAL(rain);
        }
    }
    
    // ------------------ COMPUTER -------------------------
    private ArrayList<Computer> _coms = new ArrayList<>();
    
    public ArrayList<Computer> computers() {
        return _coms;
    }
    
    /**
    * Check computer if it die.
    */
    private void checkComDie() {
        boolean[] trash = new boolean[_coms.size()];
        
        for (int i = 0; i < _coms.size(); i++) {
            if (_coms.get(i).life() == 0) {
                field().removeObject(_coms.get(i));
                trash[i] = true;
                
                // fire trigger to game panel
                for (GameEventListener gel : _gameListeners)
                    gel.positionChanged();
            }
        }
        
        // remove from _coms
        for (int i = trash.length - 1; i >= 0; i--)
            if (trash[i])
                _coms.remove(i);
    }
    
    /**
    * Direct every computer.
    */
    private void playAsComputer() {
        // make computer move
        for (Computer com : _coms) {
            boolean moved = false;
            ArrayList<Direction> dir = com.think();
            for (Direction d : dir) {
                if (com.move(d)) {
                    System.err.println(com.name() + " move " + dir.toString());
                    moved = true;
                    break;
                }
            }
            
            if (!moved)
                System.err.println(com.name() + " can not move anyway");
        }
    }
    
    // ------------------ PLAYER -------------------------
    private Player _player;
    
    public Player player() {
        return _player;
    }
    
    private void checkGameOver() {
        // check game over
        if (player().life() == 0) {
            for (GameEventListener gel : _gameListeners)
                gel.gameOver();
        }
    }
    
    // ------------------- RAIN FACTORY ------------------
    private RainFactory _rainFact = new RainFactory(this);
    
    // ------------------- Rain --------------------------
    private ArrayList<Rain> _rains = new ArrayList<>();
    
    private void makeRainMove() {
        for (Rain rain : _rains)
            rain.moveDown();
    }
    
    
    // ------------------- FOOD FACTORY ------------------
    private FoodFactory _foodFact = new FoodFactory(this);
    
    private void spendMoreFood() {
        // generate more food
        if (_level.spin() && field().foodPerSpider() < 1.0) {
            _field.captureMoreFood(_foodFact.createFood(_level.numberBug()));
            
            // fire trigger to game panel
            for (GameEventListener gel : _gameListeners)
                gel.positionChanged();
        }
    }
    
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
        playAsComputer();
        
        spendMoreFood();
        
        // check computer die and remove it
        checkComDie();
        checkGameOver();
        
        // bugs are escaping
        field().letBugGoOut();
        // let rain move down
        makeRainMove();
        
        for (GameEventListener gel : _gameListeners)
            gel.positionChanged();
        
        increaseStep();
    }
    
    // ------------------- GAME LISTENER -----------------------------
    private ArrayList<GameEventListener> _gameListeners = new ArrayList<>();
    
    public void addGEL(GameEventListener l) {
        _gameListeners.add(l);
    }
}
