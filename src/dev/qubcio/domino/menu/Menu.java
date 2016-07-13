/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.qubcio.domino.menu;

import dev.qubcio.domino.common.basics.Terminal;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author qubcio
 */
public class Menu extends JFrame {
    public static void main(String[] args) {
        new Menu();
    }
    private JPanel panel;
    public Menu() {
        initializationComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initializationComponents() {
        Container c = new Container();
        Font font = new Font("Arial", 1, 50);
        c.setLayout(new BorderLayout());
        
        //author button
        JButton n = new JButton();
        n.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.this.dispose();
                new Terminal();
            }            
        });
        n.setFont(font);
        n.setText("NEW GAME");
        c.add(n, BorderLayout.NORTH);
        
        //author button
        JButton a = new JButton();
        a.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.revalidate();
                panel.repaint();
            }            
        });
        a.setFont(font);
        a.setText("AUTHOR");
        c.add(a, BorderLayout.CENTER);
        
        //exit button
        JButton e = new JButton();
        e.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }            
        });
        e.setFont(font);
        e.setText("EXIT");
        c.add(e, BorderLayout.SOUTH);
        
        
        panel = new JPanel();
        panel.add(c);
    }
}
