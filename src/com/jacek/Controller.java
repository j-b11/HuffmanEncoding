package com.jacek;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.HashMap;

public class Controller {

    @FXML
    private TextArea inputText;
    @FXML
    private TextArea encodedText;
    @FXML
    private Label entropy;
    @FXML
    private Label averageCodeword;
    @FXML
    private Canvas canvas;

    private static int gap = 30;
    private static int vGap = 50;
    private HuffmanTree tree;


    @FXML protected void handleEncodeButtonAction(ActionEvent event) {
        String input = inputText.getText();
        tree = new HuffmanTree(input);
        HashMap<Character, String> map = tree.getHuffmanEncoding();
        System.out.println(map);

        int height = tree.getHeight();
        StringBuilder disp = new StringBuilder();
        for(int i=0; i<input.length(); i++) {
            disp.append(map.get(input.charAt(i)));
        }
        encodedText.setText(disp.toString());

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,3000,3000);
        gc.setStroke(Color.BLUE);
        gc.setFont(new Font("Arial",20));
        double begin = gap*Math.pow(2,(height-2));
        double dim = Math.pow(2,(height - 1)) * 30;
        HuffmanTreeDrawer.display(tree.getRoot(), dim,10, gc, begin);

        entropy.setText(String.format("%.3f%n", tree.computeEntrpy(map, input)));
        averageCodeword.setText(String.format("%.3f%n", tree.computeAverageCodewordLength(map,input)));
    }

    @FXML protected void handleDecodeButtonAction(ActionEvent event) {
        String input = encodedText.getText();
        HuffmanTreeDecoder decoder = new HuffmanTreeDecoder(tree, input);
        String disp = decoder.decode();
        inputText.setText(input+" is decoded as " + disp);
    }

}
