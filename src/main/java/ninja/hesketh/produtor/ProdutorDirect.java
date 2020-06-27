package ninja.hesketh.produtor;

import ninja.hesketh.configuracao.Configuracao;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ProdutorDirect {

    public static void main(String[] args) {

        //Set up queue, exchanges and bindings
        RabbitAdmin admin = new RabbitAdmin(Configuracao.getConnection());
        Queue queueSPFC = new Queue("spfc");
        Queue queueSantos = new Queue("santos");
        Queue queueCorinthians = new Queue("corinthians");
        Queue queuePalmeiras = new Queue("palmeiras");

        admin.declareQueue(queueSPFC);
        admin.declareQueue(queueSantos);
        admin.declareQueue(queueCorinthians);
        admin.declareQueue(queuePalmeiras);

        DirectExchange exchange = new DirectExchange("myExchangeRodrigo");

        admin.declareExchange(exchange);

        admin.declareBinding(BindingBuilder.bind(queueSPFC).to(exchange).with("torcedor.spfc"));
        admin.declareBinding(BindingBuilder.bind(queueSantos).to(exchange).with("torcedor.santos"));
        admin.declareBinding(BindingBuilder.bind(queueCorinthians).to(exchange).with("torcedor.corinthians"));
        admin.declareBinding(BindingBuilder.bind(queuePalmeiras).to(exchange).with("torcedor.palmeiras"));

        RabbitTemplate template = new RabbitTemplate(Configuracao.getConnection());

//        for (int i = 0; i < 10; i++) {
            template.convertAndSend("myExchangeRodrigo", "torcedor.santos", "Rodrigo Ornellas Santos!!!");
            template.convertAndSend("myExchangeRodrigo", "torcedor.spfc", "Rodrigo Ornellas SPFC!!!");
            template.convertAndSend("myExchangeRodrigo", "torcedor.corinthians", "Rodrigo Ornellas Corinthians!!!");
            template.convertAndSend("myExchangeRodrigo", "torcedor.palmeiras", "Rodrigo Ornellas Palmeiras!!!");
//        }

        return;
    }
}
