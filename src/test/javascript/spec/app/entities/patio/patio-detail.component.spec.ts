import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { PatioDetailComponent } from 'app/entities/patio/patio-detail.component';
import { Patio } from 'app/shared/model/patio.model';

describe('Component Tests', () => {
  describe('Patio Management Detail Component', () => {
    let comp: PatioDetailComponent;
    let fixture: ComponentFixture<PatioDetailComponent>;
    const route = ({ data: of({ patio: new Patio('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [PatioDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PatioDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PatioDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load patio on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.patio).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
