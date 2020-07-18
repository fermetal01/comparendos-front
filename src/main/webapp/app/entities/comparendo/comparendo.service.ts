import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IComparendo } from 'app/shared/model/comparendo.model';

type EntityResponseType = HttpResponse<IComparendo>;
type EntityArrayResponseType = HttpResponse<IComparendo[]>;

@Injectable({ providedIn: 'root' })
export class ComparendoService {
  public resourceUrl = SERVER_API_URL + 'api/comparendos';

  constructor(protected http: HttpClient) {}

  create(comparendo: IComparendo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(comparendo);
    return this.http
      .post<IComparendo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(comparendo: IComparendo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(comparendo);
    return this.http
      .put<IComparendo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IComparendo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IComparendo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(comparendo: IComparendo): IComparendo {
    const copy: IComparendo = Object.assign({}, comparendo, {
      fechaHora: comparendo.fechaHora && comparendo.fechaHora.isValid() ? comparendo.fechaHora.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaHora = res.body.fechaHora ? moment(res.body.fechaHora) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((comparendo: IComparendo) => {
        comparendo.fechaHora = comparendo.fechaHora ? moment(comparendo.fechaHora) : undefined;
      });
    }
    return res;
  }
}
