package view;

import model.Log;
import model.Model;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class BarChart extends JFrame implements UIActions {
    private final Logger logger;
    private JPanel background;
    private JLabel title;
    private JPanel chartArea;
    private JButton goBackButton;

    public BarChart() {
        this.logger = Log.getLogger();
        this.add(background);
        this.setTitle("ADA 9");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void updateContent(Model model) {
        logger.info("");
        generateChart(model);
    }

    private void generateChart(Model model) {
        logger.info("");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(model.getP1VotesAmount(), "Votos", model.getFirstProductName());
        dataset.setValue(model.getP2VotesAmount(), "Votos", model.getSecondProductName());
        dataset.setValue(model.getP3VotesAmount(), "Votos", model.getThirdProductName());

        JFreeChart chart = ChartFactory.createBarChart(
                "Votaciones",
                "Productos en votación",
                "Votos acumulados",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(300, 200));

        chartArea.setLayout(new BorderLayout());
        chartArea.add(chartPanel, BorderLayout.CENTER);

        repaint();
    }

    public void showError(String message) {
        logger.info("");
        JOptionPane.showMessageDialog(this, message);
    }

    public void addGoToMainListener(ActionListener listener) {
        logger.info("");
        goBackButton.addActionListener(listener);
    }
}
