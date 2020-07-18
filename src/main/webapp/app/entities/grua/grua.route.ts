import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGrua, Grua } from 'app/shared/model/grua.model';
import { GruaService } from './grua.service';
import { GruaComponent } from './grua.component';
import { GruaDetailComponent } from './grua-detail.component';
import { GruaUpdateComponent } from './grua-update.component';

@Injectable({ providedIn: 'root' })
export class GruaResolve implements Resolve<IGrua> {
  constructor(private service: GruaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGrua> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((grua: HttpResponse<Grua>) => {
          if (grua.body) {
            return of(grua.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Grua());
  }
}

export const gruaRoute: Routes = [
  {
    path: '',
    component: GruaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.grua.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GruaDetailComponent,
    resolve: {
      grua: GruaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.grua.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GruaUpdateComponent,
    resolve: {
      grua: GruaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.grua.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GruaUpdateComponent,
    resolve: {
      grua: GruaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.grua.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
