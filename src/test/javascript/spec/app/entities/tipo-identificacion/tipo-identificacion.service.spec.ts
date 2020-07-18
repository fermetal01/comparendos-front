import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TipoIdentificacionService } from 'app/entities/tipo-identificacion/tipo-identificacion.service';
import { ITipoIdentificacion, TipoIdentificacion } from 'app/shared/model/tipo-identificacion.model';

describe('Service Tests', () => {
  describe('TipoIdentificacion Service', () => {
    let injector: TestBed;
    let service: TipoIdentificacionService;
    let httpMock: HttpTestingController;
    let elemDefault: ITipoIdentificacion;
    let expectedResult: ITipoIdentificacion | ITipoIdentificacion[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TipoIdentificacionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TipoIdentificacion('ID', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TipoIdentificacion', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TipoIdentificacion()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TipoIdentificacion', () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TipoIdentificacion', () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TipoIdentificacion', () => {
        service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
