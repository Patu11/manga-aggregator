package dataservice;

import dataservice.model.MangaMessage;
import dataservice.service.MangaService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueConsumer {

	private final MangaService mangaService;

	@RabbitListener(queues = {"${manga.send.queue}"})
	public void receive(MangaMessage mangaMessage) {
		this.mangaService.saveMangas(mangaMessage.mangas());
		System.out.println("Saved " + mangaMessage.mangas().size() + " mangas");
	}
}
