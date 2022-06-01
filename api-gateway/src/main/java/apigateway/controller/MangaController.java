package apigateway.controller;

import apigateway.model.Manga;
import apigateway.service.MangaService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manga")
public class MangaController {

	private final MangaService mangaService;

	@GetMapping("/all")
	@Cacheable(value = "Manga")
	public List<Manga> all() {
		return this.mangaService.getAllMangas();
	}
}
