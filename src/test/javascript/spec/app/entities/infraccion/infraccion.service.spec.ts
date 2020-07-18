import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { InfraccionService } from 'app/entities/infraccion/infraccion.service';
import { IInfraccion, Infraccion } from 'app/shared/model/infraccion.model';

describe('Service Tests', () => {
  describe('Infraccion Service', () => {
    let injector: TestBed;
    let service: InfraccionService;
    let httpMock: HttpTestingController;
    let elemDefault: IInfraccion;
    let expectedResult: IInfraccion | IInfraccion[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InfraccionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Infraccion('ID', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Infraccion', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Infraccion()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Infraccion', () => {
        const returnedFromService = Object.assign(
          {
            codigo: 'BBBBBB',
            descripcion: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Infraccion', () => {
        const returnedFromService = Object.assign(
          {
            codigo: 'BBBBBB',
            descripcion: 'BBBBBB',
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

      it('should delete a Infraccion', () => {
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
