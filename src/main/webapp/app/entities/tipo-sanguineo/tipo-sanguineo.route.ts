import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITipoSanguineo, TipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';
import { TipoSanguineoService } from './tipo-sanguineo.service';
import { TipoSanguineoComponent } from './tipo-sanguineo.component';
import { TipoSanguineoDetailComponent } from './tipo-sanguineo-detail.component';
import { TipoSanguineoUpdateComponent } from './tipo-sanguineo-update.component';

@Injectable({ providedIn: 'root' })
export class TipoSanguineoResolve implements Resolve<ITipoSanguineo> {
  constructor(private service: TipoSanguineoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITipoSanguineo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tipoSanguineo: HttpResponse<TipoSanguineo>) => {
          if (tipoSanguineo.body) {
            return of(tipoSanguineo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TipoSanguineo());
  }
}

export const tipoSanguineoRoute: Routes = [
  {
    path: '',
    component: TipoSanguineoComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.tipoSanguineo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TipoSanguineoDetailComponent,
    resolve: {
      tipoSanguineo: TipoSanguineoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.tipoSanguineo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TipoSanguineoUpdateComponent,
    resolve: {
      tipoSanguineo: TipoSanguineoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.tipoSanguineo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TipoSanguineoUpdateComponent,
    resolve: {
      tipoSanguineo: TipoSanguineoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.tipoSanguineo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
