import java.time.LocalDateTime;

public class ProductorExamenes implements Runnable {
    private BufferExamenes buffer;
    private static int numeroExamen = 0;
    private Thread hilo;

    public ProductorExamenes(BufferExamenes buffer) {
        // Incrementa el contador de exámenes
        numeroExamen++;
        // Construye el hilo
        this.hilo = new Thread(this, "E" + numeroExamen);
        // Establece el valor de la propiedad buffer
        this.buffer = buffer;
        // Inicia el hilo
        this.hilo.start();
    }

    @Override
    public void run() {
        int aa = LocalDateTime.now().getYear();
        // Generación del código de examen
        String codigo = this.hilo.getName() + "-" + aa;
        // Añade el nuevo código al buffer de exámenes
        buffer.fabricarNuevoExamen(codigo);
    }
}
