package org.jfree.graphics2d.area;

import org.junit.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

  final static int TILE_WIDTH = 100;
  final static int TILE_HEIGHT = 65;

  public static void main(String[] args) {
    Area a1 = SSCCE_Intersect();
    drawArea(a1);
  }

  public static void drawArea(Area a1) {
    BufferedImage image = new BufferedImage(TILE_WIDTH, TILE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    Graphics2D gr = image.createGraphics();

    gr.setPaint(Color.blue);
    gr.fill(a1);
    gr.setPaint(Color.red);
    Area areaToDelimit = a1;
    System.out.println(areaToDelimit.getBounds2D().toString());
    gr.drawLine((int) areaToDelimit.getBounds2D().getX(), (int) areaToDelimit.getBounds2D().getY(), (int) (areaToDelimit.getBounds2D().getX() + areaToDelimit.getBounds2D().getWidth()), (int) (areaToDelimit.getBounds2D().getY()));
    gr.drawLine((int) areaToDelimit.getBounds2D().getX(), (int) areaToDelimit.getBounds2D().getY(), (int) (areaToDelimit.getBounds2D().getX()), (int) (areaToDelimit.getBounds2D().getY() + areaToDelimit.getBounds2D().getHeight()));

    saveImage(image, "testcase-shapes.png");
  }

  public static Area SSCCE_Intersect() {
    final double margin = 0;
    Rectangle2D areaBounds = new Rectangle2D.Double(margin, margin, TILE_WIDTH - 2 * margin, TILE_HEIGHT - 2 * margin);

    double w = areaBounds.getWidth() / 1.5;
    double h = areaBounds.getHeight() / 1.5;

    System.out.println(areaBounds.getBounds2D());
    Ellipse2D fig1 = new Ellipse2D.Double(areaBounds.getX(), areaBounds.getY(), w, h);
    Ellipse2D fig2 = new Ellipse2D.Double(areaBounds.getMaxX() - w, areaBounds.getMaxY() - h, w, h);
    System.out.println(fig1.getBounds2D());
    System.out.println(fig2.getBounds2D());


    Area a1 = new Area(fig1);
    Area a2 = new Area(fig2);

    a1.intersect(a2);

    System.out.println(a1.getBounds2D().toString());
    if (a1.getBounds2D().getWidth() == 0 && a1.getBounds2D().getHeight() == 0) {
      System.out.println("Failure: Has no size!!!");
    } else {
      System.out.println("Correct: Has size");
    }
    return a1;
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