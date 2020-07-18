import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { VehiculoComponent } from './vehiculo.component';
import { VehiculoDetailComponent } from './vehiculo-detail.component';
import { VehiculoUpdateComponent } from './vehiculo-update.component';
import { VehiculoDeleteDialogComponent } from './vehiculo-delete-dialog.component';
import { vehiculoRoute } from './vehiculo.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(vehiculoRoute)],
  declarations: [VehiculoComponent, VehiculoDetailComponent, VehiculoUpdateComponent, VehiculoDeleteDialogComponent],
  entryComponents: [VehiculoDeleteDialogComponent],
})
export class ComparendosVehiculoModule {}
