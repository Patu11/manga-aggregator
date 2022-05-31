package mangascrapingservice.service;

import mangascrapingservice.model.Manga;
import mangascrapingservice.model.MangaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
public class ScheduledMessageService {

	private final MangaService mangaService;
	private final RabbitMessageSender messageSender;

	@Autowired
	public ScheduledMessageService(MangaService mangaService, RabbitMessageSender messageSender) {
		this.mangaService = mangaService;
		this.messageSender = messageSender;
	}

	@Scheduled(cron = "0 0 0/6 ? * * *")
	public void downloadAndSendManga() {
		List<Manga> mangas = mangaService.getAllDownloadedMangas();
		messageSender.send(new MangaMessage(LocalDateTime.now(), mangas));
		System.out.println("Sent " + mangas.size() + " mangas");
	}
}
