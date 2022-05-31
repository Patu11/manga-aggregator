package dataservice.model;

import java.io.Serializable;
import java.util.List;


public record Chapter(double number, String url, List<Panel> panels) implements Serializable {
}
