import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGrua } from 'app/shared/model/grua.model';

type EntityResponseType = HttpResponse<IGrua>;
type EntityArrayResponseType = HttpResponse<IGrua[]>;

@Injectable({ providedIn: 'root' })
export class GruaService {
  public resourceUrl = SERVER_API_URL + 'api/gruas';

  constructor(protected http: HttpClient) {}

  create(grua: IGrua): Observable<EntityResponseType> {
    return this.http.post<IGrua>(this.resourceUrl, grua, { observe: 'response' });
  }

  update(grua: IGrua): Observable<EntityResponseType> {
    return this.http.put<IGrua>(this.resourceUrl, grua, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IGrua>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGrua[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
