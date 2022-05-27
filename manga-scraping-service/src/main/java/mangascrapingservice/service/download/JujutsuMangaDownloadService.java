package mangascrapingservice.service.download;

import mangascrapingservice.model.Chapter;
import mangascrapingservice.model.Manga;
import mangascrapingservice.model.Panel;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class JujutsuMangaDownloadService implements MangaDownloadService {
	private static final String JUJUTSU_URL = "https://jujustukaisen.com/";
	private static final String TITLE = "Jujutsu Kaisen";
	private static final String DESCRIPTION = """
			Jujutsu Kaisen (呪術廻戦, “Sorcery Fight”) is a Japanese manga series written and illustrated by Gege Akutami,
			serialized in Weekly Shōnen Jump since March 5, 2018. The individual chapters are collected and published by
			Shueisha, with six tankōbon volumes released as of July 2019.
			""";

	private static final String META_TAG = "meta";
	private static final String PROPERTY = "property";
	private static final String CONTENT = "content";
	private static final String TWITTER_IMAGE = "twitter:image";

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
			Elements metaTags = doc.getElementsByTag(META_TAG);
			AtomicInteger panelCounter = new AtomicInteger(0);
			return metaTags.stream()
					.filter(tag -> tag.attr(PROPERTY).equals(TWITTER_IMAGE))
					.map(image -> new Panel(panelCounter.getAndIncrement(), image.attr(CONTENT)))
					.toList();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Map<String, Double> getChapterUrlsWithNumbers() {
		try {
			Document doc = Jsoup.connect(JUJUTSU_URL).get();
			Element list = doc.getElementById("ceo_latest_comics_widget-3");

			if (list != null) {
				Elements a = list.getElementsByTag("a");
				return a.eachAttr("href").stream()
						.collect(Collectors.toMap(s -> s, this::getChapterNumberFromUrl));
			} else {
				return new HashMap<>();
			}
		} catch (IOException e) {
			return new HashMap<>();
		}
	}

	private double getChapterNumberFromUrl(String url) {
		String[] parts = url.split("-");
		String last = parts[parts.length - 1];
		String secondLast = parts[parts.length - 2];

		if (last.endsWith("/")) {
			last = StringUtils.trimTrailingCharacter(last, '/');
		}

		if (StringUtil.isNumeric(last) && StringUtil.isNumeric(secondLast)) {
			if (Integer.parseInt(last) == 5) {
				return Double.parseDouble(secondLast) + 0.5;
			}
		}

		return Double.parseDouble(last);
	}
}
