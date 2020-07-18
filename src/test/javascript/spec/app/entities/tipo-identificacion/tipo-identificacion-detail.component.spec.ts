import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComparendosTestModule } from '../../../test.module';
import { TipoIdentificacionDetailComponent } from 'app/entities/tipo-identificacion/tipo-identificacion-detail.component';
import { TipoIdentificacion } from 'app/shared/model/tipo-identificacion.model';

describe('Component Tests', () => {
  describe('TipoIdentificacion Management Detail Component', () => {
    let comp: TipoIdentificacionDetailComponent;
    let fixture: ComponentFixture<TipoIdentificacionDetailComponent>;
    const route = ({ data: of({ tipoIdentificacion: new TipoIdentificacion('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [TipoIdentificacionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TipoIdentificacionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoIdentificacionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tipoIdentificacion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tipoIdentificacion).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
