import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEstadoComparendo, EstadoComparendo } from 'app/shared/model/estado-comparendo.model';
import { EstadoComparendoService } from './estado-comparendo.service';

@Component({
  selector: 'jhi-estado-comparendo-update',
  templateUrl: './estado-comparendo-update.component.html',
})
export class EstadoComparendoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tipo: [],
  });

  constructor(
    protected estadoComparendoService: EstadoComparendoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadoComparendo }) => {
      this.updateForm(estadoComparendo);
    });
  }

  updateForm(estadoComparendo: IEstadoComparendo): void {
    this.editForm.patchValue({
      id: estadoComparendo.id,
      tipo: estadoComparendo.tipo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estadoComparendo = this.createFromForm();
    if (estadoComparendo.id !== undefined) {
      this.subscribeToSaveResponse(this.estadoComparendoService.update(estadoComparendo));
    } else {
      this.subscribeToSaveResponse(this.estadoComparendoService.create(estadoComparendo));
    }
  }

  private createFromForm(): IEstadoComparendo {
    return {
      ...new EstadoComparendo(),
      id: this.editForm.get(['id'])!.value,
      tipo: this.editForm.get(['tipo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadoComparendo>>): void {
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
