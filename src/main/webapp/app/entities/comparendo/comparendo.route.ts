import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IComparendo, Comparendo } from 'app/shared/model/comparendo.model';
import { ComparendoService } from './comparendo.service';
import { ComparendoComponent } from './comparendo.component';
import { ComparendoDetailComponent } from './comparendo-detail.component';
import { ComparendoUpdateComponent } from './comparendo-update.component';

@Injectable({ providedIn: 'root' })
export class ComparendoResolve implements Resolve<IComparendo> {
  constructor(private service: ComparendoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IComparendo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((comparendo: HttpResponse<Comparendo>) => {
          if (comparendo.body) {
            return of(comparendo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Comparendo());
  }
}

export const comparendoRoute: Routes = [
  {
    path: '',
    component: ComparendoComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'comparendosApp.comparendo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ComparendoDetailComponent,
    resolve: {
      comparendo: ComparendoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.comparendo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ComparendoUpdateComponent,
    resolve: {
      comparendo: ComparendoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.comparendo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ComparendoUpdateComponent,
    resolve: {
      comparendo: ComparendoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'comparendosApp.comparendo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
