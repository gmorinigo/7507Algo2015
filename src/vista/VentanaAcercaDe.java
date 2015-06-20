package vista;


	import javax.swing.JFrame;
	import java.awt.TextArea;
	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Font;

	public class VentanaAcercaDe extends JFrame {
	        
	        private static final long serialVersionUID = -8050569541646747071L;

	        public VentanaAcercaDe(String string) {
	                
	                TextArea textArea = new TextArea();
	                textArea.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
	                textArea.setBackground(Color.LIGHT_GRAY);
	                textArea.setEditable(false);
	                textArea.setText("Bienvenido al AlgoTank! \r\nEl juego consiste en defender el cuartel argentino del ataque de los tanques enemigos. \r\nPara ello, tienes que dispararles a tus enemigos tratando de destruirlos. \r\nA lo largo del juego podras capturar distintos bonus que te ayudaran en la tarea. \r\n\r\nPara cumplir el objetivo tienes que superar una serie de misiones.\r\nBuena suerte! \r\n\r\nCOMO JUGAR: \r\n\r\nTecla UP: Mover arriba,\r\nTecla DOWN: Mover abajo,\r\nTecla RIGHT: Mover derecha,\r\nTecla LEFT: Mover izquierda,\r\nBARRA ESPACIADORA: disparar.");
	                getContentPane().add(textArea, BorderLayout.CENTER);
	        }

	}