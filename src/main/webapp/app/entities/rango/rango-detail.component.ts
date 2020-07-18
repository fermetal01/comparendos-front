import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRango } from 'app/shared/model/rango.model';

@Component({
  selector: 'jhi-rango-detail',
  templateUrl: './rango-detail.component.html',
})
export class RangoDetailComponent implements OnInit {
  rango: IRango | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rango }) => (this.rango = rango));
  }

  previousState(): void {
    window.history.back();
  }
}
