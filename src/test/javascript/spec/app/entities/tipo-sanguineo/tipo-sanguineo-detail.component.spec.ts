import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { TipoSanguineoDetailComponent } from 'app/entities/tipo-sanguineo/tipo-sanguineo-detail.component';
import { TipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';

describe('Component Tests', () => {
  describe('TipoSanguineo Management Detail Component', () => {
    let comp: TipoSanguineoDetailComponent;
    let fixture: ComponentFixture<TipoSanguineoDetailComponent>;
    const route = ({ data: of({ tipoSanguineo: new TipoSanguineo('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [TipoSanguineoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TipoSanguineoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoSanguineoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tipoSanguineo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tipoSanguineo).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
