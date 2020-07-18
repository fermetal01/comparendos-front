import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { RestriccionComponent } from './restriccion.component';
import { RestriccionDetailComponent } from './restriccion-detail.component';
import { RestriccionUpdateComponent } from './restriccion-update.component';
import { RestriccionDeleteDialogComponent } from './restriccion-delete-dialog.component';
import { restriccionRoute } from './restriccion.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(restriccionRoute)],
  declarations: [RestriccionComponent, RestriccionDetailComponent, RestriccionUpdateComponent, RestriccionDeleteDialogComponent],
  entryComponents: [RestriccionDeleteDialogComponent],
})
export class ComparendosRestriccionModule {}
