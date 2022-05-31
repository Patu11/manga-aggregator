package dataservice.model;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record Chapter(double number, String url, List<Panel> panels) implements Serializable {
}
