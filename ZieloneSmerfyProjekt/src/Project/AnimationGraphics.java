package Project;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class AnimationGraphics extends JPanel {
	private VariableFrame vPanel;
	
	private int edge;
	private double phi;
	private double theta;
	
	private Point offsetVect;
	private Point detectorLoc;
	
	//Konstruktor
	public AnimationGraphics(int edge, VariableFrame vPanel) {
		this.edge = edge;
		this.vPanel = vPanel;
		
		offsetVect = new Point((edge/2)+4,(edge/2)+4);
		phi = 9;
		theta = 0;
	}
		
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Color color = new Color(112,148,255);
		
		int height = 0;
		int width = 0;
		double norma = ((double)(edge))/500;
		
		//Czystka
		g2.clearRect(2, 2, edge+4, edge+4);
		
		//BackGround
		g2.setColor(color);
		g2.fillRect(2, 2, edge+5, edge+5);
		g2.setColor(Color.WHITE);
		g2.fillOval(2, 2, edge+4, edge+4);
		g2.setColor(Color.BLACK);
		g2.drawOval(2, 2, edge+4, edge+4);
		
		//Emieter Elektronów
		width  = 60;
		height = 30;
		drawLine(g2, 0, edge/2, offsetVect, 0.0, Color.GREEN);
		drawLine(g2, 0, edge/2, offsetVect, 2*theta , Color.GREEN);
		drawRect(g2, (int)(width*norma), (int)(height*norma), edge/2, offsetVect, 0.0 , Color.GRAY);
		
		//Detektor Elektronów
		width  = 30;
		height = 30;
		drawLine(g2, edge/2-edge/10, edge/2, offsetVect, theta , Color.RED);
		detectorLoc = drawRect(g2, (int)(width*norma), (int)(height*norma),  edge/2, offsetVect, phi , Color.GRAY);
		
		// Kryształ
		width  = 10; 
		height = 100;
		drawRect(g2, (int)(width*norma), (int)(height*norma), 0, offsetVect, theta , color); 	
		
		//odświerzanie wartości vPanel wraz ze zmianą animacji :)
		//vPanel.compute();
	}
	
	//Metody Pomocnicze
	private void drawLine (Graphics2D g2, int rectWidht, int r, Point u, double phi, Color color) {
		int pX = (int) (r*Math.cos(Math.toRadians(phi-180))+u.x-rectWidht*Math.cos(Math.toRadians(phi-180)));
		int pY = (int) (r*Math.sin(Math.toRadians(phi-180))+u.y-rectWidht*Math.sin(Math.toRadians(phi-180)));
		
		g2.setColor(color);
		g2.drawLine(pX, pY, u.x, u.y);
	}
	
	private Point drawRect (Graphics2D g2, int rectWidht, int rectHeight, int r, Point u, double phi, Color color) {				
		int X[] = {0,0,0,0};
		int Y[] = {0,0,0,0};
		
		X[0] = (int) (r*Math.cos(Math.toRadians(phi-180))+u.x-(rectHeight/2)*Math.cos(Math.toRadians(phi-180+90))); 
		Y[0] = (int) (r*Math.sin(Math.toRadians(phi-180))+u.y-(rectHeight/2)*Math.sin(Math.toRadians(phi-180+90)));

		X[1] = (int) (r*Math.cos(Math.toRadians(phi-180))+u.x+(rectHeight/2)*Math.cos(Math.toRadians(phi-180+90)));
		Y[1] = (int) (r*Math.sin(Math.toRadians(phi-180))+u.y+(rectHeight/2)*Math.sin(Math.toRadians(phi-180+90)));
		
		X[2] = (int) (r*Math.cos(Math.toRadians(phi-180))+u.x-rectWidht*Math.cos(Math.toRadians(phi-180))+(rectHeight/2)*Math.cos(Math.toRadians(phi-180+90)));
		Y[2] = (int) (r*Math.sin(Math.toRadians(phi-180))+u.y-rectWidht*Math.sin(Math.toRadians(phi-180))+(rectHeight/2)*Math.sin(Math.toRadians(phi-180+90)));
		
		X[3] = (int) (r*Math.cos(Math.toRadians(phi-180))+u.x-rectWidht*Math.cos(Math.toRadians(phi-180))-(rectHeight/2)*Math.cos(Math.toRadians(phi-180+90)));
		Y[3] = (int) (r*Math.sin(Math.toRadians(phi-180))+u.y-rectWidht*Math.sin(Math.toRadians(phi-180))-(rectHeight/2)*Math.sin(Math.toRadians(phi-180+90)));
		
		//srodek kwadratu
		int pX = (int) (r*Math.cos(Math.toRadians(phi-180))+u.x-(rectWidht/2)*Math.cos(Math.toRadians(phi-180)));
		int pY = (int) (r*Math.sin(Math.toRadians(phi-180))+u.y-(rectWidht/2)*Math.sin(Math.toRadians(phi-180)));
		Point sPoint = new Point (pX, pY);
		
		
		g2.setColor(color);
		g2.fillPolygon(X, Y, 4);
		g2.setColor(Color.BLACK);
		g2.drawPolygon(X, Y, 4);
		
		return sPoint; 
	}
	
	//dostęp do pól
	public Point getDetLoc () {
		return detectorLoc;
	}
	
	public void setPhi(double iniPhi) {
		phi = iniPhi;
	}
	
	public double getPhi() {
		return phi;
	}
	
	public void setTheta(double iniTheta) {
		theta = iniTheta;
	}
	
	public double getTheta() {
		return theta;
	}
	
	public Point getOVect() {
		return offsetVect;
	}
}