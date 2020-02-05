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
  constructor(private noticiaService: NoticiaService) { }

  ngOnInit() {
    this.getNoticias();
  }

 aux: number = 4;

  getNoticias(): void {
    this.noticiaService.getNoticias().subscribe(noticias => this.noticias = noticias.slice(0, this.aux));
  }

  cargarMas(){
    this.aux = this.aux+4;
    this.noticiaService.getNoticias().subscribe(noticias => this.noticias = noticias.slice(0, this.aux));
  }

}
