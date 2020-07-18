import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { EstadoComparendoComponent } from './estado-comparendo.component';
import { EstadoComparendoDetailComponent } from './estado-comparendo-detail.component';
import { EstadoComparendoUpdateComponent } from './estado-comparendo-update.component';
import { EstadoComparendoDeleteDialogComponent } from './estado-comparendo-delete-dialog.component';
import { estadoComparendoRoute } from './estado-comparendo.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(estadoComparendoRoute)],
  declarations: [
    EstadoComparendoComponent,
    EstadoComparendoDetailComponent,
    EstadoComparendoUpdateComponent,
    EstadoComparendoDeleteDialogComponent,
  ],
  entryComponents: [EstadoComparendoDeleteDialogComponent],
})
export class ComparendosEstadoComparendoModule {}
