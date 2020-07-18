import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { TipoIdentificacionComponent } from './tipo-identificacion.component';
import { TipoIdentificacionDetailComponent } from './tipo-identificacion-detail.component';
import { TipoIdentificacionUpdateComponent } from './tipo-identificacion-update.component';
import { TipoIdentificacionDeleteDialogComponent } from './tipo-identificacion-delete-dialog.component';
import { tipoIdentificacionRoute } from './tipo-identificacion.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(tipoIdentificacionRoute)],
  declarations: [
    TipoIdentificacionComponent,
    TipoIdentificacionDetailComponent,
    TipoIdentificacionUpdateComponent,
    TipoIdentificacionDeleteDialogComponent,
  ],
  entryComponents: [TipoIdentificacionDeleteDialogComponent],
})
export class ComparendosTipoIdentificacionModule {}
