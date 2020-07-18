import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPersona, Persona } from 'app/shared/model/persona.model';
import { PersonaService } from './persona.service';
import { ICiudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from 'app/entities/ciudad/ciudad.service';
import { IGenero } from 'app/shared/model/genero.model';
import { GeneroService } from 'app/entities/genero/genero.service';
import { ITipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';
import { TipoSanguineoService } from 'app/entities/tipo-sanguineo/tipo-sanguineo.service';
import { ITipoIdentificacion } from 'app/shared/model/tipo-identificacion.model';
import { TipoIdentificacionService } from 'app/entities/tipo-identificacion/tipo-identificacion.service';
import { ILicencia } from 'app/shared/model/licencia.model';
import { LicenciaService } from 'app/entities/licencia/licencia.service';

type SelectableEntity = ICiudad | IGenero | ITipoSanguineo | ITipoIdentificacion | ILicencia;

@Component({
  selector: 'jhi-persona-update',
  templateUrl: './persona-update.component.html',
})
export class PersonaUpdateComponent implements OnInit {
  isSaving = false;
  ciudads: ICiudad[] = [];
  generos: IGenero[] = [];
  tiposanguineos: ITipoSanguineo[] = [];
  tipoidentificacions: ITipoIdentificacion[] = [];
  licencias: ILicencia[] = [];
  fechaNacimientoDp: any;

  editForm = this.fb.group({
    id: [],
    nombres: [],
    apellidos: [],
    fechaNacimiento: [],
    numeroId: [],
    direccion: [],
    telefono: [],
    celular: [],
    email: [],
    ciudadId: [],
    generoId: [],
    tipoSanguineoId: [],
    tipoIdId: [],
    licenciaId: [],
  });

  constructor(
    protected personaService: PersonaService,
    protected ciudadService: CiudadService,
    protected generoService: GeneroService,
    protected tipoSanguineoService: TipoSanguineoService,
    protected tipoIdentificacionService: TipoIdentificacionService,
    protected licenciaService: LicenciaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ persona }) => {
      this.updateForm(persona);

      this.ciudadService.query().subscribe((res: HttpResponse<ICiudad[]>) => (this.ciudads = res.body || []));

      this.generoService.query().subscribe((res: HttpResponse<IGenero[]>) => (this.generos = res.body || []));

      this.tipoSanguineoService.query().subscribe((res: HttpResponse<ITipoSanguineo[]>) => (this.tiposanguineos = res.body || []));

      this.tipoIdentificacionService
        .query()
        .subscribe((res: HttpResponse<ITipoIdentificacion[]>) => (this.tipoidentificacions = res.body || []));

      this.licenciaService.query().subscribe((res: HttpResponse<ILicencia[]>) => (this.licencias = res.body || []));
    });
  }

  updateForm(persona: IPersona): void {
    this.editForm.patchValue({
      id: persona.id,
      nombres: persona.nombres,
      apellidos: persona.apellidos,
      fechaNacimiento: persona.fechaNacimiento,
      numeroId: persona.numeroId,
      direccion: persona.direccion,
      telefono: persona.telefono,
      celular: persona.celular,
      email: persona.email,
      ciudadId: persona.ciudadId,
      generoId: persona.generoId,
      tipoSanguineoId: persona.tipoSanguineoId,
      tipoIdId: persona.tipoIdId,
      licenciaId: persona.licenciaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const persona = this.createFromForm();
    if (persona.id !== undefined) {
      this.subscribeToSaveResponse(this.personaService.update(persona));
    } else {
      this.subscribeToSaveResponse(this.personaService.create(persona));
    }
  }

  private createFromForm(): IPersona {
    return {
      ...new Persona(),
      id: this.editForm.get(['id'])!.value,
      nombres: this.editForm.get(['nombres'])!.value,
      apellidos: this.editForm.get(['apellidos'])!.value,
      fechaNacimiento: this.editForm.get(['fechaNacimiento'])!.value,
      numeroId: this.editForm.get(['numeroId'])!.value,
      direccion: this.editForm.get(['direccion'])!.value,
      telefono: this.editForm.get(['telefono'])!.value,
      celular: this.editForm.get(['celular'])!.value,
      email: this.editForm.get(['email'])!.value,
      ciudadId: this.editForm.get(['ciudadId'])!.value,
      generoId: this.editForm.get(['generoId'])!.value,
      tipoSanguineoId: this.editForm.get(['tipoSanguineoId'])!.value,
      tipoIdId: this.editForm.get(['tipoIdId'])!.value,
      licenciaId: this.editForm.get(['licenciaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersona>>): void {
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
}
