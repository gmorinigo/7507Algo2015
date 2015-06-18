package vista;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;


public class VentanaPrincipal extends JFrame{
    private JFrame frame;
    private JPanel panelTablero;
    private JButtonPos matriz[][];
    JLabel lblCantidadDeTurnos;
    
    private ImageIcon tierra;
    
    public static void main(String[] args){
            EventQueue.invokeLater(new Runnable(){
                    public void run(){
                            try{
                                    VentanaPrincipal window = new VentanaPrincipal();
                                    window.frame.setVisible(true);
                            }
                            catch(Exception e){
                                    e.printStackTrace();
                            }
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
        this.tierra = (new ImageIcon(ictierra.getImage().getScaledInstance(35,30,Image.SCALE_SMOOTH)));        
        
        frame = new JFrame();
        frame.setForeground(new Color(0,0,0));
        frame.setBounds(200, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        

        panelTablero = new JPanel();
        panelTablero.setBounds(100, 120, 800, 800);
        panelTablero.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelTablero.setLayout(new GridLayout(100,100));
        matriz = new JButtonPos[100][100];
        llenarMapaConLabels(matriz);
        agregarLabels(matriz);
        frame.getContentPane().add(panelTablero);
    }
    
    public void llenarMapaConLabels(JButtonPos unMapa[][]) throws IOException{
        for (int i=0 ; i<100 ; i++){
                for (int j=0 ; j<100 ; j++){     
                        final JButtonPos unBoton = new JButtonPos(i,j);
                        unBoton.setIcon(this.tierra);
                        unMapa[i][j] = unBoton;
                }
        }
    }
    
    public void agregarLabels(JButtonPos unMapa[][]){
        for (int i=0 ; i<100 ; i++){
                for (int j=0 ; j<100 ; j++){     
                        panelTablero.add(unMapa[i][j]);                                
                }
        }
}
}
