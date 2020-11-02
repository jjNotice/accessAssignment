package adventuregame;

import java.util.Scanner;                           //Importing scanner so the player can make choices by use of input

public class AdventureGame {

    static String dir = "";                         //Player's input command
    static int player;                              //Player
    static int cutlass;                             //Item to pick up
    static int treasure;                            //Item to pick up
    static int water;                               //Map is based on an island; water is the boundary
    static int message;                             //Item to pick up
    static int tile;                                //Tile you can walk on
    static int boat;                                //Tile that allows you to leave the island
    static int carryNo;                             //Item identifier for dropping item at location
    static String choice;
    static String playerInv = "";                   //Player inventory
    static Scanner scan = new Scanner(System.in);   //User input
    static boolean isCarrying;                      //Detect if carrying
    static int dropCount;                           //Counter to end game
    static int gameStarted = 0;
    static boolean gameRunning;
    
    static Map map = new Map(5, 5);                 //Map class (player x and player y)

    public static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println("--Welcome to Pirate Island--");
        System.out.println("-----Enter '1' to start-----");
        System.out.println("-Enter N, S, E or W to move-");
        System.out.println("---Try not to get lost...---");
        System.out.println("-Pick up all 3 items to win-");             //Intro message for the player in the player's console
        System.out.println("-After all items are looted-");
        System.out.println("------Exit on the boat------");
        System.out.println("----------------------------");
        gameStarted = scan.nextInt();                                   //gamestarted = user input number
        isCarrying = false;
        dropCount = 0;
        if (gameStarted == 1) {                                         //if user started game
        	System.out.println("You arrive at Pirate's Island on the South-East coast.");
        	System.out.println("Which way would you like to move?");
            gameRunning = true;
            startGame();
        }
    }
    
    private static void startGame() {
        while (gameRunning == true) {                   //while game is running loop input
        	dir = scan.nextLine();                  //dir = users next input
            switch (dir) {                              //switch between possible inputs
                case "South":
                case "south":
                case "S":
                case "s":
                    map.setPlayerX( map.getPlayerX() + 1 );     //set player x to increment
                    map.setPlayerTile( map.getPlayerX(), map.getPlayerY() );    //set player tile to player position
                    map(0);                                     //map(0 is for boundary movement)
                    break;
                case "North":
                case "north":
                case "N":
                case "n":
                    map.setPlayerX( map.getPlayerX() - 1 );     //set player x to decrement
                    map.setPlayerTile( map.getPlayerX(), map.getPlayerY() );
                    map(1);
                    break;
                case "West":
                case "west":
                case "W":
                case "w":
                    map.setPlayerY( map.getPlayerY() - 1 );     //set player y to decrement
                    map.setPlayerTile( map.getPlayerX(), map.getPlayerY() );
                    map(2);
                    break;
                case "East":
                case "east":
                case "e":
                case "E":
                    map.setPlayerY( map.getPlayerY() + 1 );     //set player y to increment
                    map.setPlayerTile( map.getPlayerX(), map.getPlayerY() );
                    map(3);
                    break;
                default:                                        //default for wrong input error handling
                    System.out.println("You pressed an incorrect response.");
                    System.out.println("Try again.");
                    System.out.println("Where would you like to move to now?");
            }
            if(dropCount == 3)                              //end game if player dropped 3 items
            	gameRunning = false;
        }
        
        System.out.println("Do you want to play again?");
        System.out.println("1) Yes\t 2) No");               //prompt replay
                gameStarted = scan.nextInt();
                if (gameStarted == 1) {                     //reset game
                    System.out.println("You arrive at Pirate's Island on the South-East coast.");
                    System.out.println("Which way would you like to move?");
                    gameRunning = true;
                    map = new Map(5, 5);
                    dropCount = 0;
                    startGame();
                }
    }
    private static void map(int XorY) {
        boat = 6;
        water = 5;
        tile = 4;
        cutlass = 3;
        treasure = 2;
        message = 1;
        player = 0;
        int playerTile = map.getPlayerTile();
        
        System.out.println(playerTile);
        if (playerTile == water) {
                                                        //knock player back from water
            if(XorY == 0)
                map.setPlayerX( map.getPlayerX() - 1 );
            else if(XorY == 1)
                map.setPlayerX( map.getPlayerX() + 1 );
            else if(XorY == 2)
                map.setPlayerY( map.getPlayerY() + 1 );
            else
                map.setPlayerY( map.getPlayerY() - 1 );
            System.out.println("You try to enter the water, but realise you cannot swim and quickly get out of the water.");
        }
        else if (playerTile == tile) {
            System.out.println("You move 1 square.");
        } else {
            if(isCarrying) {                            //check if player is carrying item
                System.out.print("Your inventory:" + playerInv + ".\n");
                System.out.println("Would you like to drop your item?\n\t1)Yes\t2) No");
                choice = scan.nextLine();
                if (choice.equals("1")){                //drop item
                    playerInv = "";
                    map.setTile(map.getPlayerX(), map.getPlayerY(), carryNo);   //place item at location
                    carryNo = 0;
                    isCarrying = false;
                    dropCount++;                        //increment dropcount
                }
                
                //    Would you like to drop?
                //    isCarrying = false;
            } else {
                if (playerTile == cutlass) {
                    findCutlass();
                }
                else if (playerTile == message) {
                    findMessage();
                }
                else if (playerTile == treasure) {
                    findTreasure();
                }
            }
        }
        System.out.println("Where would you like to move to now?");
    }
    
    public static void findCutlass() {  //prompt pickup
        System.out.println("You find a cutlass on the floor. This may come in handy.\nDo you take the cutlass?\n\t1)Yes\t2)No");
        choice = scan.nextLine();
        if (choice.equals("1")) {           //check if player wants to pick up item
            isCarrying = true;
            carryNo = 3;
            map.setTile(map.getPlayerX(), map.getPlayerY(), 4); //set current tile to blank tile
            playerInv = "A rusty cutlass";
        }
    }
    public static void findMessage() {
        System.out.println("You find a message in a bottle. This may have information on the map.\nDo you take the message?\n\t1)Yes\t2)No");
        choice = scan.nextLine();
        if (choice.equals("1")) {
            isCarrying = true;
            carryNo = 1;
            map.setTile(map.getPlayerX(), map.getPlayerY(), 4);
            playerInv = "Message in a Bottle";
        }
}
    public static void findTreasure() {
        System.out.println("You find treasure under a tree. This may make you rich!\nDo you take the treasure?\n\t1)Yes\t2)No");
        choice = scan.nextLine();
        if (choice.equals("1")) {
            isCarrying = true;
            carryNo = 2;
            map.setTile(map.getPlayerX(), map.getPlayerY(), 4);
            playerInv = "A chest of Treasure";
        }
}

}
