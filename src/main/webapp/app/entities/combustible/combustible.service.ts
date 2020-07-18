import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICombustible } from 'app/shared/model/combustible.model';

type EntityResponseType = HttpResponse<ICombustible>;
type EntityArrayResponseType = HttpResponse<ICombustible[]>;

@Injectable({ providedIn: 'root' })
export class CombustibleService {
  public resourceUrl = SERVER_API_URL + 'api/combustibles';

  constructor(protected http: HttpClient) {}

  create(combustible: ICombustible): Observable<EntityResponseType> {
    return this.http.post<ICombustible>(this.resourceUrl, combustible, { observe: 'response' });
  }

  update(combustible: ICombustible): Observable<EntityResponseType> {
    return this.http.put<ICombustible>(this.resourceUrl, combustible, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ICombustible>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICombustible[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
