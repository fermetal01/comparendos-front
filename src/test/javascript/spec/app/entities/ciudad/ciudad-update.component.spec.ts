import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { CiudadUpdateComponent } from 'app/entities/ciudad/ciudad-update.component';
import { CiudadService } from 'app/entities/ciudad/ciudad.service';
import { Ciudad } from 'app/shared/model/ciudad.model';

describe('Component Tests', () => {
  describe('Ciudad Management Update Component', () => {
    let comp: CiudadUpdateComponent;
    let fixture: ComponentFixture<CiudadUpdateComponent>;
    let service: CiudadService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [CiudadUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CiudadUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CiudadUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CiudadService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Ciudad('123');
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
        const entity = new Ciudad();
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
