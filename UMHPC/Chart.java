package Assignment.UMHPC;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * @author kinderBono
 */
public class Chart {
    
    public void errorPieChart(Map<String, Integer> errorCounts){
        JFrame jframe = new JFrame();
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        
        for (String user : errorCounts.keySet()){
            pieDataset.setValue(user, errorCounts.get(user));
        }
        
        JFreeChart chart = ChartFactory.createPieChart(
                "Total Error with \nIt's Corresponding User", 
                pieDataset, 
                true, true, false);
        
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(172, 255, 252));
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {1}"));
        
        chart.setBackgroundPaint(new Color(209, 209, 209));
        ChartFrame frame = new ChartFrame("Pie Chart - Error Count", chart);
        frame.pack();
        
        jframe.add(frame.getChartPanel());
        jframe.setTitle("Pie Chart - Error Count");
        try {
            jframe.setIconImage(ImageIO.read(new File("Pie Chart.png")));
        } catch (IOException ex) {
            Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, null, ex);
        }
        jFrameSetProperties(jframe);
        jframe.setVisible(true);
    }
    
    public void executionLineChart(int oneHdrd, int twoHdrd, int threeHdrd, int upperFifty, 
                                   int lowerFifty, int twentyFour, int twelve, int oneHr){
        JFrame jframe = new JFrame();
        
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        barDataset.addValue(oneHr, "Jobs", "Within 1");
        barDataset.addValue(twelve, "Jobs", "Within 12");
        barDataset.addValue(twentyFour, "Jobs", "Within 24");
        barDataset.addValue(lowerFifty, "Jobs", "< 50");
        barDataset.addValue(upperFifty, "Jobs", "> 50");
        barDataset.addValue(oneHdrd, "Jobs", "> 100");
        barDataset.addValue(twoHdrd, "Jobs", "> 200");
        barDataset.addValue(threeHdrd, "Jobs", "> 300");

        JFreeChart barChart = ChartFactory.createBarChart("Job Execution Time", "Range", "Number of Jobs", barDataset);
        barChart.removeLegend();
        CategoryPlot barPlot = barChart.getCategoryPlot();
        barPlot.setBackgroundPaint(new Color(172, 255, 252));

        DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();
        lineDataset.addValue(oneHr, "Jobs", "Within 1");
        lineDataset.addValue(twelve, "Jobs", "Within 12");
        lineDataset.addValue(twentyFour, "Jobs", "Within 24");
        lineDataset.addValue(lowerFifty, "Jobs", "< 50");
        lineDataset.addValue(upperFifty, "Jobs", "> 50");
        lineDataset.addValue(oneHdrd, "Jobs", "> 100");
        lineDataset.addValue(twoHdrd, "Jobs", "> 200");
        lineDataset.addValue(threeHdrd, "Jobs", "> 300");

        JFreeChart lineChart = ChartFactory.createLineChart("Job Execution Time", "Range", "Number of Jobs", lineDataset);
        lineChart.removeLegend();
        CategoryPlot linePlot = lineChart.getCategoryPlot();
        linePlot.setBackgroundPaint(new Color(172, 255, 252));

        CombinedDomainCategoryPlot combinedPlot = new CombinedDomainCategoryPlot(new CategoryAxis("Execution Time"));
        combinedPlot.add(barPlot, 2);
        combinedPlot.add(linePlot, 1);

        JFreeChart combinedChart = new JFreeChart("Job Execution Time", JFreeChart.DEFAULT_TITLE_FONT, combinedPlot, false);
        
        ChartPanel panel = new ChartPanel(combinedChart);
        
        jframe.add(panel);
        jframe.setTitle("Combined Chart - Execution Time");
        try {
            jframe.setIconImage(ImageIO.read(new File("Line Chart.png")));
        } catch (IOException ex) {
            Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, null, ex);
        }
        jFrameSetProperties(jframe);
        jframe.setVisible(true);
    }
    
    public void allJobBarChart(int jobAllocate, int jobComplete, int jobKill,
                            int jobExhaust, int jobClean, int allocateComplete, int allocateKill, 
                            int allocateExhaust, int allocateClean){
        JFrame jframe = new JFrame();
        DefaultCategoryDataset barChart = new DefaultCategoryDataset();
        
        barChart.addValue(jobAllocate, "Allocated", "Allocated");
        barChart.addValue(jobComplete, "Total Jobs", "Completed");
        barChart.addValue(allocateComplete, "Within Allocated Time Range", "Completed");
        barChart.addValue(jobKill, "Total Jobs", "Killed");
        barChart.addValue(allocateKill, "Within Allocated Time Range", "Killed");
        barChart.addValue(jobExhaust, "Total Jobs", "Exhausted");
        barChart.addValue(allocateExhaust, "Within Allocated Time Range", "Exhausted");
        barChart.addValue(jobClean, "Total Jobs", "Cleaned");
        barChart.addValue(allocateClean, "Within Allocated Time Range", "Cleaned");
        barChart.addValue((jobAllocate - (allocateComplete + allocateKill)), "Total Jobs", "Running");

        JFreeChart chart = ChartFactory.createBarChart(
                "Job Allocation Status", "Job Status", "Number of Jobs", barChart, PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(new Color(210, 210, 210));
        
        ChartPanel panel = new ChartPanel(chart);
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesVisibleInLegend(0, false);
        plot.setBackgroundPaint(new Color(172, 255, 252));
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setMaximumBarWidth(3);
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setItemLabelsVisible(true);
        renderer.setBarPainter(new StandardBarPainter());
        
        jframe.add(panel);
        jframe.pack();
        jframe.setTitle("Bar Chart - Job Allocation");
        try {
            jframe.setIconImage(ImageIO.read(new File("Bar Chart.png")));
        } catch (IOException ex) {
            Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, null, ex);
        }
        jFrameSetProperties(jframe);
        jframe.setVisible(true);
    }
    public void jFrameSetProperties(JFrame jframe){
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(600, 600);
        jframe.setLocationRelativeTo(null);
        jframe.setBackground(new Color(172, 255, 252));
        jframe.setResizable(false);
    }
}

