/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.qubcio.domino.common.basics;

import java.awt.Graphics;

/**
 *
 * @author qubcio
 */
public interface BasicMethods {

    /**
     *  draw
     * @param g
     */
    public void render(Graphics g);

    /**
     *  60 times per second 
     */
    public void tick();

    /**
     *  1 time per sec
     */
    public void sec();
}
