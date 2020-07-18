import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMarca } from 'app/shared/model/marca.model';

type EntityResponseType = HttpResponse<IMarca>;
type EntityArrayResponseType = HttpResponse<IMarca[]>;

@Injectable({ providedIn: 'root' })
export class MarcaService {
  public resourceUrl = SERVER_API_URL + 'api/marcas';

  constructor(protected http: HttpClient) {}

  create(marca: IMarca): Observable<EntityResponseType> {
    return this.http.post<IMarca>(this.resourceUrl, marca, { observe: 'response' });
  }

  update(marca: IMarca): Observable<EntityResponseType> {
    return this.http.put<IMarca>(this.resourceUrl, marca, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IMarca>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMarca[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
