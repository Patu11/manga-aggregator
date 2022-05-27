package mangascrapingservice.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMessageSender {

	private final RabbitTemplate rabbitTemplate;
	private final Queue mangaQueue;

	@Autowired
	public RabbitMessageSender(RabbitTemplate rabbitTemplate, Queue mangaQueue) {
		this.rabbitTemplate = rabbitTemplate;
		this.mangaQueue = mangaQueue;
	}

	public void send(Object message) {
		rabbitTemplate.convertAndSend(mangaQueue.getName(), message);
	}

	public void send(List<Object> messages) {
		messages.forEach(this::send);
	}
}
