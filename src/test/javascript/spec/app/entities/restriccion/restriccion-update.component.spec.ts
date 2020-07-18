import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { RestriccionUpdateComponent } from 'app/entities/restriccion/restriccion-update.component';
import { RestriccionService } from 'app/entities/restriccion/restriccion.service';
import { Restriccion } from 'app/shared/model/restriccion.model';

describe('Component Tests', () => {
  describe('Restriccion Management Update Component', () => {
    let comp: RestriccionUpdateComponent;
    let fixture: ComponentFixture<RestriccionUpdateComponent>;
    let service: RestriccionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [RestriccionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RestriccionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RestriccionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RestriccionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Restriccion('123');
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
        const entity = new Restriccion();
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
