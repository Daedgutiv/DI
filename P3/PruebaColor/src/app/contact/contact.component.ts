import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';



@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  form: FormGroup;

  constructor() { }

  ngOnInit() {
    this.form = new FormGroup({
      Nombre: new FormControl('',[Validators.required, Validators.minLength(2), Validators.maxLength(16)]),
      Apellidos: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]),
      Email: new FormControl('',[Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]),
      Telefono: new FormControl('', [Validators.minLength(9), Validators.maxLength(9), Validators.pattern('[1-9]*')])
    })
  }

}
