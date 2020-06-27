package ninja.hesketh.consumidor;

import ninja.hesketh.configuracao.Configuracao;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ConsumidorDirect {

    public static void main(String[] args) {
        //Set up queue, exchanges and bindings
        RabbitTemplate template = new RabbitTemplate(Configuracao.getConnection());

        Message santos = template.receive("santos");
        Message spfc = template.receive("spfc");
        Message corinthians = template.receive("corinthians");
        Message palmeiras = template.receive("palmeiras");

        if(santos != null)
            System.out.println(new String(santos.getBody()));

        if(spfc != null)
            System.out.println(new String(spfc.getBody()));

        if(corinthians != null)
            System.out.println(new String(corinthians.getBody()));

        if(palmeiras != null)
            System.out.println(new String(palmeiras.getBody()));
    }

}
