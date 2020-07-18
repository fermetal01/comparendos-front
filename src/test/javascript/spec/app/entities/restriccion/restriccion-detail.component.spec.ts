import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { RestriccionDetailComponent } from 'app/entities/restriccion/restriccion-detail.component';
import { Restriccion } from 'app/shared/model/restriccion.model';

describe('Component Tests', () => {
  describe('Restriccion Management Detail Component', () => {
    let comp: RestriccionDetailComponent;
    let fixture: ComponentFixture<RestriccionDetailComponent>;
    const route = ({ data: of({ restriccion: new Restriccion('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [RestriccionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RestriccionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RestriccionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load restriccion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.restriccion).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
