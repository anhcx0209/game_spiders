package spiders;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import spiders.events.GameEventListener;
import spiders.model.GameModel;
import spiders.model.Level;

/**
 * Main class
 * @author anhcx
 */
public class Spiders extends JFrame {
    
    // ------------ HIGH SCORE ---------------------
    private Map<Integer, String> _scoreBoard = new TreeMap();
    
    // ------------ COMPONENTS ---------------------
    private JPanel _controlPanel = new JPanel(new FlowLayout());
    private JLabel _bestResultInfo = new JLabel();
    private JLabel _stepInfo = new JLabel();
    private JPanel _content = new JPanel();
    private GamePanel _panel = new GamePanel(new GameModel(Level.easy()));
    
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
    private class FileMenuListener implements ActionListener {
        
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
                
                if (selectedValue != null) {
                    if (selectedValue.equals("Easy")) {
                        _panel = new GamePanel(new GameModel(Level.easy()));
                    }

                    if (selectedValue.equals("Medium")) {
                        _panel = new GamePanel(new GameModel(Level.medium()));
                    }

                    if (selectedValue.equals("Hard")) {
                        _panel = new GamePanel(new GameModel(Level.hard()));
                    }

                    repaintField();
                }
            }
            
            if ("high score".equals(command)) {
                // build content
                String content = "";
                for (Map.Entry<Integer, String> entry : _scoreBoard.entrySet()) {
                    content += "Score : " + entry.getKey() + " Name : " + entry.getValue();
                    content += "\n";
                }
                
                JOptionPane.showMessageDialog(null, content);
            }
        }
    }
    
    private void repaintField() {
        _content.removeAll();
        _controlPanel.removeAll();
        
        // add item to control panel
        _controlPanel.add(createInfoPanel());
        _content.setLayout(new BorderLayout());
        _content.add(_controlPanel, BorderLayout.NORTH);
        _content.add(_panel, BorderLayout.CENTER);
        
        // add listener to each spider
        _panel.gameModel().player().addGEL(new RepaintByAction());
        _panel.gameModel().addGEL(new RepaintByAction());
        
        _content.revalidate();
        pack();
        
        _panel.setFocusable(true);
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
        Set keys = _scoreBoard.keySet();
        Object[] objs = keys.toArray();
        String str = objs[objs.length - 1].toString();
        _bestResultInfo.setText(str);
        box.add(Box.createHorizontalStrut(10));
        box.add(_bestResultInfo);

        box.add(Box.createHorizontalStrut(10));
        
        return box;
    }
    
    /**
     * Create a new game window
     */
    public Spiders() {
        // read high score
        try {
            readHighScore();
        } catch (IOException ex) {
            Logger.getLogger(Spiders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        // add control panel and game panel to content
        _controlPanel.add(createInfoPanel());
        _content.setLayout(new BorderLayout());
        _content.add(_controlPanel, BorderLayout.NORTH);
        _content.add(_panel, BorderLayout.CENTER);
        setContentPane(_content);
        
        // add listener to each player
        _panel.gameModel().player().addGEL(new RepaintByAction());
        _panel.gameModel().addGEL(new RepaintByAction());
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
        
        _panel.setFocusable(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Spiders frame = new Spiders();
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        frame.writeWhenClose();
                        System.err.println("Save high scores successful.");
                    }
                });
            }
        });
    }
    
    // ----------------------- READ HIGH SCORE ----------------------
    private void readHighScore() throws FileNotFoundException, IOException {
        HashMap ret = new HashMap();
        BufferedReader br = new BufferedReader(new FileReader("D:\\Tmp\\hs.txt"));
        
        try {
            String name = br.readLine();
            String number = br.readLine();
            
            while (name != null) {
                int n = Integer.parseInt(number);
                ret.put(n, name);
                
                name = br.readLine();
                number = br.readLine();
            }
        } finally {
            br.close();
        }
        
        _scoreBoard = new TreeMap();
        _scoreBoard.putAll(ret);
    }
    
    // ----------------------- GAME EVENT LISTENER ------------------
    public class RepaintByAction implements GameEventListener {

        @Override
        public void positionChanged() {
            _stepInfo.setText("" + _panel.gameModel().step());
            _panel.repaint();
        }

        @Override
        public void gameOver() {
            String playername = (String)JOptionPane.showInputDialog(
                    null,
                    "Your score: " + _panel.gameModel().step() + "\n" +
                    "Enter your name:\n",
                    "Game Over",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "new player");
            if (playername != null)
                _scoreBoard.put(_panel.gameModel().step(), playername);
        }
        
    }
    
    private void writeWhenClose() {
        // write to file
        BufferedWriter bw = null;
        File file = new File("D:\\Tmp\\hs.txt");
        try {
            // create file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            
            
            // write all map to file
            for (Map.Entry<Integer, String> entry : _scoreBoard.entrySet()) {
                bw.write(entry.getValue());
                bw.newLine();
                bw.write(entry.getKey().toString());
                bw.newLine();
            }
            
        } catch (IOException ex) {
                Logger.getLogger(Spiders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Spiders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }
}
