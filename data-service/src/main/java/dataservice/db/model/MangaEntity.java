package dataservice.db.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mangas", schema = "public")
public class MangaEntity {
	@Id
	@GenericGenerator(name = "mangagenerator", strategy = "increment")
	@GeneratedValue(generator = "mangagenerator")
	private Long id;
	private String title;
	@Column(length = 500)
	private String description;

	@OneToMany(mappedBy = "manga", cascade = CascadeType.PERSIST)
	@OrderBy(value = "number ASC")
	private List<ChapterEntity> chapters;

	public void addChapters(List<ChapterEntity> chapters) {
		this.chapters = chapters;
		for (ChapterEntity chapter : chapters) {
			chapter.setManga(this);
		}
	}
}
