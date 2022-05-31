package dataservice.model;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record Manga(String title, String description, List<Chapter> chapters) implements Serializable {
}
