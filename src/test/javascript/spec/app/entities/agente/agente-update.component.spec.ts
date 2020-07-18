import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { AgenteUpdateComponent } from 'app/entities/agente/agente-update.component';
import { AgenteService } from 'app/entities/agente/agente.service';
import { Agente } from 'app/shared/model/agente.model';

describe('Component Tests', () => {
  describe('Agente Management Update Component', () => {
    let comp: AgenteUpdateComponent;
    let fixture: ComponentFixture<AgenteUpdateComponent>;
    let service: AgenteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [AgenteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AgenteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AgenteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AgenteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Agente('123');
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
        const entity = new Agente();
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
