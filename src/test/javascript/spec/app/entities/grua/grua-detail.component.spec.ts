import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { GruaDetailComponent } from 'app/entities/grua/grua-detail.component';
import { Grua } from 'app/shared/model/grua.model';

describe('Component Tests', () => {
  describe('Grua Management Detail Component', () => {
    let comp: GruaDetailComponent;
    let fixture: ComponentFixture<GruaDetailComponent>;
    const route = ({ data: of({ grua: new Grua('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [GruaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GruaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GruaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load grua on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.grua).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
