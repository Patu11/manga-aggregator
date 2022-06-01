import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Manga } from '../model/Manga';

@Injectable({
	providedIn: 'root'
})
export class MangaService {

	URL: string = 'http://localhost:8080/api/manga/all';

	constructor(private http: HttpClient) { }

	getAllMangas() {
		return this.http.get<Array<Manga>>(this.URL);
	}
}
