import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInfraccion } from 'app/shared/model/infraccion.model';

type EntityResponseType = HttpResponse<IInfraccion>;
type EntityArrayResponseType = HttpResponse<IInfraccion[]>;

@Injectable({ providedIn: 'root' })
export class InfraccionService {
  public resourceUrl = SERVER_API_URL + 'api/infraccions';

  constructor(protected http: HttpClient) {}

  create(infraccion: IInfraccion): Observable<EntityResponseType> {
    return this.http.post<IInfraccion>(this.resourceUrl, infraccion, { observe: 'response' });
  }

  update(infraccion: IInfraccion): Observable<EntityResponseType> {
    return this.http.put<IInfraccion>(this.resourceUrl, infraccion, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IInfraccion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInfraccion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
