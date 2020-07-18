import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IServicio } from 'app/shared/model/servicio.model';

type EntityResponseType = HttpResponse<IServicio>;
type EntityArrayResponseType = HttpResponse<IServicio[]>;

@Injectable({ providedIn: 'root' })
export class ServicioService {
  public resourceUrl = SERVER_API_URL + 'api/servicios';

  constructor(protected http: HttpClient) {}

  create(servicio: IServicio): Observable<EntityResponseType> {
    return this.http.post<IServicio>(this.resourceUrl, servicio, { observe: 'response' });
  }

  update(servicio: IServicio): Observable<EntityResponseType> {
    return this.http.put<IServicio>(this.resourceUrl, servicio, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IServicio>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IServicio[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
