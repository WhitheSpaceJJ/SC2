package consumidor;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import mensajes.Mensaje;

public class Consumidor {

    private final static String QUEUE_NAME = "cola_mensajes";

    public static void main(String[] args) throws IOException, TimeoutException {
        // Creamos una conexión al servidor de RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Creamos la cola si no existe
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // Definimos el callback para recibir los mensajes
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String mensajeJson = new String(delivery.getBody(), "UTF-8");
            Mensaje mensaje = new Gson().fromJson(mensajeJson, Mensaje.class);
            System.out.println("Recibido mensaje: " + mensaje.toString());
        };

        // Nos suscribimos a la cola para recibir los mensajes
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });

        // Esperamos a recibir mensajes
        System.out.println("Esperando mensajes...");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
