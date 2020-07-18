import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';

@Component({
  selector: 'jhi-tipo-sanguineo-detail',
  templateUrl: './tipo-sanguineo-detail.component.html',
})
export class TipoSanguineoDetailComponent implements OnInit {
  tipoSanguineo: ITipoSanguineo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoSanguineo }) => (this.tipoSanguineo = tipoSanguineo));
  }

  previousState(): void {
    window.history.back();
  }
}
