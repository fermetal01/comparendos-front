import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGrua, Grua } from 'app/shared/model/grua.model';
import { GruaService } from './grua.service';
import { IVehiculo } from 'app/shared/model/vehiculo.model';
import { VehiculoService } from 'app/entities/vehiculo/vehiculo.service';

@Component({
  selector: 'jhi-grua-update',
  templateUrl: './grua-update.component.html',
})
export class GruaUpdateComponent implements OnInit {
  isSaving = false;
  vehiculos: IVehiculo[] = [];

  editForm = this.fb.group({
    id: [],
    codigo: [],
    vehiculoId: [],
  });

  constructor(
    protected gruaService: GruaService,
    protected vehiculoService: VehiculoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ grua }) => {
      this.updateForm(grua);

      this.vehiculoService.query().subscribe((res: HttpResponse<IVehiculo[]>) => (this.vehiculos = res.body || []));
    });
  }

  updateForm(grua: IGrua): void {
    this.editForm.patchValue({
      id: grua.id,
      codigo: grua.codigo,
      vehiculoId: grua.vehiculoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const grua = this.createFromForm();
    if (grua.id !== undefined) {
      this.subscribeToSaveResponse(this.gruaService.update(grua));
    } else {
      this.subscribeToSaveResponse(this.gruaService.create(grua));
    }
  }

  private createFromForm(): IGrua {
    return {
      ...new Grua(),
      id: this.editForm.get(['id'])!.value,
      codigo: this.editForm.get(['codigo'])!.value,
      vehiculoId: this.editForm.get(['vehiculoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGrua>>): void {
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

  trackById(index: number, item: IVehiculo): any {
    return item.id;
  }
}
