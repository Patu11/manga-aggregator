package dataservice;

import dataservice.model.Manga;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

	@RabbitListener(queues = {"${manga.send.queue}"})
	public void receive(Manga manga) {
		//TODO manga saving
	}
}
