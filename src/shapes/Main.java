package shapes;

import shapes.controller.ShapeController;
import shapes.model.Shape;
import shapes.view.ShapeView;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Shape("Quadrado", "audio/square.wav", new Color(33, 150, 243)), // Azul
            new Shape("Triângulo", "audio/triangle.wav", new Color(76, 175, 80)), // Verde
            new Shape("Círculo", "audio/circle.wav", new Color(244, 67, 54)) // Vermelho
        };

        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(shapes, view);
        view.setVisible(true);
    }
}