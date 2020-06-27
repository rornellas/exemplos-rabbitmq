package ninja.hesketh.configuracao;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

public class Configuracao {
    private static CachingConnectionFactory connectionFactory;

    public static CachingConnectionFactory getConnection(){

        if(connectionFactory == null){
            connectionFactory = new CachingConnectionFactory("spider.rmq.cloudamqp.com");
            connectionFactory.setUsername("xynklnau");
            connectionFactory.setPassword("T3r0XAwCvz7CmSRx65w7X07QnJQUkX3U");
            connectionFactory.setVirtualHost("xynklnau");

            //Recommended settings
            connectionFactory.setRequestedHeartBeat(30);
            connectionFactory.setConnectionTimeout(30000);
        }

        return connectionFactory;
    }
}
