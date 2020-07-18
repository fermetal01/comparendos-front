import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { GeneroUpdateComponent } from 'app/entities/genero/genero-update.component';
import { GeneroService } from 'app/entities/genero/genero.service';
import { Genero } from 'app/shared/model/genero.model';

describe('Component Tests', () => {
  describe('Genero Management Update Component', () => {
    let comp: GeneroUpdateComponent;
    let fixture: ComponentFixture<GeneroUpdateComponent>;
    let service: GeneroService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [GeneroUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeneroUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeneroUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeneroService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Genero('123');
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
        const entity = new Genero();
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
