package BW;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.*;

public class Window extends BaseWindow {

    private int size;
    private Model model;
    private JLabel label;
    private MainWindow mainWindow;
    Player[][] t;
    
    public Window(int size, MainWindow mainWindow) {
        this.size = size;
        this.mainWindow = mainWindow;
        mainWindow.getWindowList().add(this);
        model = new Model(size);

        JPanel top = new JPanel();
        
        label = new JLabel();
        updateLabelText();
        
        JButton newGameButton = new JButton();
        newGameButton.setText("�j j�t�k");
        newGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
            
        });
        
        top.add(label);
        top.add(newGameButton);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                addButton(mainPanel, i, j);
            }
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
       
    }
    /**
     * l�trehozza a sz�ks�ges gombokat amikhez hozz�ad egy actionListener-t ami hat�s�ra hozz�ad egy KeyListener ami figyeli, hogy a kattint�s ut�n
     * milyen gombot nyomunk meg �s annak seg�ts�g�vel megh�vja a sz�ks�g�s l�p�s met�dusokat. Ellen�rzi van-e nyertes ha van akkor egy dial�gus ablakkal
     * ezt jelzi is. V�g�l be�ll�tja a gombok cimk�j�t �s hozz�adja a panelhez
     * 
     * @param panel panel objektum amihez hozz�adjuk a gombokat
     * @param i i koordin�ta seg�ts�g�vel hozzuk l�tre a gombokat �s h�vjuk meg a l�p�s met�dusokat
     * @param j j koordin�ta seg�ts�g�vel hozzuk l�tre a gombokat �s h�vjuk meg a l�p�s met�dusokat
     */
    private void addButton(JPanel panel, final int i,final int j) {
        final JButton button = new JButton();
        t = model.table;
        button.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		button.addKeyListener(new KeyAdapter() {
        			@Override
        			public void keyReleased(KeyEvent k) {
        				
        				Player bC = model.getIndexedPlayer(i, j);
        				if(bC == model.getActualPlayer() && bC!=Player.NOBODY) {
        				char keyc = k.getKeyChar();
        				
        				switch(keyc) {
        				case 'w':
        					System.out.println(i+" "+ j);
        					t = model.stepUp(i, j);
        					break;
        				case 's':
        					System.out.println(i+" "+ j);
        					t = model.stepDown(i, j);
        					break;
        				case 'a':
        					System.out.println(i+" "+ j);
        					t = model.stepL(i, j);
        					break;
        				case 'd':
        					System.out.println(i+" "+ j);
        					t = model.stepR(i,j);
        					break;
        				}
        				buttonRefresh(panel);
        				updateLabelText();
        				Player nwinner = model.findWinner();
        				if(model.counter !=0 && nwinner !=Player.NOBODY) {
        					showGameOverMessage(nwinner);
        				}
        				
        				if(model.counter == 0) {
        					Player winner = model.findWinner();
        					showGameOverMessage(winner);
        				}
        				}
        				
        			}
        		});
        		}
        	});
        
        	if(t[i][j] == Player.B || t[i][j] == Player.W) {
        		button.setText(t[i][j].name());
        	}
        	
        	panel.add(button);
        	
        	}
        	
    

    private void showGameOverMessage(Player winner) {
        JOptionPane.showMessageDialog(this,
                "J�t�k v�ge. Nyert: " + winner.name());
        newGame();
    }
    
    private void newGame() {
        Window newWindow = new Window(size, mainWindow);
        newWindow.setVisible(true);
        
        this.dispose();
        mainWindow.getWindowList().remove(this);
    }
    
    private void updateLabelText() {
        label.setText("Aktu�lis j�t�kos: "
                + model.getActualPlayer().name());
    }

    @Override
    protected void doUponExit() {
        super.doUponExit();
        mainWindow.getWindowList().remove(this);
    }
    

	protected void buttonRefresh(JPanel panel) {
		panel.removeAll();
		panel.revalidate();
		for(int i = 0; i < size; i++) {
			
			for(int j = 0; j < size; j++) {
				
				addButton(panel, i ,j);
			}
		}
	}
}
