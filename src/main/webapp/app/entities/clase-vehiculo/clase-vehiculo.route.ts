import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClaseVehiculo, ClaseVehiculo } from 'app/shared/model/clase-vehiculo.model';
import { ClaseVehiculoService } from './clase-vehiculo.service';
import { ClaseVehiculoComponent } from './clase-vehiculo.component';
import { ClaseVehiculoDetailComponent } from './clase-vehiculo-detail.component';
import { ClaseVehiculoUpdateComponent } from './clase-vehiculo-update.component';

@Injectable({ providedIn: 'root' })
export class ClaseVehiculoResolve implements Resolve<IClaseVehiculo> {
  constructor(private service: ClaseVehiculoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClaseVehiculo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((claseVehiculo: HttpResponse<ClaseVehiculo>) => {
          if (claseVehiculo.body) {
            return of(claseVehiculo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ClaseVehiculo());
  }
}

export const claseVehiculoRoute: Routes = [
  {
    path: '',
    component: ClaseVehiculoComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.claseVehiculo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ClaseVehiculoDetailComponent,
    resolve: {
      claseVehiculo: ClaseVehiculoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.claseVehiculo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ClaseVehiculoUpdateComponent,
    resolve: {
      claseVehiculo: ClaseVehiculoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.claseVehiculo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ClaseVehiculoUpdateComponent,
    resolve: {
      claseVehiculo: ClaseVehiculoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.claseVehiculo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
