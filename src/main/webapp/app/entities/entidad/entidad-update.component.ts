import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEntidad, Entidad } from 'app/shared/model/entidad.model';
import { EntidadService } from './entidad.service';
import { ICiudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from 'app/entities/ciudad/ciudad.service';

@Component({
  selector: 'jhi-entidad-update',
  templateUrl: './entidad-update.component.html',
})
export class EntidadUpdateComponent implements OnInit {
  isSaving = false;
  ciudads: ICiudad[] = [];

  editForm = this.fb.group({
    id: [],
    nombre: [],
    direccion: [],
    telefono: [],
    celular: [],
    email: [],
    ciudadId: [],
  });

  constructor(
    protected entidadService: EntidadService,
    protected ciudadService: CiudadService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entidad }) => {
      this.updateForm(entidad);

      this.ciudadService.query().subscribe((res: HttpResponse<ICiudad[]>) => (this.ciudads = res.body || []));
    });
  }

  updateForm(entidad: IEntidad): void {
    this.editForm.patchValue({
      id: entidad.id,
      nombre: entidad.nombre,
      direccion: entidad.direccion,
      telefono: entidad.telefono,
      celular: entidad.celular,
      email: entidad.email,
      ciudadId: entidad.ciudadId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entidad = this.createFromForm();
    if (entidad.id !== undefined) {
      this.subscribeToSaveResponse(this.entidadService.update(entidad));
    } else {
      this.subscribeToSaveResponse(this.entidadService.create(entidad));
    }
  }

  private createFromForm(): IEntidad {
    return {
      ...new Entidad(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      direccion: this.editForm.get(['direccion'])!.value,
      telefono: this.editForm.get(['telefono'])!.value,
      celular: this.editForm.get(['celular'])!.value,
      email: this.editForm.get(['email'])!.value,
      ciudadId: this.editForm.get(['ciudadId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntidad>>): void {
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

  trackById(index: number, item: ICiudad): any {
    return item.id;
  }
}
