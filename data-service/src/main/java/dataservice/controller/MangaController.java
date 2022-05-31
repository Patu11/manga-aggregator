package dataservice.controller;

import dataservice.model.Manga;
import dataservice.service.MangaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/manga")
@RestController
@RequiredArgsConstructor
public class MangaController {
	private final MangaService mangaService;

	@GetMapping("/all")
	public List<Manga> getAllMangas() {
		return this.mangaService.getAllMangas();
	}
}
