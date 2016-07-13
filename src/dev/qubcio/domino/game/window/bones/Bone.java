/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.qubcio.domino.game.window.bones;

import static dev.qubcio.domino.common.basics.Parametrs.SHEIGHT;
import static dev.qubcio.domino.common.basics.Parametrs.SMALL_DECK_SCALL;
import static dev.qubcio.domino.common.basics.Parametrs.SWIDTH;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author qubcio
 */
public class Bone {
    private Point spots;
    private Point position;
    private BufferedImage image;
    private boolean start = true;
    private boolean show = false;
    private String source;
    private boolean highlight;
    private boolean rotate;
    private boolean onDeck;
    public Bone(Point spots, Point position) {
        this.spots = spots;
        this.position = position;
        onDeck = false;
        highlight = false;
    }
    
    public void render(Graphics g) {
        if(start) {
            scale(0.7);
            start = false;
        }
        if(rotate) {
            g.fillRect((int)(position.x +(SWIDTH*SMALL_DECK_SCALL)/2), (int)(position.y + (SHEIGHT*SMALL_DECK_SCALL)/2), (int)(SHEIGHT*SMALL_DECK_SCALL), (int)(SWIDTH*SMALL_DECK_SCALL));
            AffineTransform at = AffineTransform.getTranslateInstance(position.x + (SWIDTH*SMALL_DECK_SCALL)/2, position.y + (SHEIGHT*SMALL_DECK_SCALL)/2);
            at.rotate(Math.toRadians(90));
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(image, at, null);
        } else {
            g.drawImage(image, position.x, position.y, null);
        }
        if (highlight && !onDeck) {
            g.setColor(Color.red);
            g.drawRect(position.x - 1, position.y -1, image.getWidth() + 1, image.getHeight() + 1);
            g.drawRect(position.x - 2, position.y -2, image.getWidth() + 3, image.getHeight() + 3);
        }
    }
    
    /**
     * Scaling image size of choice bone.
     * @param fWidth    width of bone
     * @param fHeight   height of bone
     */
    public void scale( double scala) {
        try {
            image = ImageIO.read(new File(source));
            BufferedImage dbi = null;
            if(image != null) {
                dbi = new BufferedImage((int) (SWIDTH*scala), (int) (SHEIGHT*scala), 1);
                Graphics2D g = dbi.createGraphics();
                AffineTransform at = AffineTransform.getScaleInstance(scala, scala);
                g.drawRenderedImage(image, at);
            }
            image = dbi;
        } catch (IOException ex) {
            Logger.getLogger(Bone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Resize image to choice sizes
     * @param wid width to scall
     * @param hei height to scall
     */
    public void scale( int wid, int hei) {
        try {
            image = ImageIO.read(new File(source));
            BufferedImage dbi = null;
            if(image != null) {
                dbi = new BufferedImage(wid, hei, 1);
                Graphics2D g = dbi.createGraphics();
                AffineTransform at = AffineTransform.getScaleInstance(((double)wid)/((double)SWIDTH), ((double)hei)/((double)SHEIGHT));
                g.drawRenderedImage(image, at);
            }
            image = dbi;
        } catch (IOException ex) {
            Logger.getLogger(Bone.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
        
    public void makeS() {
        if (show) {
            source = "res/bones/" + Integer.toString(spots.x) + "_" + Integer.toString(spots.y) + ".png";
            scale(0.7);
        } else {
            source = "res/bones/unknown_bone.png";
            show = true;
        }
    }

    @Override
    public String toString() {
        return "Bone{" + "spots=" + spots + ", position=" + position + ", image=" + image + ", start=" + start + ", show=" + show + ", source=" + source + '}';
    }
    
    public boolean clickOn(int x, int y) {
        return (x > position.x && x < position.x+image.getWidth()) &&
                (y > position.y && y < position.y+image.getHeight());
    }

    public Point getSpots() {
        return spots;
    }

    public void setSpots(Point spots) {
        this.spots = spots;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public boolean isRotate() {
        return rotate;
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
    }

    public boolean isOnDeck() {
        return onDeck;
    }

    public void setOnDeck(boolean onDeck) {
        this.onDeck = onDeck;
    }
    
}
