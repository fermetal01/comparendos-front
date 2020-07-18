import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { ComparendoDetailComponent } from 'app/entities/comparendo/comparendo-detail.component';
import { Comparendo } from 'app/shared/model/comparendo.model';

describe('Component Tests', () => {
  describe('Comparendo Management Detail Component', () => {
    let comp: ComparendoDetailComponent;
    let fixture: ComponentFixture<ComparendoDetailComponent>;
    const route = ({ data: of({ comparendo: new Comparendo('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [ComparendoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ComparendoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ComparendoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load comparendo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.comparendo).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
