import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPatio } from 'app/shared/model/patio.model';

@Component({
  selector: 'jhi-patio-detail',
  templateUrl: './patio-detail.component.html',
})
export class PatioDetailComponent implements OnInit {
  patio: IPatio | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patio }) => (this.patio = patio));
  }

  previousState(): void {
    window.history.back();
  }
}
