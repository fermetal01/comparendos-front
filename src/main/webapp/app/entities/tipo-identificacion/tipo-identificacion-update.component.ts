import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITipoIdentificacion, TipoIdentificacion } from 'app/shared/model/tipo-identificacion.model';
import { TipoIdentificacionService } from './tipo-identificacion.service';

@Component({
  selector: 'jhi-tipo-identificacion-update',
  templateUrl: './tipo-identificacion-update.component.html',
})
export class TipoIdentificacionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [],
  });

  constructor(
    protected tipoIdentificacionService: TipoIdentificacionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoIdentificacion }) => {
      this.updateForm(tipoIdentificacion);
    });
  }

  updateForm(tipoIdentificacion: ITipoIdentificacion): void {
    this.editForm.patchValue({
      id: tipoIdentificacion.id,
      nombre: tipoIdentificacion.nombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tipoIdentificacion = this.createFromForm();
    if (tipoIdentificacion.id !== undefined) {
      this.subscribeToSaveResponse(this.tipoIdentificacionService.update(tipoIdentificacion));
    } else {
      this.subscribeToSaveResponse(this.tipoIdentificacionService.create(tipoIdentificacion));
    }
  }

  private createFromForm(): ITipoIdentificacion {
    return {
      ...new TipoIdentificacion(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoIdentificacion>>): void {
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
}
