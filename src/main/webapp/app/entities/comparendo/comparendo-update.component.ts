import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IComparendo, Comparendo } from 'app/shared/model/comparendo.model';
import { ComparendoService } from './comparendo.service';
import { IInfraccion } from 'app/shared/model/infraccion.model';
import { InfraccionService } from 'app/entities/infraccion/infraccion.service';
import { IEstadoComparendo } from 'app/shared/model/estado-comparendo.model';
import { EstadoComparendoService } from 'app/entities/estado-comparendo/estado-comparendo.service';
import { IVehiculo } from 'app/shared/model/vehiculo.model';
import { VehiculoService } from 'app/entities/vehiculo/vehiculo.service';
import { ILicencia } from 'app/shared/model/licencia.model';
import { LicenciaService } from 'app/entities/licencia/licencia.service';
import { IAgente } from 'app/shared/model/agente.model';
import { AgenteService } from 'app/entities/agente/agente.service';
import { ICiudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from 'app/entities/ciudad/ciudad.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';
import { IPatio } from 'app/shared/model/patio.model';
import { PatioService } from 'app/entities/patio/patio.service';
import { IGrua } from 'app/shared/model/grua.model';
import { GruaService } from 'app/entities/grua/grua.service';
import { IEntidad } from 'app/shared/model/entidad.model';
import { EntidadService } from 'app/entities/entidad/entidad.service';

type SelectableEntity = IInfraccion | IEstadoComparendo | IVehiculo | ILicencia | IAgente | ICiudad | IPersona | IPatio | IGrua | IEntidad;

@Component({
  selector: 'jhi-comparendo-update',
  templateUrl: './comparendo-update.component.html',
})
export class ComparendoUpdateComponent implements OnInit {
  isSaving = false;
  infraccions: IInfraccion[] = [];
  estadocomparendos: IEstadoComparendo[] = [];
  vehiculos: IVehiculo[] = [];
  licencias: ILicencia[] = [];
  agentes: IAgente[] = [];
  ciudads: ICiudad[] = [];
  personas: IPersona[] = [];
  patios: IPatio[] = [];
  gruas: IGrua[] = [];
  entidads: IEntidad[] = [];

  editForm = this.fb.group({
    id: [],
    fechaHora: [],
    direccion: [],
    observaciones: [],
    codigoInmovilizacion: [],
    infracciones: [null, Validators.required],
    estadoId: [],
    vehiculoId: [],
    licenciaTransitoId: [],
    licenciaConduccionId: [],
    agenteId: [],
    ciudadId: [],
    infractorId: [],
    testigoId: [],
    patioId: [],
    gruaId: [],
    entidadId: [],
  });

  constructor(
    protected comparendoService: ComparendoService,
    protected infraccionService: InfraccionService,
    protected estadoComparendoService: EstadoComparendoService,
    protected vehiculoService: VehiculoService,
    protected licenciaService: LicenciaService,
    protected agenteService: AgenteService,
    protected ciudadService: CiudadService,
    protected personaService: PersonaService,
    protected patioService: PatioService,
    protected gruaService: GruaService,
    protected entidadService: EntidadService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comparendo }) => {
      if (!comparendo.id) {
        const today = moment().startOf('day');
        comparendo.fechaHora = today;
      }

      this.updateForm(comparendo);

      this.infraccionService.query().subscribe((res: HttpResponse<IInfraccion[]>) => (this.infraccions = res.body || []));

      this.estadoComparendoService.query().subscribe((res: HttpResponse<IEstadoComparendo[]>) => (this.estadocomparendos = res.body || []));

      this.vehiculoService.query().subscribe((res: HttpResponse<IVehiculo[]>) => (this.vehiculos = res.body || []));

      this.licenciaService.query().subscribe((res: HttpResponse<ILicencia[]>) => (this.licencias = res.body || []));

      this.agenteService.query().subscribe((res: HttpResponse<IAgente[]>) => (this.agentes = res.body || []));

      this.ciudadService.query().subscribe((res: HttpResponse<ICiudad[]>) => (this.ciudads = res.body || []));

      this.personaService.query().subscribe((res: HttpResponse<IPersona[]>) => (this.personas = res.body || []));

      this.patioService.query().subscribe((res: HttpResponse<IPatio[]>) => (this.patios = res.body || []));

      this.gruaService.query().subscribe((res: HttpResponse<IGrua[]>) => (this.gruas = res.body || []));

      this.entidadService.query().subscribe((res: HttpResponse<IEntidad[]>) => (this.entidads = res.body || []));
    });
  }

  updateForm(comparendo: IComparendo): void {
    this.editForm.patchValue({
      id: comparendo.id,
      fechaHora: comparendo.fechaHora ? comparendo.fechaHora.format(DATE_TIME_FORMAT) : null,
      direccion: comparendo.direccion,
      observaciones: comparendo.observaciones,
      codigoInmovilizacion: comparendo.codigoInmovilizacion,
      infracciones: comparendo.infracciones,
      estadoId: comparendo.estadoId,
      vehiculoId: comparendo.vehiculoId,
      licenciaTransitoId: comparendo.licenciaTransitoId,
      licenciaConduccionId: comparendo.licenciaConduccionId,
      agenteId: comparendo.agenteId,
      ciudadId: comparendo.ciudadId,
      infractorId: comparendo.infractorId,
      testigoId: comparendo.testigoId,
      patioId: comparendo.patioId,
      gruaId: comparendo.gruaId,
      entidadId: comparendo.entidadId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const comparendo = this.createFromForm();
    if (comparendo.id !== undefined) {
      this.subscribeToSaveResponse(this.comparendoService.update(comparendo));
    } else {
      this.subscribeToSaveResponse(this.comparendoService.create(comparendo));
    }
  }

  private createFromForm(): IComparendo {
    return {
      ...new Comparendo(),
      id: this.editForm.get(['id'])!.value,
      fechaHora: this.editForm.get(['fechaHora'])!.value ? moment(this.editForm.get(['fechaHora'])!.value, DATE_TIME_FORMAT) : undefined,
      direccion: this.editForm.get(['direccion'])!.value,
      observaciones: this.editForm.get(['observaciones'])!.value,
      codigoInmovilizacion: this.editForm.get(['codigoInmovilizacion'])!.value,
      infracciones: this.editForm.get(['infracciones'])!.value,
      estadoId: this.editForm.get(['estadoId'])!.value,
      vehiculoId: this.editForm.get(['vehiculoId'])!.value,
      licenciaTransitoId: this.editForm.get(['licenciaTransitoId'])!.value,
      licenciaConduccionId: this.editForm.get(['licenciaConduccionId'])!.value,
      agenteId: this.editForm.get(['agenteId'])!.value,
      ciudadId: this.editForm.get(['ciudadId'])!.value,
      infractorId: this.editForm.get(['infractorId'])!.value,
      testigoId: this.editForm.get(['testigoId'])!.value,
      patioId: this.editForm.get(['patioId'])!.value,
      gruaId: this.editForm.get(['gruaId'])!.value,
      entidadId: this.editForm.get(['entidadId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComparendo>>): void {
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

  getSelected(selectedVals: IInfraccion[], option: IInfraccion): IInfraccion {
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
