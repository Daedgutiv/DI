import { Component, OnInit } from '@angular/core';
import { NOTICIAS } from '../noticiasLista';
import { Noticia } from '../noticia';
import { NoticiaService } from '../noticia.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  noticias: Noticia[] = [];
  importantes: Noticia[] = [];
  breakpoint: number;
  constructor(private noticiaService: NoticiaService) { }

  ngOnInit() {
    this.getNoticias();
    if (screen.width < 415) {
      this.breakpoint = 1;
    } else if (screen.width > 415 && screen.width < 1150) {
      this.breakpoint = 2
    } else {
      this.breakpoint = 4
    }

  }

  onResize(event) {
    if (screen.width < 415) {
      this.breakpoint = 1;
    } else if (screen.width > 415 && screen.width < 1150) {
      this.breakpoint = 2
    } else {
      this.breakpoint = 4
    }
  }

  aux: number = 8;
  aux2: number = 4;
  altura: string;

  getNoticias(): void {
    if (screen.width < 415) {
      this.noticiaService.getNoticias().subscribe(noticias => this.noticias = noticias.slice(0, this.aux2));
    } else if (screen.width > 415 && screen.width < 1150) {
      this.noticiaService.getNoticias().subscribe(noticias => this.noticias = noticias.slice(0, this.aux2));
    } else {
      this.noticiaService.getNoticias().subscribe(noticias => this.noticias = noticias.slice(0, this.aux));
    }

    this.noticiaService.getNoticias().subscribe(noticias => this.importantes = noticias.slice(0, 4));

    if (screen.width < 415) {
      this.altura = "1:1";
    } else if (screen.width > 415 && screen.width < 1150) {
      this.altura = "1:1.1";
    } else {
      this.altura = "1:1";
    }

  }

  cargarMas() {
    this.aux = this.aux + 4;
    this.aux2 = this.aux2 + 4;
    if (screen.width < 415) {
      this.noticiaService.getNoticias().subscribe(noticias => this.noticias = noticias.slice(0, this.aux2));
    } else if (screen.width > 415 && screen.width < 1150) {
      this.noticiaService.getNoticias().subscribe(noticias => this.noticias = noticias.slice(0, this.aux2));
    } else {
      this.noticiaService.getNoticias().subscribe(noticias => this.noticias = noticias.slice(0, this.aux));
    }


  }



}
