import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { GruaComponent } from './grua.component';
import { GruaDetailComponent } from './grua-detail.component';
import { GruaUpdateComponent } from './grua-update.component';
import { GruaDeleteDialogComponent } from './grua-delete-dialog.component';
import { gruaRoute } from './grua.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(gruaRoute)],
  declarations: [GruaComponent, GruaDetailComponent, GruaUpdateComponent, GruaDeleteDialogComponent],
  entryComponents: [GruaDeleteDialogComponent],
})
export class ComparendosGruaModule {}
