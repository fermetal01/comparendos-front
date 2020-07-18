import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { OrganismoTransitoUpdateComponent } from 'app/entities/organismo-transito/organismo-transito-update.component';
import { OrganismoTransitoService } from 'app/entities/organismo-transito/organismo-transito.service';
import { OrganismoTransito } from 'app/shared/model/organismo-transito.model';

describe('Component Tests', () => {
  describe('OrganismoTransito Management Update Component', () => {
    let comp: OrganismoTransitoUpdateComponent;
    let fixture: ComponentFixture<OrganismoTransitoUpdateComponent>;
    let service: OrganismoTransitoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [OrganismoTransitoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OrganismoTransitoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrganismoTransitoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrganismoTransitoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrganismoTransito('123');
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
        const entity = new OrganismoTransito();
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
