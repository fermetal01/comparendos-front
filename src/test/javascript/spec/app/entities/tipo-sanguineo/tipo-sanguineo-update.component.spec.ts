import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { TipoSanguineoUpdateComponent } from 'app/entities/tipo-sanguineo/tipo-sanguineo-update.component';
import { TipoSanguineoService } from 'app/entities/tipo-sanguineo/tipo-sanguineo.service';
import { TipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';

describe('Component Tests', () => {
  describe('TipoSanguineo Management Update Component', () => {
    let comp: TipoSanguineoUpdateComponent;
    let fixture: ComponentFixture<TipoSanguineoUpdateComponent>;
    let service: TipoSanguineoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [TipoSanguineoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TipoSanguineoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoSanguineoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoSanguineoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TipoSanguineo('123');
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
        const entity = new TipoSanguineo();
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
