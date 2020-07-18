import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { EstadoComparendoDetailComponent } from 'app/entities/estado-comparendo/estado-comparendo-detail.component';
import { EstadoComparendo } from 'app/shared/model/estado-comparendo.model';

describe('Component Tests', () => {
  describe('EstadoComparendo Management Detail Component', () => {
    let comp: EstadoComparendoDetailComponent;
    let fixture: ComponentFixture<EstadoComparendoDetailComponent>;
    const route = ({ data: of({ estadoComparendo: new EstadoComparendo('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [EstadoComparendoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EstadoComparendoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadoComparendoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load estadoComparendo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.estadoComparendo).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
