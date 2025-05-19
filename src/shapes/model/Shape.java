package shapes.model;

import java.awt.Color;

public class Shape {
    private String name;
    private String audioPath;
    private Color color;

    public Shape(String name, String audioPath, Color color) {
        this.name = name;
        this.audioPath = audioPath;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public Color getColor() {
        return color;
    }
}