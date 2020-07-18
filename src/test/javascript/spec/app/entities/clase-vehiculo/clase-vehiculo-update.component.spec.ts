import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { ClaseVehiculoUpdateComponent } from 'app/entities/clase-vehiculo/clase-vehiculo-update.component';
import { ClaseVehiculoService } from 'app/entities/clase-vehiculo/clase-vehiculo.service';
import { ClaseVehiculo } from 'app/shared/model/clase-vehiculo.model';

describe('Component Tests', () => {
  describe('ClaseVehiculo Management Update Component', () => {
    let comp: ClaseVehiculoUpdateComponent;
    let fixture: ComponentFixture<ClaseVehiculoUpdateComponent>;
    let service: ClaseVehiculoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [ClaseVehiculoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ClaseVehiculoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaseVehiculoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaseVehiculoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ClaseVehiculo('123');
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
        const entity = new ClaseVehiculo();
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
