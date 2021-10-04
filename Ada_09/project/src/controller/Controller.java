package controller;

import model.Model;
import model.Log;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

public class Controller {
    private final Logger logger;
    private final Model model;
    private final View view;
    private final BarChart barChart;
    private final CakeChart cakeChart;

    public Controller(Model model, View view, BarChart barChart, CakeChart cakeChart) {
        this.logger = Log.getLogger();
        this.model = model;
        this.view = view;
        this.barChart = barChart;
        this.cakeChart = cakeChart;

        // Adding listeners area
        view.addGoToCakeChartListener(new GoToCakeChart());
        view.addGoToBarChartListener(new GoToBarChart());
        view.addWindowListener(new SaveChanges());

        cakeChart.addGoToMainListener(new GoToMain());
        cakeChart.addWindowListener(new SaveChanges());

        barChart.addGoToMainListener(new GoToMain());
        barChart.addWindowListener(new SaveChanges());
    }

    public void start() {
        logger.info("");
        model.readInfo();

        // TEMPORAL TESTING (DELETE LATER)
        // THE OBSERVER IS SUPPOSED TO DO THIS
        // IN THIS ORDER
        view.updateContent(model);
        barChart.updateContent(model);
        cakeChart.updateContent(model);
        // END OF TESTING

        view.setVisible(true);
    }

    class SaveChanges extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent event) {
            logger.info("");
            model.saveVotesInFile();
        }
    }

    class GoToMain implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            logger.info("");
            view.setVisible(true);
            barChart.setVisible(false);
            cakeChart.setVisible(false);
        }
    }

    class GoToCakeChart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            logger.info("");
            view.setVisible(false);
            cakeChart.setVisible(true);
        }
    }

    class GoToBarChart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            logger.info("");
            view.setVisible(false);
            barChart.setVisible(true);
        }
    }
}
