import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICiudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from './ciudad.service';

@Component({
  templateUrl: './ciudad-delete-dialog.component.html',
})
export class CiudadDeleteDialogComponent {
  ciudad?: ICiudad;

  constructor(protected ciudadService: CiudadService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.ciudadService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ciudadListModification');
      this.activeModal.close();
    });
  }
}
