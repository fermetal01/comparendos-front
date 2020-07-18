import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDepartamento, Departamento } from 'app/shared/model/departamento.model';
import { DepartamentoService } from './departamento.service';
import { DepartamentoComponent } from './departamento.component';
import { DepartamentoDetailComponent } from './departamento-detail.component';
import { DepartamentoUpdateComponent } from './departamento-update.component';

@Injectable({ providedIn: 'root' })
export class DepartamentoResolve implements Resolve<IDepartamento> {
  constructor(private service: DepartamentoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDepartamento> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((departamento: HttpResponse<Departamento>) => {
          if (departamento.body) {
            return of(departamento.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Departamento());
  }
}

export const departamentoRoute: Routes = [
  {
    path: '',
    component: DepartamentoComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.departamento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DepartamentoDetailComponent,
    resolve: {
      departamento: DepartamentoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.departamento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DepartamentoUpdateComponent,
    resolve: {
      departamento: DepartamentoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.departamento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DepartamentoUpdateComponent,
    resolve: {
      departamento: DepartamentoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.departamento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
