package mangascrapingservice.service.download;

import mangascrapingservice.model.Chapter;
import mangascrapingservice.model.Manga;

import java.util.List;

public interface MangaDownloadService {
	Manga downloadManga();

	List<Chapter> downloadChapters();
}
