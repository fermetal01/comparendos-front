import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { VehiculoService } from 'app/entities/vehiculo/vehiculo.service';
import { IVehiculo, Vehiculo } from 'app/shared/model/vehiculo.model';

describe('Service Tests', () => {
  describe('Vehiculo Service', () => {
    let injector: TestBed;
    let service: VehiculoService;
    let httpMock: HttpTestingController;
    let elemDefault: IVehiculo;
    let expectedResult: IVehiculo | IVehiculo[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(VehiculoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Vehiculo(
        'ID',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fechaMatricula: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Vehiculo', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            fechaMatricula: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaMatricula: currentDate,
          },
          returnedFromService
        );

        service.create(new Vehiculo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Vehiculo', () => {
        const returnedFromService = Object.assign(
          {
            placa: 'BBBBBB',
            linea: 'BBBBBB',
            modelo: 1,
            cilindraje: 1,
            color: 'BBBBBB',
            capacidad: 1,
            numeroMotor: 'BBBBBB',
            vin: 'BBBBBB',
            serie: 'BBBBBB',
            chasis: 'BBBBBB',
            potencia: 'BBBBBB',
            fechaMatricula: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaMatricula: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Vehiculo', () => {
        const returnedFromService = Object.assign(
          {
            placa: 'BBBBBB',
            linea: 'BBBBBB',
            modelo: 1,
            cilindraje: 1,
            color: 'BBBBBB',
            capacidad: 1,
            numeroMotor: 'BBBBBB',
            vin: 'BBBBBB',
            serie: 'BBBBBB',
            chasis: 'BBBBBB',
            potencia: 'BBBBBB',
            fechaMatricula: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaMatricula: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Vehiculo', () => {
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
