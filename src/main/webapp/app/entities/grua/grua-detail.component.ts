import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGrua } from 'app/shared/model/grua.model';

@Component({
  selector: 'jhi-grua-detail',
  templateUrl: './grua-detail.component.html',
})
export class GruaDetailComponent implements OnInit {
  grua: IGrua | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ grua }) => (this.grua = grua));
  }

  previousState(): void {
    window.history.back();
  }
}
