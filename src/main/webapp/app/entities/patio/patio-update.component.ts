import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPatio, Patio } from 'app/shared/model/patio.model';
import { PatioService } from './patio.service';

@Component({
  selector: 'jhi-patio-update',
  templateUrl: './patio-update.component.html',
})
export class PatioUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [],
    numero: [],
    direccion: [],
  });

  constructor(protected patioService: PatioService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patio }) => {
      this.updateForm(patio);
    });
  }

  updateForm(patio: IPatio): void {
    this.editForm.patchValue({
      id: patio.id,
      nombre: patio.nombre,
      numero: patio.numero,
      direccion: patio.direccion,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const patio = this.createFromForm();
    if (patio.id !== undefined) {
      this.subscribeToSaveResponse(this.patioService.update(patio));
    } else {
      this.subscribeToSaveResponse(this.patioService.create(patio));
    }
  }

  private createFromForm(): IPatio {
    return {
      ...new Patio(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      direccion: this.editForm.get(['direccion'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPatio>>): void {
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
