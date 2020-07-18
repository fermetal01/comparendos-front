import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { VehiculoUpdateComponent } from 'app/entities/vehiculo/vehiculo-update.component';
import { VehiculoService } from 'app/entities/vehiculo/vehiculo.service';
import { Vehiculo } from 'app/shared/model/vehiculo.model';

describe('Component Tests', () => {
  describe('Vehiculo Management Update Component', () => {
    let comp: VehiculoUpdateComponent;
    let fixture: ComponentFixture<VehiculoUpdateComponent>;
    let service: VehiculoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [VehiculoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(VehiculoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VehiculoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VehiculoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Vehiculo('123');
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
        const entity = new Vehiculo();
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
