import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstadoComparendo } from 'app/shared/model/estado-comparendo.model';

@Component({
  selector: 'jhi-estado-comparendo-detail',
  templateUrl: './estado-comparendo-detail.component.html',
})
export class EstadoComparendoDetailComponent implements OnInit {
  estadoComparendo: IEstadoComparendo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadoComparendo }) => (this.estadoComparendo = estadoComparendo));
  }

  previousState(): void {
    window.history.back();
  }
}
