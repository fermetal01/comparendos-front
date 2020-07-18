import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { ServicioDetailComponent } from 'app/entities/servicio/servicio-detail.component';
import { Servicio } from 'app/shared/model/servicio.model';

describe('Component Tests', () => {
  describe('Servicio Management Detail Component', () => {
    let comp: ServicioDetailComponent;
    let fixture: ComponentFixture<ServicioDetailComponent>;
    const route = ({ data: of({ servicio: new Servicio('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [ServicioDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ServicioDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ServicioDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load servicio on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.servicio).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
