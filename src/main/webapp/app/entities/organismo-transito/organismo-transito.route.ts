import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrganismoTransito, OrganismoTransito } from 'app/shared/model/organismo-transito.model';
import { OrganismoTransitoService } from './organismo-transito.service';
import { OrganismoTransitoComponent } from './organismo-transito.component';
import { OrganismoTransitoDetailComponent } from './organismo-transito-detail.component';
import { OrganismoTransitoUpdateComponent } from './organismo-transito-update.component';

@Injectable({ providedIn: 'root' })
export class OrganismoTransitoResolve implements Resolve<IOrganismoTransito> {
  constructor(private service: OrganismoTransitoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrganismoTransito> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((organismoTransito: HttpResponse<OrganismoTransito>) => {
          if (organismoTransito.body) {
            return of(organismoTransito.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrganismoTransito());
  }
}

export const organismoTransitoRoute: Routes = [
  {
    path: '',
    component: OrganismoTransitoComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.organismoTransito.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OrganismoTransitoDetailComponent,
    resolve: {
      organismoTransito: OrganismoTransitoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.organismoTransito.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OrganismoTransitoUpdateComponent,
    resolve: {
      organismoTransito: OrganismoTransitoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.organismoTransito.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OrganismoTransitoUpdateComponent,
    resolve: {
      organismoTransito: OrganismoTransitoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.organismoTransito.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
