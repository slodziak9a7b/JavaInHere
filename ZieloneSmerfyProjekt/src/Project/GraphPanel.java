package Project;
import java.awt.Point;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

//Powinna to być metoda w Interfejs
public class GraphPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GraphPanel () {
		
		//Zmienne sterujące
		final int n = 3; 
		final int max = 50;
		
		// pusty zestaw danych
	    final XYSeriesCollection dataset = new XYSeriesCollection(); 
	    final XYSeries series1 = new XYSeries("Object 1");
	    final XYSeries series2 = new XYSeries("Object 2");
	    final XYSeries series3 = new XYSeries("Object 3");
	    dataset.addSeries(series1);
	    dataset.addSeries(series2);
	    dataset.addSeries(series3);
	    
	    //Zestaw danych
		final Random generator = new Random();
		final Point p1[] = new Point[n];
		final Point p2[] = new Point[n];
		final Point p3[] = new Point[n];
		for (int i = 0; i < n; i++) {
			p1[i] = new Point();
			p2[i] = new Point();
			p3[i] = new Point();
		}

		// wykres liniowy
		JFreeChart lineGraph = ChartFactory.createXYLineChart
			     ("Detektor",					// Title
			       "Kąt Obserwacji",			// X-Axis label
			       "Natężenie ",				// Y-Axis label
			       dataset,						// Dataset
			       PlotOrientation.VERTICAL,	// Plot orientation
			       false,						// show legend
			       true,						// Show tooltips
			       false						// url show
			);

		
		//Ustawiam Elementy
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); {
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			
			ChartPanel chartPanel = new ChartPanel(lineGraph);
			this.add(chartPanel);
		}
		
		// zapelniam wykres
		/*JButton button = new JButton("Generuj Wykres");
		button.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				series1.clear();
				series1.add(1,1);
				for (int i = 0; i<n; i++) {
					p1[i].x = generator.nextInt(max)+1;
					p1[i].y = generator.nextInt(max)+1;
					series1.add(p1[i].x, p1[i].y);
				}
				series1.add(max,1);
				
				series2.clear();
				series2.add(1,1);
				for (int i = 0; i<n; i++) {
					p2[i].x = generator.nextInt(max)+1;
					p2[i].y = generator.nextInt(max)+1;
					series2.add(p2[i].x, p2[i].y);
				}
				series2.add(max,1);

				series3.clear();
				series3.add(1,1);
				for (int i = 0; i<n; i++) {
					p3[i].x = generator.nextInt(max)+1;
					p3[i].y = generator.nextInt(max)+1;
					series3.add(p3[i].x, p3[i].y);
				}
				series3.add(max,1);
			}
	    });
		this.add(button);*/
	}
}
