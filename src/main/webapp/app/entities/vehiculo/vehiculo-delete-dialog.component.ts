import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVehiculo } from 'app/shared/model/vehiculo.model';
import { VehiculoService } from './vehiculo.service';

@Component({
  templateUrl: './vehiculo-delete-dialog.component.html',
})
export class VehiculoDeleteDialogComponent {
  vehiculo?: IVehiculo;

  constructor(protected vehiculoService: VehiculoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.vehiculoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('vehiculoListModification');
      this.activeModal.close();
    });
  }
}
