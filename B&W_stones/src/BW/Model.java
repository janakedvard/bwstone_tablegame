package BW;

import BW.Player;

public class Model {
    private int size;
    
    protected int counter;
    
    private Player actualPlayer;

    protected Player[][] table;

    public Model(int size) {
        this.size = size;
        actualPlayer = Player.B;
        counter = 5*size;
        table = new Player[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                table[i][j] = Player.NOBODY;
            }
        }
        
        for(int i = 0; i<size; i++) {
        boolean k = false;
        while(!k) {
        	int x = getRandomNumber(0, size);
        	int y = getRandomNumber(0, size);
        	if(table[x][y] == Player.NOBODY) {
        		table[x][y] = Player.B;
        		k = true;
        		}
        	}
        }
        for(int i = 0; i<size; i++) {
        boolean k = false;
        while(!k) {
        	int x = getRandomNumber(0, size);
        	int y = getRandomNumber(0, size);
        	if(table[x][y] == Player.NOBODY) {
        		table[x][y] = Player.W;
        		k = true;
        		}
        	}
        }
    }
    /**
     * a megfelel� soron eltolja a m�trix �rt�keit 1-el jobbra ut�na �t�ll�tja az aktu�lisan soron k�vetkez� j�t�kost
     * 
     * @param row t�bla sor�nak a sz�ma 
     * @param column t�bla oszlop�nak sz�ma 
     * @return vissza adja a m�r m�dos�tott t�bl�zatot
     */
    public Player[][] stepR(int row, int column) {

    	
    	for(int i = size-1; i>column; i--) {
    		System.out.println("table i:" + table[row][i] + "table i-1:" + table[row][i-1]);
    		table[row][i] = table[row][i-1];
    	}
        if (actualPlayer == Player.B) {
            actualPlayer = Player.W;
        } else {
            actualPlayer = Player.B;
        }
        table[row][column] = Player.NOBODY;
        
    	counter--;
    	return table;
    }
    
    /**
     * a megfelel� soron eltolja a m�trix �rt�keit 1-el balra ut�na �t�ll�tja az aktu�lisan soron k�vetkez� j�t�kost
     * 
     * @param row t�bla sor�nak a sz�ma 
     * @param column t�bla oszlop�nak sz�ma 
     * @return vissza adja a m�r m�dos�tott t�bl�zatot
     */
    public Player[][] stepL(int row, int column) {

    	for(int i=0; i<column; i++) {
    		System.out.println("table i:" + table[row][i] + "table i+1:" + table[row][i+1]);
    		table[row][i] = table[row][i+1];
    	}
        if (actualPlayer == Player.B) {
            actualPlayer = Player.W;
        } else {
            actualPlayer = Player.B;
        }
        table[row][column] = Player.NOBODY;
 
    	counter--;
    	return table;
    }
    /**
     * a megfelel� oszlopon eltolja a m�trix �rt�keit 1-el felfel� ut�na �t�ll�tja az aktu�lisan soron k�vetkez� j�t�kost
     * 
     * @param row t�bla sor�nak a sz�ma 
     * @param column t�bla oszlop�nak sz�ma 
     * @return vissza adja a m�r m�dos�tott t�bl�zatot
     */
    
    public Player[][] stepUp(int row, int column) {

    	for(int i=0; i<row; i++) {
    		System.out.println("table i:" + table[i][column] + "table i+1:" + table[i+1][column]);
    		table[i][column] = table[i+1][column];
    	}
        if (actualPlayer == Player.B) {
            actualPlayer = Player.W;
        } else {
            actualPlayer = Player.B;
        }
    	table[row][column] = Player.NOBODY;
    	counter--;
    	return table;
    }
    /**
     * a megfelel� oszlopon eltolja a m�trix �rt�keit 1-el lefel� ut�na �t�ll�tja az aktu�lisan soron k�vetkez� j�t�kost
     * 
     * @param row t�bla sor�nak a sz�ma 
     * @param column t�bla oszlop�nak sz�ma 
     * @return vissza adja a m�r m�dos�tott t�bl�zatot
     */
    public Player[][] stepDown(int row, int column) {
    	for(int i = size-1; i>row; i--) {
    		System.out.println("table i:" + table[i][column] + "table i-1:" + table[i-1][column]);
    		table[i][column] = table[i-1][column];
    	}
        if (actualPlayer == Player.B) {
            actualPlayer = Player.W;
        } else {
            actualPlayer = Player.B;
        }
        table[row][column] = Player.NOBODY;
    	
    	counter--;
    	return table;
    }
    
    public Player findWinner() {
    	int Wcount = 0;
    	int Bcount = 0;

    		for(int i = 0; i < size; i++) {
    			for(int j = 0; j < size; j++) {
    				if(table[i][j] == Player.B) {
    					Bcount++;
    				}else if(table[i][j] == Player.W) {
    					Wcount++;
    				}
    			}
    		}
 
    	System.out.println("Counter:"+counter);
    	if(counter!=0 && (Wcount==0||Bcount==0)) {
    		if(Wcount == 0) {
    			return Player.B;
    		}else {
    			return Player.W;
    		}
    	}
    	if(counter == 0) {
    	if(Wcount > Bcount) {
    		return Player.W;
    	}else if (Wcount < Bcount) {
    		return Player.B;
    	}else {
    		return Player.NOBODY;
    	}
    }
    	return Player.NOBODY;
    }

    public Player getActualPlayer() {
        return actualPlayer;
    }
    
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public Player getIndexedPlayer(int i,int j) {
    	return table[i][j];
    }
}
