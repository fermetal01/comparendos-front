import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInfraccion, Infraccion } from 'app/shared/model/infraccion.model';
import { InfraccionService } from './infraccion.service';
import { InfraccionComponent } from './infraccion.component';
import { InfraccionDetailComponent } from './infraccion-detail.component';
import { InfraccionUpdateComponent } from './infraccion-update.component';

@Injectable({ providedIn: 'root' })
export class InfraccionResolve implements Resolve<IInfraccion> {
  constructor(private service: InfraccionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInfraccion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((infraccion: HttpResponse<Infraccion>) => {
          if (infraccion.body) {
            return of(infraccion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Infraccion());
  }
}

export const infraccionRoute: Routes = [
  {
    path: '',
    component: InfraccionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.infraccion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InfraccionDetailComponent,
    resolve: {
      infraccion: InfraccionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.infraccion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InfraccionUpdateComponent,
    resolve: {
      infraccion: InfraccionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.infraccion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InfraccionUpdateComponent,
    resolve: {
      infraccion: InfraccionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.infraccion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
