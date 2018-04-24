package com.mycompany.a3;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.geom.Point2D;
import com.codename1.ui.layouts.BorderLayout;

import java.util.Observable;
import java.util.Random;

public class GameWorld extends Observable implements IGameWorld {
    
    private final int STARTING_LIVES = 3;
    private final int ACCELERATION_QTY = 5;
    private final int TURN_QTY = 20;
    private final int TOTAL_FLAGS = 4;

    private int clockTime;
    private int livesRemaining;
    private boolean soundEnabled;
    private int maxWidth;
    private int maxHeight;

    private Sound flagSound;
    private Sound foodSound;
    private Sound spiderSound;

    private Ladybug player;
    private GameObjectCollection<GameObject> objects;

    public GameWorld() {
        this.clockTime = 0;
        this.livesRemaining = STARTING_LIVES;
        this.soundEnabled = false;
        // establish sensible defaults for map size until Map is created
        this.maxWidth = 1024;
        this.maxHeight = 768;
        this.flagSound = new Sound("coin.wav");
        this.foodSound = new Sound("up.wav");
        this.spiderSound = new Sound("enemy.wav");
    }
    
    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#init(int, int)
     */
    public void init(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        init();
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#init()
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
            Flag flag;
            do {
                flag = new Flag(new GameWorldObjectProxy(this),
                                new Point2D(random.nextDouble() * this.getMaxWidth(), random.nextDouble() * this.getMaxHeight()),
                                i + 1);
            } while (flag.collidesWithAny());
            objects.add(flag);
        }
        
        // add ladybug at flag 1
        objects.add(player = Ladybug.getLadybug(new GameWorldObjectProxy(this), objects.getIterator().getNext().getLocation()));
      
        objects.add(new Spider(new GameWorldObjectProxy(this)));
        objects.add(new Spider(new GameWorldObjectProxy(this)));
        objects.add(new FoodStation(new GameWorldObjectProxy(this)));
        objects.add(new FoodStation(new GameWorldObjectProxy(this)));

        System.out.println("Initialized game world with size " + maxWidth + "x" + maxHeight + ".");
        
        setChanged();
    }

    /**
     * Displays output on successful win condition
     */
    private void win() {
        Dialog dialog = new Dialog("Winner Winner Chicken Dinner!", new BorderLayout());
        dialog.add(BorderLayout.CENTER, new SpanLabel("Game over, you win! Total time: " + clockTime / 50 + " seconds."));
        dialog.add(BorderLayout.SOUTH, new Button(new Command("Ok")));
        dialog.showDialog();
        exit();
    }
    
    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#exit()
     */
    public void exit() {
        System.out.println("Exiting..");
        Display.getInstance().exitApplication();
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#acceleratePlayer()
     */
    public void acceleratePlayer() {
        System.out.println("Accelerating Ladybug by " + ACCELERATION_QTY);
        player.setSpeed(player.getSpeed() + ACCELERATION_QTY);
        setChanged();
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#brakePlayer()
     */
    public void brakePlayer() {
        System.out.println("Braking Ladybug by " + ACCELERATION_QTY);
        player.setSpeed(player.getSpeed() - ACCELERATION_QTY);
        setChanged();
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#turnPlayerLeft()
     */
    public void turnPlayerLeft() {
        System.out.println("Changing Ladybug heading by -" + TURN_QTY + " degrees");
        player.changeHeading(-TURN_QTY);
        setChanged();
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#turnPlayerRight()
     */
    public void turnPlayerRight() {
        System.out.println("Changing Ladybug heading by +" + TURN_QTY + " degrees");
        player.changeHeading(TURN_QTY);
        setChanged();
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#displayMap()
     */
    public void displayMap() {
        IIterator<GameObject> it = objects.getIterator();
        while (it.hasNext()) {
            System.out.println(it.getNext());
        }
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#handleFlagCollision(int)
     */
    public void handleFlagCollision(Flag flag) {
        System.out.println("Flag collision with Flag #" + flag.getSequenceNumber());
        if (player.getLastFlagReached() == flag.getSequenceNumber() - 1) {
            if (soundEnabled) {
                flagSound.play();
            }
            if (flag.getSequenceNumber() == TOTAL_FLAGS) {
                win();
            }
            player.setLastFlagReached(flag.getSequenceNumber());
            setChanged();
        }
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#handleFoodCollision()
     */
    public void handleFoodCollision(FoodStation foodStation) {
        int acquiredFood = 0;
        acquiredFood = foodStation.deplete();
        if (acquiredFood > 0) {
            System.out.println("Collision with a FoodStation with capacity " + foodStation.getCapacity());
            objects.add(new FoodStation(new GameWorldObjectProxy(this)));
            if (soundEnabled) {
                foodSound.play();
            }
        }
        player.setFoodLevel(player.getFoodLevel() + acquiredFood);
        setChanged();
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#handleSpiderCollision()
     */
    public void handleSpiderCollision() {
        System.out.println("Collision with a Spider");
        if(soundEnabled) {
            spiderSound.play();
        }
        player.decreaseHealth();
        if (player.getHealthLevel() == 0) {
            System.out.println("Player death");
            player.reset();
            init();
        }
        setChanged();
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#tickClock()
     */
    public void tickClock(int tickRate) {
        System.out.println("Ticking game clock...");

        IIterator<GameObject> it = objects.getIterator();
        while (it.hasNext()) {
            GameObject object = it.getNext();
            if (object instanceof Movable) {
                ((Movable) object).move(tickRate);
            }
            IIterator<GameObject> it2 = objects.getIterator();
            while (it2.hasNext()) {
                GameObject otherObject = it2.getNext();
                if (object != otherObject && object.collidesWith(otherObject)) {
                    if (!object.getCollisionSet().contains(otherObject)) {
                        object.handleCollision(otherObject);
                        object.getCollisionSet().add(otherObject);
                        otherObject.getCollisionSet().add(object);
                    }
                } else if (object != otherObject && !object.collidesWith(otherObject)) {
                    object.getCollisionSet().remove(otherObject);
                    otherObject.getCollisionSet().remove(object);
                }
            }
        }

        player.reduceFood();
        if (player.getFoodLevel() == 0) {
            player.reset();
            init();
        }

        this.clockTime++;
        
        setChanged();
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#getClockTime()
     */
    public int getClockTime() {
        return clockTime;
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#getLivesRemaining()
     */
    public int getLivesRemaining() {
        return livesRemaining;
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#getPlayer()
     */
    public Ladybug getPlayer() {
        return player;
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#getMaxWidth()
     */
    public int getMaxWidth() {
        return maxWidth;
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#getMaxHeight()
     */
    public int getMaxHeight() {
        return maxHeight;
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#isSoundEnabled()
     */
    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#setSoundEnabled()
     */
    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
        setChanged();
    }

    /* (non-Javadoc)
     * @see com.mycompany.a3.IGameWorld#getObjects()
     */
    public GameObjectCollection<GameObject> getObjects() {
        return objects;
    }
    
}
