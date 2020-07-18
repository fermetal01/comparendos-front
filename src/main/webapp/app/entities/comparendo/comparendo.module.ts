import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { ComparendoComponent } from './comparendo.component';
import { ComparendoDetailComponent } from './comparendo-detail.component';
import { ComparendoUpdateComponent } from './comparendo-update.component';
import { ComparendoDeleteDialogComponent } from './comparendo-delete-dialog.component';
import { comparendoRoute } from './comparendo.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(comparendoRoute)],
  declarations: [ComparendoComponent, ComparendoDetailComponent, ComparendoUpdateComponent, ComparendoDeleteDialogComponent],
  entryComponents: [ComparendoDeleteDialogComponent],
})
export class ComparendosComparendoModule {}
