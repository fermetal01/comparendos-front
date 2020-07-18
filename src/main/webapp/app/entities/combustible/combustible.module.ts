import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { CombustibleComponent } from './combustible.component';
import { CombustibleDetailComponent } from './combustible-detail.component';
import { CombustibleUpdateComponent } from './combustible-update.component';
import { CombustibleDeleteDialogComponent } from './combustible-delete-dialog.component';
import { combustibleRoute } from './combustible.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(combustibleRoute)],
  declarations: [CombustibleComponent, CombustibleDetailComponent, CombustibleUpdateComponent, CombustibleDeleteDialogComponent],
  entryComponents: [CombustibleDeleteDialogComponent],
})
export class ComparendosCombustibleModule {}
