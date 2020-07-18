import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoIdentificacion } from 'app/shared/model/tipo-identificacion.model';

@Component({
  selector: 'jhi-tipo-identificacion-detail',
  templateUrl: './tipo-identificacion-detail.component.html',
})
export class TipoIdentificacionDetailComponent implements OnInit {
  tipoIdentificacion: ITipoIdentificacion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoIdentificacion }) => (this.tipoIdentificacion = tipoIdentificacion));
  }

  previousState(): void {
    window.history.back();
  }
}
