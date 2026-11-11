import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Type } from '../model/type.model';
import { BookService } from '../services/book';
import { UpdateType } from '../update-type/update-type';

@Component({
  selector: 'app-liste-types',
  standalone: true,
  imports: [CommonModule, UpdateType],
  templateUrl: './liste-types.html',
  styles: ``
})
export class ListeTypes implements OnInit {

  types!: Type[];
  updatedType: Type = { 'idType': 0, 'nomType': '' };
  ajout: boolean = true;

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.chargerTypes();
  }

  chargerTypes() {
    this.bookService.listeTypes().
      subscribe(types => { this.types = types;
                           console.log(types);
      });
  }

  typeUpdated(type: Type) {
    console.log('Type updated event', type);
    this.bookService.ajouterType(type).
      subscribe(() => this.chargerTypes());
  }

  updateType(type: Type) {
    this.updatedType = type;
    this.ajout = false;
  }
}
