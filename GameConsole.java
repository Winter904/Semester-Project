import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameConsole {

    private static final JTextArea display = new JTextArea();
    private static final JTextField inputField = new JTextField();
    private static final BlockingQueue<String> commandQueue = new LinkedBlockingQueue<>();

    public static void init() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game Console");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            display.setEditable(false);
            display.setLineWrap(true);
            display.setWrapStyleWord(true);
            display.setBackground(Color.black);
            display.setForeground(Color.green);
            display.setFont(new Font("Monospaced", Font.PLAIN, 14));
            JScrollPane scrollPane = new JScrollPane(display);

            JButton sendBtn = new JButton("Send");
            sendBtn.addActionListener(e -> submitInput());

            inputField.addActionListener(e -> submitInput());
            inputField.setBackground(Color.black);
            inputField.setForeground(Color.green);
            display.setFont(new Font("Monospaced", Font.PLAIN, 14));

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.add(inputField, BorderLayout.CENTER);
            bottomPanel.add(sendBtn, BorderLayout.EAST);

            frame.setLayout(new BorderLayout());
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
            inputField.requestFocusInWindow();
        });
    }

    private static void submitInput() {
        String text = inputField.getText().trim();
        if (!text.isEmpty()) {
            commandQueue.offer(text);
            inputField.setText("");
        }
    }

    public static void println(String text) {
        SwingUtilities.invokeLater(() -> {
            display.append(text + "\n");
            display.setCaretPosition(display.getDocument().getLength());
        });
    }

    public static String nextCommand() {
        try {
            return commandQueue.take(); // waits for input
        } catch (InterruptedException e) {
            return "";
        }
    }
}