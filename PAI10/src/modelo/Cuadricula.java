package modelo;

import java.awt.*;
import javax.swing.*;
import vista.*;

public class Cuadricula {

	private int factorDivision;
	private PanelTablero panel;
	
	public Cuadricula(){
		setFactorDivision(3);
		setPanel(new PanelTablero());
	}
	public Cuadricula(PanelTablero p){
		setFactorDivision(3);
		setPanel(p);
	}
	public Cuadricula(int factor, PanelTablero p){
		setFactorDivision(factor);
		setPanel(p);
	}

	public PanelTablero getPanel() {
		return panel;
	}
	public void setPanel(PanelTablero panel) {
		this.panel = panel;
	}
	
	public int getFactorDivision() {
		return factorDivision;
	}

	public void setFactorDivision(int factorDivision) {
		this.factorDivision = factorDivision;
	}
	
	public double[] getCord(int x, int y){
		
		double xtemp = (getFactorDivision() - 1);
		double xpos = (1 / xtemp ) * x;
		
		double ytemp1 = (getFactorDivision() - 1);
		double ytemp =(1 / ytemp1);
		double ypos = 1 - ytemp * y;
		
		double[] out = {xpos, ypos};
		return out;
	}
	public void paint(Graphics graphic, int size){
		graphic.setColor(Color.LIGHT_GRAY);
		for(int i =0; i< getFactorDivision(); i++){
			double[] cords1 = getCord(i, 0);
			double[] cords2 = getCord(i, getFactorDivision()-1);
			int x1 = (int)(cords1[0] * (double)size);
			int y1 = (int)(cords1[1] * (double)size);
			int x2 = (int)(cords2[0] * (double)size);
			int y2 = (int)(cords2[1] * (double)size);
			graphic.drawLine(x1, y1, x2, y2);
		}
		for(int i =0; i< getFactorDivision(); i++){
			double[] cords1 = getCord(0, i);
			double[] cords2 = getCord(getFactorDivision()-1, i);
			int x1 = (int)(cords1[0] * (double)size);
			int y1 = (int)(cords1[1] * (double)size);
			int x2 = (int)(cords2[0] * (double)size);
			int y2 = (int)(cords2[1] * (double)size);
			graphic.drawLine(x1, y1, x2, y2);
		}
	}
}
