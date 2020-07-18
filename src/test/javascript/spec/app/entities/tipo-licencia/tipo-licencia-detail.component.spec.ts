import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { TipoLicenciaDetailComponent } from 'app/entities/tipo-licencia/tipo-licencia-detail.component';
import { TipoLicencia } from 'app/shared/model/tipo-licencia.model';

describe('Component Tests', () => {
  describe('TipoLicencia Management Detail Component', () => {
    let comp: TipoLicenciaDetailComponent;
    let fixture: ComponentFixture<TipoLicenciaDetailComponent>;
    const route = ({ data: of({ tipoLicencia: new TipoLicencia('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [TipoLicenciaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TipoLicenciaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoLicenciaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tipoLicencia on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tipoLicencia).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
