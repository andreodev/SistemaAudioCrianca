package shapes.controller;

import shapes.model.Shape;
import shapes.view.ShapeView;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class ShapeController {
    private Shape[] shapes;
    private ShapeView view;
    private Clip currentClip; // Armazena o Clip atual em reprodução

    public ShapeController(Shape[] shapes, ShapeView view) {
        this.shapes = shapes;
        this.view = view;

        // Adicionar MouseListener às formas com animação
        view.getSquareLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                playSound(shapes[0].getAudioPath());
                view.animateShape("square");
            }
        });

        view.getTriangleLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                playSound(shapes[1].getAudioPath());
                view.animateShape("triangle");
            }
        });

        view.getCircleLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                playSound(shapes[2].getAudioPath());
                view.animateShape("circle");
            }
        });

        view.getResetButton().addActionListener(e -> reset());
    }

    private void playSound(String audioPath) {
        try {
            // Parar o áudio anterior, se estiver tocando
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
                currentClip.close();
            }

            // Carregar o novo arquivo como recurso usando o classpath
            URL url = getClass().getResource("/" + audioPath);
            if (url == null) {
                System.err.println("Recurso não encontrado: /" + audioPath);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erro ao reproduzir o áudio: " + audioPath);
            e.printStackTrace();
        }
    }

    private void reset() {
        System.out.println("Reiniciar clicado");
        // Parar o áudio atual ao reiniciar
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }
        currentClip = null;
    }
}