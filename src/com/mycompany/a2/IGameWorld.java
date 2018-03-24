package com.mycompany.a2;

public interface IGameWorld {

	/**
	 * Initializes the game world
	 * @param maxWidth max width of the map container
	 * @param maxHeight max height of the map container
	 */
	void init(int maxWidth, int maxHeight);

	/**
	 * Initializes the game world
	 */
	void init();

	/**
	 * Exits the game
	 */
	void exit();

	/**
	 * Accelerates the player object
	 */
	void acceleratePlayer();

	/**
	 * Brakes the player object
	 */
	void brakePlayer();

	/**
	 * Adjusts the player object's heading left by TURN_QTY
	 */
	void turnPlayerLeft();

	/**
	 * Adjusts the player object's heading right by TURN QTY
	 */
	void turnPlayerRight();

	/**
	 * Outputs all objects in the GameWorld
	 */
	void displayMap();

	/**
	 * Handles player object colliding with a Flag
	 */
	void handleFlagCollision(int flagReached);

	/**
	 * Handles the player object colliding with a FoodStation
	 */
	void handleFoodCollision();

	/**
	 * Handles the player object colliding with a Spider
	 */
	void handleSpiderCollision();

	/**
	 * Updates object states and advances the game clock
	 */
	void tickClock();

	/**
	 * @return the clockTime
	 */
	int getClockTime();

	/**
	 * @return the livesRemaining
	 */
	int getLivesRemaining();

	/**
	 * @return the player
	 */
	Ladybug getPlayer();

	/**
	 * @return the maxWidth
	 */
	int getMaxWidth();

	/**
	 * @return the maxHeight
	 */
	int getMaxHeight();

}