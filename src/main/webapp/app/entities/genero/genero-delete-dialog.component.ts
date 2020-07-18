import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGenero } from 'app/shared/model/genero.model';
import { GeneroService } from './genero.service';

@Component({
  templateUrl: './genero-delete-dialog.component.html',
})
export class GeneroDeleteDialogComponent {
  genero?: IGenero;

  constructor(protected generoService: GeneroService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.generoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('generoListModification');
      this.activeModal.close();
    });
  }
}
