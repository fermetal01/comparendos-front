import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComparendo } from 'app/shared/model/comparendo.model';
import { ComparendoService } from './comparendo.service';

@Component({
  templateUrl: './comparendo-delete-dialog.component.html',
})
export class ComparendoDeleteDialogComponent {
  comparendo?: IComparendo;

  constructor(
    protected comparendoService: ComparendoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.comparendoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('comparendoListModification');
      this.activeModal.close();
    });
  }
}
