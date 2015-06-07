package Project;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class AnimationPanel extends JPanel {
	private AnimationGraphics animation;
	
	private double thetaOffset;
	private final Point start;
	private final Point end;
	
	private boolean phiFlag;
	private boolean thetaFlag;
	
	
	//Konstruktor
	public AnimationPanel(int edge, VariableFrame vPanel) { 
		thetaOffset = 0;
		start = new Point();
		end = new Point();
		phiFlag = false;
		thetaFlag = false;
		
		//Ustawianie elementów 
		this.setLayout(new BorderLayout()); {
		
			JTextField textField = new JTextField();
				/*
				 * TODO
				 * ulepszyć metodę generacji treści tego TextField`a
				 */
				textField.setText("Hello!");
				textField.setEditable(false);
			this.add(textField,BorderLayout.PAGE_END);
			
			animation = new AnimationGraphics(edge,vPanel);
				animation.setPreferredSize(new Dimension(edge+10, edge+10));
				animation.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
				
				animation.addMouseListener(new AnimationMouseListener(textField));
				animation.addMouseMotionListener(new AnimationMouseMotionListener(textField));
			this.add(animation,BorderLayout.CENTER);
		}
	}

	//Pola Dostępu
	public AnimationGraphics getAnimationGraph() {
		return animation;
	}
	
	
	//Klasy wewnętrzne
	private int eOffsetX (MouseEvent e) {  
		return (-1)*(e.getX()-animation.getOVect().x);
	}
	
	private int eOffsetY (MouseEvent e) {
		return (-1)*(e.getY()-animation.getOVect().y);
	}
	
	private double round (double val, int acc) {
		return (double)(Math.round(Math.pow(10, acc)*val))/Math.pow(10, acc);
	}
	
	private final class AnimationMouseListener implements MouseListener {	//Klasa wewnętrzna utworzona
		private final JTextField statusBar;										//by nadać czytelności kodu
		private int acc; 
		
		public AnimationMouseListener(JTextField textField) {
			this.statusBar = textField;
			acc = 2;
		}
		@Override
		public void mousePressed(MouseEvent e) {
			start.x = eOffsetX(e);
			start.y = eOffsetY(e);
			
			int sX = (-1)*(animation.getDetLoc().x-animation.getOVect().x);
			int sY = (-1)*(animation.getDetLoc().y-animation.getOVect().y);
			if (Math.pow(eOffsetX(e)-sX, 2) +Math.pow(eOffsetY(e)-sY, 2) <= 2*Math.pow(30/2,2)) {
				phiFlag = true;
			}
			
			if (Math.pow(eOffsetX(e), 2) +Math.pow(eOffsetY(e), 2) <= 2*Math.pow(35,2)) {
				thetaFlag = true;
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			end.x = eOffsetX(e);
			end.y = eOffsetY(e);
			
			if (thetaFlag) {
				thetaOffset = animation.getTheta();
			}
			
			phiFlag = false;
			thetaFlag = false;
		}
		@Override
		public void mouseExited(MouseEvent e) {
			statusBar.setText(	"Mouse Exited :) \t" + 
								"Phi = " + round(animation.getPhi() < 0 ? 360+animation.getPhi() : animation.getPhi(),acc) + "\t" + 
								"Theta = " + round(animation.getTheta(),acc));
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseClicked(MouseEvent e) {
		}
	}
	
	private final class AnimationMouseMotionListener implements MouseMotionListener {
		private final JTextField statusBar;
		private int acc;
		
		public AnimationMouseMotionListener(JTextField textField) {
			this.statusBar = textField;
			acc = 2;
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			statusBar.setText(displayText(e));
		}
		@Override
		public void mouseDragged(MouseEvent e) {		
			statusBar.setText(displayText(e));

			/*
			 * TODO
			 * warunki rysowania detektora
			 */
			if (phiFlag) { 
				animation.setPhi(Math.toDegrees(Math.atan2(eOffsetY(e), eOffsetX(e))));
				animation.repaint();
			}
			
			if (thetaFlag) {		
				double iniTheta = Math.toDegrees(Math.atan2(eOffsetY(e), eOffsetX(e))) - (90-Math.toDegrees(Math.atan2(start.x, start.y))) + thetaOffset;
				
				if((iniTheta<=0)&&(iniTheta>=(-90))) {
					iniTheta = 0;
				}
				
				if(iniTheta>=90) {
					iniTheta = 90;
				}
				
				if ((iniTheta>=0)&&(iniTheta<=90)) {
					animation.setTheta(iniTheta);
				}
				animation.repaint();
			}
		}
		
		//metoda pomocnicza
		private String displayText(MouseEvent e) {
			return ("Mouse: ("+eOffsetX(e)+","+eOffsetY(e)+")" + "   \t" + 
					"Phi = " + round(animation.getPhi() < 0 ? 360+animation.getPhi() : animation.getPhi(),acc) + "\t" + 
					"Theta = " + round(animation.getTheta(),acc));
		}
	}
}