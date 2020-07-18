import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoLicencia } from 'app/shared/model/tipo-licencia.model';

@Component({
  selector: 'jhi-tipo-licencia-detail',
  templateUrl: './tipo-licencia-detail.component.html',
})
export class TipoLicenciaDetailComponent implements OnInit {
  tipoLicencia: ITipoLicencia | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoLicencia }) => (this.tipoLicencia = tipoLicencia));
  }

  previousState(): void {
    window.history.back();
  }
}
