import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICombustible } from 'app/shared/model/combustible.model';

@Component({
  selector: 'jhi-combustible-detail',
  templateUrl: './combustible-detail.component.html',
})
export class CombustibleDetailComponent implements OnInit {
  combustible: ICombustible | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ combustible }) => (this.combustible = combustible));
  }

  previousState(): void {
    window.history.back();
  }
}
