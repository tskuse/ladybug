package com.mycompany.a3;

public class GameWorldInfoProxy implements IGameWorld {
    
    private GameWorld realGameWorld;
    
    public GameWorldInfoProxy(GameWorld realGameWorld) {
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

    public void handleFlagCollision(int flagReached) {
        return;
    }

    public void handleFoodCollision() {
        return;
    }

    public void handleSpiderCollision() {
        return;
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
