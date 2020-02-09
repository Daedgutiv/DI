import { Injectable } from '@angular/core';
import {Comentario} from './comentarioNoticia';
import { Observable, of } from 'rxjs';
import{COMENTARIOS} from './comentariosNoticiaLista';

@Injectable({
  providedIn: 'root'
})
export class ComentariosNoticiaService {

  add(comentario: Comentario){
     COMENTARIOS.push(comentario);
  }

  getComentarios(id:number): Observable<Comentario[]> {

    var aux: Comentario[]=[];

    for (var i:number=0; i<COMENTARIOS.length;i++){

      if (COMENTARIOS[i].id==id){
        aux.push(COMENTARIOS[i]);
      }

    }

    return of(aux);
  }

}
