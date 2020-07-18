import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClaseVehiculo } from 'app/shared/model/clase-vehiculo.model';
import { ClaseVehiculoService } from './clase-vehiculo.service';

@Component({
  templateUrl: './clase-vehiculo-delete-dialog.component.html',
})
export class ClaseVehiculoDeleteDialogComponent {
  claseVehiculo?: IClaseVehiculo;

  constructor(
    protected claseVehiculoService: ClaseVehiculoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.claseVehiculoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('claseVehiculoListModification');
      this.activeModal.close();
    });
  }
}
