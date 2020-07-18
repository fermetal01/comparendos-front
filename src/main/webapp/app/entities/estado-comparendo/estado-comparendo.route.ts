import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEstadoComparendo, EstadoComparendo } from 'app/shared/model/estado-comparendo.model';
import { EstadoComparendoService } from './estado-comparendo.service';
import { EstadoComparendoComponent } from './estado-comparendo.component';
import { EstadoComparendoDetailComponent } from './estado-comparendo-detail.component';
import { EstadoComparendoUpdateComponent } from './estado-comparendo-update.component';

@Injectable({ providedIn: 'root' })
export class EstadoComparendoResolve implements Resolve<IEstadoComparendo> {
  constructor(private service: EstadoComparendoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEstadoComparendo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((estadoComparendo: HttpResponse<EstadoComparendo>) => {
          if (estadoComparendo.body) {
            return of(estadoComparendo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EstadoComparendo());
  }
}

export const estadoComparendoRoute: Routes = [
  {
    path: '',
    component: EstadoComparendoComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.estadoComparendo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EstadoComparendoDetailComponent,
    resolve: {
      estadoComparendo: EstadoComparendoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.estadoComparendo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EstadoComparendoUpdateComponent,
    resolve: {
      estadoComparendo: EstadoComparendoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.estadoComparendo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EstadoComparendoUpdateComponent,
    resolve: {
      estadoComparendo: EstadoComparendoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.estadoComparendo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
