import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IClaseVehiculo } from 'app/shared/model/clase-vehiculo.model';

type EntityResponseType = HttpResponse<IClaseVehiculo>;
type EntityArrayResponseType = HttpResponse<IClaseVehiculo[]>;

@Injectable({ providedIn: 'root' })
export class ClaseVehiculoService {
  public resourceUrl = SERVER_API_URL + 'api/clase-vehiculos';

  constructor(protected http: HttpClient) {}

  create(claseVehiculo: IClaseVehiculo): Observable<EntityResponseType> {
    return this.http.post<IClaseVehiculo>(this.resourceUrl, claseVehiculo, { observe: 'response' });
  }

  update(claseVehiculo: IClaseVehiculo): Observable<EntityResponseType> {
    return this.http.put<IClaseVehiculo>(this.resourceUrl, claseVehiculo, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IClaseVehiculo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClaseVehiculo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
