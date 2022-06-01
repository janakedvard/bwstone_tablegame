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
     * a megfelelõ soron eltolja a mátrix értékeit 1-el jobbra utána átállítja az aktuálisan soron következõ játékost
     * 
     * @param row tábla sorának a száma 
     * @param column tábla oszlopának száma 
     * @return vissza adja a már módosított táblázatot
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
     * a megfelelõ soron eltolja a mátrix értékeit 1-el balra utána átállítja az aktuálisan soron következõ játékost
     * 
     * @param row tábla sorának a száma 
     * @param column tábla oszlopának száma 
     * @return vissza adja a már módosított táblázatot
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
     * a megfelelõ oszlopon eltolja a mátrix értékeit 1-el felfelé utána átállítja az aktuálisan soron következõ játékost
     * 
     * @param row tábla sorának a száma 
     * @param column tábla oszlopának száma 
     * @return vissza adja a már módosított táblázatot
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
     * a megfelelõ oszlopon eltolja a mátrix értékeit 1-el lefelé utána átállítja az aktuálisan soron következõ játékost
     * 
     * @param row tábla sorának a száma 
     * @param column tábla oszlopának száma 
     * @return vissza adja a már módosított táblázatot
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
