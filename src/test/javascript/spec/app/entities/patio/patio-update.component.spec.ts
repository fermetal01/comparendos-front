import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { PatioUpdateComponent } from 'app/entities/patio/patio-update.component';
import { PatioService } from 'app/entities/patio/patio.service';
import { Patio } from 'app/shared/model/patio.model';

describe('Component Tests', () => {
  describe('Patio Management Update Component', () => {
    let comp: PatioUpdateComponent;
    let fixture: ComponentFixture<PatioUpdateComponent>;
    let service: PatioService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [PatioUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PatioUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PatioUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PatioService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Patio('123');
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
        const entity = new Patio();
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
