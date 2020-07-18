import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAgente, Agente } from 'app/shared/model/agente.model';
import { AgenteService } from './agente.service';
import { AgenteComponent } from './agente.component';
import { AgenteDetailComponent } from './agente-detail.component';
import { AgenteUpdateComponent } from './agente-update.component';

@Injectable({ providedIn: 'root' })
export class AgenteResolve implements Resolve<IAgente> {
  constructor(private service: AgenteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAgente> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((agente: HttpResponse<Agente>) => {
          if (agente.body) {
            return of(agente.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Agente());
  }
}

export const agenteRoute: Routes = [
  {
    path: '',
    component: AgenteComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.agente.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AgenteDetailComponent,
    resolve: {
      agente: AgenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.agente.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AgenteUpdateComponent,
    resolve: {
      agente: AgenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.agente.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AgenteUpdateComponent,
    resolve: {
      agente: AgenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.agente.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
