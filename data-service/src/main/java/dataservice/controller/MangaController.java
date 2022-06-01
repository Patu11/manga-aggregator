package dataservice.controller;

import dataservice.model.MangaResponse;
import dataservice.service.MangaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/manga")
@RestController
@RequiredArgsConstructor
public class MangaController {
	private final MangaService mangaService;

	@GetMapping("/all")
	public MangaResponse getAllMangas() {
		return this.mangaService.getAllMangas();
	}
}
