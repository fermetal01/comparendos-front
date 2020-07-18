import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRestriccion, Restriccion } from 'app/shared/model/restriccion.model';
import { RestriccionService } from './restriccion.service';

@Component({
  selector: 'jhi-restriccion-update',
  templateUrl: './restriccion-update.component.html',
})
export class RestriccionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tipo: [],
  });

  constructor(protected restriccionService: RestriccionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ restriccion }) => {
      this.updateForm(restriccion);
    });
  }

  updateForm(restriccion: IRestriccion): void {
    this.editForm.patchValue({
      id: restriccion.id,
      tipo: restriccion.tipo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const restriccion = this.createFromForm();
    if (restriccion.id !== undefined) {
      this.subscribeToSaveResponse(this.restriccionService.update(restriccion));
    } else {
      this.subscribeToSaveResponse(this.restriccionService.create(restriccion));
    }
  }

  private createFromForm(): IRestriccion {
    return {
      ...new Restriccion(),
      id: this.editForm.get(['id'])!.value,
      tipo: this.editForm.get(['tipo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRestriccion>>): void {
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
