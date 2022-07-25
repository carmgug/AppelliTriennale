package poo.figure;
import poo.geometria.*;

public abstract class Figura implements FiguraPiana{
	private double dimensione;
	public Figura( double dimensione ) { this.dimensione=dimensione; }
	protected double getDimensione() { return dimensione; }
	//i metodi perimetro() ed area() provengono ora da FiguraPiana
	//e sono automaticamente abstract anche non citandoli
}//Figura
