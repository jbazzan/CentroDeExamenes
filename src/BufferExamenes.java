import java.util.LinkedList;
import java.util.Queue;

public class BufferExamenes {
    private Queue<String> colaExamenes;

    public BufferExamenes() {
        colaExamenes = new LinkedList<String>();
    }

    public synchronized void fabricarNuevoExamen(String codigo) {
        // Añade el código pasado como argumento a la cola
        colaExamenes.add(codigo);
        // Notifica a los hilos que están esperando un examen
        notifyAll();
        System.out.println("Producido examen " + codigo);
    }

    public synchronized String consumirExamen() {
        // Espera hasta que haya algún examen para consumir
        while (colaExamenes.isEmpty()) {
            try {
                wait(); // Espera hasta que se fabrique un nuevo examen
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Si la cola no está vacía, saca un examen y entrégalo como retorno
        return colaExamenes.poll();
    }
}
