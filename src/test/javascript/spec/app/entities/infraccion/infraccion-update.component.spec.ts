import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { InfraccionUpdateComponent } from 'app/entities/infraccion/infraccion-update.component';
import { InfraccionService } from 'app/entities/infraccion/infraccion.service';
import { Infraccion } from 'app/shared/model/infraccion.model';

describe('Component Tests', () => {
  describe('Infraccion Management Update Component', () => {
    let comp: InfraccionUpdateComponent;
    let fixture: ComponentFixture<InfraccionUpdateComponent>;
    let service: InfraccionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [InfraccionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InfraccionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InfraccionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InfraccionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Infraccion('123');
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
        const entity = new Infraccion();
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
