import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMarca } from 'app/shared/model/marca.model';
import { MarcaService } from './marca.service';

@Component({
  templateUrl: './marca-delete-dialog.component.html',
})
export class MarcaDeleteDialogComponent {
  marca?: IMarca;

  constructor(protected marcaService: MarcaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.marcaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('marcaListModification');
      this.activeModal.close();
    });
  }
}
