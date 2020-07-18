import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComparendosTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { OrganismoTransitoDeleteDialogComponent } from 'app/entities/organismo-transito/organismo-transito-delete-dialog.component';
import { OrganismoTransitoService } from 'app/entities/organismo-transito/organismo-transito.service';

describe('Component Tests', () => {
  describe('OrganismoTransito Management Delete Component', () => {
    let comp: OrganismoTransitoDeleteDialogComponent;
    let fixture: ComponentFixture<OrganismoTransitoDeleteDialogComponent>;
    let service: OrganismoTransitoService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [OrganismoTransitoDeleteDialogComponent],
      })
        .overrideTemplate(OrganismoTransitoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrganismoTransitoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrganismoTransitoService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete('123');
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith('123');
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
