package com.mycompany.a2;

import com.codename1.ui.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

public class GameWorld extends Observable {
    
    private final int STARTING_LIVES = 3;
    private final int ACCELERATION_QTY = 5;
    private final int TURN_QTY = 5;
    private final int TOTAL_FLAGS = 4;
    
    private int clockTime;
    private int livesRemaining;

    private Ladybug player;
    private Vector<GameObject> objects;

    public GameWorld() {
        this.clockTime = 0;
        this.livesRemaining = STARTING_LIVES;
    }

    /**
     * Initializes the game world
     */
    public void init() {
        if (--this.livesRemaining < 0) {
            System.out.println("Game over, you failed!");
            exit();
        }

        this.objects = new Vector<GameObject>();
        
        Random random = new Random();
        for (int i = 0; i < TOTAL_FLAGS; i++) {
            // choosing random locations for flags for now
            objects.add(new Flag(new Point2D(random.nextDouble() * GameObject.LOCATION_MAX_X,
                                             random.nextDouble() * GameObject.LOCATION_MAX_Y),
                                             i + 1));
        }
        
        // add ladybug at flag 1
        objects.add(player = new Ladybug(objects.get(0).getLocation(), 30, 30, 5));
      
        objects.add(new Spider());
        objects.add(new Spider());
        
        objects.add(new FoodStation());
        objects.add(new FoodStation());

        System.out.println("Initialized game world.");
    }

    /**
     * Displays output on successful win condition
     */
    private void win() {
        System.out.println("Game over, you win! Total time: " + clockTime);
        exit();
    }
    
    /**
     * Exits the game
     */
    public void exit() {
        System.out.println("Exiting..");
        System.exit(0);
    }

    /**
     * Accelerates the player object
     */
    public void acceleratePlayer() {
        System.out.println("Accelerating Ladybug by " + ACCELERATION_QTY);
        player.setSpeed(player.getSpeed() + ACCELERATION_QTY);
    }

    /**
     * Brakes the player object
     */
    public void brakePlayer() {
        System.out.println("Braking Ladybug by " + ACCELERATION_QTY);
        player.setSpeed(player.getSpeed() - ACCELERATION_QTY);
    }

    /**
     * Adjusts the player object's heading left by TURN_QTY
     */
    public void turnPlayerLeft() {
        System.out.println("Changing Ladybug heading by -" + TURN_QTY + " degrees");
        player.changeHeading(-TURN_QTY);
    }

    /**
     * Adjusts the player object's heading right by TURN QTY
     */
    public void turnPlayerRight() {
        System.out.println("Changing Ladybug heading by +" + TURN_QTY + " degrees");
        player.changeHeading(TURN_QTY);
    }

    /**
     * Displays output of the current game state
     */
    public void displayState() {
        System.out.println("Lives remaining: " + this.livesRemaining);
        System.out.println("Elapsed time: " + this.clockTime);
        System.out.println("Highest flag reached: " + player.getLastFlagReached());
        System.out.println("Food level: " + player.getFoodLevel());
        System.out.println("Health Level: " + player.getHealthLevel());
    }

    /**
     * Outputs all objects in the GameWorld
     */
    public void displayMap() {
        for(GameObject object : objects) {
            System.out.println(object);
        }
    }

    /**
     * Handles player object colliding with a Flag
     */
    public void handleFlagCollision(int flagReached) {
        System.out.println("Flag collision with Flag #" + flagReached);
        if (player.getLastFlagReached() == flagReached - 1) {
            if (flagReached == TOTAL_FLAGS) {
                win();
            }
            player.setLastFlagReached(flagReached);
        }
    }

    /**
     * Handles the player object colliding with a FoodStation
     */
	public void handleFoodCollision() {
        FoodStation foodStation;
        int acquiredFood = 0;

        for (GameObject object : objects) {
            if (object instanceof FoodStation) {
                foodStation = (FoodStation) object;
                acquiredFood = foodStation.deplete();
                if (acquiredFood > 0) {
                    System.out.println("SIMULATING collision with a food station");
                    break;
                }
            }
        }
        
        player.setFoodLevel(player.getFoodLevel() + acquiredFood);
        objects.add(new FoodStation());
    }

    /**
     * Handles the player object colliding with a Spider
     */
    public void handleSpiderCollision() {
        System.out.println("SIMULATING collision with a Spider");
        player.decreaseHealth();
        if (player.getHealthLevel() == 0) {
            System.out.println("Player death");
            init();
        }
    }

    /**
     * Updates object states and advances the game clock
     */
	public void tickClock() {
        System.out.println("Ticking game clock...");

        for (GameObject object : objects) {
            if (object instanceof Movable) {
                Movable movable = (Movable) object;
                movable.move();
            }
        }

        player.reduceFood();
        if (player.getFoodLevel() == 0) {
            init();
        }

        this.clockTime++;
	}
    
}
