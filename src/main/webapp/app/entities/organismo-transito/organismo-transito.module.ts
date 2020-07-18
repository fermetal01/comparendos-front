import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { OrganismoTransitoComponent } from './organismo-transito.component';
import { OrganismoTransitoDetailComponent } from './organismo-transito-detail.component';
import { OrganismoTransitoUpdateComponent } from './organismo-transito-update.component';
import { OrganismoTransitoDeleteDialogComponent } from './organismo-transito-delete-dialog.component';
import { organismoTransitoRoute } from './organismo-transito.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(organismoTransitoRoute)],
  declarations: [
    OrganismoTransitoComponent,
    OrganismoTransitoDetailComponent,
    OrganismoTransitoUpdateComponent,
    OrganismoTransitoDeleteDialogComponent,
  ],
  entryComponents: [OrganismoTransitoDeleteDialogComponent],
})
export class ComparendosOrganismoTransitoModule {}
