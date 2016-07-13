/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.qubcio.domino.game.window;

import dev.qubcio.domino.common.basics.BasicMethods;
import dev.qubcio.domino.common.screen.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Start frome two player version\
 * @version 06.08.2016
 * @author qubcio
 */
public class Window implements BasicMethods{
    private MouseInput mIn;
    private KeyInput kIn;
    public static int go = 0;
    private Color color;
    public Window(Main m) {
        kIn = new KeyInput();
        mIn = new MouseInput(this, kIn);
        m.addMouseListener(mIn);
        m.addKeyListener(kIn);
        color = new Color(0,0,0);
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Main.MY_WIDTH, Main.MY_HEIGHT);
        if (go < 4) {
            g.setColor(color);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("Now on screen will show bones pick 7 frome them.",
                    Main.MY_WIDTH/8, Main.MY_HEIGHT*9/20);
        }else{
            
        }
        mIn.render(g);
    }

    @Override
    public void tick() {
        mIn.tick();
        if(go < 4) {
            color = new Color(color.getRed()+1, color.getGreen()+1, color.getBlue()+1);
        }
    }

    @Override
    public void sec() {
        mIn.sec();
        go++;
    }
    
}
