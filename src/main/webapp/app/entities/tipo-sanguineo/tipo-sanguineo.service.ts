import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';

type EntityResponseType = HttpResponse<ITipoSanguineo>;
type EntityArrayResponseType = HttpResponse<ITipoSanguineo[]>;

@Injectable({ providedIn: 'root' })
export class TipoSanguineoService {
  public resourceUrl = SERVER_API_URL + 'api/tipo-sanguineos';

  constructor(protected http: HttpClient) {}

  create(tipoSanguineo: ITipoSanguineo): Observable<EntityResponseType> {
    return this.http.post<ITipoSanguineo>(this.resourceUrl, tipoSanguineo, { observe: 'response' });
  }

  update(tipoSanguineo: ITipoSanguineo): Observable<EntityResponseType> {
    return this.http.put<ITipoSanguineo>(this.resourceUrl, tipoSanguineo, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ITipoSanguineo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITipoSanguineo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
