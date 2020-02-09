import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NoticiaService } from '../noticia.service';
import { Noticia } from '../noticia';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Comentario } from '../comentarioNoticia';
import { ComentarioService } from '../comentario.service';
import { ComentariosNoticiaService } from '../comentarios-noticia.service';
import { MatCalendar } from '@angular/material';

@Component({
  selector: 'app-noticia-detalle',
  templateUrl: './noticia-detalle.component.html',
  styleUrls: ['./noticia-detalle.component.css']
})
export class NoticiaDetalleComponent implements OnInit {
  @Input() noticia: Noticia;

  form: FormGroup;
  comentarios: Comentario[] = [];
  cantidad: number = 0;

  constructor(
    private route: ActivatedRoute,
    private noticiaService: NoticiaService,
    private comentarioService: ComentariosNoticiaService
  ) { }

  ngOnInit() {
    this.getNoticia();
    this.getComentarios();


    this.form = new FormGroup({
      nombre: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(30)]),
      comentario: new FormControl('', [Validators.required, Validators.minLength(10)])
    })
  }

  id: number;

  getNoticia(): void {
    this.id = +this.route.snapshot.paramMap.get('id');

    this.noticiaService.getNoticia(this.id).subscribe(noticia => this.noticia = noticia);

  }

  getComentarios(): void {

    this.comentarioService.getComentarios(this.id).subscribe(comentarios => this.comentarios = comentarios.slice(0, 4));
    this.cantidad = this.comentarios.length;
  }

  enviar(nombre: string, comentario: string) {

    var id = this.id;
    var fecha: number = new Date().getTime();


    this.comentarioService.add({ id, nombre, comentario, fecha } as Comentario);

    this.getComentarios();

    this.form.reset();
  }


}
