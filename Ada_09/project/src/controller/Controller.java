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
        view.addVoteListener(new registerVote());

        cakeChart.addGoToMainListener(new GoToMain());
        cakeChart.addWindowListener(new SaveChanges());

        barChart.addGoToMainListener(new GoToMain());
        barChart.addWindowListener(new SaveChanges());
    }

    public void start() {
        logger.info("");
        model.readInfo("options.txt");

        update();

        view.setVisible(true);
    }

    public void update(){
        view.updateContent(model);
        barChart.updateContent(model);
        cakeChart.updateContent(model);
    }

    class SaveChanges extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent event) {
            logger.info("");
            model.generateVotesFiles();
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

    class registerVote implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            logger.info("");
            model.registerVote(view.getSelectedProduct());
            update();
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
