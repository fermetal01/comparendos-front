import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { ComparendosTestModule } from '../../../test.module';
import { TipoIdentificacionComponent } from 'app/entities/tipo-identificacion/tipo-identificacion.component';
import { TipoIdentificacionService } from 'app/entities/tipo-identificacion/tipo-identificacion.service';
import { TipoIdentificacion } from 'app/shared/model/tipo-identificacion.model';

describe('Component Tests', () => {
  describe('TipoIdentificacion Management Component', () => {
    let comp: TipoIdentificacionComponent;
    let fixture: ComponentFixture<TipoIdentificacionComponent>;
    let service: TipoIdentificacionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ComparendosTestModule],
        declarations: [TipoIdentificacionComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(TipoIdentificacionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoIdentificacionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoIdentificacionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TipoIdentificacion('123')],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tipoIdentificacions && comp.tipoIdentificacions[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TipoIdentificacion('123')],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tipoIdentificacions && comp.tipoIdentificacions[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
