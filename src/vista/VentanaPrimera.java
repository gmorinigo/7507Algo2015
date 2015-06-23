package vista;


import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.EmptyBorder;

import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.IOException;

public class VentanaPrimera extends JFrame {
    private static final long serialVersionUID = -3210439722833694388L;
    private JPanel jContentPane = null;
    private JToolBar jJToolBarBar = null;
    private JMenuItem jMenuItem = null;
    private JMenuItem jMenuItem2 = null;
    private JPanel panel = null;
    private JPanel jPanelEstado = null;
    private JLabel jLabel = null;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private ImageIcon fondo;
    private JButton boton;
    /**
     * This method initializes jJToolBarBar 
     *      
     * @return javax.swing.JToolBar 
     */
    private JToolBar getJJToolBarBar() {
            if (jJToolBarBar == null) {
                    jJToolBarBar = new JToolBar();
                    jJToolBarBar.setBounds(new Rectangle(0, 0, 372, 33));
                    jJToolBarBar.add(getJMenuItem());
                    jJToolBarBar.add(getJMenuItem2());
                    

            }
            return jJToolBarBar;
    }

    /**
     * This method initializes jMenuItem    
     *      
     * @return javax.swing.JMenuItem        
     */
    private JMenuItem getJMenuItem() {
            if (jMenuItem == null) {
                    jMenuItem = new JMenuItem();
                    jMenuItem.setText("Comenzar");
                    jMenuItem.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent e) {
                                    System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
                            }
                    });
                    jMenuItem.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent e) {
                                  
                            }
                    });
            }
            return jMenuItem;
    }

    
    private void detener() {
  //          this.ejemplo.detener();         
    }
    
    private JMenuItem getJMenuItem2() {
            if (jMenuItem2 == null) {
                    jMenuItem2 = new JMenuItem();
                    jMenuItem2.setText("Acerca de");
                    jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent e) {
                                    VentanaAcercaDe newFrame = new VentanaAcercaDe("AlgoCraft");
                                    newFrame.pack();
                                    newFrame.setVisible(true);
                            }
                    });
            }
            return jMenuItem2;
            
    }

    /**
     * This method initializes jPanel       
     *      
     * @return javax.swing.JPanel   
     * @throws IOException 
     */
    private JPanel getSuperficieDeDibujo() throws IOException {
            if (panel == null) {
                    panel = new JPanel();
                    panel.setForeground(Color.BLACK);
                    panel.addKeyListener(new KeyAdapter() {
                    });
                    panel.setLayout(new GridBagLayout());
                    panel.setBounds(new Rectangle(140, 44, 500, 500));                      
                    panel.setBorder(new EmptyBorder(0, 0, 0, 0));                        
                    panel.setLayout(new GridLayout(80,80));
            }
            
            
            return panel;
    }

    /**
     * This method initializes jPanelEstado 
     *      
     * @return javax.swing.JPanel   
     */
    private JPanel getJPanelEstado() {
            if (jPanelEstado == null) {
                    jPanelEstado = new JPanel();
                    GridBagLayout gbl_jPanelEstado = new GridBagLayout();
                    gbl_jPanelEstado.columnWeights = new double[]{1.0};
                    jPanelEstado.setLayout(gbl_jPanelEstado);
                    jPanelEstado.setBounds(new Rectangle(10, 11, 120, 405));
                    GridBagConstraints gridBagConstraints = new GridBagConstraints();
                    gridBagConstraints.insets = new Insets(0, 0, 5, 0);
                    gridBagConstraints.gridx = 0;
                    gridBagConstraints.gridy = 0;
                    jLabel = new JLabel();
                    jLabel.setText("Nombre Jugador 1:");
                    jPanelEstado.add(jLabel, gridBagConstraints);
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(0, 0, 5, 0);
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    jPanelEstado.add(getTextField(), gbc);
                    
                    JLabel jLabel1 = new JLabel("Color Jugador 1:");
                    GridBagConstraints gbc_jLabel1 = new GridBagConstraints();
                    gbc_jLabel1.insets = new Insets(0, 0, 5, 0);
                    gbc_jLabel1.gridx = 0;
                    gbc_jLabel1.gridy = 2;
                    jPanelEstado.add(jLabel1, gbc_jLabel1);
                    GridBagConstraints gbc_textField_1 = new GridBagConstraints();
                    gbc_textField_1.insets = new Insets(0, 0, 5, 0);
                    gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
                    gbc_textField_1.gridx = 0;
                    gbc_textField_1.gridy = 3;
                    jPanelEstado.add(getTextField_1(), gbc_textField_1);
                    
                    JLabel lblRaza = new JLabel("Raza:");
                    GridBagConstraints gbc_lblRaza = new GridBagConstraints();
                    gbc_lblRaza.insets = new Insets(0, 0, 5, 0);
                    gbc_lblRaza.gridx = 0;
                    gbc_lblRaza.gridy = 4;
                    jPanelEstado.add(lblRaza, gbc_lblRaza);
                    
                    JRadioButton rdbtnNewRadioButton = new JRadioButton("Terran");
                    GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
                    gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 0);
                    gbc_rdbtnNewRadioButton.gridx = 0;
                    gbc_rdbtnNewRadioButton.gridy = 5;
                    jPanelEstado.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
                    
                    JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Protoss");
                    GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
                    gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 0);
                    gbc_rdbtnNewRadioButton_1.gridx = 0;
                    gbc_rdbtnNewRadioButton_1.gridy = 6;
                    jPanelEstado.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
                    
                    JLabel lblJugador = new JLabel("Nombre Jugador 2");
                    GridBagConstraints gbc_lblJugador = new GridBagConstraints();
                    gbc_lblJugador.insets = new Insets(0, 0, 5, 0);
                    gbc_lblJugador.gridx = 0;
                    gbc_lblJugador.gridy = 7;
                    jPanelEstado.add(lblJugador, gbc_lblJugador);
                    
                    textField_2 = new JTextField();
                    GridBagConstraints gbc_textField_2 = new GridBagConstraints();
                    gbc_textField_2.insets = new Insets(0, 0, 5, 0);
                    gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
                    gbc_textField_2.gridx = 0;
                    gbc_textField_2.gridy = 8;
                    jPanelEstado.add(textField_2, gbc_textField_2);
                    textField_2.setColumns(10);
                    
                    JLabel lblColorJugador = new JLabel("Color Jugador 2");
                    GridBagConstraints gbc_lblColorJugador = new GridBagConstraints();
                    gbc_lblColorJugador.insets = new Insets(0, 0, 5, 0);
                    gbc_lblColorJugador.gridx = 0;
                    gbc_lblColorJugador.gridy = 9;
                    jPanelEstado.add(lblColorJugador, gbc_lblColorJugador);
                    
                    textField_3 = new JTextField();
                    GridBagConstraints gbc_textField_3 = new GridBagConstraints();
                    gbc_textField_3.insets = new Insets(0, 0, 5, 0);
                    gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
                    gbc_textField_3.gridx = 0;
                    gbc_textField_3.gridy = 10;
                    jPanelEstado.add(textField_3, gbc_textField_3);
                    textField_3.setColumns(10);
                    
                    JLabel lblRaza_1 = new JLabel("Raza:");
                    GridBagConstraints gbc_lblRaza_1 = new GridBagConstraints();
                    gbc_lblRaza_1.insets = new Insets(0, 0, 5, 0);
                    gbc_lblRaza_1.gridx = 0;
                    gbc_lblRaza_1.gridy = 11;
                    jPanelEstado.add(lblRaza_1, gbc_lblRaza_1);
                    
                    JRadioButton rdbtnTerran = new JRadioButton("Terran");
                    GridBagConstraints gbc_rdbtnTerran = new GridBagConstraints();
                    gbc_rdbtnTerran.insets = new Insets(0, 0, 5, 0);
                    gbc_rdbtnTerran.gridx = 0;
                    gbc_rdbtnTerran.gridy = 12;
                    jPanelEstado.add(rdbtnTerran, gbc_rdbtnTerran);
                    
                    JRadioButton rdbtnProtoss = new JRadioButton("Protoss");
                    GridBagConstraints gbc_rdbtnProtoss = new GridBagConstraints();
                    gbc_rdbtnProtoss.gridx = 0;
                    gbc_rdbtnProtoss.gridy = 13;
                    jPanelEstado.add(rdbtnProtoss, gbc_rdbtnProtoss);
            }
            return jPanelEstado;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                            VentanaPrimera thisClass = new VentanaPrimera();
                            (thisClass).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            thisClass.setVisible(true);
                    }
            });
    }

    /**
     * This is the default constructor
     */
    public VentanaPrimera() {
            super();
            try {
				initialize();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

    /**
     * This method initializes this
     * 
     * @return void
     * @throws IOException 
     */
    private void initialize() throws IOException {
            this.setSize(645, 588);
            this.setContentPane(getJContentPane());
            this.setTitle("AlgoCraft");
            this.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                            System.out.println("windowClosing()"); // TODO Auto-generated Event stub windowClosing()
                            System.exit(NORMAL);
                    }
            });
    }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     * @throws IOException 
     */
    private JPanel getJContentPane() throws IOException {
            if (jContentPane == null) {
                    jContentPane = new JPanel();
                    jContentPane.setLayout(null);
                    jContentPane.add(getJJToolBarBar(), null);
                    jContentPane.add(getSuperficieDeDibujo(), null);
                    jContentPane.add(getJPanelEstado(), null);
                    
                    JRadioButtonMenuItem rdbtnmntmTerran = new JRadioButtonMenuItem("Terran");
                    rdbtnmntmTerran.setBounds(5, 197, 125, 22);
                    jContentPane.add(rdbtnmntmTerran);
                    
                    JButton button = new JButton("New button");
                    button.setBounds(140, 44, 372, 500);
                    BufferedImage imFondo= ImageIO.read(this.getClass().getResource("fondo.png"));
                    ImageIcon icFondo = new ImageIcon(imFondo);
                    this.fondo = (new ImageIcon(icFondo.getImage().getScaledInstance(500,500,Image.SCALE_SMOOTH)));  
                    button.setIcon(this.fondo);
                    jContentPane.add(button);
            }
            return jContentPane;
    }
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
		}
		return textField_1;
	}
}  

