import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IServicio, Servicio } from 'app/shared/model/servicio.model';
import { ServicioService } from './servicio.service';

@Component({
  selector: 'jhi-servicio-update',
  templateUrl: './servicio-update.component.html',
})
export class ServicioUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tipo: [],
  });

  constructor(protected servicioService: ServicioService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ servicio }) => {
      this.updateForm(servicio);
    });
  }

  updateForm(servicio: IServicio): void {
    this.editForm.patchValue({
      id: servicio.id,
      tipo: servicio.tipo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const servicio = this.createFromForm();
    if (servicio.id !== undefined) {
      this.subscribeToSaveResponse(this.servicioService.update(servicio));
    } else {
      this.subscribeToSaveResponse(this.servicioService.create(servicio));
    }
  }

  private createFromForm(): IServicio {
    return {
      ...new Servicio(),
      id: this.editForm.get(['id'])!.value,
      tipo: this.editForm.get(['tipo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServicio>>): void {
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
