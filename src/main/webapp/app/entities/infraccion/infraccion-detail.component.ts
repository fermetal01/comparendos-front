import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInfraccion } from 'app/shared/model/infraccion.model';

@Component({
  selector: 'jhi-infraccion-detail',
  templateUrl: './infraccion-detail.component.html',
})
export class InfraccionDetailComponent implements OnInit {
  infraccion: IInfraccion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ infraccion }) => (this.infraccion = infraccion));
  }

  previousState(): void {
    window.history.back();
  }
}
