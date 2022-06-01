package dataservice.service;

import dataservice.db.MangaRepository;
import dataservice.db.model.MangaEntity;
import dataservice.mapper.MangaMapper;
import dataservice.model.Manga;
import dataservice.model.MangaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MangaService {
	private final MangaRepository mangaRepository;
	private final MangaMapper mangaMapper;

	public void saveMangas(List<Manga> mangas) {
		List<MangaEntity> mappedMangas = mangas
				.stream()
				.map(mangaMapper::mapToEntity)
				.toList();
		this.mangaRepository.saveAll(mappedMangas);
	}

	public MangaResponse getAllMangas() {
		List<MangaEntity> mangas = this.mangaRepository.findAll();
		return new MangaResponse(mangas
				.stream()
				.map(mangaMapper::mapToModel)
				.toList());
	}
}
