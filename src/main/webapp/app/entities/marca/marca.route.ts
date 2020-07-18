import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMarca, Marca } from 'app/shared/model/marca.model';
import { MarcaService } from './marca.service';
import { MarcaComponent } from './marca.component';
import { MarcaDetailComponent } from './marca-detail.component';
import { MarcaUpdateComponent } from './marca-update.component';

@Injectable({ providedIn: 'root' })
export class MarcaResolve implements Resolve<IMarca> {
  constructor(private service: MarcaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMarca> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((marca: HttpResponse<Marca>) => {
          if (marca.body) {
            return of(marca.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Marca());
  }
}

export const marcaRoute: Routes = [
  {
    path: '',
    component: MarcaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.marca.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MarcaDetailComponent,
    resolve: {
      marca: MarcaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.marca.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MarcaUpdateComponent,
    resolve: {
      marca: MarcaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.marca.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MarcaUpdateComponent,
    resolve: {
      marca: MarcaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.marca.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
