import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { ComparendoUpdateComponent } from 'app/entities/comparendo/comparendo-update.component';
import { ComparendoService } from 'app/entities/comparendo/comparendo.service';
import { Comparendo } from 'app/shared/model/comparendo.model';

describe('Component Tests', () => {
  describe('Comparendo Management Update Component', () => {
    let comp: ComparendoUpdateComponent;
    let fixture: ComponentFixture<ComparendoUpdateComponent>;
    let service: ComparendoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [ComparendoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ComparendoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ComparendoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ComparendoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Comparendo('123');
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
        const entity = new Comparendo();
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
