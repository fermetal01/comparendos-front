import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITipoLicencia } from 'app/shared/model/tipo-licencia.model';

type EntityResponseType = HttpResponse<ITipoLicencia>;
type EntityArrayResponseType = HttpResponse<ITipoLicencia[]>;

@Injectable({ providedIn: 'root' })
export class TipoLicenciaService {
  public resourceUrl = SERVER_API_URL + 'api/tipo-licencias';

  constructor(protected http: HttpClient) {}

  create(tipoLicencia: ITipoLicencia): Observable<EntityResponseType> {
    return this.http.post<ITipoLicencia>(this.resourceUrl, tipoLicencia, { observe: 'response' });
  }

  update(tipoLicencia: ITipoLicencia): Observable<EntityResponseType> {
    return this.http.put<ITipoLicencia>(this.resourceUrl, tipoLicencia, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ITipoLicencia>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITipoLicencia[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
