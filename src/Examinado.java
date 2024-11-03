import java.util.Random;

public class Examinado implements Runnable {
    private Thread hilo;
    private BufferExamenes buffer;

    public Examinado(String alumno, BufferExamenes generador) {
        // Construye el hilo
        this.hilo = new Thread(this, alumno);
        // Establece el valor de la propiedad buffer
        this.buffer = generador;
        // Inicia el hilo
        this.hilo.start();
    }

    @Override
    public void run() {
        String codigoExamen = this.buffer.consumirExamen();
        if (codigoExamen != null) {
            // Simula aquí un examen de 10 preguntas
            Random random = new Random();
            for (int i = 1; i <= 10; i++) {
                // Genera una respuesta aleatoria
                char respuesta = (char) ('A' + random.nextInt(5)); // A, B, C, D o '-'
                // En caso de que sea el 10, puede ser '-'
                if (i == 10) respuesta = '-';
                // Muestra la respuesta en consola
                System.out.println(codigoExamen + ";" + hilo.getName() + "; Pregunta " + i + ";" + respuesta);
            }
        } else {
            System.out.println("Agotado tiempo de espera y no hay más exámenes");
        }
    }
}
