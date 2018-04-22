package com.mycompany.a3;

public class GameWorldObjectProxy implements IGameWorld {
    
    private GameWorld realGameWorld;
    
    public GameWorldObjectProxy(GameWorld realGameWorld) {
        this.realGameWorld = realGameWorld;
    }

    public void init(int maxWidth, int maxHeight) {
        return;
    }

    public void init() {
        return;
    }

    public void exit() {
        return;
    }

    public void acceleratePlayer() {
        return;
    }

    public void brakePlayer() {
        return;
    }

    public void turnPlayerLeft() {
        return;
    }

    public void turnPlayerRight() {
        return;
    }

    public void displayMap() {
        return;
    }

    public void handleFlagCollision(Flag flag) {
        realGameWorld.handleFlagCollision(flag);
    }

    public void handleFoodCollision(FoodStation foodStation) {
        realGameWorld.handleFoodCollision(foodStation);
    }

    public void handleSpiderCollision() {
        realGameWorld.handleSpiderCollision();
    }

    public void tickClock(int tickRate) {
        return;
    }

    public int getClockTime() {
        return realGameWorld.getClockTime();
    }

    public int getLivesRemaining() {
        return realGameWorld.getLivesRemaining();
    }

    public Ladybug getPlayer() {
        return null;
    }

    public int getMaxWidth() {
        return realGameWorld.getMaxWidth();
    }

    public int getMaxHeight() {
        return realGameWorld.getMaxHeight();
    }

    public boolean isSoundEnabled() {
        return realGameWorld.isSoundEnabled();
    }

    public void setSoundEnabled(boolean soundEnabled) {
        return;
    }

    public GameObjectCollection<GameObject> getObjects() {
        return null;
    }

}
