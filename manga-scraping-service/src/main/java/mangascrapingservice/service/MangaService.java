package mangascrapingservice.service;

import mangascrapingservice.model.Manga;
import mangascrapingservice.service.download.MangaDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {
	private final List<MangaDownloadService> mangaDownloadServices;

	@Autowired
	public MangaService(List<MangaDownloadService> mangaDownloadServices) {
		this.mangaDownloadServices = mangaDownloadServices;
	}

	public List<Manga> getAllDownloadedMangas() {
		return mangaDownloadServices.stream().map(MangaDownloadService::downloadManga).toList();
	}
}
