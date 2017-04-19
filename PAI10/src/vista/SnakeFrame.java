package vista;

import modelo.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class SnakeFrame extends JFrame {
	private Cuadricula tablero;
	private Linea snake;
	private JTextField gridSize;
	private PanelTablero panel;
	
	public SnakeFrame(){
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tablero = new Cuadricula(panel);
		snake = new Linea(tablero, panel);
		panel = new PanelTablero(tablero, snake);
		tablero.setPanel(panel);
		snake.setPanel(panel);
		this.add(panel, BorderLayout.CENTER);
		
		JButton start = new JButton("Start");
		JButton end = new JButton("End");
		JButton color = new JButton("Color");
		gridSize = new JTextField();
		JPanel botones = new JPanel();
		
		botones.setLayout(new GridLayout(4,1));
		botones.add(start);
		botones.add(end);
		botones.add(color);
		botones.add(gridSize);
		
		/** Añadimos los listener */
		start.addActionListener(new StartListener());
		end.addActionListener(new EndListener());
		color.addActionListener(new ColorListener());
		
		add(botones, BorderLayout.EAST);
	}
	
	public PanelTablero getPanel() {
		return panel;
	}

	public void setPanel(PanelTablero panel) {
		this.panel = panel;
	}

	public Cuadricula getTablero() {
		return tablero;
	}

	public void setTablero(Cuadricula tablero) {
		this.tablero = tablero;
	}

	public Linea getSnake() {
		return snake;
	}

	public void setSnake(Linea snake) {
		this.snake = snake;
	}

	public JTextField getGridSize() {
		return gridSize;
	}

	public void setGridSize(JTextField gridSize) {
		this.gridSize = gridSize;
	}

	/** Clases Listener privadas */
	private class StartListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String text = getGridSize().getText();
			if(text.matches("\\d+")){
				getTablero().setFactorDivision(Integer.parseInt(text));
				getPanel().repaint();
			}
			getSnake().endMovement();
			setSnake(new Linea(tablero, panel));
			getPanel().setSnake(getSnake());
			getSnake().startMovement();
		}
		
	}
	
	private class EndListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			getSnake().endMovement();
		}
		
	}
	
	private class ColorListener implements ActionListener{
		int actualcolor =0;
		public void actionPerformed(ActionEvent e) {
			Random randomGenerator = new Random();
			int temp;
			do{
				temp = randomGenerator.nextInt(10);
			}while (temp == actualcolor);
			actualcolor = temp;
	    	switch(temp){
	    	case 0: getSnake().setColor(Color.RED); break;
	    	case 1: getSnake().setColor(Color.BLUE); break;
	    	case 2: getSnake().setColor(Color.BLACK); break;
	    	case 3: getSnake().setColor(Color.WHITE); break;
	    	case 4: getSnake().setColor(Color.PINK); break;
	    	case 5: getSnake().setColor(Color.YELLOW); break;
	    	case 6: getSnake().setColor(Color.CYAN); break;
	    	case 7: getSnake().setColor(Color.MAGENTA); break;
	    	case 8: getSnake().setColor(Color.GREEN); break;
	    	case 9: getSnake().setColor(Color.GRAY); break;
	    	}
			getPanel().repaint();
		}
	}
	
}