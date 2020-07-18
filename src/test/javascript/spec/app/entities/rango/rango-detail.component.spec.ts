import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { RangoDetailComponent } from 'app/entities/rango/rango-detail.component';
import { Rango } from 'app/shared/model/rango.model';

describe('Component Tests', () => {
  describe('Rango Management Detail Component', () => {
    let comp: RangoDetailComponent;
    let fixture: ComponentFixture<RangoDetailComponent>;
    const route = ({ data: of({ rango: new Rango('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [RangoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RangoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RangoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load rango on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rango).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
