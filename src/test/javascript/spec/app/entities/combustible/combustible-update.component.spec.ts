import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { CombustibleUpdateComponent } from 'app/entities/combustible/combustible-update.component';
import { CombustibleService } from 'app/entities/combustible/combustible.service';
import { Combustible } from 'app/shared/model/combustible.model';

describe('Component Tests', () => {
  describe('Combustible Management Update Component', () => {
    let comp: CombustibleUpdateComponent;
    let fixture: ComponentFixture<CombustibleUpdateComponent>;
    let service: CombustibleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [CombustibleUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CombustibleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CombustibleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CombustibleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Combustible('123');
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
        const entity = new Combustible();
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
