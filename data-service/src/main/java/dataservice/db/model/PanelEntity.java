package dataservice.db.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "panels", schema = "public")
public class PanelEntity {
	@Id
	@GenericGenerator(name = "panelgenerator", strategy = "increment")
	@GeneratedValue(generator = "panelgenerator")
	private Long id;
	private int number;
	private String link;
	@ManyToOne
	@JoinColumn(name = "chapter_id", nullable = false)
	private ChapterEntity chapter;
}
