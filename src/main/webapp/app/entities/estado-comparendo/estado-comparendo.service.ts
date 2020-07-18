import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEstadoComparendo } from 'app/shared/model/estado-comparendo.model';

type EntityResponseType = HttpResponse<IEstadoComparendo>;
type EntityArrayResponseType = HttpResponse<IEstadoComparendo[]>;

@Injectable({ providedIn: 'root' })
export class EstadoComparendoService {
  public resourceUrl = SERVER_API_URL + 'api/estado-comparendos';

  constructor(protected http: HttpClient) {}

  create(estadoComparendo: IEstadoComparendo): Observable<EntityResponseType> {
    return this.http.post<IEstadoComparendo>(this.resourceUrl, estadoComparendo, { observe: 'response' });
  }

  update(estadoComparendo: IEstadoComparendo): Observable<EntityResponseType> {
    return this.http.put<IEstadoComparendo>(this.resourceUrl, estadoComparendo, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IEstadoComparendo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEstadoComparendo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
