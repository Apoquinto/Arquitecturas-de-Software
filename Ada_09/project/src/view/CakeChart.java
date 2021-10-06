package view;

import model.Log;
import model.Model;
import model.PollOption;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.logging.Logger;

public class CakeChart extends JFrame implements UIActions {
    private final Logger logger;
    private JPanel background;
    private JButton goBackButton;
    private JPanel chartArea;
    private JLabel title;

    public CakeChart() {
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
        DefaultPieDataset dataset = new DefaultPieDataset();
        int index = 0;
        for (PollOption option: model.getOptionsData()) {
            dataset.insertValue(index, option.getName(), option.getCount());
            index++;
        }
        JFreeChart chart = ChartFactory.createPieChart(
                "Votaciones",
                dataset,
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
