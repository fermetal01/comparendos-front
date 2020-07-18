import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRestriccion } from 'app/shared/model/restriccion.model';

type EntityResponseType = HttpResponse<IRestriccion>;
type EntityArrayResponseType = HttpResponse<IRestriccion[]>;

@Injectable({ providedIn: 'root' })
export class RestriccionService {
  public resourceUrl = SERVER_API_URL + 'api/restriccions';

  constructor(protected http: HttpClient) {}

  create(restriccion: IRestriccion): Observable<EntityResponseType> {
    return this.http.post<IRestriccion>(this.resourceUrl, restriccion, { observe: 'response' });
  }

  update(restriccion: IRestriccion): Observable<EntityResponseType> {
    return this.http.put<IRestriccion>(this.resourceUrl, restriccion, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IRestriccion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRestriccion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
