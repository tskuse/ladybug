package com.mycompany.a3;

public interface IGameWorld {

	/**
	 * Initializes the game world
	 * @param maxWidth max width of the map container
	 * @param maxHeight max height of the map container
	 */
	public void init(int maxWidth, int maxHeight);

	/**
	 * Initializes the game world
	 */
	public void init();

	/**
	 * Exits the game
	 */
	public void exit();

	/**
	 * Accelerates the player object
	 */
	public void acceleratePlayer();

	/**
	 * Brakes the player object
	 */
	public void brakePlayer();

	/**
	 * Adjusts the player object's heading left by TURN_QTY
	 */
	public void turnPlayerLeft();

	/**
	 * Adjusts the player object's heading right by TURN QTY
	 */
	public void turnPlayerRight();

	/**
	 * Outputs all objects in the GameWorld
	 */
	public void displayMap();

	/**
	 * Handles player object colliding with a Flag
	 */
	public void handleFlagCollision(int flagReached);

	/**
	 * Handles the player object colliding with a FoodStation
	 */
	public void handleFoodCollision();

	/**
	 * Handles the player object colliding with a Spider
	 */
	public void handleSpiderCollision();

	/**
	 * Updates object states and advances the game clock
	 */
	public void tickClock();

	/**
	 * @return the clockTime
	 */
	public int getClockTime();

	/**
	 * @return the livesRemaining
	 */
	public int getLivesRemaining();

	/**
	 * @return the player
	 */
	public Ladybug getPlayer();

	/**
	 * @return the maxWidth
	 */
	public int getMaxWidth();

	/**
	 * @return the maxHeight
	 */
	public int getMaxHeight();
	
	/**
	 * @return the soundEnabled
	 */
	public boolean isSoundEnabled();

	/**
	 * @param soundEnabled the soundEnabled to set
	 */
	public void setSoundEnabled(boolean soundEnabled);

	/**
	 * @return the objects
	 */
	public GameObjectCollection<GameObject> getObjects();

}