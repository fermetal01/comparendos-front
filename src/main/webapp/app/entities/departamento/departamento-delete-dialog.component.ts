import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDepartamento } from 'app/shared/model/departamento.model';
import { DepartamentoService } from './departamento.service';

@Component({
  templateUrl: './departamento-delete-dialog.component.html',
})
export class DepartamentoDeleteDialogComponent {
  departamento?: IDepartamento;

  constructor(
    protected departamentoService: DepartamentoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.departamentoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('departamentoListModification');
      this.activeModal.close();
    });
  }
}
