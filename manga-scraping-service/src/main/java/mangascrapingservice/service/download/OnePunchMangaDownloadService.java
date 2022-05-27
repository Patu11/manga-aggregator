package mangascrapingservice.service.download;

import mangascrapingservice.model.Chapter;
import mangascrapingservice.model.Manga;
import mangascrapingservice.model.Panel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OnePunchMangaDownloadService implements MangaDownloadService {
	private static final String ONEPUNCH_URL = "https://onepunch.online/";
	private static final String TITLE = "One - Punch Man";
	private static final String DESCRIPTION = "One-Punch Man (ワンパンマン Wanpanman) is a Japanese webcomic, manga, and anime series created by ONE. The webcomic started in July 2009, with more than 100 million total views and 20,000 hits per day.";

	@Override
	public Manga downloadManga() {
		return new Manga(TITLE, DESCRIPTION, this.downloadChapters());
	}

	@Override
	public List<Chapter> downloadChapters() {
		return this.getChapterUrlsWithNumbers().entrySet().stream()
				.map(entry -> new Chapter(entry.getValue(), entry.getKey(), this.downloadChapterPanels(entry.getKey())))
				.sorted(Comparator.comparingDouble(Chapter::number))
				.toList();
	}

	private List<Panel> downloadChapterPanels(String chapterUrl) {
		try {
			Document doc = Jsoup.connect(chapterUrl).get();
			Elements img = doc.getElementsByTag("img");
			List<String> urls = img.eachAttr("src").stream().filter(u -> u.contains("chapter") || u.contains("Chapter")).toList();
			AtomicInteger panelCounter = new AtomicInteger(0);

			return urls.stream()
					.map(url -> new Panel(panelCounter.getAndIncrement(), url)).toList();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Map<String, Double> getChapterUrlsWithNumbers() {
		try {
			Document doc = Jsoup.connect(ONEPUNCH_URL).get();
			Elements list = doc.getElementsByClass("su-posts su-posts-list-loop ");
			Elements li = list.stream().map(el -> el.getElementsByTag("li")).toList().get(0);

			if (li == null) {
				return new HashMap<>();
			}

			return li.stream().collect(Collectors.toMap(k -> k.getElementsByTag("a").attr("href"), v -> getChapterNumberFromTitle(v.text())));
		} catch (IOException e) {
			return new HashMap<>();
		}
	}

	private double getChapterNumberFromTitle(String title) {
		String[] parts = title.split(" ");
		return Double.parseDouble(parts[parts.length - 1]);
	}
}
