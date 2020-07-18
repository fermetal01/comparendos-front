import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { MarcaUpdateComponent } from 'app/entities/marca/marca-update.component';
import { MarcaService } from 'app/entities/marca/marca.service';
import { Marca } from 'app/shared/model/marca.model';

describe('Component Tests', () => {
  describe('Marca Management Update Component', () => {
    let comp: MarcaUpdateComponent;
    let fixture: ComponentFixture<MarcaUpdateComponent>;
    let service: MarcaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [MarcaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MarcaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MarcaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MarcaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Marca('123');
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
        const entity = new Marca();
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
