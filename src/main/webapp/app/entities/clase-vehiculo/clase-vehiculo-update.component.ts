import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IClaseVehiculo, ClaseVehiculo } from 'app/shared/model/clase-vehiculo.model';
import { ClaseVehiculoService } from './clase-vehiculo.service';

@Component({
  selector: 'jhi-clase-vehiculo-update',
  templateUrl: './clase-vehiculo-update.component.html',
})
export class ClaseVehiculoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [],
  });

  constructor(protected claseVehiculoService: ClaseVehiculoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claseVehiculo }) => {
      this.updateForm(claseVehiculo);
    });
  }

  updateForm(claseVehiculo: IClaseVehiculo): void {
    this.editForm.patchValue({
      id: claseVehiculo.id,
      nombre: claseVehiculo.nombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const claseVehiculo = this.createFromForm();
    if (claseVehiculo.id !== undefined) {
      this.subscribeToSaveResponse(this.claseVehiculoService.update(claseVehiculo));
    } else {
      this.subscribeToSaveResponse(this.claseVehiculoService.create(claseVehiculo));
    }
  }

  private createFromForm(): IClaseVehiculo {
    return {
      ...new ClaseVehiculo(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClaseVehiculo>>): void {
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
