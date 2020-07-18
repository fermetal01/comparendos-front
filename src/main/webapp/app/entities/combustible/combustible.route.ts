import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICombustible, Combustible } from 'app/shared/model/combustible.model';
import { CombustibleService } from './combustible.service';
import { CombustibleComponent } from './combustible.component';
import { CombustibleDetailComponent } from './combustible-detail.component';
import { CombustibleUpdateComponent } from './combustible-update.component';

@Injectable({ providedIn: 'root' })
export class CombustibleResolve implements Resolve<ICombustible> {
  constructor(private service: CombustibleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICombustible> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((combustible: HttpResponse<Combustible>) => {
          if (combustible.body) {
            return of(combustible.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Combustible());
  }
}

export const combustibleRoute: Routes = [
  {
    path: '',
    component: CombustibleComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.combustible.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CombustibleDetailComponent,
    resolve: {
      combustible: CombustibleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.combustible.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CombustibleUpdateComponent,
    resolve: {
      combustible: CombustibleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.combustible.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CombustibleUpdateComponent,
    resolve: {
      combustible: CombustibleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.combustible.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
