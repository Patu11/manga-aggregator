package mangascrapingservice;

import mangascrapingservice.model.Manga;
import mangascrapingservice.service.download.OnePunchMangaDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private OnePunchMangaDownloadService mangaDownloadService;

	@GetMapping("/manga")
	public Manga send() {
		return mangaDownloadService.downloadManga();
	}

}
