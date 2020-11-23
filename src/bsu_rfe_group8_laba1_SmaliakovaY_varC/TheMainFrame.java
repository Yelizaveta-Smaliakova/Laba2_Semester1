package bsu_rfe_group8_laba1_SmaliakovaY_varC;

import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.*;

public class TheMainFrame extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    // private Image img;
    private JTextField textFiledX;
    private JTextField textFieldY;
    private JTextField textFieldZ;

    private Box hboxImg = Box.createHorizontalBox();

    private JTextField textFiledMem1;
    private JTextField textFieldMem2;
    private JTextField textFieldMem3;

    private JTextField textFieldResult;
    private ButtonGroup radioButtonsVariables = new ButtonGroup();
    private ButtonGroup radioButtonsFormula = new ButtonGroup();

    private Box hboxVariabeType = Box.createHorizontalBox();

    private int formulaId = 1;

    private ImageComponent component = new ImageComponent(formulaId);

    private int variableId = 1;

    private Double mem1;
    private Double mem2;
    private Double mem3;

    Box contentBox = Box.createVerticalBox();

    private void addRadioButton(String buttonName, final int formulaId, Box hboxFormulaType) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TheMainFrame.this.formulaId = formulaId;
                TheMainFrame.this.contentBox.remove(component);
                TheMainFrame.this.component = new ImageComponent(formulaId);
                TheMainFrame.this.contentBox.add(component);
                setSize(WIDTH+1, HEIGHT+1);
                setSize(WIDTH-1, HEIGHT-1);
            }
        });
        radioButtonsFormula.add(button);
        hboxFormulaType.add(button);
    }

    public double calculate1(double x, double y, double z) {
        return (Math.sin(Math.log(y) + Math.sin(Math.PI * y * y)) * Math.sqrt(Math.sqrt(x * x + Math.sin(z) + Math.pow(Math.E, Math.cos(z)))));
    }

    public double calculate2(double x, double y, double z) {
        return (Math.pow(Math.cos(Math.pow(Math.E, x))+Math.log((1+y)*(1+y)) + Math.sqrt(Math.pow(Math.E, Math.cos(x))+Math.pow(Math.sin(Math.PI*z), 2)) + Math.sqrt(1/x)+ Math.cos(y*y),Math.sin(z)));
    }

    public void addRadioButtonVariables(String buttonName, int variableId)  {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TheMainFrame.this.variableId = variableId;
            }
        });
        radioButtonsVariables.add(button);
        hboxVariabeType.add(button);
    }

    public static void main(String[] args)  {
        TheMainFrame frame = new TheMainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public TheMainFrame ()  {
        super("Calculating a value");
        Toolkit kit = Toolkit.getDefaultToolkit();

        //Размер окна
        setSize(WIDTH, HEIGHT);
        setLocation((kit.getScreenSize().width - WIDTH)/2,  (kit.getScreenSize().height - HEIGHT)/2);

        //Радиокнопки X, Y, Z
        addRadioButtonVariables("Variable X", 1);
        addRadioButtonVariables("Variable Y", 2);
        addRadioButtonVariables("Variable Z", 3);

        //Радиокнопки для формул
        Box hboxFormulaType = Box.createHorizontalBox();
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Formula 1", 1, hboxFormulaType);
        addRadioButton("Formula 2", 2, hboxFormulaType);
        radioButtonsFormula.setSelected(radioButtonsFormula.getElements().nextElement().getModel(), true);
        radioButtonsVariables.setSelected(radioButtonsVariables.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        //Поля для ввода значений переменных
        JLabel labelForX = new JLabel("X:");
        textFiledX = new JTextField("0", 10);
        textFiledX.setMaximumSize(textFiledX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("           Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        //hboxVariables.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createVerticalStrut(10));
        hboxVariables.add(textFiledX);
        hboxVariables.add(Box.createVerticalStrut(80));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createVerticalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createVerticalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());

        //Считывание значений переменных из полей
        mem1 = Double.parseDouble(textFiledX.getText());
        mem2 = Double.parseDouble(textFieldY.getText());
        mem3 = Double.parseDouble(textFieldZ.getText());

        //Кнопка MC
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (variableId == 1)
                {
                    textFiledX.setText("0");
                }
                if (variableId == 2)
                {
                    textFieldY.setText("0");
                }
                if (variableId == 3)
                {
                    textFieldZ.setText("0");
                }
                textFiledMem1.setText(textFiledX.getText());
                textFieldMem2.setText(textFieldY.getText());
                textFieldMem3.setText(textFieldZ.getText());
            }
        });

        //Кнопка М+
        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (variableId == 1)
                {
                    Double Result;
                    Result = Double.parseDouble(textFiledX.getText())+ Double.parseDouble(textFieldResult.getText());
                    textFieldResult.setText(Result.toString());
                }
                if (variableId == 2)
                {
                    Double Result;
                    Result = Double.parseDouble(textFieldY.getText())+ Double.parseDouble(textFieldResult.getText());
                    textFieldResult.setText(Result.toString());
                }
                if (variableId == 3)
                {
                    Double Result;
                    Result = Double.parseDouble(textFieldZ.getText())+ Double.parseDouble(textFieldResult.getText());
                    textFieldResult.setText(Result.toString());
                }
                textFiledMem1.setText(textFiledX.getText());
                textFieldMem2.setText(textFieldY.getText());
                textFieldMem3.setText(textFieldZ.getText());
            }
        });

        // Размещение МС и М+
        Box hboxAcrionsVariable = Box.createHorizontalBox();
        hboxAcrionsVariable.add(Box.createHorizontalGlue());
        hboxAcrionsVariable.add(buttonMC);
        hboxAcrionsVariable.add(Box.createVerticalStrut(30));
        hboxAcrionsVariable.add(buttonMPlus);
        hboxAcrionsVariable.add(Box.createHorizontalGlue());

        //Размещение Результата
        JLabel labelForResult = new JLabel("Result:");
        textFieldResult = new JTextField("0", 15);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createVerticalStrut(40));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.RED));

        //Кнопка Расчета результата
        JButton bottonCalc = new JButton("Calculate");
        bottonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Double x = Double.parseDouble(textFiledX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId == 1)
                        result = calculate1(x,y,z);
                    else
                        result = calculate2(x,y,z);
                    textFieldResult.setText(result.toString());
                    textFiledMem1.setText(textFiledX.getText());
                    textFieldMem2.setText(textFieldY.getText());
                    textFieldMem3.setText(textFieldZ.getText());
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(TheMainFrame.this, "Data entered error","Data error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        //Кнопка для очищения полей
        JButton buttonReset = new JButton("Clear fields");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFiledX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
                textFiledMem1.setText(textFiledX.getText());
                textFieldMem2.setText(textFieldY.getText());
                textFieldMem3.setText(textFieldZ.getText());
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(bottonCalc);
        hboxButtons.add(Box.createVerticalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        //   hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Box hboxMems = Box.createHorizontalBox();
        JLabel labelForMem1 = new JLabel("X:");
        textFiledMem1 = new JTextField(textFiledX.getText(), 10);
        textFiledMem1.setMaximumSize(textFiledMem1.getPreferredSize());
        JLabel labelForMem2 = new JLabel("Y:");
        textFieldMem2 = new JTextField(textFieldY.getText(), 10);
        textFieldMem2.setMaximumSize(textFieldMem2.getPreferredSize());
        JLabel labelForMem3 = new JLabel("           Z:");
        textFieldMem3 = new JTextField(textFieldZ.getText(), 10);
        textFieldMem3.setMaximumSize(textFieldMem3.getPreferredSize());
        hboxMems.add(Box.createHorizontalGlue());
        hboxMems.add(labelForMem1);
        hboxMems.add(Box.createVerticalStrut(10));
        hboxMems.add(textFiledMem1);
        hboxMems.add(Box.createVerticalStrut(20));
        hboxMems.add(labelForMem2);
        hboxMems.add(Box.createVerticalStrut(10));
        hboxMems.add(textFieldMem2);
        hboxMems.add(labelForMem3);
        hboxMems.add(Box.createVerticalStrut(10));
        hboxMems.add(textFieldMem3);
        hboxMems.add(Box.createHorizontalGlue());

        Box hboxNow = Box.createHorizontalBox();
        hboxNow.add(Box.createHorizontalGlue());
        JLabel labelForNow = new JLabel("Current value of variables");
        hboxNow.add(labelForNow);
        hboxNow.add(Box.createHorizontalGlue());

        JButton refel = new JButton("Refresh");
        refel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariabeType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxAcrionsVariable);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxNow);
        contentBox.add(hboxMems);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(component);
        getContentPane().add(contentBox, BorderLayout.CENTER);

    }
}