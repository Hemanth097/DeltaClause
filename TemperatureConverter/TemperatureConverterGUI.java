import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI {

    private JFrame frame;
    private JTextField tempField;
    private JRadioButton fahrenheitRadio;
    private JRadioButton celsiusRadio;
    private JLabel resultLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TemperatureConverterGUI window = new TemperatureConverterGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TemperatureConverterGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Temperature Converter");
        frame.setBounds(100, 100, 450, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        tempField = new JTextField();
        tempField.setColumns(10);
        frame.getContentPane().add(tempField);

        ButtonGroup group = new ButtonGroup();

        fahrenheitRadio = new JRadioButton("Fahrenheit");
        fahrenheitRadio.setSelected(true);
        group.add(fahrenheitRadio);
        frame.getContentPane().add(fahrenheitRadio);

        celsiusRadio = new JRadioButton("Celsius");
        group.add(celsiusRadio);
        frame.getContentPane().add(celsiusRadio);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(tempField.getText());
                if (fahrenheitRadio.isSelected()) {
                    double celsius = (temp - 32) * 5.0 / 9.0;
                    resultLabel.setText(temp + " Fahrenheit is equal to " + celsius + " Celsius.");
                } else if (celsiusRadio.isSelected()) {
                    double fahrenheit = (temp * 9.0 / 5.0) + 32;
                    resultLabel.setText(temp + " Celsius is equal to " + fahrenheit + " Fahrenheit.");
                }
            }
        });
        frame.getContentPane().add(convertButton);

        resultLabel = new JLabel("");
        frame.getContentPane().add(resultLabel);
    }

}