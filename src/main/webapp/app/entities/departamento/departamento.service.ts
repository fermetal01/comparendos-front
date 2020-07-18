import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDepartamento } from 'app/shared/model/departamento.model';

type EntityResponseType = HttpResponse<IDepartamento>;
type EntityArrayResponseType = HttpResponse<IDepartamento[]>;

@Injectable({ providedIn: 'root' })
export class DepartamentoService {
  public resourceUrl = SERVER_API_URL + 'api/departamentos';

  constructor(protected http: HttpClient) {}

  create(departamento: IDepartamento): Observable<EntityResponseType> {
    return this.http.post<IDepartamento>(this.resourceUrl, departamento, { observe: 'response' });
  }

  update(departamento: IDepartamento): Observable<EntityResponseType> {
    return this.http.put<IDepartamento>(this.resourceUrl, departamento, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IDepartamento>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDepartamento[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
