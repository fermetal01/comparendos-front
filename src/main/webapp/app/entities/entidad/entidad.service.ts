import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEntidad } from 'app/shared/model/entidad.model';

type EntityResponseType = HttpResponse<IEntidad>;
type EntityArrayResponseType = HttpResponse<IEntidad[]>;

@Injectable({ providedIn: 'root' })
export class EntidadService {
  public resourceUrl = SERVER_API_URL + 'api/entidads';

  constructor(protected http: HttpClient) {}

  create(entidad: IEntidad): Observable<EntityResponseType> {
    return this.http.post<IEntidad>(this.resourceUrl, entidad, { observe: 'response' });
  }

  update(entidad: IEntidad): Observable<EntityResponseType> {
    return this.http.put<IEntidad>(this.resourceUrl, entidad, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IEntidad>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEntidad[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
