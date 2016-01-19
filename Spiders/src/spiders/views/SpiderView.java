/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import spiders.figure.Player;
import spiders.figure.Spider;

/**
 * Player appearance.
 * @author anhcx
 */
public class SpiderView extends ObjectView {
    
    private static final String PLAYER_IMG = ROOT.concat("\\image\\player.png");
    private static final String COMPUTER_IMG = ROOT.concat("\\image\\computer.png");
    
    public SpiderView(Spider obj) {
        super(obj);
        if (obj instanceof Player)
            getImageFrom(PLAYER_IMG);
        else 
            getImageFrom(COMPUTER_IMG);
    }
    
    @Override
    public void drawOn(Graphics g, GamePanel p) {
        Spider s = (Spider)object();
        
        Point pos = p.coordPoint(s.position());
        String str = "" + s.life();
        
        g.setColor(Color.RED);
        g.drawImage(_image, pos.x, pos.y, 50, 30, p);
        g.drawString(str, pos.x + (5 * p.cellSize())/8, pos.y + (2 * p.cellSize())/4 + p.fontHeight());
    }
    
}
