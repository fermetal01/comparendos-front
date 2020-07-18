import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAgente } from 'app/shared/model/agente.model';
import { AgenteService } from './agente.service';

@Component({
  templateUrl: './agente-delete-dialog.component.html',
})
export class AgenteDeleteDialogComponent {
  agente?: IAgente;

  constructor(protected agenteService: AgenteService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.agenteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('agenteListModification');
      this.activeModal.close();
    });
  }
}
