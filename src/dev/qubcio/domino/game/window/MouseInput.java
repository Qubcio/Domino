/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.qubcio.domino.game.window;

import dev.qubcio.domino.common.basics.BasicMethods;
import dev.qubcio.domino.common.basics.Parametrs;
import static dev.qubcio.domino.common.basics.Parametrs.ARRANGE_SCALL;
import static dev.qubcio.domino.common.basics.Parametrs.BIG_DECK_SCALL;
import static dev.qubcio.domino.common.basics.Parametrs.MAX_PLAYER;
import static dev.qubcio.domino.common.basics.Parametrs.MAX_SPOTS;
import static dev.qubcio.domino.common.basics.Parametrs.MIN_SPOTS;
import static dev.qubcio.domino.common.basics.Parametrs.SHEIGHT;
import static dev.qubcio.domino.common.basics.Parametrs.STARTX;
import static dev.qubcio.domino.common.basics.Parametrs.STARTY;
import static dev.qubcio.domino.common.basics.Parametrs.SWIDTH;
import dev.qubcio.domino.common.screen.Main;
import dev.qubcio.domino.game.window.bones.Bone;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.function.Consumer;

/**
 *
 * @author qubcio
 */
public class MouseInput implements MouseListener, BasicMethods {
    private Window w;
    private ArrayList<Bone> pool;
    private ArrayList<Bone> player1;
    private int move;
    private Font font;
    private boolean selected;
    private Color color;
    private boolean waitOnAcc;
    private KeyInput k;
    private LinkedList<Bone> deck;
    private int take;
    private Etap etap;
    MouseInput(Window w, KeyInput k) {                                                                                          /*  1   */
        etap = Etap.LOADING;
        this.w = w;
        this.k = k;
        move = Integer.MAX_VALUE;
        selected = false;
        waitOnAcc = false;
        color = new Color(250,250,250);
        take = -1;
        pool = new ArrayList<>();
        player1 = new ArrayList<>();
        deck = new LinkedList<>();
        for (int i = MIN_SPOTS; i <= MAX_SPOTS; i++) {
            for (int j = i; j <= MAX_SPOTS; j++) {
                pool.add(new Bone(new Point(i,j), new Point(0,0)));
                pool.get(pool.size()-1).makeS();
            }
        }
        Collections.shuffle(pool);
        int sX = Main.MY_WIDTH/7;
        int sY = Main.MY_HEIGHT/6;
        int ww = (int) (SWIDTH*BIG_DECK_SCALL + 150);
        int hh = (int) (SHEIGHT*BIG_DECK_SCALL + 30);
        for (int i = 0; i < pool.size(); i++) {
            pool.get(i).setPosition(new Point(sX + (i%7)*ww, sY + (i%4)*hh));
        }
        etap = Etap.FILLING;
    }                                                                                                                       /****************/
    
    @Override
    public synchronized void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (Window.go > 4 && player1.size() < MAX_PLAYER && !selected) {
            for (int i = 0; i < pool.size(); i++) {
                Bone p = pool.get(i);
                if (p.clickOn(x, y)) {
                    p.setPosition(new Point(p.getPosition().x + p.getImage().getWidth() + 10, p.getPosition().y));
                    p.makeS();
                    pool.remove(p);
                    player1.add(p);
                    if (player1.size() == MAX_PLAYER) {
                        move = Window.go;
                    }
                }
            }
        } else if (selected) {
            for (int i = 0; i < player1.size(); i++) {
                Bone p = player1.get(i);
                if (p.clickOn(x, y)) {
                    take = i;
                    p.setHighlight(true);
                    waitOnAcc = true;
                    k.setAcc(false);
                    k.setPressed(false);
                }
            }
        }
    }

    @Override
    public synchronized void render(Graphics g) {
        if (Window.go > 4) {
            if(Window.go - move > 1) {
                font = new Font("Arial", 1, 50);
                g.setFont(font);
                g.setColor(Color.BLACK);
                //on the top screen there are players bones
                g.drawString("Yours:", Main.MY_WIDTH/20, Main.MY_HEIGHT/10);
                //on the botom screen there are still not taken bones
                g.drawString("Free:", Main.MY_WIDTH/20, Main.MY_HEIGHT*9/10);
                if (Window.go - move > 2) {
                    if (deck.isEmpty()){
                         font = new Font("Arial", 1, 50);
                        g.setFont(font);
                        g.setColor(color);
                        //on the top screen there are players bones
                        g.drawString("Select from yours deck first", Main.MY_WIDTH/3, Main.MY_HEIGHT/2);
                        g.drawString("press enter to accept or esc to cancel.", Main.MY_WIDTH/4, Main.MY_HEIGHT*2/3);
                    } else {
                        
                    }
                }
            }
            pool.stream().forEach(new Consumer<Bone>() {
                @Override
                public void accept(Bone p) {
                    p.render(g);
                }
            });
            player1.stream().forEach(new Consumer<Bone>() {
                @Override
                public void accept(Bone p) {
                    p.render(g);
                }
            });
            deck.stream().forEach(new Consumer<Bone>() {
                @Override
                public void accept(Bone p) {
                    p.render(g);
                }
            });
        }
        
    }

    @Override
    public void tick() {
        if (waitOnAcc) {
            if(k.isPressed()) {
                Bone b = player1.get(take);
                if(k.isAcc()) {
                    b.scale(ARRANGE_SCALL);
                    b.setRotate(true);
                    b.setOnDeck(true);
                    player1.remove(take);
                    deck.add(b);
                    if(deck.size() == 1) {
                        b.setPosition(new Point(STARTX, STARTY));
                    }
                    waitOnAcc = false;
                } else {
                    b.setHighlight(false);
                }
            }
        }
    }

    @Override
    public void sec() {
        if (selected) color = color.darker();
        if(Window.go - move == 2) {
            selected = true;
            pool.stream().forEach((p)->{
                p.scale(0.5);
            });
            int sX = Main.MY_WIDTH/6;
            int sY = Main.MY_HEIGHT/30;
            for (int i = 0; i < player1.size(); i++) {
                player1.get(i).setPosition(new Point((int) (sX + i* (SWIDTH*0.7 + 20)), sY));
            }
            sY = Main.MY_HEIGHT*17/20;
            for (int i = 0; i < pool.size(); i++) {
                pool.get(i).setPosition(new Point((int) (sX + i* (SWIDTH*0.5 + 15)), sY));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
