import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { VehiculoDetailComponent } from 'app/entities/vehiculo/vehiculo-detail.component';
import { Vehiculo } from 'app/shared/model/vehiculo.model';

describe('Component Tests', () => {
  describe('Vehiculo Management Detail Component', () => {
    let comp: VehiculoDetailComponent;
    let fixture: ComponentFixture<VehiculoDetailComponent>;
    const route = ({ data: of({ vehiculo: new Vehiculo('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [VehiculoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(VehiculoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VehiculoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load vehiculo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.vehiculo).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
