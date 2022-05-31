package dataservice.mapper;

import dataservice.db.model.ChapterEntity;
import dataservice.db.model.MangaEntity;
import dataservice.db.model.PanelEntity;
import dataservice.model.Chapter;
import dataservice.model.Manga;
import dataservice.model.Panel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MangaMapper {

	public Manga mapToModel(MangaEntity manga) {
		return Manga.builder()
				.title(manga.getTitle())
				.description(manga.getDescription())
				.chapters(this.mapChaptersToModel(manga.getChapters()))
				.build();
	}

	public MangaEntity mapToEntity(Manga manga) {
		MangaEntity mangaEntity = MangaEntity.builder()
				.title(manga.title())
				.description(manga.description())
				.build();
		mangaEntity.addChapters(this.mapChaptersToEntity(manga.chapters()));
		return mangaEntity;
	}

	private List<Chapter> mapChaptersToModel(List<ChapterEntity> chapters) {
		return chapters
				.stream()
				.map(chapter -> Chapter.builder()
						.number(chapter.getNumber())
						.url(chapter.getUrl())
						.panels(this.mapPanelsToModel(chapter.getPanels()))
						.build())
				.toList();
	}

	private List<Panel> mapPanelsToModel(List<PanelEntity> panels) {
		return panels
				.stream()
				.map(panel -> Panel.builder()
						.number(panel.getNumber())
						.link(panel.getLink())
						.build())
				.toList();
	}

	private List<ChapterEntity> mapChaptersToEntity(List<Chapter> chapters) {
		return chapters
				.stream()
				.map(chapter -> {
					ChapterEntity chapterEntity = ChapterEntity.builder()
							.number(chapter.number())
							.url(chapter.url())
							.build();
					chapterEntity.addPanels(this.mapPanelsToEntity(chapter.panels()));
					return chapterEntity;
				})
				.toList();
	}

	private List<PanelEntity> mapPanelsToEntity(List<Panel> panels) {
		return panels
				.stream()
				.map(panel -> PanelEntity.builder()
						.number(panel.number())
						.link(panel.link())
						.build())
				.toList();
	}
}
