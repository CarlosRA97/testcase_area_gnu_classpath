package org.jfree.graphics2d.composite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  final static int TILE_WIDTH = 100;
  final static int TILE_HEIGHT = 65;

  public static void main(String[] args) {
    BufferedImage image = new BufferedImage(TILE_WIDTH, TILE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    Graphics2D gr = image.createGraphics();

    double w = TILE_WIDTH / 1.5;
    double h = TILE_HEIGHT / 1.5;

    Rectangle2D rectangle1 = new Rectangle2D.Double(0, 0, w, h);
    Rectangle2D rectangle2 = new Rectangle2D.Double(w/2, h/2, w, h);

    gr.setPaint(Color.red);
    gr.fill(rectangle1);

    gr.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.2f));

    gr.setPaint(Color.yellow);
    gr.fill(rectangle2);

    saveImage(image, "testcase.png");

  }

  private static void saveImage(BufferedImage image, String file) {
    Toolkit.getDefaultToolkit().sync();

    try {
      ImageIO.write(image, "png", new File(file));
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private static BufferedImage readImage(String file) throws IOException {
    return ImageIO.read(new File(file));
  }
}