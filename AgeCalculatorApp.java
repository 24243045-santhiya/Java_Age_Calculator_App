import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.Year;

public class AgeCalculatorApp extends JFrame {
    private final JTextField birthYearField;
    private final JLabel resultLabel;
    private final JLabel detailLabel;

    public AgeCalculatorApp() {
        setTitle("Age Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 430);
        setLocationRelativeTo(null);
        setResizable(false);

        Color navy = new Color(22, 34, 66);
        Color blue = new Color(55, 105, 220);
        Color background = new Color(245, 247, 252);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(background);
        root.setBorder(new EmptyBorder(24, 28, 24, 28));
        setContentPane(root);

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel("◷");
        icon.setFont(new Font("SansSerif", Font.BOLD, 34));
        icon.setForeground(blue);
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("AGE CALCULATOR");
        title.setFont(new Font("SansSerif", Font.BOLD, 23));
        title.setForeground(navy);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Find your age from your birth year");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(new Color(100, 110, 130));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(icon);
        header.add(Box.createVerticalStrut(4));
        header.add(title);
        header.add(Box.createVerticalStrut(6));
        header.add(subtitle);
        root.add(header, BorderLayout.NORTH);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 240)),
                new EmptyBorder(22, 22, 22, 22)));

        JLabel yearLabel = new JLabel("Enter your birth year");
        yearLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        yearLabel.setForeground(navy);
        yearLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        birthYearField = new JTextField();
        birthYearField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        birthYearField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        birthYearField.setPreferredSize(new Dimension(300, 42));
        birthYearField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 218, 232)),
                new EmptyBorder(8, 10, 8, 10)));
        birthYearField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton calculateButton = new JButton("Calculate Age");
        calculateButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setBackground(blue);
        calculateButton.setFocusPainted(false);
        calculateButton.setBorderPainted(false);
        calculateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        calculateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        calculateButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        calculateButton.setPreferredSize(new Dimension(300, 44));
        calculateButton.addActionListener(e -> calculateAge());
        birthYearField.addActionListener(e -> calculateAge());

        resultLabel = new JLabel("Your age will appear here");
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        resultLabel.setForeground(navy);
        resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        detailLabel = new JLabel(" ");
        detailLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        detailLabel.setForeground(new Color(100, 110, 130));
        detailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(yearLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(birthYearField);
        card.add(Box.createVerticalStrut(16));
        card.add(calculateButton);
        card.add(Box.createVerticalStrut(20));
        card.add(resultLabel);
        card.add(Box.createVerticalStrut(7));
        card.add(detailLabel);

        JPanel centerWrap = new JPanel(new BorderLayout());
        centerWrap.setOpaque(false);
        centerWrap.setBorder(new EmptyBorder(22, 0, 0, 0));
        centerWrap.add(card, BorderLayout.CENTER);
        root.add(centerWrap, BorderLayout.CENTER);

        JLabel footer = new JLabel("Simple • Fast • Free");
        footer.setFont(new Font("SansSerif", Font.PLAIN, 11));
        footer.setForeground(new Color(125, 132, 150));
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setBorder(new EmptyBorder(12, 0, 0, 0));
        root.add(footer, BorderLayout.SOUTH);
    }

    private void calculateAge() {
        String input = birthYearField.getText().trim();
        int currentYear = Year.now().getValue();

        try {
            int birthYear = Integer.parseInt(input);
            if (birthYear < 1 || birthYear > currentYear) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a year between 1 and " + currentYear + ".",
                        "Invalid Birth Year", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int age = currentYear - birthYear;
            resultLabel.setText("You are approximately " + age + " years old");
            detailLabel.setText("Birth year: " + birthYear + "  |  Current year: " + currentYear);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid whole-number year.",
                    "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgeCalculatorApp().setVisible(true));
    }
}
