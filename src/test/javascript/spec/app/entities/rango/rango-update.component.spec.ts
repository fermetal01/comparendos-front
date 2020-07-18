import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { RangoUpdateComponent } from 'app/entities/rango/rango-update.component';
import { RangoService } from 'app/entities/rango/rango.service';
import { Rango } from 'app/shared/model/rango.model';

describe('Component Tests', () => {
  describe('Rango Management Update Component', () => {
    let comp: RangoUpdateComponent;
    let fixture: ComponentFixture<RangoUpdateComponent>;
    let service: RangoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [RangoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RangoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RangoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RangoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Rango('123');
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
        const entity = new Rango();
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
