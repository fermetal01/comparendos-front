import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoIdentificacion } from 'app/shared/model/tipo-identificacion.model';
import { TipoIdentificacionService } from './tipo-identificacion.service';

@Component({
  templateUrl: './tipo-identificacion-delete-dialog.component.html',
})
export class TipoIdentificacionDeleteDialogComponent {
  tipoIdentificacion?: ITipoIdentificacion;

  constructor(
    protected tipoIdentificacionService: TipoIdentificacionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.tipoIdentificacionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tipoIdentificacionListModification');
      this.activeModal.close();
    });
  }
}
