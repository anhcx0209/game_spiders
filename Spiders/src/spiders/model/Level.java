/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Game's level
 * Hard - 
 * Easy - 
 * Medium - 
 * @author anhcx
 */
public class Level {
    // ----------------- CONSTANT --------------------
    private static final int N_FF_HARD = 10;
    private static final int N_FF_MEDIUM = 15;
    private static final int N_FF_EASY = 20;
    
    private int _baseSize;
    private int _numberOfComputer;
    private int _numberBug;
    private int[] _factorGenFood; 
    
    /**
     * @return number of bug must be generate per time.
     */
    public int numberBug() {
        return _numberBug;
    }
    
    /**
     * @return number of computer in start of game
     */
    public int numberCom() {
        return _numberOfComputer;
    }
    
    /**
     * @return size of game field
     */
    public int baseSize() {
        return _baseSize;
    }
    
    public boolean spin() {
        Random rand = new Random();
        int x = rand.nextInt(100);
        for (int i = 0; i < _factorGenFood.length; i++)
            if (_factorGenFood[i] == x)
                return true;
        
        return false;
    }
    
    /**
     * create a hard level
     * base size = 15
     * @return
     */
    public static Level hard() {
        Level l = new Level();
        l._baseSize = 12;
        l._numberOfComputer = 3;
        l._numberBug = 20;
        l.makeFactor(N_FF_HARD);
        
        return l;
    }
    
    /**
     * create easy level
     * base = 6
     * @return
     */
    public static Level easy() {
        Level l = new Level();
        l._baseSize = 10;
        l._numberOfComputer = 2;
        l._numberBug = 6;
        l.makeFactor(N_FF_EASY);
        
        int x = 100;
        return l;
    }
    
    /**
     * create a medium level
     * base size = 10
     * @return
     */
    public static Level medium() {
        Level l = new Level();
        l._baseSize = 10;
        l._numberOfComputer = 2;
        l._numberBug = 8;
        l.makeFactor(N_FF_MEDIUM);
        return l;
    }
    
    
    private void makeFactor(int n) {
        _factorGenFood = new int[n]; // 10 % gen food
        
        boolean[] flag = new boolean[100];
        Random rn = new Random();
        for (int i = 0; i < n; i++) {
            boolean ok = true;
            while (ok) {
                int k = rn.nextInt(100);
                if (!flag[k]) {
                    flag[k] = true;
                    _factorGenFood[i] = k;
                    ok = false;
                }
            }
        }
    }
}
