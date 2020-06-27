package ninja.hesketh.produtor;

import ninja.hesketh.configuracao.Configuracao;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ProdutorFanout {

    public static void main(String[] args) {

        //Set up queue, exchanges and bindings
        RabbitAdmin admin = new RabbitAdmin(Configuracao.getConnection());

        Queue elevador = new Queue("elevador");
        Queue cafeteira = new Queue("cafeteira");

        admin.declareQueue(elevador);
        admin.declareQueue(cafeteira);

        FanoutExchange exchange = new FanoutExchange("myExchangeRodrigoFanout");

        admin.declareExchange(exchange);

        admin.declareBinding(BindingBuilder.bind(elevador).to(exchange));
        admin.declareBinding(BindingBuilder.bind(cafeteira).to(exchange));

        RabbitTemplate template = new RabbitTemplate(Configuracao.getConnection());

//        for (int i = 0; i < 10; i++) {
            template.convertAndSend("myExchangeRodrigoFanout", "qualquer.coisa", "Rodrigo Ornellas FANOUT!!!");
//        }

        System.exit(0);
    }
}
