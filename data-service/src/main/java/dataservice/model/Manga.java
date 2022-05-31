package dataservice.model;

import java.io.Serializable;
import java.util.List;

public record Manga(String title, String description, List<Chapter> chapters) implements Serializable {
}
