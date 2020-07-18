import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGenero, Genero } from 'app/shared/model/genero.model';
import { GeneroService } from './genero.service';
import { GeneroComponent } from './genero.component';
import { GeneroDetailComponent } from './genero-detail.component';
import { GeneroUpdateComponent } from './genero-update.component';

@Injectable({ providedIn: 'root' })
export class GeneroResolve implements Resolve<IGenero> {
  constructor(private service: GeneroService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGenero> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((genero: HttpResponse<Genero>) => {
          if (genero.body) {
            return of(genero.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Genero());
  }
}

export const generoRoute: Routes = [
  {
    path: '',
    component: GeneroComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.genero.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeneroDetailComponent,
    resolve: {
      genero: GeneroResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.genero.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeneroUpdateComponent,
    resolve: {
      genero: GeneroResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.genero.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeneroUpdateComponent,
    resolve: {
      genero: GeneroResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.genero.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
