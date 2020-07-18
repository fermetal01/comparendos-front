import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAgente } from 'app/shared/model/agente.model';

type EntityResponseType = HttpResponse<IAgente>;
type EntityArrayResponseType = HttpResponse<IAgente[]>;

@Injectable({ providedIn: 'root' })
export class AgenteService {
  public resourceUrl = SERVER_API_URL + 'api/agentes';

  constructor(protected http: HttpClient) {}

  create(agente: IAgente): Observable<EntityResponseType> {
    return this.http.post<IAgente>(this.resourceUrl, agente, { observe: 'response' });
  }

  update(agente: IAgente): Observable<EntityResponseType> {
    return this.http.put<IAgente>(this.resourceUrl, agente, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IAgente>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAgente[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
