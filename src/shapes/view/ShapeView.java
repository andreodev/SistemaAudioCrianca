package shapes.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShapeView extends JFrame {
    private JLabel squareLabel;
    private JLabel triangleLabel;
    private JLabel circleLabel;
    private JButton resetButton;
    private float squareScale = 1.0f;
    private float triangleScale = 1.0f;
    private float circleScale = 1.0f;
    private boolean squareGlow = false;
    private boolean triangleGlow = false;
    private boolean circleGlow = false;

    public ShapeView() {
        setTitle("Identificação de Formas com Áudio");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout moderno com GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // Título principal
        JLabel titleLabel = new JLabel("Aprenda Formas!");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(45, 85, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);

        // Subtítulo
        JLabel subtitleLabel = new JLabel("Toque nas formas para ouvir o nome");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        gbc.gridy = 1;
        mainPanel.add(subtitleLabel, gbc);

        // Formas em linha
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        squareLabel = createShapeLabel("square", new Color(0, 123, 255));
        gbc.gridx = 0;
        mainPanel.add(squareLabel, gbc);

        triangleLabel = createShapeLabel("triangle", new Color(40, 167, 69));
        gbc.gridx = 1;
        mainPanel.add(triangleLabel, gbc);

        circleLabel = createShapeLabel("circle", new Color(220, 53, 69));
        gbc.gridx = 2;
        mainPanel.add(circleLabel, gbc);

        // Botão reiniciar com design flat
        resetButton = new JButton("Reiniciar");
        resetButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(45, 85, 255));
        resetButton.setFocusPainted(false);
        resetButton.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                resetButton.setBackground(new Color(30, 65, 220));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                resetButton.setBackground(new Color(45, 85, 255));
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(40, 20, 20, 20);
        mainPanel.add(resetButton, gbc);
    }

    private JLabel createShapeLabel(String shape, Color baseColor) {
        JLabel label = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int size = 140;
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;

                // Sombra sutil
                g2d.setColor(new Color(0, 0, 0, 30));
                if (shape.equals("square")) {
                    g2d.fillRoundRect(centerX - size / 2 + 5, centerY - size / 2 + 5, size, size, 15, 15);
                } else if (shape.equals("triangle")) {
                    int[] xPointsShadow = {centerX, centerX + size / 2 + 5, centerX - size / 2 + 5};
                    int[] yPointsShadow = {centerY - size / 2 + 5, centerY + size / 2 + 5, centerY + size / 2 + 5};
                    g2d.fillPolygon(xPointsShadow, yPointsShadow, 3);
                } else if (shape.equals("circle")) {
                    g2d.fillOval(centerX - size / 2 + 5, centerY - size / 2 + 5, size, size);
                }

                // Forma principal
                g2d.setColor(baseColor);
                if (shape.equals("square")) {
                    g2d.fillRoundRect(centerX - size / 2, centerY - size / 2, size, size, 15, 15);
                } else if (shape.equals("triangle")) {
                    int[] xPoints = {centerX, centerX + size / 2, centerX - size / 2};
                    int[] yPoints = {centerY - size / 2, centerY + size / 2, centerY + size / 2};
                    g2d.fillPolygon(xPoints, yPoints, 3);
                } else if (shape.equals("circle")) {
                    g2d.fillOval(centerX - size / 2, centerY - size / 2, size, size);
                }

                // Glow no hover
                if ((shape.equals("square") && squareGlow) ||
                    (shape.equals("triangle") && triangleGlow) ||
                    (shape.equals("circle") && circleGlow)) {
                    g2d.setColor(new Color(255, 255, 255, 100));
                    Stroke oldStroke = g2d.getStroke();
                    g2d.setStroke(new BasicStroke(8));
                    if (shape.equals("square")) {
                        g2d.drawRoundRect(centerX - size / 2, centerY - size / 2, size, size, 15, 15);
                    } else if (shape.equals("triangle")) {
                        int[] xPointsGlow = {centerX, centerX + size / 2, centerX - size / 2};
                        int[] yPointsGlow = {centerY - size / 2, centerY + size / 2, centerY + size / 2};
                        g2d.drawPolygon(xPointsGlow, yPointsGlow, 3);
                    } else if (shape.equals("circle")) {
                        g2d.drawOval(centerX - size / 2, centerY - size / 2, size, size);
                    }
                    g2d.setStroke(oldStroke);
                }

                g2d.dispose();
            }
        };
        label.setOpaque(false);
        label.setPreferredSize(new Dimension(160, 160));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (shape.equals("square")) squareGlow = true;
                else if (shape.equals("triangle")) triangleGlow = true;
                else if (shape.equals("circle")) circleGlow = true;
                label.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (shape.equals("square")) squareGlow = false;
                else if (shape.equals("triangle")) triangleGlow = false;
                else if (shape.equals("circle")) circleGlow = false;
                label.repaint();
            }
        });

        return label;
    }

    public JLabel getSquareLabel() {
        return squareLabel;
    }

    public JLabel getTriangleLabel() {
        return triangleLabel;
    }

    public JLabel getCircleLabel() {
        return circleLabel;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public void animateShape(String shape) {
        Timer timer = new Timer(20, null);
        int steps = 10;
        final float scaleStep = 0.1f / steps;
        final boolean[] growing = {true};
        timer.addActionListener(e -> {
            if (shape.equals("square")) {
                squareScale += growing[0] ? scaleStep : -scaleStep;
                if (squareScale >= 1.1f) {
                    growing[0] = false;
                }
                if (squareScale <= 1.0f && !growing[0]) {
                    squareScale = 1.0f;
                    timer.stop();
                }
                squareLabel.repaint();
            } else if (shape.equals("triangle")) {
                triangleScale += growing[0] ? scaleStep : -scaleStep;
                if (triangleScale >= 1.1f) {
                    growing[0] = false;
                }
                if (triangleScale <= 1.0f && !growing[0]) {
                    triangleScale = 1.0f;
                    timer.stop();
                }
                triangleLabel.repaint();
            } else if (shape.equals("circle")) {
                circleScale += growing[0] ? scaleStep : -scaleStep;
                if (circleScale >= 1.1f) {
                    growing[0] = false;
                }
                if (circleScale <= 1.0f && !growing[0]) {
                    circleScale = 1.0f;
                    timer.stop();
                }
                circleLabel.repaint();
            }
        });
        timer.start();
    }
}
