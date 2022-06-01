package apigateway.service;

import apigateway.model.Manga;
import apigateway.model.MangaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataServiceClient {
	private static final String DATA_SERVICE_URL = "http://localhost:8282";
	private static final String GET_ALL_MANGAS_URL = DATA_SERVICE_URL + "/api/manga/all";

	private final RestTemplate restTemplate;

	public List<Manga> getAllMangas() {
		MangaResponse response = this.restTemplate.getForObject(GET_ALL_MANGAS_URL, MangaResponse.class);
		if (response == null) {
			return new LinkedList<>();
		}
		return response.mangas();
	}
}
