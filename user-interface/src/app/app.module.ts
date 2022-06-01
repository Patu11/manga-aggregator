import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MangaComponent } from './manga/manga.component';
import { ChapterComponent } from './chapter/chapter.component';
import { PanelComponent } from './panel/panel.component';
import { AllMangasComponent } from './all-mangas/all-mangas.component';

@NgModule({
  declarations: [
    AppComponent,
    MangaComponent,
    ChapterComponent,
    PanelComponent,
    AllMangasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
