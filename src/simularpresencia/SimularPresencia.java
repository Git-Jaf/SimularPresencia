package simularpresencia;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * @author Jonatan Alvarez
 */
public class SimularPresencia implements Runnable {
    /**
     * @param args the command line arguments
     */
    boolean estado = true;
    public static JFrame ventana = new JFrame("Escape Fast");
    
    public static void main(String[] args) {
        Runnable vagancia = new SimularPresencia();
        new Thread(vagancia).start();

    }

    @Override
    public void run() {
        Robot robot = null;
        int contador = 0;
        int contadorTiempo = 0;
        int x = 0, y = 0;
        int tiempo = Integer.parseInt(JOptionPane.showInputDialog("Digite cuanto tiempo va estar fuera (en segundos de preferencia par)"));
        System.out.println("Tiempo fuera: "+tiempo+ " segundos");
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(SimularPresencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (estado) {
            for (int i = 0; i <= tiempo; i++) {
                x = i*100; 
                y = i*100;
                robot.mouseMove(x, y);
                if (contador == tiempo) {
                    JOptionPane.showMessageDialog(null, "Ha cumplido su tiempo fuera");
                    estado = false;
                }
                if (par(tiempo)) {
                    if (contadorTiempo == tiempo/2) {
                        i = 0;
                    } 
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SimularPresencia.class.getName()).log(Level.SEVERE, null, ex);
                }
                contadorTiempo++;
                contador++;
                System.out.println(contador);
            }
        }
    }

    public boolean par(int tiempo) {
        return tiempo % 2 == 0;
    }
}
