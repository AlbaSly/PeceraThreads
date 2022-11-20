
package pecera;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase extendible de JPanel para manipulación gráfica. Contiene todo lo relacionado a la interfaz e instancia de hilos para los animales marinos.
 * @author Raxel Arias
 * @version 1.0
 * @see JPanel
 */
public class Pecera extends JPanel {
    
    private Image fondo;
    
    //Tamaño del ancho de la ventana
    public static int WIDTH = 1376;
    
    //Tamaño del largo de la ventana
    public static int HEIGTH = 744;
    
    /**
     * Metodo constructor por defecto
     */
    public Pecera() {
        this.configurarResolucion();
        this.instanciarAnimales();
    }
    
    /**
     * Metodo para dibujado. Sobrecarga de método desde JPanel
     * @param g Objeto Graphics
     */
    @Override
    public void paint(Graphics g) {
        this.configurarFondo(g);
    }
    
    /**
     * Método para manipular el tamaño del JPanel Pecera
     */
    private void configurarResolucion() {
        this.setLayout(null);
        this.setSize(WIDTH, HEIGTH);
    }
    /**
     * Método que permite configurar una imagen de fondo para el JPanel Pecera
     * @param g Objeto Graphics
     */
    public void configurarFondo(Graphics g) {
        fondo = new ImageIcon(getClass().getResource("/recursos/fondo.gif")).getImage();
        
        g.drawImage(fondo,0, 0, 1366, 768, this);
        this.setOpaque(false);
        super.paint(g);
    }
    
    /**
     * Método que crea e instancia los hilos herados a AnimalMarino
     */
    private void instanciarAnimales() {
        AnimalMarino pez = new AnimalMarino("pez1", "pez", new JLabel(), this, "clownfish.png", "clownfish_reverse.png", 300);
        pez.setLocation(10, 300);
        
        AnimalMarino ballena = new AnimalMarino("ballena", "pez", new JLabel(), this, "whale.png", "whale_reverse.png", 800);
        ballena.setLocation(WIDTH-240, 200);
        
        AnimalMarino pez2 = new AnimalMarino("pez2", "pez", new JLabel(), this, "clownfish.png", "clownfish_reverse.png", 100);
        pez2.setLocation(200, 450);
        
        AnimalMarino cangrejo = new AnimalMarino("cangrejo", "cangrejo", new JLabel(), this, "crab.png", "crab_reverse.png", 400);
        cangrejo.setLocation(WIDTH/3, HEIGTH-120);
        
        AnimalMarino tortuga = new AnimalMarino("tortuga", "tortuga", new JLabel(), this, "tortoise.png", "tortoise_reverse.png", 750);
        tortuga.setLocation(350, 350);
        
        AnimalMarino tortuga2 = new AnimalMarino("tortuga2", "tortuga", new JLabel(), this, "tortoise.png", "tortoise_reverse.png", 750);
        tortuga2.setLocation(500, 80);
        
        AnimalMarino pulpo = new AnimalMarino("pulpo1", "pulpo", new JLabel(), this, "octopus.png", "octopus_reverse.png", 100);
        pulpo.setLocation(WIDTH/2, 450);
        
        AnimalMarino pulpo2 = new AnimalMarino("pulpo2", "pulpo", new JLabel(), this, "octopus.png", "octopus_reverse.png", 200);
        pulpo2.setLocation((WIDTH/3), 0);
        
        AnimalMarino pulpo3 = new AnimalMarino("pulpo3", "pulpo", new JLabel(), this, "octopus.png", "octopus_reverse.png", 50);
        pulpo3.setLocation(20, 300);
        
        pez.start();
        pez2.start();
        ballena.start();
        cangrejo.start();
        
        tortuga.start();
        tortuga2.start();
        
        pulpo.start();
        pulpo2.start();
        pulpo3.start();
    }
}

/**
 * Clase que contiene el método main para ejecutar la aplicación. Contiene la creación del JFrame y el añadido de una instancia de la Clase Pecera para que actúe de contenedor.
 * @author Raxel Arias
 * @version 1.0
 */
class Index {
    
    /**
     * Método principal para ejecución de la aplicación. Crea un objeto JFrame y Pecera para añadirla dentro del JFrame.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        configurarFrame(frame);
        
        Pecera pecera = new Pecera();
        frame.getContentPane().add(pecera);
        
        configurarVentanaFrame(frame);
    }
    
    /**
    * Método que hace ajustes a un objeto JFrame para modificar el título y la presentación de ventana.
    * @param frame objeto JFrame por referencia
    */
    private static void configurarFrame(JFrame frame) {
        frame.setTitle("Pecera | Raxel Arias");
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
    
    /**
    * Método que hace ajustes a un objeto JFrame para bloquear/desbloquear ajuste de pantalla y habilitar visibilidad
    * @param frame objeto JFrame por referencia
    */
    private static void configurarVentanaFrame(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
