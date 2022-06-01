import { Component, Input, OnInit } from '@angular/core';
import { Manga } from '../model/Manga';

@Component({
	selector: 'app-manga',
	templateUrl: './manga.component.html',
	styleUrls: ['./manga.component.css']
})
export class MangaComponent implements OnInit {

	@Input()
	manga?: Manga = new Manga("NOT LOADED", "NOT LOADED", new Array)

	constructor() { }

	ngOnInit(): void {
	}

}
