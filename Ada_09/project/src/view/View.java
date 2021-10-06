package view;

import model.Log;
import model.Model;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class View extends JFrame implements UIActions {
    private final Logger logger;
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
        votingOptions.setSelected(productOneRB.getModel(), true);
        /*
        productOneRB.setText(model.getFirstProductName());
        productTwoRB.setText(model.getSecondProductName());
        productThreeRB.setText(model.getThirdProductName());
        productOneVotes.setText("" + model.getP1VotesAmount());
        productTwoVotes.setText("" + model.getP2VotesAmount());
        productThreeVotes.setText("" + model.getP3VotesAmount());
         */
    }

    public void showError(String message) {
        logger.info("");
        JOptionPane.showMessageDialog(this, message);
    }

    public int getSelectedProduct() {
        logger.info("");
        int selectedIndex = -1;

        if (productOneRB.isSelected()) {
            selectedIndex = 0;
        } else if (productTwoRB.isSelected()) {
            selectedIndex = 1;
        } else if (productThreeRB.isSelected()) {
            selectedIndex = 2;
        }

        return selectedIndex;
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
