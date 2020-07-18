import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { ServicioComponent } from './servicio.component';
import { ServicioDetailComponent } from './servicio-detail.component';
import { ServicioUpdateComponent } from './servicio-update.component';
import { ServicioDeleteDialogComponent } from './servicio-delete-dialog.component';
import { servicioRoute } from './servicio.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(servicioRoute)],
  declarations: [ServicioComponent, ServicioDetailComponent, ServicioUpdateComponent, ServicioDeleteDialogComponent],
  entryComponents: [ServicioDeleteDialogComponent],
})
export class ComparendosServicioModule {}
