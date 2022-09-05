package simularpresencia;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * @author Jonatan Alvarez
 */
public class SimularPresencia implements Runnable {
    double tiempo;
    double tiempoAux;
    
    @Override
    public void run() {
        Runnable vagancia = new SimularPresencia();
        boolean estado = ControlMouse.estado;
        Robot robot = null;
        int contador = 0;
        int x = 0, y = 0, z = 0;
        tiempo = Double.parseDouble(ControlMouse.cbTiempo.getSelectedItem().toString());
        tiempoAux = tiempo * 12;
        System.out.println("Tiempo fuera: "+tiempoAux * 5+ " segundos");
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(SimularPresencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (estado) {
            for (int i = 0; i <= tiempoAux; i++) {
                x = 500;
                y = 20;
                if (par(i) || i == 0) {
                    robot.mouseMove(x, y);
                } else {
                    robot.mouseMove(y, x);
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SimularPresencia.class.getName()).log(Level.SEVERE, null, ex);
                }
                contador++;
                System.out.println(contador);
                System.out.println("Valor de i: "+i);
                System.out.println("Valor de x: "+x);
                System.out.println("Valor de y: "+y);
                if (contador == tiempoAux) {
                    JOptionPane.showMessageDialog(null, "Ha cumplido su tiempo fuera");
                    new Thread(vagancia).stop();
                    estado = false;
                    ControlMouse.btnIniciar.setEnabled(true);
                }
            }
        }
    }

    public boolean par(int tiempo) {
        return tiempo % 2 == 0;
    }
}
