package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Random;

import vista.*;

public class Linea {

	private Cuadricula limites;
	private ArrayList<Integer[]> coordenadas;
	private Color color;
	private Timer timer;
	private PanelTablero panel;
	
	public Linea(){
		setLimites(new Cuadricula());
		setCoordenadas(new ArrayList<Integer[]>());
		addCoordenadas(0,0);
		setColor(Color.RED);
		setPanel(new PanelTablero());
		timer = new Timer (1000, new TimerListener()); 
	}
	public Linea(Cuadricula cuad, PanelTablero p){
		setLimites(cuad);
		setCoordenadas(new ArrayList<Integer[]>());
		addCoordenadas(0,0);
		setColor(Color.RED);
		setPanel(p);
		timer = new Timer (3000, new TimerListener()); 
	}
	
	public PanelTablero getPanel() {
		return panel;
	}
	public void setPanel(PanelTablero panel) {
		this.panel = panel;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public Cuadricula getLimites() {
		return limites;
	}
	public void setLimites(Cuadricula limites) {
		this.limites = limites;
	}
	public ArrayList<Integer[]> getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(ArrayList<Integer[]> coordenadas) {
		this.coordenadas = coordenadas;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void addCoordenadas(int x, int y){
		if( x < 0 || x >= getLimites().getFactorDivision() || y < 0 || y >= getLimites().getFactorDivision()){
			throw new IllegalArgumentException("Coordenadas inválidas");
		}
		Integer[] temp = {x, y};
		getCoordenadas().add(temp);
	}
	public Integer[] getCoordenada(int index){
		return getCoordenadas().get(index);
	}
	
	public void moveUp(){
		Integer[] lastCords = getCoordenada(getCoordenadas().size()-1);
		Integer newcords = lastCords[1] + 1;
		try{
			addCoordenadas(lastCords[0], newcords);
		}
		catch(IllegalArgumentException e){
			endMovement();
		}
	}
	public void moveDown(){
		Integer[] lastCords = getCoordenada(getCoordenadas().size()-1);
		int newcords = lastCords[1] - 1;
		try{
			addCoordenadas(lastCords[0], newcords);
		}
		catch(IllegalArgumentException e){
			endMovement();
		}
	}
	public void moveRight(){
		Integer[] lastCords = getCoordenada(getCoordenadas().size()-1);
		int newcords = lastCords[0] + 1;
		try{
			addCoordenadas(newcords, lastCords[1]);
		}
		catch(IllegalArgumentException e){
			endMovement();
		}
	}
	public void moveLeft(){
		Integer[] lastCords = getCoordenada(getCoordenadas().size()-1);
		int newcords = lastCords[0] - 1;
		try{
			addCoordenadas(newcords, lastCords[1]);
		}
		catch(IllegalArgumentException e){
			endMovement();
		}
	}
	
	public void startMovement(){
		getTimer().start();
	}
	public void endMovement(){
		getTimer().stop();
	}
	
	public void paint(Graphics graphic, int size){
		int[] xcords = new int[getCoordenadas().size()];
		int[] ycords = new int[getCoordenadas().size()];
		graphic.setColor(getColor());
		for(int i =0; i<getCoordenadas().size(); i++){
			double[] actualCords = getLimites().getCord( getCoordenada(i)[0], getCoordenada(i)[1]);
			xcords[i] = (int)(actualCords[0] * (double)size);
			ycords[i] = (int)(actualCords[1] * (double)size);
		}
		graphic.drawPolyline(xcords, ycords, getCoordenadas().size());
	}
	
	private class TimerListener implements ActionListener{
			int noback = 1;
		    public void actionPerformed(ActionEvent e) { 
		    	Random randomGenerator = new Random();
		    	int temp;
		    	do{
		    		temp = randomGenerator.nextInt(4);
		    	}while(temp == noback);
		    	switch(temp){
		    	case 0: moveUp();
		    		noback = 1;
		    		break;
		    	case 1: moveDown();
		    		noback = 0;
		    		break;
		    	case 2: moveLeft();
		    		noback = 3;
		    		break;
		    	case 3: moveRight();
		    		noback = 2;
		    		break;
		    	}
		    	getPanel().repaint();
		     } 
	}
}
