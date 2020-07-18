import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { ServicioUpdateComponent } from 'app/entities/servicio/servicio-update.component';
import { ServicioService } from 'app/entities/servicio/servicio.service';
import { Servicio } from 'app/shared/model/servicio.model';

describe('Component Tests', () => {
  describe('Servicio Management Update Component', () => {
    let comp: ServicioUpdateComponent;
    let fixture: ComponentFixture<ServicioUpdateComponent>;
    let service: ServicioService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [ServicioUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ServicioUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ServicioUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ServicioService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Servicio('123');
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
        const entity = new Servicio();
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
