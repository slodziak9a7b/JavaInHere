package Project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

public class Interfejs extends JFrame {
	private AnimationPanel animation;
	private GraphPanel graph;
	private VariableFrame variableFrame;
	private JPanel list;
	
	
	public Interfejs(String title){ 
		int edge = 413;
		
		/*
		 * TODO 
		 * zmiana data na metodę silnika obliczeniowego :) 
		 */
 		Double data[] = {10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0, 110.0, 120.0, 130.0, 140.0, 150.0};
		JList<Double> dataList = new JList<Double>(data);
		
		
		//inne okna
		variableFrame = new VariableFrame ();
		variableFrame.setVisible(true);
		
		
		//menu Bar
		JMenuBar menuBar = new JMenuBar();
		
			JMenu menu = new JMenu ("Menu");
				JMenuItem itemNew = new JMenuItem("New");
				menu.add(itemNew);
				
				JMenuItem itemOpen = new JMenuItem("Open");
				menu.add(itemOpen);
				
				JMenuItem itemSave = new JMenuItem("Save");
				menu.add(itemSave);
				
				JMenuItem itemClose = new JMenuItem("Close");
				menu.add(itemClose);
			menuBar.add(menu);
			
			JMenu window = new JMenu ("Okna");
				JMenuItem itemVariableWindow = new JMenuItem("Wartości");
				itemVariableWindow.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (variableFrame.isShowing()) {
							variableFrame.setVisible(false);
						} else {
							variableFrame.setVisible(true);
						}
					}
				});
				window.add(itemVariableWindow);
			menuBar.add(window);
		setJMenuBar(menuBar);
		
		
		// ustawienie elementów
		this.getContentPane().setLayout(new BorderLayout()); {
			
			//PAGE_START
			JPanel pageStartPanel = new JPanel (); {
			pageStartPanel.setLayout(new BorderLayout());
				
			}
			
			//CENTER
			JPanel centerPanel = new JPanel (); {
			centerPanel.setLayout(new BorderLayout());
				
				// Work Panel, animacja i wykres :)
				JPanel centerPanel_workPanel = new JPanel(); {
				centerPanel_workPanel.setLayout(new FlowLayout());
				centerPanel_workPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
					
					//Animacja
					animation = new AnimationPanel(edge, variableFrame);
					centerPanel_workPanel.add(animation);
					
					// Panel wykresu
					JPanel centerPanel_workPanel_graphPanel = new JPanel (); {
					centerPanel_workPanel_graphPanel.setLayout(new BorderLayout());
					
						JPanel centerPanel_workPanel_graphPanel_graphControlPanel = new JPanel (); {
						centerPanel_workPanel_graphPanel_graphControlPanel.setLayout(new BorderLayout());
						
							/* 
							 * TODO
							 * w GraphPanel
							 * 	check Box follow detektr
							 * 	możliwość dowolnego powiększania // pomniejszania // przybliżania // oddalania obrazu detektora obsługiwana przez mysz i specialny panel
							 * 	możliwośc zmiany koloru wykresu
							 * 
							 * 	czyli panel sterowania wykresem
							 */
						
							//Coś by panel nie był pusty :) 
							JTextField field = new JTextField("Tutaj Bedzie sterowanie wykresem");
							centerPanel_workPanel_graphPanel_graphControlPanel.add(field,BorderLayout.CENTER);
						}
						centerPanel_workPanel_graphPanel.add(centerPanel_workPanel_graphPanel_graphControlPanel,BorderLayout.PAGE_START);
						
						
						//Wykres
						graph = new GraphPanel();
						centerPanel_workPanel_graphPanel.add(graph,BorderLayout.CENTER);
					} 
					centerPanel_workPanel.add(centerPanel_workPanel_graphPanel);
				}
				centerPanel.add(centerPanel_workPanel,BorderLayout.CENTER);
				
				
				// list Panel, lista ustawień detektora i kryształu 
				JPanel centerPanel_listPanel = new JPanel (); {
				centerPanel_listPanel.setLayout(new BorderLayout());
				centerPanel_listPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
					
					JLabel centerPanel_2nd_label = new JLabel ("Zestawy najbardziej interesujaćych konfiguracji ustawień krzyształu i detektora:");
						centerPanel_2nd_label.setFont(centerPanel_2nd_label.getFont().deriveFont(centerPanel_2nd_label.getFont().getStyle() & ~Font.BOLD));
						centerPanel_2nd_label.setBorder(BorderFactory.createRaisedBevelBorder());
					centerPanel_listPanel.add(centerPanel_2nd_label,BorderLayout.PAGE_START);
				
					list = setList(dataList, 70);
					centerPanel_listPanel.add(list,BorderLayout.CENTER);
				}
				centerPanel.add(centerPanel_listPanel,BorderLayout.PAGE_END);
			}
			this.getContentPane().add(centerPanel,BorderLayout.CENTER);
			
			//PAGE_END
			JPanel pageEndPanel = new JPanel(); {
			pageEndPanel.setLayout(new BorderLayout());
				
			}
			this.getContentPane().add(pageEndPanel, BorderLayout.PAGE_END);
		}
		
		
		// Ustawienia okna
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Pakowanie Interfejsu :)
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	
	//MAXIMAS PANEL METHODS
	private JPanel setList(JList<Double> list, int height) {
		JPanel mlPanel = new JPanel();
		mlPanel.setLayout(new BorderLayout()); 
		mlPanel.setPreferredSize(new Dimension(10,height));
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(5);
			
		JScrollPane LmmScroller = new JScrollPane(list);
		
		mlPanel.add(LmmScroller,BorderLayout.CENTER);
		return mlPanel;
	}

	public static void main(String[] args) {
		String wTitle = "Doświadczenie Davissona-Germera";  
		
		Interfejs window = new Interfejs(wTitle);
		window.setVisible(true);
	}
}