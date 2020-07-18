import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInfraccion, Infraccion } from 'app/shared/model/infraccion.model';
import { InfraccionService } from './infraccion.service';

@Component({
  selector: 'jhi-infraccion-update',
  templateUrl: './infraccion-update.component.html',
})
export class InfraccionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codigo: [],
    descripcion: [],
  });

  constructor(protected infraccionService: InfraccionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ infraccion }) => {
      this.updateForm(infraccion);
    });
  }

  updateForm(infraccion: IInfraccion): void {
    this.editForm.patchValue({
      id: infraccion.id,
      codigo: infraccion.codigo,
      descripcion: infraccion.descripcion,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const infraccion = this.createFromForm();
    if (infraccion.id !== undefined) {
      this.subscribeToSaveResponse(this.infraccionService.update(infraccion));
    } else {
      this.subscribeToSaveResponse(this.infraccionService.create(infraccion));
    }
  }

  private createFromForm(): IInfraccion {
    return {
      ...new Infraccion(),
      id: this.editForm.get(['id'])!.value,
      codigo: this.editForm.get(['codigo'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInfraccion>>): void {
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
