import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPatio } from 'app/shared/model/patio.model';
import { PatioService } from './patio.service';

@Component({
  templateUrl: './patio-delete-dialog.component.html',
})
export class PatioDeleteDialogComponent {
  patio?: IPatio;

  constructor(protected patioService: PatioService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.patioService.delete(id).subscribe(() => {
      this.eventManager.broadcast('patioListModification');
      this.activeModal.close();
    });
  }
}
