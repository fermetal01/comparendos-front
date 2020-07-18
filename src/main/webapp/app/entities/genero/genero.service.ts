import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGenero } from 'app/shared/model/genero.model';

type EntityResponseType = HttpResponse<IGenero>;
type EntityArrayResponseType = HttpResponse<IGenero[]>;

@Injectable({ providedIn: 'root' })
export class GeneroService {
  public resourceUrl = SERVER_API_URL + 'api/generos';

  constructor(protected http: HttpClient) {}

  create(genero: IGenero): Observable<EntityResponseType> {
    return this.http.post<IGenero>(this.resourceUrl, genero, { observe: 'response' });
  }

  update(genero: IGenero): Observable<EntityResponseType> {
    return this.http.put<IGenero>(this.resourceUrl, genero, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IGenero>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGenero[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
