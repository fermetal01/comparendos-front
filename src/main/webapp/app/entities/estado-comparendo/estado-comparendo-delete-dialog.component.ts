import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadoComparendo } from 'app/shared/model/estado-comparendo.model';
import { EstadoComparendoService } from './estado-comparendo.service';

@Component({
  templateUrl: './estado-comparendo-delete-dialog.component.html',
})
export class EstadoComparendoDeleteDialogComponent {
  estadoComparendo?: IEstadoComparendo;

  constructor(
    protected estadoComparendoService: EstadoComparendoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.estadoComparendoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('estadoComparendoListModification');
      this.activeModal.close();
    });
  }
}
