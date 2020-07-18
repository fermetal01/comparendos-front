import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRango } from 'app/shared/model/rango.model';
import { RangoService } from './rango.service';

@Component({
  templateUrl: './rango-delete-dialog.component.html',
})
export class RangoDeleteDialogComponent {
  rango?: IRango;

  constructor(protected rangoService: RangoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.rangoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rangoListModification');
      this.activeModal.close();
    });
  }
}
