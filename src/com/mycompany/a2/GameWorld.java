package com.mycompany.a2;

import com.codename1.ui.geom.Point2D;
import java.util.Observable;
import java.util.Random;

public class GameWorld extends Observable {
    
    private final int STARTING_LIVES = 3;
    private final int ACCELERATION_QTY = 5;
    private final int TURN_QTY = 5;
    private final int TOTAL_FLAGS = 4;
    
    private int clockTime;
    private int livesRemaining;

    private Ladybug player;
    private GameObjectCollection<GameObject> objects;

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

        this.objects = new GameObjectCollection<GameObject>();
        
        Random random = new Random();
        for (int i = 0; i < TOTAL_FLAGS; i++) {
            // choosing random locations for flags for now
            objects.add(new Flag(new Point2D(random.nextDouble() * GameObject.LOCATION_MAX_X,
                                             random.nextDouble() * GameObject.LOCATION_MAX_Y),
                                             i + 1));
        }
        
        // add ladybug at flag 1
        objects.add(player = new Ladybug(objects.getIterator().getNext().getLocation(), 30, 30, 5));
      
        objects.add(new Spider());
        objects.add(new Spider());
        objects.add(new FoodStation());
        objects.add(new FoodStation());

        System.out.println("Initialized game world.");
        
        setChanged();
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
        setChanged();
    }

    /**
     * Brakes the player object
     */
    public void brakePlayer() {
        System.out.println("Braking Ladybug by " + ACCELERATION_QTY);
        player.setSpeed(player.getSpeed() - ACCELERATION_QTY);
        setChanged();
    }

    /**
     * Adjusts the player object's heading left by TURN_QTY
     */
    public void turnPlayerLeft() {
        System.out.println("Changing Ladybug heading by -" + TURN_QTY + " degrees");
        player.changeHeading(-TURN_QTY);
        setChanged();
    }

    /**
     * Adjusts the player object's heading right by TURN QTY
     */
    public void turnPlayerRight() {
        System.out.println("Changing Ladybug heading by +" + TURN_QTY + " degrees");
        player.changeHeading(TURN_QTY);
        setChanged();
    }

    /**
     * Outputs all objects in the GameWorld
     */
    public void displayMap() {
        IIterator<GameObject> it = objects.getIterator();
        while (it.hasNext()) {
        	System.out.println(it.getNext());
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
            setChanged();
        }
    }

    /**
     * Handles the player object colliding with a FoodStation
     */
	public void handleFoodCollision() {
        FoodStation foodStation;
        int acquiredFood = 0;
        
        IIterator<GameObject> it = objects.getIterator();
        while (it.hasNext()) {
        	GameObject object = it.getNext();
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
        setChanged();
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
        setChanged();
    }

    /**
     * Updates object states and advances the game clock
     */
	public void tickClock() {
        System.out.println("Ticking game clock...");
        
        IIterator<GameObject> it = objects.getIterator();
        while (it.hasNext()) {
        	GameObject object = it.getNext();
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
        
        setChanged();
	}

	/**
	 * @return the clockTime
	 */
	public int getClockTime() {
		return clockTime;
	}

	/**
	 * @return the livesRemaining
	 */
	public int getLivesRemaining() {
		return livesRemaining;
	}

	/**
	 * @return the player
	 */
	public Ladybug getPlayer() {
		return player;
	}
    
}
