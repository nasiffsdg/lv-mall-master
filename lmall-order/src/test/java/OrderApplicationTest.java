import com.lv.mall.OrderApplication;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest(classes = OrderApplication.class)
public class OrderApplicationTest {

    // 管理组件
    @Resource
    AmqpAdmin amqpAdmin;

    @Resource
    RabbitTemplate rabbitTemplate;



    @Test
    public void createExchange(){

        DirectExchange directExchange = new DirectExchange("hello.java", true, false);
        amqpAdmin.declareExchange(directExchange);

        Queue queue = new Queue("hello.java", true, false, false);
        amqpAdmin.declareQueue(queue);

        Binding binding = new Binding("hello.java", Binding.DestinationType.QUEUE, "hello.java", "hello",null);
        amqpAdmin.declareBinding(binding);

    }

    @Test
    public void sendMes(){
        rabbitTemplate.convertAndSend("hello.java","hello","你好");
    }

    @RabbitListener(queues = "hello.java")
    public void receive(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        channel.basicAck(deliveryTag, false);
        channel.basicNack(deliveryTag, false, true);

        System.out.println(message);
    }
}
