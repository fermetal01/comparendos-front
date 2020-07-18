import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComparendo } from 'app/shared/model/comparendo.model';

@Component({
  selector: 'jhi-comparendo-detail',
  templateUrl: './comparendo-detail.component.html',
})
export class ComparendoDetailComponent implements OnInit {
  comparendo: IComparendo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comparendo }) => (this.comparendo = comparendo));
  }

  previousState(): void {
    window.history.back();
  }
}
