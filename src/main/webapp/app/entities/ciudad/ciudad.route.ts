import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICiudad, Ciudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from './ciudad.service';
import { CiudadComponent } from './ciudad.component';
import { CiudadDetailComponent } from './ciudad-detail.component';
import { CiudadUpdateComponent } from './ciudad-update.component';

@Injectable({ providedIn: 'root' })
export class CiudadResolve implements Resolve<ICiudad> {
  constructor(private service: CiudadService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICiudad> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ciudad: HttpResponse<Ciudad>) => {
          if (ciudad.body) {
            return of(ciudad.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Ciudad());
  }
}

export const ciudadRoute: Routes = [
  {
    path: '',
    component: CiudadComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.ciudad.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CiudadDetailComponent,
    resolve: {
      ciudad: CiudadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.ciudad.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CiudadUpdateComponent,
    resolve: {
      ciudad: CiudadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.ciudad.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CiudadUpdateComponent,
    resolve: {
      ciudad: CiudadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.ciudad.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
