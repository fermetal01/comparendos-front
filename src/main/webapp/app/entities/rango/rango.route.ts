import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRango, Rango } from 'app/shared/model/rango.model';
import { RangoService } from './rango.service';
import { RangoComponent } from './rango.component';
import { RangoDetailComponent } from './rango-detail.component';
import { RangoUpdateComponent } from './rango-update.component';

@Injectable({ providedIn: 'root' })
export class RangoResolve implements Resolve<IRango> {
  constructor(private service: RangoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRango> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rango: HttpResponse<Rango>) => {
          if (rango.body) {
            return of(rango.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Rango());
  }
}

export const rangoRoute: Routes = [
  {
    path: '',
    component: RangoComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.rango.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RangoDetailComponent,
    resolve: {
      rango: RangoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.rango.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RangoUpdateComponent,
    resolve: {
      rango: RangoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.rango.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RangoUpdateComponent,
    resolve: {
      rango: RangoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.rango.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
