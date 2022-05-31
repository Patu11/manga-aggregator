package mangascrapingservice.controller;

import lombok.RequiredArgsConstructor;
import mangascrapingservice.model.Manga;
import mangascrapingservice.model.MangaMessage;
import mangascrapingservice.service.MangaService;
import mangascrapingservice.service.RabbitMessageSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api/download")
@RestController
@RequiredArgsConstructor
public class DownloadController {

	private final MangaService mangaService;
	private final RabbitMessageSender messageSender;

	@GetMapping("/trigger")
	public String triggerDownload() {
		List<Manga> mangas = mangaService.getAllDownloadedMangas();
		messageSender.send(new MangaMessage(LocalDateTime.now(), mangas));
		return "Downloaded and sent " + mangas.size() + " mangas.";
	}
}
