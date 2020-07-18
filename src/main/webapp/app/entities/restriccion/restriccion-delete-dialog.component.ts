import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRestriccion } from 'app/shared/model/restriccion.model';
import { RestriccionService } from './restriccion.service';

@Component({
  templateUrl: './restriccion-delete-dialog.component.html',
})
export class RestriccionDeleteDialogComponent {
  restriccion?: IRestriccion;

  constructor(
    protected restriccionService: RestriccionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.restriccionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('restriccionListModification');
      this.activeModal.close();
    });
  }
}
