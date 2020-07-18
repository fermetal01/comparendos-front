import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { EstadoComparendoUpdateComponent } from 'app/entities/estado-comparendo/estado-comparendo-update.component';
import { EstadoComparendoService } from 'app/entities/estado-comparendo/estado-comparendo.service';
import { EstadoComparendo } from 'app/shared/model/estado-comparendo.model';

describe('Component Tests', () => {
  describe('EstadoComparendo Management Update Component', () => {
    let comp: EstadoComparendoUpdateComponent;
    let fixture: ComponentFixture<EstadoComparendoUpdateComponent>;
    let service: EstadoComparendoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [EstadoComparendoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EstadoComparendoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadoComparendoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadoComparendoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EstadoComparendo('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EstadoComparendo();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
