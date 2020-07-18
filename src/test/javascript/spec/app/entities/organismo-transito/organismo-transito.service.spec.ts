import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { OrganismoTransitoService } from 'app/entities/organismo-transito/organismo-transito.service';
import { IOrganismoTransito, OrganismoTransito } from 'app/shared/model/organismo-transito.model';

describe('Service Tests', () => {
  describe('OrganismoTransito Service', () => {
    let injector: TestBed;
    let service: OrganismoTransitoService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrganismoTransito;
    let expectedResult: IOrganismoTransito | IOrganismoTransito[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OrganismoTransitoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new OrganismoTransito('ID', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a OrganismoTransito', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new OrganismoTransito()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OrganismoTransito', () => {
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

      it('should return a list of OrganismoTransito', () => {
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

      it('should delete a OrganismoTransito', () => {
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
