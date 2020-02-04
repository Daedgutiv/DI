import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ComentarioService } from '../comentario.service';
import { Comentario } from '../comentario';
import { MatInput } from '@angular/material';



@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  form: FormGroup;

  contador: number = 0;

  constructor(private comentarioService: ComentarioService) { }

  ngOnInit() {
    this.form = new FormGroup({
      Nombre: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(16)]),
      Apellidos: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]),
      Email: new FormControl('', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]),
      Telefono: new FormControl('', [Validators.minLength(9), Validators.maxLength(9), Validators.pattern('[1-9]*')]),
      Comentario: new FormControl('', [Validators.required])
    })
  }

  enviar(nombre: string, apellidos: string, email: string, telefono: string, comentario: string) {

    if (!nombre || !apellidos || !email || !comentario) {
      return;
    }

    var id = this.contador;
    this.contador++;
    this.comentarioService.add({ id, nombre, apellidos, email, telefono, comentario } as Comentario)

    this.form.reset("");


  }


}
