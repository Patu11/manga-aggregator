import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  url: string = 'http://localhost:8080/test';

  constructor(private http: HttpClient) { }

  test() {
    return this.http.get(this.url);
  }
}
