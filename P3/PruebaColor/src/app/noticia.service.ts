import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import {Noticia} from './noticia';
import {NOTICIAS} from './noticiasLista';

@Injectable({
  providedIn: 'root'
})
export class NoticiaService {

  constructor() { }

  getNoticias(): Observable<Noticia[]>{
    return of(NOTICIAS);
  }
}
