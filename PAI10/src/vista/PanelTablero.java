package vista;

import modelo.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelTablero extends JPanel{
	private Cuadricula tablero;
	private Linea snake;
	
	public PanelTablero(){
		setTablero(new Cuadricula());
		setSnake(new Linea());
	}
	public PanelTablero(Cuadricula c, Linea l){
		setTablero(c);
		setSnake(l);
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
	
	public void paintComponent(Graphics g){
		int size;
		if(this.getHeight() < this.getWidth())
			size = this.getHeight();
		else
			size = this.getWidth();
		Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(5));
		getTablero().paint(g, size);
		getSnake().paint(g, size);
	}
}
