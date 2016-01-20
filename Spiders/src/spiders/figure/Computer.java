package spiders.figure;

import java.util.ArrayList;
import java.util.Random;
import spiders.events.PlayerActionListener;
import spiders.model.CobWeb;
import spiders.model.CobWebObject;
import spiders.navigations.Direction;
import spiders.navigations.Position;

/**
 * Computer can think and move to food, which is nearest from him.
 * @author anhcx
 */
public class Computer extends Spider implements PlayerActionListener {

    /**
     * Constructor with a cobweb.
     * @param cw cobweb, where object will play on.
     */
    public Computer(CobWeb cw) {
        super(cw);
    }
    
    /**
     * Find the nearest food from him.
     * @return list of direction to nearest food.
     */
    public ArrayList<Direction> findTheNearestFood() {
        
        Position myPos = position();
        Position bestPos = null;
        int min = 999999;
        
        // find the min lenght
        for (CobWebObject e : cobweb().objects()) {
            if (e instanceof SpiderFood) {
                int cur_length = Position.lengthPath(myPos, e.position());
                System.err.println(name() + " see: " + e.position().toString() + " length " + cur_length);
                if (cur_length < min) {
                    bestPos = e.position();
                    min = cur_length;
                }
            }
        }
        
        // in case best pos is found, return a list of moveable to target
        if (bestPos != null) {
            System.err.println(name() + " dertemined target: " + bestPos.toString());
            return getDirectionTo(myPos, bestPos);
        } else
            return null;
    }
    
    private Direction randomDirection() {
        ArrayList<Direction> ret = new ArrayList<>();
        
        Direction west = Direction.west();
        if (moveIsPossible(west))
            ret.add(west);
        
        Direction east = Direction.east();
        if (moveIsPossible(east))
            ret.add(east);
        
        Direction north = Direction.north();
        if (moveIsPossible(north))
            ret.add(north);
        
        Direction south = Direction.south();
        if (moveIsPossible(south))
            ret.add(north);
        
        if (ret.size() > 0) {
            Random rand = new Random();
            int n = rand.nextInt(ret.size());
            return ret.get(n);
        } else 
            return null;
    }
    
    private ArrayList<Direction> getDirectionTo(Position start, Position finish) {
        ArrayList<Direction> ret = new ArrayList<>();
        
        int delta_row = start.row() - finish.row();
        int delta_col = start.column() - finish.column();
        
        if (delta_col == 0) {
            if (delta_row > 0)
                ret.add(Direction.north());

            if (delta_row < 0)
                ret.add(Direction.south());
        }
        
        if (delta_row == 0) {
            if (delta_col > 0)
                ret.add(Direction.west());

            if (delta_col < 0)
                ret.add(Direction.east());
        }
        
        if (delta_col != 0 && delta_row != 0) {
            if (delta_col > 0)
                ret.add(Direction.west());

            if (delta_col < 0)
                ret.add(Direction.east());
            
            if (delta_row > 0)
                ret.add(Direction.north());

            if (delta_row < 0)
                ret.add(Direction.south());
        }
        
        return ret;
    }

    @Override
    public void playerMoved() {
        boolean moved = false;
        ArrayList<Direction> dir = findTheNearestFood();
        if (dir == null) {
            Direction randDir = randomDirection();
            if (randDir != null) {
                tryMovingTo(randDir);
                moved = true;
            }
        } else {
            for (Direction d : dir) {
                if (tryMovingTo(d)) {
                    moved = true;
                    break;
                }
            }
        }

        if (!moved)
            System.err.println(name() + " can not move anyway.");
    }
}
