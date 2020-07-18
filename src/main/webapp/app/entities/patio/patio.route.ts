import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPatio, Patio } from 'app/shared/model/patio.model';
import { PatioService } from './patio.service';
import { PatioComponent } from './patio.component';
import { PatioDetailComponent } from './patio-detail.component';
import { PatioUpdateComponent } from './patio-update.component';

@Injectable({ providedIn: 'root' })
export class PatioResolve implements Resolve<IPatio> {
  constructor(private service: PatioService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPatio> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((patio: HttpResponse<Patio>) => {
          if (patio.body) {
            return of(patio.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Patio());
  }
}

export const patioRoute: Routes = [
  {
    path: '',
    component: PatioComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.patio.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PatioDetailComponent,
    resolve: {
      patio: PatioResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.patio.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PatioUpdateComponent,
    resolve: {
      patio: PatioResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.patio.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PatioUpdateComponent,
    resolve: {
      patio: PatioResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.patio.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
