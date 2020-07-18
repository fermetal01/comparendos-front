import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { InfraccionComponent } from './infraccion.component';
import { InfraccionDetailComponent } from './infraccion-detail.component';
import { InfraccionUpdateComponent } from './infraccion-update.component';
import { InfraccionDeleteDialogComponent } from './infraccion-delete-dialog.component';
import { infraccionRoute } from './infraccion.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(infraccionRoute)],
  declarations: [InfraccionComponent, InfraccionDetailComponent, InfraccionUpdateComponent, InfraccionDeleteDialogComponent],
  entryComponents: [InfraccionDeleteDialogComponent],
})
export class ComparendosInfraccionModule {}
