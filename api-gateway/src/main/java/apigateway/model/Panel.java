package apigateway.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record Panel(int number, String link) implements Serializable {
}
