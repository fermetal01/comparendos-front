import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { TipoLicenciaComponent } from './tipo-licencia.component';
import { TipoLicenciaDetailComponent } from './tipo-licencia-detail.component';
import { TipoLicenciaUpdateComponent } from './tipo-licencia-update.component';
import { TipoLicenciaDeleteDialogComponent } from './tipo-licencia-delete-dialog.component';
import { tipoLicenciaRoute } from './tipo-licencia.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(tipoLicenciaRoute)],
  declarations: [TipoLicenciaComponent, TipoLicenciaDetailComponent, TipoLicenciaUpdateComponent, TipoLicenciaDeleteDialogComponent],
  entryComponents: [TipoLicenciaDeleteDialogComponent],
})
export class ComparendosTipoLicenciaModule {}
