import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComparendosTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { TipoIdentificacionDeleteDialogComponent } from 'app/entities/tipo-identificacion/tipo-identificacion-delete-dialog.component';
import { TipoIdentificacionService } from 'app/entities/tipo-identificacion/tipo-identificacion.service';

describe('Component Tests', () => {
  describe('TipoIdentificacion Management Delete Component', () => {
    let comp: TipoIdentificacionDeleteDialogComponent;
    let fixture: ComponentFixture<TipoIdentificacionDeleteDialogComponent>;
    let service: TipoIdentificacionService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [TipoIdentificacionDeleteDialogComponent],
      })
        .overrideTemplate(TipoIdentificacionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoIdentificacionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoIdentificacionService);
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
