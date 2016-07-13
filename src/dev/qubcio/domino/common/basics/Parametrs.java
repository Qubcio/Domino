/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.qubcio.domino.common.basics;

import dev.qubcio.domino.common.screen.Main;

/**
 *
 * @author qubcio
 */
public class Parametrs {

    /**
     *  Max numbers of spots on one side of bone.
     */
    public static final int MAX_SPOTS = 6;

    /**
     *  Min numbers of spots on one side of bone.
     */
    public static final int MIN_SPOTS = 0;

    /**
     *  Max numbers of start deck overall.
     */
    public static final int MAX_BONES = 28;

    /**
     *  Max numbers on bone per player, on start.
     */
    public static final int MAX_PLAYER = 7;

    /**
     *  Start x position for choice bones(when they are still in pool).
     */
    public static final int STARTX = Main.MY_WIDTH/2-100;

    /**
     *  Start y position for choice bones(when they are still in pool).
     */
    public static final int STARTY = Main.MY_HEIGHT/2-40;

    /**
     *  All bones have this width as default.
     */
    public static final int SWIDTH = 97;

    /**
     *  All bones have this height as default.
     */
    public static final int SHEIGHT = 195;

    /**
     *  Normal size size for image.
     */
    public static final double DEFAULT_SCALL    = 1.0;

    /**
     *  When they already in deck on table.
     */
    public static final double ARRANGE_SCALL    = 0.5;

    /**
     *  When they are for picks and in deck with small number.
     */
    public static final double BIG_DECK_SCALL   = 0.7;

    /**
     *  When they are in deck with big number.
     */
    public static final double SMALL_DECK_SCALL = 0.6;
}
