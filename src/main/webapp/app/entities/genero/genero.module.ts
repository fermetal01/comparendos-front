import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComparendosSharedModule } from 'app/shared/shared.module';
import { GeneroComponent } from './genero.component';
import { GeneroDetailComponent } from './genero-detail.component';
import { GeneroUpdateComponent } from './genero-update.component';
import { GeneroDeleteDialogComponent } from './genero-delete-dialog.component';
import { generoRoute } from './genero.route';

@NgModule({
  imports: [ComparendosSharedModule, RouterModule.forChild(generoRoute)],
  declarations: [GeneroComponent, GeneroDetailComponent, GeneroUpdateComponent, GeneroDeleteDialogComponent],
  entryComponents: [GeneroDeleteDialogComponent],
})
export class ComparendosGeneroModule {}
