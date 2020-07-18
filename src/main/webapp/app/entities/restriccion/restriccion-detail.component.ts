import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRestriccion } from 'app/shared/model/restriccion.model';

@Component({
  selector: 'jhi-restriccion-detail',
  templateUrl: './restriccion-detail.component.html',
})
export class RestriccionDetailComponent implements OnInit {
  restriccion: IRestriccion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ restriccion }) => (this.restriccion = restriccion));
  }

  previousState(): void {
    window.history.back();
  }
}
