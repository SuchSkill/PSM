package task_05_Solar.src;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class SolarSystem {
	static final double G = 0.000667;           //universal gravitational constant
	ArrayList<Planet> lista = new ArrayList<Planet>();
	float timestep=0;

	public SolarSystem(){
	
		Planet Sole = new Planet("Sole",19.89,0,0);
		Planet Terra = new Planet("Terra", 0.00005974, 15.2, 0, 0, -0.029291);	//posizione in afelio e vel min da wikipedia 29,291 km/s
		Planet Luna = new Planet("Luna",0.0000007342, 15.2+0.040550, 0, 0, -0.029291+0.000964);

	
		Planet Mercurio = new Planet("Mercurio",0.00000330,- 5.7,0);
//		Planet Mercurio = new Planet("Mercurio",0.1,- 5.7,0);
		Planet Venere = new Planet("Venere",0.0000487,- 10.8,0);
		Planet Marte = new Planet("Marte",0.00000642,+ 22.8,0);
		Planet Giove = new Planet("Giove",0.01899,- 77.8,0);
		Planet Saturno = new Planet("Saturno",0.00568,+ 142.4,0);
		Planet Urano = new Planet("Urano",0.000866,+ 287.2,0);
		Planet Nettuno = new Planet("Nettuno",0.00103, + 449.9,0);

		
		lista = new ArrayList<Planet>();		
		lista.add(Sole);
		lista.add(Mercurio);
		lista.add(Venere);
		lista.add(Terra);
		lista.add(Marte);
		lista.add(Giove);
		lista.add(Saturno);
		lista.add(Urano);
		lista.add(Nettuno);
		
		for(Planet p : lista){
			if(p.name!="Sole")
				p.velY = Math.sqrt(G * Sole.mass / Math.abs(p.x - Sole.x));
			System.out.println("v = "+p.velY);
			if (p.x > Sole.x) 
				p.velY = -p.velY;
		}
		
		
//		lista.add(Terra);
		Luna.velY=Terra.velY+0.000964;
		lista.add(Luna);
	}

	void updatePositions(){

		double ax[] = new double[lista.size()];
		double ay[] = new double[lista.size()];
		double dx, dy, dz;          //distance between particles along axes
		double D;                   //resultant distance between particles
		double A;                   //resultant acceleration

		int i=0;
		for(Planet p1 : lista){
			ax[i] = ay[i] = 0;
			for(Planet p2 : lista){
				if (p1 != p2)
				{
					dx = p2.x - p1.x;
					dy = p2.y - p1.y;
					D = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
					A = G * p2.mass / Math.pow(D, 2);
					ax[i] += dx * A / D;
					ay[i] += dy * A / D;
					if(p1.name=="Luna" && p2.name=="Sole") 
						System.out.println("SL ax "+dx * A / D+" ay "+dy * A / D);
					if(p1.name=="Luna" && p2.name=="Terra") 
						System.out.println("TL ax "+dx * A / D+" ay "+dy * A / D);
				}			 
			}
			i++;
		}

		i=0;
		for(Planet p1 : lista){
//			if(p1!=lista.get(0)){
				p1.velX += ax[i] * timestep;                            //accelerate
				p1.velY += ay[i] * timestep;
				//p1.x += p1.velX * timestep;                             //move
				//p1.y += p1.velY * timestep;
				p1.setPosition(p1.x+p1.velX * timestep, p1.y+p1.velY * timestep);
				//if(p1.name=="Terra")
					System.out.println(p1.name+": "+p1.velY+" y: "+p1.y);
//			}
			i++;
		}
	}
}




