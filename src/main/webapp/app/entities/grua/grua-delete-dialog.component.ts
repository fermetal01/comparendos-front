import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGrua } from 'app/shared/model/grua.model';
import { GruaService } from './grua.service';

@Component({
  templateUrl: './grua-delete-dialog.component.html',
})
export class GruaDeleteDialogComponent {
  grua?: IGrua;

  constructor(protected gruaService: GruaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.gruaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('gruaListModification');
      this.activeModal.close();
    });
  }
}
