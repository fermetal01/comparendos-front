import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { CombustibleDetailComponent } from 'app/entities/combustible/combustible-detail.component';
import { Combustible } from 'app/shared/model/combustible.model';

describe('Component Tests', () => {
  describe('Combustible Management Detail Component', () => {
    let comp: CombustibleDetailComponent;
    let fixture: ComponentFixture<CombustibleDetailComponent>;
    const route = ({ data: of({ combustible: new Combustible('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [CombustibleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CombustibleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CombustibleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load combustible on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.combustible).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
