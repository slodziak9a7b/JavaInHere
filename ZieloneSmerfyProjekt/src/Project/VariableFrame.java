package Project;

import improvedFormattedTextField.ImprovedFormattedTextField;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class VariableFrame extends JFrame {
	//Values for the fields
	private double voltage;
	private double velocity;
	private double energy;
	private double momentum;
	private double beta;
	private double gamma;
	private double dConst;  
	
	//Fields for data entry
	private ImprovedFormattedTextField voltageField;
	private ImprovedFormattedTextField dConstField;
	private ImprovedFormattedTextField tTF; 
	private ImprovedFormattedTextField oTF;
	
	//Labels for data exit
	private JLabel velocityLabel;
	private JLabel energyLabel;
	private JLabel momentumLabel;
	private JLabel betaLabel; 
	private JLabel gammaLabel; 
	private JLabel nMaxLabel;
	private JLabel phL;
	private JLabel iL;
	
	    
	public VariableFrame() {
		String[] title = {"U = ", "V = ", "E kin = ", "p = ", "V/C = ", "γ = "}; 
		String[] unit = {"[V]", "[km/s]","[eV]", "[E-23 Kg*m/s]", "[1]", "[1]"};   
		
		// wartości domyślne
		voltage = 54;
		dConst = 9;
		
		//Ustawiam Elementy
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS)); {
			
			// Voltage Panel
			JPanel voltagePanel = new JPanel(); { 
			voltagePanel.setLayout(new FlowLayout());
			voltagePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));	
			
				JPanel voltagePanel_titlePanel = new JPanel (); {
				voltagePanel_titlePanel.setLayout(new GridLayout(0,1));
				voltagePanel_titlePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
				
					JLabel voltageTitleLabel = tLabel(title[0]);
					voltageTitleLabel.setLabelFor(voltageField);
					voltagePanel_titlePanel.add(voltageTitleLabel);
					
					JLabel velocityTitleLabel = tLabel(title[1]);
					velocityTitleLabel.setLabelFor(velocityLabel);
					voltagePanel_titlePanel.add(velocityTitleLabel);
					
					
					JLabel energyTitleLabel = tLabel(title[2]);
					energyTitleLabel.setLabelFor(energyLabel);
					voltagePanel_titlePanel.add(energyTitleLabel);
					
					JLabel momentumTitleLabel = tLabel(title[3]);
					momentumTitleLabel.setLabelFor(momentumLabel);
					voltagePanel_titlePanel.add(momentumTitleLabel);
					
					JLabel betaTitleLabel = tLabel(title[4]);
					betaTitleLabel.setLabelFor(betaLabel);
					voltagePanel_titlePanel.add(betaTitleLabel);
					
					JLabel gammaTitleLabel = tLabel(title[5]);
					gammaTitleLabel.setLabelFor(gammaLabel);
					voltagePanel_titlePanel.add(gammaTitleLabel);
				}
				voltagePanel.add(voltagePanel_titlePanel);

				JPanel voltagePanel_valuesPanel = new JPanel (); {
				voltagePanel_valuesPanel.setLayout(new GridLayout(0,1));
				voltagePanel_valuesPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

					voltageField = inField (voltage);
					voltageField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
					voltagePanel_valuesPanel.add(voltageField);
					
					velocityLabel = outLabel (velocity);
					voltagePanel_valuesPanel.add(velocityLabel);
					
					energyLabel = outLabel (energy);
					voltagePanel_valuesPanel.add(energyLabel);
					
					momentumLabel = outLabel (momentum);
					voltagePanel_valuesPanel.add(momentumLabel);
					
					betaLabel = outLabel (beta);
					voltagePanel_valuesPanel.add(betaLabel);
					
					gammaLabel = outLabel (gamma);
					voltagePanel_valuesPanel.add(gammaLabel);
				}
				voltagePanel.add(voltagePanel_valuesPanel);

				JPanel voltagePanel_unitPanel = new JPanel (); {
				voltagePanel_unitPanel.setLayout(new GridLayout(0,1));
				voltagePanel_unitPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));	
				
					JLabel voltageUnitLabel = uLabel(unit[0]);
					voltagePanel_unitPanel.add(voltageUnitLabel);
					
					voltagePanel_unitPanel.add(uLabel(unit[1]));
					voltagePanel_unitPanel.add(uLabel(unit[2]));
					voltagePanel_unitPanel.add(uLabel(unit[3]));
					voltagePanel_unitPanel.add(uLabel(unit[4]));
					voltagePanel_unitPanel.add(uLabel(unit[5]));
				}
				voltagePanel.add(voltagePanel_unitPanel);
			}
			this.getContentPane().add(voltagePanel);
			
			
			// dConst Panel 
			JPanel dConstPanel = new JPanel (); {
			dConstPanel.setLayout(new BoxLayout(dConstPanel, BoxLayout.LINE_AXIS));
			dConstPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
				
				JPanel dConstPanel_titlePanel = new JPanel (); {
					
				}
				dConstPanel.add(dConstPanel_titlePanel);
				
				JPanel dConstPanel_valuesPanel = new JPanel (); {
					
				}
				dConstPanel.add(dConstPanel_valuesPanel);
				
				JPanel dConstPanel_unitPanel = new JPanel (); {
					
				}
				dConstPanel.add(dConstPanel_unitPanel);
			}
			this.getContentPane().add(dConstPanel);
		}
	
					
		/*dConstField = setInRow ("d =", "[nm]", this);
			nMaxLabel		= setOutRow("N max =", "[1]", this);
		placeHolder(this);
		
		tTF	= setInRow ("θ =", "[Deg]", this);
			phL	= setOutRow("Φ =", "[1]", this);		
		placeHolder(this);
		
		oTF	= setInRow ("Io = ", " [ ]", this);
			iL	= setOutRow("I(θ) = ", " [ ]", this);	
		placeHolder(this);*/
		
		// Ustawienia okna
		setTitle("Wartosci");

		//Pakowanie Interfejsu :)
		pack();
		setResizable(false);
	}
	
	private ImprovedFormattedTextField inField (double value) {
		NumberFormat format = NumberFormat.getInstance();
			format.setMaximumFractionDigits(3);
		
	    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
	    	dfs.setDecimalSeparator('.');
		
		DecimalFormat deciFormat = (DecimalFormat) format;
			deciFormat.setDecimalSeparatorAlwaysShown(true);
			deciFormat.setMinimumFractionDigits(2);
			deciFormat.setMaximumFractionDigits(2);
		    deciFormat.setDecimalFormatSymbols(dfs);
	    
		ImprovedFormattedTextField field = new ImprovedFormattedTextField(deciFormat, value);
			field.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			field.setColumns(8);
	    
		return field;
	}
	
	private JLabel outLabel (double value) {
		JLabel label = new JLabel();
			label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			label.setText(Double.toString(value));
		
		return label;
	}
	
	private JLabel tLabel (String title) {
		JLabel label = new JLabel(title);
			label.setBorder(BorderFactory.createRaisedBevelBorder());
			label.setFont(label.getFont().deriveFont(label.getFont().getStyle() & ~Font.BOLD));
			label.setHorizontalAlignment(SwingConstants.RIGHT);

		return label;
	}
	private JLabel uLabel (String unit) {
		JLabel label = new JLabel(unit);
			label.setBorder(BorderFactory.createRaisedBevelBorder());
			label.setFont(label.getFont().deriveFont(label.getFont().getStyle() & ~Font.BOLD));
			label.setHorizontalAlignment(SwingConstants.LEFT);
		
		return label;
	}

	//Model matematyczny znajduje się w tej metodzie
	private double round (double val, int acc) {
		return (double)(Math.round(Math.pow(10, acc)*val))/Math.pow(10, acc);
	}
	
	public void compute() {
		
		double q = 1.6021765*Math.pow(10,-19);
		double cv = 299792458.0;
		double m = 9.109382*Math.pow(10,-31);
		double eV = 6.24150947*Math.pow(10,18);	
		
		velocity = Math.sqrt(2)*Math.sqrt(((-1)*Math.pow(q,2)*Math.pow(voltage,2)+Math.sqrt(Math.pow(cv,4)*Math.pow(m,2)*Math.pow(q,2)*Math.pow(voltage,2)+Math.pow(q,4)*Math.pow(voltage,4)))/Math.pow(cv,2)/Math.pow(m,2))/1000;
		gamma = Math.sqrt(1-Math.pow(velocity*1000/cv,2));
		energy =(m*Math.pow(cv,2)/gamma-m*Math.pow(cv,2))*eV;
		momentum = (m/gamma)*velocity*1000*Math.pow(10,23);
		beta = velocity*1000/cv;
		
		
		int acc = 2;
		velocityLabel.setText(Double.toString(round(velocity,acc)));
		energyLabel.setText(Double.toString(round(energy,acc)));
		momentumLabel.setText(Double.toString(round(momentum,acc)));		
		betaLabel.setText(Double.toString(round(beta,acc)));
		gammaLabel.setText(Double.toString(round(gamma,acc)));

		//nMaxLabel.setText(Double.toString((double)(Math.round(acc*gValues.getTheta()))/acc));*/
	}
};