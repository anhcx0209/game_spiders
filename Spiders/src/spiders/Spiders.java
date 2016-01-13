/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Main class
 * @author anhcx
 */
public class Spiders extends JFrame {
    
    // ------------ COMPONENTS ---------------------
    private JPanel _controlPanel = new JPanel(new FlowLayout());
    private JLabel _bestResultInfo = new JLabel();
    private JLabel _stepInfo = new JLabel();
    private JPanel _content = new JPanel();
    
    // ----------- MENU BAR ------------------------
    private JMenuBar _menu = null;
    
    private void createMenu() {
        _menu = new JMenuBar();
        JMenu fileMenu = new JMenu("Game");
        String menuItems[] = new String []{"New", "High Score", "Exit"};
        
        for (int i = 0; i < menuItems.length; i++) {
            JMenuItem item = new JMenuItem(menuItems[i]);
            item.setActionCommand(menuItems[i].toLowerCase());
            item.addActionListener(new FileMenuListener());
            fileMenu.add(item);
        }
        fileMenu.insertSeparator(1);

        _menu.add(fileMenu);
    }

    /**
     * Menu listener
     */
    private static class FileMenuListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            if ("exit".equals(command)) {
                System.exit(0);
            }
            
            if ("new".equals(command)) {
                Object[] possibleValues = { "Easy", "Medium", "Hard" };
                Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one level", "Game Level",  JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
            }
            
            if ("high score".equals(command)) {
                // read and show high score
            }
        }
    }
    
    // ------------- GAME INFO PANEL ----------------------
    private Box createInfoPanel() {
        
        Box box = Box.createHorizontalBox();
        
        box.add(Box.createHorizontalStrut(10));
        box.add(new JLabel("Step :"));
        _stepInfo.setText("?");
        box.add(Box.createHorizontalStrut(10));
        box.add(_stepInfo);
        
        box.add(Box.createHorizontalStrut(20));

        box.add(new JLabel("Best result :"));
        box.add(Box.createHorizontalStrut(10));
        _bestResultInfo.setText("?");
        box.add(Box.createHorizontalStrut(10));
        box.add(_bestResultInfo);

        box.add(Box.createHorizontalStrut(10));
        
        return box;
    }
    
    /**
     * Create a new game window
     */
    public Spiders() {
        // add control panel and game panel to content
        _controlPanel.add(createInfoPanel());
        _content.setLayout(new BorderLayout());
        _content.add(_controlPanel, BorderLayout.NORTH);
        setContentPane(_content);
        
        // add menu bar
        createMenu();
        setJMenuBar(_menu);
        
        setTitle("Пауки");
        
        // pre setup for window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Spiders();
            }
        });
    }
    
}
