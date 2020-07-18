import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRestriccion, Restriccion } from 'app/shared/model/restriccion.model';
import { RestriccionService } from './restriccion.service';
import { RestriccionComponent } from './restriccion.component';
import { RestriccionDetailComponent } from './restriccion-detail.component';
import { RestriccionUpdateComponent } from './restriccion-update.component';

@Injectable({ providedIn: 'root' })
export class RestriccionResolve implements Resolve<IRestriccion> {
  constructor(private service: RestriccionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRestriccion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((restriccion: HttpResponse<Restriccion>) => {
          if (restriccion.body) {
            return of(restriccion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Restriccion());
  }
}

export const restriccionRoute: Routes = [
  {
    path: '',
    component: RestriccionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.restriccion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RestriccionDetailComponent,
    resolve: {
      restriccion: RestriccionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.restriccion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RestriccionUpdateComponent,
    resolve: {
      restriccion: RestriccionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.restriccion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RestriccionUpdateComponent,
    resolve: {
      restriccion: RestriccionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.restriccion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
