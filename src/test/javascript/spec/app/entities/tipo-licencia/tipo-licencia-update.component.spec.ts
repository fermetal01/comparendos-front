import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { TipoLicenciaUpdateComponent } from 'app/entities/tipo-licencia/tipo-licencia-update.component';
import { TipoLicenciaService } from 'app/entities/tipo-licencia/tipo-licencia.service';
import { TipoLicencia } from 'app/shared/model/tipo-licencia.model';

describe('Component Tests', () => {
  describe('TipoLicencia Management Update Component', () => {
    let comp: TipoLicenciaUpdateComponent;
    let fixture: ComponentFixture<TipoLicenciaUpdateComponent>;
    let service: TipoLicenciaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [TipoLicenciaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TipoLicenciaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoLicenciaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoLicenciaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TipoLicencia('123');
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
        const entity = new TipoLicencia();
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
