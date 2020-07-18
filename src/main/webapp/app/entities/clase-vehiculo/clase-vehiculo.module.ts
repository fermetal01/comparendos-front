import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { ClaseVehiculoComponent } from './clase-vehiculo.component';
import { ClaseVehiculoDetailComponent } from './clase-vehiculo-detail.component';
import { ClaseVehiculoUpdateComponent } from './clase-vehiculo-update.component';
import { ClaseVehiculoDeleteDialogComponent } from './clase-vehiculo-delete-dialog.component';
import { claseVehiculoRoute } from './clase-vehiculo.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(claseVehiculoRoute)],
  declarations: [ClaseVehiculoComponent, ClaseVehiculoDetailComponent, ClaseVehiculoUpdateComponent, ClaseVehiculoDeleteDialogComponent],
  entryComponents: [ClaseVehiculoDeleteDialogComponent],
})
export class ComparendosClaseVehiculoModule {}
