import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInfraccion } from 'app/shared/model/infraccion.model';
import { InfraccionService } from './infraccion.service';

@Component({
  templateUrl: './infraccion-delete-dialog.component.html',
})
export class InfraccionDeleteDialogComponent {
  infraccion?: IInfraccion;

  constructor(
    protected infraccionService: InfraccionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.infraccionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('infraccionListModification');
      this.activeModal.close();
    });
  }
}
