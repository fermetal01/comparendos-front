import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrganismoTransito } from 'app/shared/model/organismo-transito.model';

@Component({
  selector: 'jhi-organismo-transito-detail',
  templateUrl: './organismo-transito-detail.component.html',
})
export class OrganismoTransitoDetailComponent implements OnInit {
  organismoTransito: IOrganismoTransito | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organismoTransito }) => (this.organismoTransito = organismoTransito));
  }

  previousState(): void {
    window.history.back();
  }
}
