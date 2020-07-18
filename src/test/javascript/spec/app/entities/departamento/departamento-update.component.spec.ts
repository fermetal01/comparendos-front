import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { DepartamentoUpdateComponent } from 'app/entities/departamento/departamento-update.component';
import { DepartamentoService } from 'app/entities/departamento/departamento.service';
import { Departamento } from 'app/shared/model/departamento.model';

describe('Component Tests', () => {
  describe('Departamento Management Update Component', () => {
    let comp: DepartamentoUpdateComponent;
    let fixture: ComponentFixture<DepartamentoUpdateComponent>;
    let service: DepartamentoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [DepartamentoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DepartamentoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DepartamentoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DepartamentoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Departamento('123');
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
        const entity = new Departamento();
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
