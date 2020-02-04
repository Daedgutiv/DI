import { Injectable } from '@angular/core';
import {Comentario} from './comentario';

@Injectable({
  providedIn: 'root'
})
export class ComentarioService {

comentarios: string[] = [];

  add(comentario: Comentario){

var aux = "Nombre: " + comentario.nombre + " " + comentario.apellidos + " Email: " +
comentario.email + " Telefono: " + comentario.telefono + " Comentario: " + comentario.comentario;

    this.comentarios.push(aux);
    console.log(this.comentarios);
  }

  clear(){
    this.comentarios = [];
  }
}
