import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { GruaUpdateComponent } from 'app/entities/grua/grua-update.component';
import { GruaService } from 'app/entities/grua/grua.service';
import { Grua } from 'app/shared/model/grua.model';

describe('Component Tests', () => {
  describe('Grua Management Update Component', () => {
    let comp: GruaUpdateComponent;
    let fixture: ComponentFixture<GruaUpdateComponent>;
    let service: GruaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [GruaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GruaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GruaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GruaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Grua('123');
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
        const entity = new Grua();
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
