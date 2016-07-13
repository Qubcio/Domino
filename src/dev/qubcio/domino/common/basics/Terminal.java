/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.qubcio.domino.common.basics;

import dev.qubcio.domino.common.screen.Main;
import dev.qubcio.domino.game.window.Window;
import java.awt.Graphics;

/**
 *
 * @author qubcio
 */
public class Terminal implements BasicMethods {
    
    private Etap current;
    private Window window;

    public Terminal() {
        this.current = Etap.GAME;
        Main m = new Main(this);
        window = new Window(m);
    }
    

    @Override
    public void render(Graphics g) {
        switch(current) {
            case GAME: {
                window.render(g);
            }
        }
    }

    @Override
    public void tick() {
        switch(current) {
            case GAME: {
                window.tick();
            }
        }
    }

    @Override
    public void sec() {
        switch(current) {
            case GAME: {
                window.sec();
            }
        }
    }
    
}
