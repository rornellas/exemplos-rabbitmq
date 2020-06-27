package ninja.hesketh.consumidor;

import ninja.hesketh.configuracao.Configuracao;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ConsumidorFanout {

    public static void main(String[] args) {
        //Set up queue, exchanges and bindings
        RabbitTemplate template = new RabbitTemplate(Configuracao.getConnection());

        Message elevador = template.receive("elevador");
        Message cafeteira = template.receive("cafeteira");

        if(elevador != null)
            System.out.println(new String(elevador.getBody()));
        if(cafeteira != null)
            System.out.println(new String(cafeteira.getBody()));

        System.exit(0);
    }

}
