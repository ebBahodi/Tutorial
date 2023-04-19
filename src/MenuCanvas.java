import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MenuCanvas extends JComponent {
    private JButton playButton;
    private JButton optionsButton;
    private JButton exitButton;
    private final Menu menu;
    public MenuCanvas(Menu menu) {
        super();
        this.menu = menu;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(g instanceof Graphics2D){
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if(this.menu.getGame() != null && this.menu.getGame().isRun()){
                this.menu.getGame().setVisible(false);
                this.setVisible(true);
            }
            repaint();
        }
    }

    public void CreateButtons() {
        playButton = new JButton("Play");
        optionsButton = new JButton("Options");
        exitButton = new JButton("Exit");
        playButton.setBounds(getWidth() / 2, (int)(getHeight() * 0.75), 120, 40);
        optionsButton.setBounds(getWidth() / 2, (int)(getHeight() * 0.8), 120, 40);
        exitButton.setBounds(getWidth() / 2, (int)(getHeight() * 0.85), 120, 40);

        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);

        optionsButton.setOpaque(false);
        optionsButton.setContentAreaFilled(false);
        optionsButton.setBorderPainted(false);
        optionsButton.setFocusPainted(false);

        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);

        add(playButton);
        add(optionsButton);
        add(exitButton);

        this.addEventListeners();
    }

    private void addEventListeners() {
        playButton.addActionListener(e -> {
            menu.startGame();
        });

        optionsButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "This is yet to be implemented! :D"));
        exitButton.addActionListener(e -> System.exit(0));

        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playButton.setForeground(Color.blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playButton.setForeground(Color.black);
            }
        });

        optionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                optionsButton.setForeground(Color.blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                optionsButton.setForeground(Color.black);
            }
        });

        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setForeground(Color.blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setForeground(Color.black);
            }
        });
    }
}
