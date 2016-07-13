/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.qubcio.domino.common.screen;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author qubcio
 */
public class Frame extends Canvas {
    Frame(String title, Main main) {
        JFrame frame = new JFrame(title);
        Dimension dimension = new Dimension(Main.MY_WIDTH, Main.MY_HEIGHT);
        frame.setPreferredSize(dimension);
        frame.setMinimumSize(dimension);
        frame.setMaximumSize(dimension);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(main);
        frame.setVisible(true);
    }
}
