package vista;


import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.Color;

public class VentanaAplicacion extends JFrame {
        
        private static final long serialVersionUID = -3210439722833694388L;
        private JPanel jContentPane = null;
        private JToolBar jJToolBarBar = null;
        private JMenuItem jMenuItem = null;
        private JMenuItem jMenuItem1 = null;
        private JMenuItem jMenuItem2 = null;
        private JPanel panel = null;
        private Nivel ejemplo = null;
        private JPanel jPanelEstado = null;
        private JLabel jLabel = null;
        private JLabel jLabelPuntos;

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
                        jJToolBarBar.add(getJMenuItem1());
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
                                        comenzar();
                                }
                        });
                }
                return jMenuItem;
        }

        private void comenzar(){
                System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
                this.ejemplo = new Nivel(getSuperficieDeDibujo());
                this.ejemplo.comenzar();
//              this.addKeyListener(controlador)
        }
        
        private void detener() {
                this.ejemplo.detener();         
        }

        /**
         * This method initializes jMenuItem1   
         *      
         * @return javax.swing.JMenuItem        
         */
        private JMenuItem getJMenuItem1() {
                if (jMenuItem1 == null) {
                        jMenuItem1 = new JMenuItem();
                        jMenuItem1.setText("Pausa");
                        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent e) {
                                        System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
                                        detener();
                                }
                        });
                }
                return jMenuItem1;
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
         */
        private JPanel getSuperficieDeDibujo() {
                if (panel == null) {
                        panel = new JPanel();
                        panel.setForeground(Color.BLACK);
                        panel.addKeyListener(new KeyAdapter() {
                        });
                        panel.setLayout(new GridBagLayout());
                        panel.setBounds(new Rectangle(115, 39, 500, 500));                      
                        panel.setBorder(new EmptyBorder(0, 0, 0, 0));                        
                        panel.setLayout(new GridLayout(80,80));
                        panel.setBackground(Color.blue);
                        
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
                        jPanelEstado.setBounds(new Rectangle(10, 38, 89, 405));
                        GridBagConstraints gridBagConstraints = new GridBagConstraints();
                        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
                        gridBagConstraints.gridx = 0;
                        gridBagConstraints.gridy = 0;
                        jLabel = new JLabel();
                        jLabel.setText("Puntos:");
                        jPanelEstado.add(jLabel, gridBagConstraints);
                        GridBagConstraints gbc_jLabelPuntos = new GridBagConstraints();
                        gbc_jLabelPuntos.insets = new Insets(0, 0, 5, 0);
                        gbc_jLabelPuntos.gridx = 0;
                        gbc_jLabelPuntos.gridy = 1;
                        jLabelPuntos = new JLabel();
                        jLabelPuntos.setText("0");
                        jPanelEstado.add(jLabelPuntos, gbc_jLabelPuntos);
                        
                        JLabel jLabel1 = new JLabel("Vidas:");
                        GridBagConstraints gbc_jLabel1 = new GridBagConstraints();
                        gbc_jLabel1.insets = new Insets(0, 0, 5, 0);
                        gbc_jLabel1.gridx = 0;
                        gbc_jLabel1.gridy = 2;
                        jPanelEstado.add(jLabel1, gbc_jLabel1);
                        
                        JLabel jLabelVidas = new JLabel("5");
                        GridBagConstraints gbc_jLabelVidas = new GridBagConstraints();
                        gbc_jLabelVidas.gridx = 0;
                        gbc_jLabelVidas.gridy = 3;
                        jPanelEstado.add(jLabelVidas, gbc_jLabelVidas);
                }
                return jPanelEstado;
        }

        /**
         * @param args
         */
        public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                VentanaAplicacion thisClass = new VentanaAplicacion();
                                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                thisClass.setVisible(true);
                        }
                });
        }

        /**
         * This is the default constructor
         */
        public VentanaAplicacion() {
                super();
                initialize();
        }

        /**
         * This method initializes this
         * 
         * @return void
         */
        private void initialize() {
                this.setSize(645, 588);
                this.setContentPane(getJContentPane());
                this.setTitle("AlgoTank");
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
         */
        private JPanel getJContentPane() {
                if (jContentPane == null) {
                        jContentPane = new JPanel();
                        jContentPane.setLayout(null);
                        jContentPane.add(getJJToolBarBar(), null);
                        jContentPane.add(getSuperficieDeDibujo(), null);
                        jContentPane.add(getJPanelEstado(), null);
                }
                return jContentPane;
        }

}  //

