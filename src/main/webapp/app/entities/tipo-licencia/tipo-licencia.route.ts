import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITipoLicencia, TipoLicencia } from 'app/shared/model/tipo-licencia.model';
import { TipoLicenciaService } from './tipo-licencia.service';
import { TipoLicenciaComponent } from './tipo-licencia.component';
import { TipoLicenciaDetailComponent } from './tipo-licencia-detail.component';
import { TipoLicenciaUpdateComponent } from './tipo-licencia-update.component';

@Injectable({ providedIn: 'root' })
export class TipoLicenciaResolve implements Resolve<ITipoLicencia> {
  constructor(private service: TipoLicenciaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITipoLicencia> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tipoLicencia: HttpResponse<TipoLicencia>) => {
          if (tipoLicencia.body) {
            return of(tipoLicencia.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TipoLicencia());
  }
}

export const tipoLicenciaRoute: Routes = [
  {
    path: '',
    component: TipoLicenciaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.tipoLicencia.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TipoLicenciaDetailComponent,
    resolve: {
      tipoLicencia: TipoLicenciaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.tipoLicencia.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TipoLicenciaUpdateComponent,
    resolve: {
      tipoLicencia: TipoLicenciaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.tipoLicencia.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TipoLicenciaUpdateComponent,
    resolve: {
      tipoLicencia: TipoLicenciaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.tipoLicencia.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
