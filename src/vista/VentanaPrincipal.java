package vista;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;


public class VentanaPrincipal extends JFrame{
    private JFrame frame;
    private JPanel panelTablero;
    private JButtonPos matriz[][];
    JLabel lblCantidadDeTurnos;
    private int DIMENSION = 20;
    
    private ImageIcon tierra;
   
    /**
     * @param args
     */
    public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                    	VentanaPrincipal window = new VentanaPrincipal();
                            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            window.setVisible(true);
                    }
            });
    }  
   
    
    public VentanaPrincipal(){
            try{
            	initialize();  
                    
            }catch (IOException e){
                    e.printStackTrace();
                  
            }
    }

    private void initialize() throws IOException{
        BufferedImage imTierra= ImageIO.read(this.getClass().getResource("tierra.png"));
        ImageIcon ictierra = new ImageIcon(imTierra);
        this.tierra = (new ImageIcon(ictierra.getImage().getScaledInstance(75,70,Image.SCALE_SMOOTH)));        
        
        frame = new JFrame();
        frame.setForeground(new Color(0,0,0));
        frame.setBounds(200, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        

        panelTablero = new JPanel();
        panelTablero.setBounds(100, 120, 800, 800);
        panelTablero.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelTablero.setLayout(new GridLayout(this.DIMENSION,this.DIMENSION));
        panelTablero.setBackground(Color.blue);
        panelTablero.addMouseListener(new MouseListener() {
  
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				JPopupMenu popupMenu = new JPopupMenu("Menu contextual");
				JMenuItem mitem = popupMenu.add(String.format("Crear Deposito en %s %s",arg0.getX(),arg0.getY()));
				popupMenu.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						JOptionPane.showMessageDialog(null,"presionaste el elemento del menu");
						
					}
				});
				popupMenu.add("Crear Fabrica");
				popupMenu.setEnabled(true);
				popupMenu.show(arg0.getComponent(), arg0.getX(), arg0.getY());


				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        //matriz = new JButtonPos[this.DIMENSION][this.DIMENSION];
        //llenarMapaConLabels(matriz);
        //agregarLabels(matriz);
        frame.getContentPane().add(panelTablero);
        
    }
   
    public void llenarMapaConLabels(JButtonPos unMapa[][]) throws IOException{
        for (int i=0 ; i<this.DIMENSION ; i++){
                for (int j=0 ; j<this.DIMENSION ; j++){     
                        final JButtonPos unBoton = new JButtonPos(i,j);
                        unBoton.setIcon(this.tierra);
                        unMapa[i][j] = unBoton;
                }
        }
    }
    
    public void agregarLabels(JButtonPos unMapa[][]){
        for (int i=0 ; i< this.DIMENSION ; i++){
                for (int j=0 ; j<this.DIMENSION ; j++){     
                        panelTablero.add(unMapa[i][j]);                                
                }
        }
}
    

}
