import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAgente } from 'app/shared/model/agente.model';

@Component({
  selector: 'jhi-agente-detail',
  templateUrl: './agente-detail.component.html',
})
export class AgenteDetailComponent implements OnInit {
  agente: IAgente | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ agente }) => (this.agente = agente));
  }

  previousState(): void {
    window.history.back();
  }
}
