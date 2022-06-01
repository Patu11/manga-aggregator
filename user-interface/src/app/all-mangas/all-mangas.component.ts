import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Manga } from '../model/Manga';
import { MangaService } from '../service/manga.service';

@Component({
	selector: 'app-all-mangas',
	templateUrl: './all-mangas.component.html',
	styleUrls: ['./all-mangas.component.css']
})
export class AllMangasComponent implements OnInit {

	mangas$: Observable<Array<Manga>>;

	constructor(private mangaService: MangaService) { }

	ngOnInit(): void {
		this.mangas$ = this.mangaService.getAllMangas();
	}

}
