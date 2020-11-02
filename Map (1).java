package adventuregame;
public class Map {
    
    static int[][] map;
    static int playerX;
    static int playerY;
    static int playerTile;
    
    Map(int x, int y){
        map = new int[][]{
            {5, 5, 5, 5, 5, 5, 5},                  //7x7 grid, 5's are to block the player from exiting the map
            {5, 4, 4, 4, 4, 4, 5},                  //5's is the 'water' surrounding the island. The player cannot swim.
            {5, 4, 4, 4, 4, 1, 5},                  //Player must find all 3 items and return to the boat @ 6 (S-E)
            {5, 4, 4, 4, 4, 3, 5},
            {5, 4, 4, 4, 4, 2, 5},
            {5, 4, 4, 4, 4, 0, 5},
            {5, 5, 5, 5, 5, 5, 5}
	};
        playerX = x;                                //set player x to x
        playerY = y;                                //set player y to y
        playerTile = map[x][y];                     //set playertime to map[x][y]
    }
    
    public int getPlayerX(){                        //method to get playerX
        return playerX;
    }
    
    public void setPlayerX(int x){
        playerX = x;                                //method to set playerX
    }
    
    public int getPlayerY(){                        //method to get playerY
        return playerY;
    }
    
    public void setPlayerY(int y){                  //method to set playerY
        playerY = y;
    }
    
    public int getPlayerTile(){                     //method to get playertile
        return playerTile;
    }
    
    public void setPlayerTile(int x, int y){        //method to set playertile
        playerTile = map[x][y];
    }
    
    public void setTile(int x, int y, int value){   //method to set tile to value
        map[x][y] = value;
    }
}
