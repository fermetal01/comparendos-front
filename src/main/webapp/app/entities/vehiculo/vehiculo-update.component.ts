import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVehiculo, Vehiculo } from 'app/shared/model/vehiculo.model';
import { VehiculoService } from './vehiculo.service';
import { IRestriccion } from 'app/shared/model/restriccion.model';
import { RestriccionService } from 'app/entities/restriccion/restriccion.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';
import { IEntidad } from 'app/shared/model/entidad.model';
import { EntidadService } from 'app/entities/entidad/entidad.service';
import { IMarca } from 'app/shared/model/marca.model';
import { MarcaService } from 'app/entities/marca/marca.service';
import { IServicio } from 'app/shared/model/servicio.model';
import { ServicioService } from 'app/entities/servicio/servicio.service';
import { IClaseVehiculo } from 'app/shared/model/clase-vehiculo.model';
import { ClaseVehiculoService } from 'app/entities/clase-vehiculo/clase-vehiculo.service';
import { ICombustible } from 'app/shared/model/combustible.model';
import { CombustibleService } from 'app/entities/combustible/combustible.service';
import { IOrganismoTransito } from 'app/shared/model/organismo-transito.model';
import { OrganismoTransitoService } from 'app/entities/organismo-transito/organismo-transito.service';
import { ILicencia } from 'app/shared/model/licencia.model';
import { LicenciaService } from 'app/entities/licencia/licencia.service';

type SelectableEntity =
  | IRestriccion
  | IPersona
  | IEntidad
  | IMarca
  | IServicio
  | IClaseVehiculo
  | ICombustible
  | IOrganismoTransito
  | ILicencia;

type SelectableManyToManyEntity = IRestriccion | IPersona | IEntidad;

@Component({
  selector: 'jhi-vehiculo-update',
  templateUrl: './vehiculo-update.component.html',
})
export class VehiculoUpdateComponent implements OnInit {
  isSaving = false;
  restriccions: IRestriccion[] = [];
  personas: IPersona[] = [];
  entidads: IEntidad[] = [];
  marcas: IMarca[] = [];
  servicios: IServicio[] = [];
  clasevehiculos: IClaseVehiculo[] = [];
  combustibles: ICombustible[] = [];
  organismotransitos: IOrganismoTransito[] = [];
  licencias: ILicencia[] = [];
  fechaMatriculaDp: any;

  editForm = this.fb.group({
    id: [],
    placa: [],
    linea: [],
    modelo: [],
    cilindraje: [],
    color: [],
    capacidad: [],
    numeroMotor: [],
    vin: [],
    serie: [],
    chasis: [],
    potencia: [],
    fechaMatricula: [],
    restriccions: [null, Validators.required],
    personas: [],
    entidads: [],
    marcaId: [],
    servicioId: [],
    claseId: [],
    combustibleId: [],
    organismoTransitoId: [],
    licenciaTransitoId: [],
    personaId: [],
  });

  constructor(
    protected vehiculoService: VehiculoService,
    protected restriccionService: RestriccionService,
    protected personaService: PersonaService,
    protected entidadService: EntidadService,
    protected marcaService: MarcaService,
    protected servicioService: ServicioService,
    protected claseVehiculoService: ClaseVehiculoService,
    protected combustibleService: CombustibleService,
    protected organismoTransitoService: OrganismoTransitoService,
    protected licenciaService: LicenciaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vehiculo }) => {
      this.updateForm(vehiculo);

      this.restriccionService.query().subscribe((res: HttpResponse<IRestriccion[]>) => (this.restriccions = res.body || []));

      this.personaService.query().subscribe((res: HttpResponse<IPersona[]>) => (this.personas = res.body || []));

      this.entidadService.query().subscribe((res: HttpResponse<IEntidad[]>) => (this.entidads = res.body || []));

      this.marcaService.query().subscribe((res: HttpResponse<IMarca[]>) => (this.marcas = res.body || []));

      this.servicioService.query().subscribe((res: HttpResponse<IServicio[]>) => (this.servicios = res.body || []));

      this.claseVehiculoService.query().subscribe((res: HttpResponse<IClaseVehiculo[]>) => (this.clasevehiculos = res.body || []));

      this.combustibleService.query().subscribe((res: HttpResponse<ICombustible[]>) => (this.combustibles = res.body || []));

      this.organismoTransitoService
        .query()
        .subscribe((res: HttpResponse<IOrganismoTransito[]>) => (this.organismotransitos = res.body || []));

      this.licenciaService.query().subscribe((res: HttpResponse<ILicencia[]>) => (this.licencias = res.body || []));
    });
  }

  updateForm(vehiculo: IVehiculo): void {
    this.editForm.patchValue({
      id: vehiculo.id,
      placa: vehiculo.placa,
      linea: vehiculo.linea,
      modelo: vehiculo.modelo,
      cilindraje: vehiculo.cilindraje,
      color: vehiculo.color,
      capacidad: vehiculo.capacidad,
      numeroMotor: vehiculo.numeroMotor,
      vin: vehiculo.vin,
      serie: vehiculo.serie,
      chasis: vehiculo.chasis,
      potencia: vehiculo.potencia,
      fechaMatricula: vehiculo.fechaMatricula,
      restriccions: vehiculo.restriccions,
      personas: vehiculo.personas,
      entidads: vehiculo.entidads,
      marcaId: vehiculo.marcaId,
      servicioId: vehiculo.servicioId,
      claseId: vehiculo.claseId,
      combustibleId: vehiculo.combustibleId,
      organismoTransitoId: vehiculo.organismoTransitoId,
      licenciaTransitoId: vehiculo.licenciaTransitoId,
      personaId: vehiculo.personaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vehiculo = this.createFromForm();
    if (vehiculo.id !== undefined) {
      this.subscribeToSaveResponse(this.vehiculoService.update(vehiculo));
    } else {
      this.subscribeToSaveResponse(this.vehiculoService.create(vehiculo));
    }
  }

  private createFromForm(): IVehiculo {
    return {
      ...new Vehiculo(),
      id: this.editForm.get(['id'])!.value,
      placa: this.editForm.get(['placa'])!.value,
      linea: this.editForm.get(['linea'])!.value,
      modelo: this.editForm.get(['modelo'])!.value,
      cilindraje: this.editForm.get(['cilindraje'])!.value,
      color: this.editForm.get(['color'])!.value,
      capacidad: this.editForm.get(['capacidad'])!.value,
      numeroMotor: this.editForm.get(['numeroMotor'])!.value,
      vin: this.editForm.get(['vin'])!.value,
      serie: this.editForm.get(['serie'])!.value,
      chasis: this.editForm.get(['chasis'])!.value,
      potencia: this.editForm.get(['potencia'])!.value,
      fechaMatricula: this.editForm.get(['fechaMatricula'])!.value,
      restriccions: this.editForm.get(['restriccions'])!.value,
      personas: this.editForm.get(['personas'])!.value,
      entidads: this.editForm.get(['entidads'])!.value,
      marcaId: this.editForm.get(['marcaId'])!.value,
      servicioId: this.editForm.get(['servicioId'])!.value,
      claseId: this.editForm.get(['claseId'])!.value,
      combustibleId: this.editForm.get(['combustibleId'])!.value,
      organismoTransitoId: this.editForm.get(['organismoTransitoId'])!.value,
      licenciaTransitoId: this.editForm.get(['licenciaTransitoId'])!.value,
      personaId: this.editForm.get(['personaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVehiculo>>): void {
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

  getSelected(selectedVals: SelectableManyToManyEntity[], option: SelectableManyToManyEntity): SelectableManyToManyEntity {
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
