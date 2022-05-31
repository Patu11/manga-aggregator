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
@Table(name = "chapters", schema = "public")
public class ChapterEntity {
	@Id
	@GenericGenerator(name = "chaptergenerator", strategy = "increment")
	@GeneratedValue(generator = "chaptergenerator")
	private Long id;
	private double number;
	private String url;

	@ManyToOne
	@JoinColumn(name = "manga_id", nullable = false)
	private MangaEntity manga;

	@OneToMany(mappedBy = "chapter", cascade = CascadeType.PERSIST)
	private List<PanelEntity> panels;

	public void addPanels(List<PanelEntity> panels) {
		this.panels = panels;
		for (PanelEntity panel : panels) {
			panel.setChapter(this);
		}
	}
}
