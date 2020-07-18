import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IServicio, Servicio } from 'app/shared/model/servicio.model';
import { ServicioService } from './servicio.service';
import { ServicioComponent } from './servicio.component';
import { ServicioDetailComponent } from './servicio-detail.component';
import { ServicioUpdateComponent } from './servicio-update.component';

@Injectable({ providedIn: 'root' })
export class ServicioResolve implements Resolve<IServicio> {
  constructor(private service: ServicioService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IServicio> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((servicio: HttpResponse<Servicio>) => {
          if (servicio.body) {
            return of(servicio.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Servicio());
  }
}

export const servicioRoute: Routes = [
  {
    path: '',
    component: ServicioComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.servicio.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ServicioDetailComponent,
    resolve: {
      servicio: ServicioResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.servicio.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ServicioUpdateComponent,
    resolve: {
      servicio: ServicioResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.servicio.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ServicioUpdateComponent,
    resolve: {
      servicio: ServicioResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.servicio.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
