import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILicencia, Licencia } from 'app/shared/model/licencia.model';
import { LicenciaService } from './licencia.service';
import { IRestriccion } from 'app/shared/model/restriccion.model';
import { RestriccionService } from 'app/entities/restriccion/restriccion.service';
import { ITipoLicencia } from 'app/shared/model/tipo-licencia.model';
import { TipoLicenciaService } from 'app/entities/tipo-licencia/tipo-licencia.service';
import { ICategoria } from 'app/shared/model/categoria.model';
import { CategoriaService } from 'app/entities/categoria/categoria.service';
import { IServicio } from 'app/shared/model/servicio.model';
import { ServicioService } from 'app/entities/servicio/servicio.service';
import { IOrganismoTransito } from 'app/shared/model/organismo-transito.model';
import { OrganismoTransitoService } from 'app/entities/organismo-transito/organismo-transito.service';

type SelectableEntity = IRestriccion | ITipoLicencia | ICategoria | IServicio | IOrganismoTransito;

@Component({
  selector: 'jhi-licencia-update',
  templateUrl: './licencia-update.component.html',
})
export class LicenciaUpdateComponent implements OnInit {
  isSaving = false;
  restriccions: IRestriccion[] = [];
  tipolicencias: ITipoLicencia[] = [];
  categorias: ICategoria[] = [];
  servicios: IServicio[] = [];
  organismotransitos: IOrganismoTransito[] = [];
  fechaExpedicionDp: any;
  vigenciaDp: any;

  editForm = this.fb.group({
    id: [],
    fechaExpedicion: [],
    vigencia: [],
    serial: [],
    restriccions: [null, Validators.required],
    tipoLicenciaId: [],
    categoriaId: [],
    servicioId: [],
    organismoTransitoId: [],
  });

  constructor(
    protected licenciaService: LicenciaService,
    protected restriccionService: RestriccionService,
    protected tipoLicenciaService: TipoLicenciaService,
    protected categoriaService: CategoriaService,
    protected servicioService: ServicioService,
    protected organismoTransitoService: OrganismoTransitoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ licencia }) => {
      this.updateForm(licencia);

      this.restriccionService.query().subscribe((res: HttpResponse<IRestriccion[]>) => (this.restriccions = res.body || []));

      this.tipoLicenciaService.query().subscribe((res: HttpResponse<ITipoLicencia[]>) => (this.tipolicencias = res.body || []));

      this.categoriaService.query().subscribe((res: HttpResponse<ICategoria[]>) => (this.categorias = res.body || []));

      this.servicioService.query().subscribe((res: HttpResponse<IServicio[]>) => (this.servicios = res.body || []));

      this.organismoTransitoService
        .query()
        .subscribe((res: HttpResponse<IOrganismoTransito[]>) => (this.organismotransitos = res.body || []));
    });
  }

  updateForm(licencia: ILicencia): void {
    this.editForm.patchValue({
      id: licencia.id,
      fechaExpedicion: licencia.fechaExpedicion,
      vigencia: licencia.vigencia,
      serial: licencia.serial,
      restriccions: licencia.restriccions,
      tipoLicenciaId: licencia.tipoLicenciaId,
      categoriaId: licencia.categoriaId,
      servicioId: licencia.servicioId,
      organismoTransitoId: licencia.organismoTransitoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const licencia = this.createFromForm();
    if (licencia.id !== undefined) {
      this.subscribeToSaveResponse(this.licenciaService.update(licencia));
    } else {
      this.subscribeToSaveResponse(this.licenciaService.create(licencia));
    }
  }

  private createFromForm(): ILicencia {
    return {
      ...new Licencia(),
      id: this.editForm.get(['id'])!.value,
      fechaExpedicion: this.editForm.get(['fechaExpedicion'])!.value,
      vigencia: this.editForm.get(['vigencia'])!.value,
      serial: this.editForm.get(['serial'])!.value,
      restriccions: this.editForm.get(['restriccions'])!.value,
      tipoLicenciaId: this.editForm.get(['tipoLicenciaId'])!.value,
      categoriaId: this.editForm.get(['categoriaId'])!.value,
      servicioId: this.editForm.get(['servicioId'])!.value,
      organismoTransitoId: this.editForm.get(['organismoTransitoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILicencia>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IRestriccion[], option: IRestriccion): IRestriccion {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
