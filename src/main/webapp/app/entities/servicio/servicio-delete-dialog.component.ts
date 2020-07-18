import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IServicio } from 'app/shared/model/servicio.model';
import { ServicioService } from './servicio.service';

@Component({
  templateUrl: './servicio-delete-dialog.component.html',
})
export class ServicioDeleteDialogComponent {
  servicio?: IServicio;

  constructor(protected servicioService: ServicioService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.servicioService.delete(id).subscribe(() => {
      this.eventManager.broadcast('servicioListModification');
      this.activeModal.close();
    });
  }
}
