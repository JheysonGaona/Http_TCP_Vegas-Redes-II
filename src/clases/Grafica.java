package clases;

import gui.FrmServidor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafica {

    XYSeries cwnd;
    XYSeries y;
    XYSeries alpha;
    XYSeries beta;
    XYSeries limit;

    public Grafica() {
        cwnd = new XYSeries("CWND");
        y = new XYSeries("Y");
        alpha = new XYSeries("ALPHA");
        beta = new XYSeries("BETA");
        limit = new XYSeries("LIMITE");
    }

    public void graficarTCP(int iter, int cwd1, int y1, int alpha1, int beta1, int pipelimit1) {
        cwnd.add(iter, cwd1);
        y.add(iter, y1);
        alpha.add(iter, alpha1);
        beta.add(iter, beta1);
        limit.add(iter, pipelimit1);
    }

    public void plotGraph() {
        // Add the series to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(cwnd);
        dataset.addSeries(y);
        dataset.addSeries(alpha);
        dataset.addSeries(beta);
        dataset.addSeries(limit);

        // Generate the graph
        JFreeChart chart = ChartFactory.createXYLineChart("HTTP CON TCP VEGAS", // Title
                "SEGMENTOS", // x-axis Label
                "CWND", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        // create and display a frame...
        ChartPanel panel = new ChartPanel(chart);
        FrmServidor.pnlGrafica.removeAll();
        FrmServidor.pnlGrafica.setLayout(new java.awt.BorderLayout());
        FrmServidor.pnlGrafica.add(panel);
        FrmServidor.pnlGrafica.validate();
    }
}
