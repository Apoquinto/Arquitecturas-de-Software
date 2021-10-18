package view;

import model.Log;
import model.Model;
import model.PollOption;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.logging.Logger;

public class View extends JFrame implements UIActions {
    private final Logger logger;
    private String selected;
    private JPanel background;
    private JLabel title;
    private JLabel optionLabelOne;
    private JLabel optionLabelTwo;
    private JLabel optionLabelThree;
    private JRadioButton productOneRB;
    private JRadioButton productTwoRB;
    private JRadioButton productThreeRB;
    private JTextField productOneVotes;
    private JTextField productTwoVotes;
    private JTextField productThreeVotes;
    private JButton voteButton;
    private JButton cakeChartButton;
    private JButton barChartButton;
    private ButtonGroup votingOptions;

    public View() {
        this.logger = Log.getLogger();
        this.add(background);
        this.setTitle("ADA 9");
        this.setSize(400, 180);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.votingOptions = new ButtonGroup();
        votingOptions.add(productOneRB);
        votingOptions.add(productTwoRB);
        votingOptions.add(productThreeRB);
    }

    public void updateContent(Model model) {
        logger.info("");
        try {
            Iterator<PollOption> options = model.getOptionsIterator();
            PollOption tempOption;
            tempOption = options.next();
            productOneRB.setText(tempOption.getName());
            productOneVotes.setText("" + tempOption.getCount());
            tempOption = options.next();
            productTwoRB.setText(tempOption.getName());
            productTwoVotes.setText("" + tempOption.getCount());
            tempOption = options.next();
            productThreeRB.setText(tempOption.getName());
            productThreeVotes.setText("" + tempOption.getCount());
        }
        catch(Exception e) {
            System.out.println("Error al consultar los datos");
            e.printStackTrace();
        }
    }

    public void showError(String message) {
        logger.info("");
        JOptionPane.showMessageDialog(this, message);
    }

    public String getSelectedProduct() {
        logger.info("");
        String result = "";

        if (productOneRB.isSelected()) {
            result = productOneRB.getText();
        } else if (productTwoRB.isSelected()) {
            result = productTwoRB.getText();
        } else if (productThreeRB.isSelected()) {
            result = productThreeRB.getText();
        }

        return result;
    }

    public void addVoteButtonListener(ActionListener listener) {
        logger.info("");
        voteButton.addActionListener(listener);
    }

    public void addGoToCakeChartListener(ActionListener listener) {
        logger.info("");
        cakeChartButton.addActionListener(listener);
    }

    public void addGoToBarChartListener(ActionListener listener) {
        logger.info("");
        barChartButton.addActionListener(listener);
    }
}
