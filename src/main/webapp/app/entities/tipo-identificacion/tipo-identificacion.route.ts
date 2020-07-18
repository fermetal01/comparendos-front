import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITipoIdentificacion, TipoIdentificacion } from 'app/shared/model/tipo-identificacion.model';
import { TipoIdentificacionService } from './tipo-identificacion.service';
import { TipoIdentificacionComponent } from './tipo-identificacion.component';
import { TipoIdentificacionDetailComponent } from './tipo-identificacion-detail.component';
import { TipoIdentificacionUpdateComponent } from './tipo-identificacion-update.component';

@Injectable({ providedIn: 'root' })
export class TipoIdentificacionResolve implements Resolve<ITipoIdentificacion> {
  constructor(private service: TipoIdentificacionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITipoIdentificacion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tipoIdentificacion: HttpResponse<TipoIdentificacion>) => {
          if (tipoIdentificacion.body) {
            return of(tipoIdentificacion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TipoIdentificacion());
  }
}

export const tipoIdentificacionRoute: Routes = [
  {
    path: '',
    component: TipoIdentificacionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.tipoIdentificacion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TipoIdentificacionDetailComponent,
    resolve: {
      tipoIdentificacion: TipoIdentificacionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.tipoIdentificacion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TipoIdentificacionUpdateComponent,
    resolve: {
      tipoIdentificacion: TipoIdentificacionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.tipoIdentificacion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TipoIdentificacionUpdateComponent,
    resolve: {
      tipoIdentificacion: TipoIdentificacionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.tipoIdentificacion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
