import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';
import { TipoSanguineoService } from './tipo-sanguineo.service';

@Component({
  templateUrl: './tipo-sanguineo-delete-dialog.component.html',
})
export class TipoSanguineoDeleteDialogComponent {
  tipoSanguineo?: ITipoSanguineo;

  constructor(
    protected tipoSanguineoService: TipoSanguineoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.tipoSanguineoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tipoSanguineoListModification');
      this.activeModal.close();
    });
  }
}
