package mangascrapingservice.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record MangaMessage(LocalDateTime timestamp, List<Manga> mangas) implements Serializable {
}
