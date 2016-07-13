/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.qubcio.domino.game.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author qubcio
 */
public class KeyInput implements KeyListener{
    private boolean pressed;
    private boolean acc;
    public KeyInput() {
        pressed = false;
        acc = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public boolean isAcc() {
        return acc;
    }

    public void setAcc(boolean acc) {
        this.acc = acc;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            pressed = true;
            acc = true;
        }
        if (key == KeyEvent.VK_ESCAPE) {
            pressed = true;
            acc = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
