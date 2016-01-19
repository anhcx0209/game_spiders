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
 * Game model, objects control logic game and play as computers.
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
        _player.addPAL(this);
        
        for (Computer com : _coms) {
            com.setPosition(field().getFreePosition());
            field().addObject(com);
        }
    }
    
    // ------------------ COMPUTER -------------------------
    private ArrayList<Computer> _coms = new ArrayList<>();
    
    public ArrayList<Computer> computers() {
        return _coms;
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
        
        // check computers
        for (Computer com : _coms) {
            if (com.life() == 0)
                field().removeObject(com);
        }
        
        // check player
        if (player().life() == 0) {
            field().removeObject(_player);
            for (GameEventListener gel : _gameListeners)
                gel.gameOver();
        }
    }
    
    // ------------------- Rain --------------------------
    private void makeRainMove() {
        ArrayList<CobWebObject> rains = field().objects(CobWebObject.TypeObject.RAIN);
        for (CobWebObject obj : rains) {
            Rain r = (Rain)obj;
            r.moveDown();
        }
    }
    
    
    // ------------------- FOOD FACTORY ------------------
    private FoodFactory _foodFact = new FoodFactory(this);
    
    public void giveMoreFood() {
        // generate more food
        if (_level.spin() && field().foodPerSpider() < 1.0) {
            _field.captureFoods(_foodFact.createFood(_level.numberOfFoods()));
            
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
        
        // check computer die and remove it
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
