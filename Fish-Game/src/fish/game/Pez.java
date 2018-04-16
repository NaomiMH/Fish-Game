/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author NirvanaGaming
 * 
 * Movimiento:
 * Derecha e Izquierda
 * 
 */
public class Pez extends Item{
    private Game game;
    //animation
    private Animation pezUp;
    private Animation pezLeft;
    private Animation pezRight;
    //boolean
    private boolean moveToRight;
    private boolean decorativo;
    //int
    private int pez;
    
    /**
     * To build a Player object
     * @param x an <code>int</code> value to get the x coordinate
     * @param y an <code>int</code> value to get the y coordinate
     * @param width an <code>int</code> value to get the width
     * @param height an <code>int</code> value to get the height
     * @param game an <code>int</code> value to get outside elements
     */
    public Pez(int x, int y, int width, int height, boolean decorativo, int pez, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.decorativo = decorativo;
        //animation
        this.pez = pez;
        choosePez(pez);
        //boolean
        moveToRight = true;
    }

    public int getPez() {
        return pez;
    }
    
    private void choosePez(int num){
        pez=num;
        if(num==1)
            activePez1();
        else if(num==2)
            activePez2();
        else if(num==3)
            activePez3();
        else if(num==4)
            activePez4();
    }
    
    private void activePez1(){
        pezUp = new Animation(Assets.player1Up,100);
        pezLeft = new Animation(Assets.player1Left,100);
        pezRight = new Animation(Assets.player1Right,100);
    }
    
    private void activePez2(){
        pezUp = new Animation(Assets.player2Up,100);
        pezLeft = new Animation(Assets.player2Left,100);
        pezRight = new Animation(Assets.player2Right,100);
    }
    
    private void activePez3(){
        pezUp = new Animation(Assets.player3Up,100);
        pezLeft = new Animation(Assets.player3Left,100);
        pezRight = new Animation(Assets.player3Right,100);
    }
    
    private void activePez4(){
        pezUp = new Animation(Assets.player4Up,100);
        pezLeft = new Animation(Assets.player4Left,100);
        pezRight = new Animation(Assets.player4Right,100);
    }

    @Override
    public void tick() {
        if(!decorativo){
            //moviendo al personaje de derecha a izquierda
            if(moveToRight){
                setX(getX() + 5);
                if(getX()+getWidth() >= game.getWidth()-80)
                    moveToRight = false;
            } else {
                setX(getX() - 5);
                if(getX() <= 80)
                    moveToRight = true;
            }
            pezUp.tick();
        }
        else{
            if(contains(game.getMouseManager().getX(),game.getMouseManager().getY())){
                if(game.getMouseManager().isIzquierdo()){
                    game.getPlayer().choosePez(getPez());
                    game.getMouseManager().setIzquierdo(false);
                    game.setBeginning(false);
                }
                pezUp.tick();
            }
        }
        pezLeft.tick();
        pezRight.tick();
    }

    @Override
    public void render(Graphics g) {
        //imagenes segun el movimiento
        if(game.getKeyManager().space){
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform at = AffineTransform.getTranslateInstance(getX(), getY());
            if(moveToRight)
                at.rotate(Math.toRadians(45),30,20);
            else
                at.rotate(Math.toRadians(-45),10,30);
            at.scale(1.2, 1.2);
            g2d.drawImage(pezUp.getCurrentFrame(), at,null);
        }
        else if(game.isBeginning()){
            g.drawImage(pezUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else if(moveToRight)
            g.drawImage(pezRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        else
            g.drawImage(pezLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
