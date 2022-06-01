package apigateway.service;

import apigateway.model.Manga;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MangaService {
	private final DataServiceClient dataServiceClient;

	public List<Manga> getAllMangas() {
		return this.dataServiceClient.getAllMangas();
	}
}
