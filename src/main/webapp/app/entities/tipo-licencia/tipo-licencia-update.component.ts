import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITipoLicencia, TipoLicencia } from 'app/shared/model/tipo-licencia.model';
import { TipoLicenciaService } from './tipo-licencia.service';

@Component({
  selector: 'jhi-tipo-licencia-update',
  templateUrl: './tipo-licencia-update.component.html',
})
export class TipoLicenciaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tipo: [],
  });

  constructor(protected tipoLicenciaService: TipoLicenciaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoLicencia }) => {
      this.updateForm(tipoLicencia);
    });
  }

  updateForm(tipoLicencia: ITipoLicencia): void {
    this.editForm.patchValue({
      id: tipoLicencia.id,
      tipo: tipoLicencia.tipo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tipoLicencia = this.createFromForm();
    if (tipoLicencia.id !== undefined) {
      this.subscribeToSaveResponse(this.tipoLicenciaService.update(tipoLicencia));
    } else {
      this.subscribeToSaveResponse(this.tipoLicenciaService.create(tipoLicencia));
    }
  }

  private createFromForm(): ITipoLicencia {
    return {
      ...new TipoLicencia(),
      id: this.editForm.get(['id'])!.value,
      tipo: this.editForm.get(['tipo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoLicencia>>): void {
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
