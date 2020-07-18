import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { AgenteDetailComponent } from 'app/entities/agente/agente-detail.component';
import { Agente } from 'app/shared/model/agente.model';

describe('Component Tests', () => {
  describe('Agente Management Detail Component', () => {
    let comp: AgenteDetailComponent;
    let fixture: ComponentFixture<AgenteDetailComponent>;
    const route = ({ data: of({ agente: new Agente('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [AgenteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AgenteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AgenteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load agente on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.agente).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
