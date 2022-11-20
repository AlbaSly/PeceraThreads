
package pecera;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Clase extendible de Thread para instanciar hilos y mostrarlos de forma gráfica en aspecto de animales marinos.
 * @author Raxel Arias
 * @version 1.0
 * @see Thread
 */
public class AnimalMarino extends Thread {
    //Tamaño en pixeles del sprite
    public static final int SPRITE_RESOLUTION = 120;
    
    private final Pecera contenedorPecera;
    
    private final JLabel etiquetaAnimal;
    private final String tipo;
    
    private final String spriteAdelante;
    private final String spriteAtras;
    
    private final int intervalo;
    
    
    //Movimiento
    private boolean haciaArriba = true;
    private boolean haciaDerecha = true;
    
    /**
     * Metodo constructor por defecto.
     * @param nombreThread Nombre específico para el hilo
     * @param tipo El tipo de animal marino
     * @param etiquetaAnimal Objeto JLabel al que se le manipulará e interactuará
     * @param contenedorPecera Objeto Pecera al que se renderizará la forma gráfica de los hilos
     * @param spriteAdelante Nombre del archivo que contiene el sprite 1
     * @param spriteAtras Nombre del archivo que contiene el sprite 2
     * @param intervalo Duración del método sleep
     */
    public AnimalMarino(String nombreThread, String tipo, JLabel etiquetaAnimal, Pecera contenedorPecera, String spriteAdelante, String spriteAtras, int intervalo) {
        
        this.etiquetaAnimal = etiquetaAnimal;
        this.contenedorPecera = contenedorPecera;
        
        this.setName(nombreThread);
        this.intervalo = intervalo;
        
        this.tipo = tipo;
        
        this.spriteAdelante = spriteAdelante;
        this.spriteAtras = spriteAtras;
        
    }
    
    /**
     * Método sobrecargado de Thread. Contiene la lógica del movimiento y animación del hilo AnimalMarino.
     */
    @Override
    public void run() {
        while (true) {
            
            this.actualizarAnimacion();
            this.avanzar();
            
            try { this.sleep(this.intervalo);} catch (InterruptedException e) {}
        }
    }
    
    /**
     * Metodo que detecta y manipula la posición del hilo en base a su etiquetaAnimal JLabel de manera condicional.
     */
    private void avanzar() {
        int distanciaAnimalX = this.etiquetaAnimal.getLocation().x;
        int distanciaAnimalY = this.etiquetaAnimal.getLocation().y;
        
        this.detectarChoque(distanciaAnimalX, distanciaAnimalY);
    
        int avanceX = this.haciaDerecha ? + 30 : -30;
        int avanceY = this.haciaArriba ? - 12 : + 12;
        
        if (this.tipo == "pulpo") {
            this.etiquetaAnimal.setLocation(distanciaAnimalX, distanciaAnimalY + avanceY);
        } else {
            this.etiquetaAnimal.setLocation(distanciaAnimalX + avanceX, distanciaAnimalY);
        }
    }
    
    /**
     * Metodo que detecta colisiones contra la pantalla de acuerdo a su etiquetaAnimal JLabel de manera condicional.
     * @param distanciaAnimalX Posición de la etiquetaAnimal JLabel en x
     * @param distanciaAnimalX Posición de la etiquetaAnimal JLabel en x
     */
    private void detectarChoque(int distanciaAnimalX, int distanciaAnimalY) {
        
        if (distanciaAnimalX >= Pecera.WIDTH - 120) {
            this.haciaDerecha = false;
            this.reproducirSonido();
        }
        if (distanciaAnimalX <= 0) {
            this.haciaDerecha = true;
            this.reproducirSonido();
        }
        
        if (this.tipo == "pulpo") {
            
            if (distanciaAnimalY <= 0) {
                this.haciaArriba = false;
                this.reproducirSonido();
            }
            if (distanciaAnimalY >= 744 - 120) {
                this.haciaArriba = true;
                this.reproducirSonido();
            }
        }
    }
    
    /**
     * Metodo que renderiza la nueva animación de la etiquetaAnimal JLabel de manera condicional.
     */
    private void actualizarAnimacion() {
        
        String nombreSprite = "";
        
        if (this.tipo == "pulpo" && this.haciaArriba) nombreSprite = this.spriteAdelante;
        if (this.tipo == "pulpo" && !this.haciaArriba) nombreSprite = this.spriteAtras;
        
        if (this.tipo != "pulpo" && this.haciaDerecha) nombreSprite = this.spriteAdelante;
        if (this.tipo != "pulpo" && !this.haciaDerecha) nombreSprite = this.spriteAtras;
        
        this.etiquetaAnimal.setIcon(new ImageIcon(this.getClass().getResource("/recursos/" + nombreSprite)));
    }
    
    /**
     * Metodo que define la posición inicial de la etiquetaAnimal JLabel
     * @param x Posición en x
     * @param y Posición en y
     */
    public void setLocation(int x, int y) {
        this.contenedorPecera.add(this.etiquetaAnimal);
        this.etiquetaAnimal.setBounds(x, y, SPRITE_RESOLUTION, SPRITE_RESOLUTION);
        
        this.contenedorPecera.add(this.etiquetaAnimal);
    }
    
    /**
     * Metodo que reproduce un sonido (Bubbles.wav)
     */
    private void reproducirSonido() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/recursos/Bubbles.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
         System.out.println("Error al reproducir el sonido.");
             System.out.println(ex);
        }
    }
}
