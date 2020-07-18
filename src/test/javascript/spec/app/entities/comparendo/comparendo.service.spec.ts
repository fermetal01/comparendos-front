import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ComparendoService } from 'app/entities/comparendo/comparendo.service';
import { IComparendo, Comparendo } from 'app/shared/model/comparendo.model';

describe('Service Tests', () => {
  describe('Comparendo Service', () => {
    let injector: TestBed;
    let service: ComparendoService;
    let httpMock: HttpTestingController;
    let elemDefault: IComparendo;
    let expectedResult: IComparendo | IComparendo[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ComparendoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Comparendo('ID', currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fechaHora: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Comparendo', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            fechaHora: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaHora: currentDate,
          },
          returnedFromService
        );

        service.create(new Comparendo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Comparendo', () => {
        const returnedFromService = Object.assign(
          {
            fechaHora: currentDate.format(DATE_TIME_FORMAT),
            direccion: 'BBBBBB',
            observaciones: 'BBBBBB',
            codigoInmovilizacion: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaHora: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Comparendo', () => {
        const returnedFromService = Object.assign(
          {
            fechaHora: currentDate.format(DATE_TIME_FORMAT),
            direccion: 'BBBBBB',
            observaciones: 'BBBBBB',
            codigoInmovilizacion: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaHora: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Comparendo', () => {
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
