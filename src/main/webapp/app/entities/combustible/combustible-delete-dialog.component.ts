import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICombustible } from 'app/shared/model/combustible.model';
import { CombustibleService } from './combustible.service';

@Component({
  templateUrl: './combustible-delete-dialog.component.html',
})
export class CombustibleDeleteDialogComponent {
  combustible?: ICombustible;

  constructor(
    protected combustibleService: CombustibleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.combustibleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('combustibleListModification');
      this.activeModal.close();
    });
  }
}
