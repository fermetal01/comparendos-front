import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { PatioComponent } from './patio.component';
import { PatioDetailComponent } from './patio-detail.component';
import { PatioUpdateComponent } from './patio-update.component';
import { PatioDeleteDialogComponent } from './patio-delete-dialog.component';
import { patioRoute } from './patio.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(patioRoute)],
  declarations: [PatioComponent, PatioDetailComponent, PatioUpdateComponent, PatioDeleteDialogComponent],
  entryComponents: [PatioDeleteDialogComponent],
})
export class ComparendosPatioModule {}
