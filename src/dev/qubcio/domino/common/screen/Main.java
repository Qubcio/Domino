/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.qubcio.domino.common.screen;

import dev.qubcio.domino.common.basics.Terminal;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qubcio
 */
public class Main extends Canvas implements Runnable{
    public static int MY_WIDTH , MY_HEIGHT;		//default sizes is 1000x700
    private Thread thread;
    private boolean running;
    double fps = 0;
    private Terminal terminal;
    @SuppressWarnings("CallToThreadStartDuringObjectConstruction")
    public Main(Terminal termianl){
        MY_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width/2;
        MY_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.terminal = termianl;
        new Frame("Domino", this);
        running = false;
        thread = new Thread(this);
        running = true;
        thread.start();
    }
    

    public synchronized void stop(){
        
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        long startLoop;
        double targetFPS = 60.0;
        long newFrame;
        double counter;
        while(running) {
            startLoop = System.currentTimeMillis();
            counter = 0;
            while(true){
                counter++;
                newFrame = System.currentTimeMillis();
                render();
                terminal.tick();
                double different = ((double)(System.currentTimeMillis() - newFrame))/1000;
                if(different < 1.0/targetFPS) {
                    try {
                        Thread.sleep((long)((1.0/targetFPS - different)*1000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(counter >= targetFPS) {
                    fps = ((double)((int)((counter/(((double)(System.currentTimeMillis() - startLoop))/1000))*10)))/10;
                    break;
                }
            }
            terminal.sec();
        }
    }
    
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    
    public void render(){
        BufferStrategy bS = this.getBufferStrategy();
        if(bS == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bS.getDrawGraphics();
        terminal.render(g);
        showFps(g);
        g.dispose();
        bS.show();
    }

    private void showFps(Graphics g) {
        g.setFont(new Font("Arial", 1, MY_WIDTH/60));
        g.setColor(Color.BLACK);
        g.drawString("FPS: " + Double.toString(fps), MY_WIDTH-200, MY_HEIGHT/20);
    }
}
