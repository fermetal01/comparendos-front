import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoLicencia } from 'app/shared/model/tipo-licencia.model';
import { TipoLicenciaService } from './tipo-licencia.service';

@Component({
  templateUrl: './tipo-licencia-delete-dialog.component.html',
})
export class TipoLicenciaDeleteDialogComponent {
  tipoLicencia?: ITipoLicencia;

  constructor(
    protected tipoLicenciaService: TipoLicenciaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.tipoLicenciaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tipoLicenciaListModification');
      this.activeModal.close();
    });
  }
}