class stackedBarChart extends ApplicationFrame {
    private Chart chart = new Chart();
    private JFrame jframe = new JFrame();

    public stackedBarChart(String applicationTitle, String chartTitle, int gpu, int epyc, int opteron) {
        super(applicationTitle);
        JFreeChart stackedBarChart = ChartFactory.createStackedBarChart(
                chartTitle, "", "Jobs", createDataset(gpu, epyc, opteron),
                org.jfree.chart.plot.PlotOrientation.VERTICAL, true, true, false);
        stackedBarChart.setBackgroundPaint(new Color(210, 210, 210));
        stackedBarChart.getCategoryPlot().setBackgroundPaint(new Color(172, 255, 252));
        
        ChartPanel chartPanel = new ChartPanel(stackedBarChart);
        
        BarRenderer renderer = (BarRenderer) stackedBarChart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, new Color(250, 0, 0));
        renderer.setSeriesPaint(1, new Color(0, 250, 0));
        renderer.setSeriesPaint(2, new Color(0, 0, 250));
        renderer.setDrawBarOutline(false);
        renderer.setBarPainter(new StandardBarPainter());
        
        chartPanel.setBounds(0,0,320,240);
        
        jframe.setContentPane(chartPanel);
        RefineryUtilities.centerFrameOnScreen(jframe);
        jframe.setTitle("Staked Bar Chart - Partitions");
        try {
            jframe.setIconImage(ImageIO.read(new File("Stacked Bar Chart.png")));
        } catch (IOException ex) {
            Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, null, ex);
        }
        chart.jFrameSetProperties(jframe);
        jframe.setVisible(true);
    }

    private DefaultCategoryDataset createDataset(int gpu, int epyc, int opteron) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(gpu, "GPU", "Partition");
        dataset.addValue(epyc, "EPYC", "Partition");
        dataset.addValue(opteron, "Opteron", "Partition");
        return dataset;
    }
}