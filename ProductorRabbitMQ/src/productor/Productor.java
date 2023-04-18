package productor;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import mensajes.Mensaje;


public class Productor {

    private final static String QUEUE_NAME = "cola_mensajes";

    public static void main(String[] args) throws IOException, TimeoutException {
        // Creamos una conexión al servidor de RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Creamos la cola si no existe
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // Creamos mensajes y los enviamos a la cola
        for (int i = 1; i <= 10; i++) {
            Mensaje mensaje = new Mensaje(i, "Contenido del mensaje " + i);
            String mensajeJson = new Gson().toJson(mensaje);
            channel.basicPublish("", QUEUE_NAME, null, mensajeJson.getBytes("UTF-8"));
            System.out.println("Enviado mensaje: " + mensajeJson);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Cerramos la conexión
        channel.close();
        connection.close();
    }

}
