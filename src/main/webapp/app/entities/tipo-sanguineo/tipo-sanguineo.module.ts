import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { TipoSanguineoComponent } from './tipo-sanguineo.component';
import { TipoSanguineoDetailComponent } from './tipo-sanguineo-detail.component';
import { TipoSanguineoUpdateComponent } from './tipo-sanguineo-update.component';
import { TipoSanguineoDeleteDialogComponent } from './tipo-sanguineo-delete-dialog.component';
import { tipoSanguineoRoute } from './tipo-sanguineo.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(tipoSanguineoRoute)],
  declarations: [TipoSanguineoComponent, TipoSanguineoDetailComponent, TipoSanguineoUpdateComponent, TipoSanguineoDeleteDialogComponent],
  entryComponents: [TipoSanguineoDeleteDialogComponent],
})
export class ComparendosTipoSanguineoModule {}
