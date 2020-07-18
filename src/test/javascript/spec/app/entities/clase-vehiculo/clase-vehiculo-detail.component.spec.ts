import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { ClaseVehiculoDetailComponent } from 'app/entities/clase-vehiculo/clase-vehiculo-detail.component';
import { ClaseVehiculo } from 'app/shared/model/clase-vehiculo.model';

describe('Component Tests', () => {
  describe('ClaseVehiculo Management Detail Component', () => {
    let comp: ClaseVehiculoDetailComponent;
    let fixture: ComponentFixture<ClaseVehiculoDetailComponent>;
    const route = ({ data: of({ claseVehiculo: new ClaseVehiculo('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [ClaseVehiculoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ClaseVehiculoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClaseVehiculoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load claseVehiculo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.claseVehiculo).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
