package UI;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author andre
 */
public class Graficar {
    
    private  SimuladorApple apple;
    private  SimuladorHP hp;
    private XYSeries seriesApple;
    private XYSeries seriesHP;
    private XYSeriesCollection dataset;
    private JFreeChart xyLineChart;
    private Timer updateTimer;

    /**
     * Constructor de ChartManager que inicializa las series de datos,
     * crea el gráfico y comienza el temporizador de actualización.
     */
    
    public Graficar(SimuladorApple apple, SimuladorHP hp) {
        this.apple = apple; // Asignar a la variable de instancia
        this.hp = hp;
        initializeSeries();
        initializeChart();
        startDataUpdateTimer();
    }
    
      /**
     * Inicializa las series de datos para Nickelodeon y Cartoon Network
     * y las agrega al conjunto de datos para el gráfico.
     */

    private void initializeSeries() {
        seriesApple = new XYSeries("Apple");
        seriesHP = new XYSeries("HP");
        dataset = new XYSeriesCollection();
        dataset.addSeries(seriesApple);
        dataset.addSeries(seriesHP);
    }

    /**
     * Crea el gráfico XY Line usando JFreeChart y configura la apariencia.
     */
    
    private void initializeChart() {
        xyLineChart = ChartFactory.createXYLineChart(
                "Tiempo vs Utilidad", // Título del gráfico
                "Tiempo",             // Etiqueta eje X
                "Utilidad",           // Etiqueta eje Y
                dataset,              // Dataset
                PlotOrientation.VERTICAL,
                true,                 // Mostrar leyenda
                true,                 // Generar tooltips
                false                 // URLs
        );

        customizeChart();
    }

     /**
     * Personaliza la apariencia del gráfico XY Line.
     */
    
    private void customizeChart() {
        XYPlot plot = xyLineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
    }

     /**
     * Inicia un temporizador que actualiza las series de datos con un intervalo
     * que se obtiene de la duración del día en la aplicación.
     */
    
    private void startDataUpdateTimer() {
        int delay = apple.getEmpresa().segundosXdia * 1000;
        updateTimer = new Timer(delay, e -> updateChartData());
        updateTimer.start();
    }

     /**
     * Actualiza las series de datos con las ganancias más recientes de Nickelodeon
     * y Cartoon Network y las agrega al gráfico.
     */
    
    public void updateChartData() {
        // Se obtienen las nuevas ganancias
        int appleProfit = apple.getEmpresa().utilidad; 
        int HPProfit = hp.getEmpresa().utilidad;
        int newTimePoint = seriesApple.getItemCount() + 1;

        seriesApple.addOrUpdate(newTimePoint, appleProfit);
        seriesHP.addOrUpdate(newTimePoint, HPProfit);
    }

    public ChartPanel getChartPanel() {
        return new ChartPanel(xyLineChart);
    }

    public void stopUpdateTimer() {
        if (updateTimer != null) {
            updateTimer.stop();
        }
    }
}
    

