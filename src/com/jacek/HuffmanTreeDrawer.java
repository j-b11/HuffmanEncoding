package com.jacek;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HuffmanTreeDrawer {

    private static int gap = 30;
    private static int vGap = 50;

    private static void drawCircle(HuffmanTreeNode root, double x, double y, GraphicsContext gc) {
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeOval(x, y, 52, 52);
        gc.setFill(Color.LIGHTSKYBLUE);
        gc.fillOval(x,y,52,52);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        if(root.character < 260){
            gc.fillText(Integer.toString(root.frequency),x+10, y+26,10);
            gc.fillText("("+root.character+")",x + 30 , y + 26,18);
        }
        else
            gc.fillText(Integer.toString(root.frequency),x + 20, y + 26,10);
    }

    static void display(HuffmanTreeNode root, double x, double y, GraphicsContext gc, double g) {
        drawCircle(root, x, y, gc);
        gc.setLineWidth(3);
        if(root.nodeToLeft != null) {
            double x0 = x + 26;
            double y0 = y + 52;
            double x1 = x0 - g;
            double y1 = y0 + vGap;
            gc.strokeLine(x0, y0, x1, y1);
            gc.fillText("0", (x0 + x1) / 2 - 7, (y1 + y0) / 2);
            display(root.nodeToLeft,x1 - 26, y1, gc,g / 2);
        }

        if(root.nodeToRight != null) {
            double x0 = x + 26;
            double y0 = y + 52;
            double x1 = x0 + g;
            double y1 = y0 + vGap;
            gc.strokeLine(x0, y0, x1, y1);
            gc.fillText("1", (x1 + x0)/2-5, (y1 + y0) / 2);
            display(root.nodeToRight,x1 - 26, y1, gc,g / 2);
        }
    }
}
