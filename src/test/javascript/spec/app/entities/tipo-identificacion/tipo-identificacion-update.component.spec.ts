import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { TipoIdentificacionUpdateComponent } from 'app/entities/tipo-identificacion/tipo-identificacion-update.component';
import { TipoIdentificacionService } from 'app/entities/tipo-identificacion/tipo-identificacion.service';
import { TipoIdentificacion } from 'app/shared/model/tipo-identificacion.model';

describe('Component Tests', () => {
  describe('TipoIdentificacion Management Update Component', () => {
    let comp: TipoIdentificacionUpdateComponent;
    let fixture: ComponentFixture<TipoIdentificacionUpdateComponent>;
    let service: TipoIdentificacionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [TipoIdentificacionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TipoIdentificacionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoIdentificacionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoIdentificacionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TipoIdentificacion('123');
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
        const entity = new TipoIdentificacion();
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
