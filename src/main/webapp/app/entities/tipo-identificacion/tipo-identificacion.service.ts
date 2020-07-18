import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITipoIdentificacion } from 'app/shared/model/tipo-identificacion.model';

type EntityResponseType = HttpResponse<ITipoIdentificacion>;
type EntityArrayResponseType = HttpResponse<ITipoIdentificacion[]>;

@Injectable({ providedIn: 'root' })
export class TipoIdentificacionService {
  public resourceUrl = SERVER_API_URL + 'api/tipo-identificacions';

  constructor(protected http: HttpClient) {}

  create(tipoIdentificacion: ITipoIdentificacion): Observable<EntityResponseType> {
    return this.http.post<ITipoIdentificacion>(this.resourceUrl, tipoIdentificacion, { observe: 'response' });
  }

  update(tipoIdentificacion: ITipoIdentificacion): Observable<EntityResponseType> {
    return this.http.put<ITipoIdentificacion>(this.resourceUrl, tipoIdentificacion, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ITipoIdentificacion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITipoIdentificacion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
