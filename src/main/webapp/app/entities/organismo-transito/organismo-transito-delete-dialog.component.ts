import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrganismoTransito } from 'app/shared/model/organismo-transito.model';
import { OrganismoTransitoService } from './organismo-transito.service';

@Component({
  templateUrl: './organismo-transito-delete-dialog.component.html',
})
export class OrganismoTransitoDeleteDialogComponent {
  organismoTransito?: IOrganismoTransito;

  constructor(
    protected organismoTransitoService: OrganismoTransitoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.organismoTransitoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('organismoTransitoListModification');
      this.activeModal.close();
    });
  }
}
