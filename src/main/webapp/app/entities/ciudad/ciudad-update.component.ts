import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICiudad, Ciudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from './ciudad.service';
import { IDepartamento } from 'app/shared/model/departamento.model';
import { DepartamentoService } from 'app/entities/departamento/departamento.service';

@Component({
  selector: 'jhi-ciudad-update',
  templateUrl: './ciudad-update.component.html',
})
export class CiudadUpdateComponent implements OnInit {
  isSaving = false;
  departamentos: IDepartamento[] = [];

  editForm = this.fb.group({
    id: [],
    nombre: [],
    departamentoId: [],
  });

  constructor(
    protected ciudadService: CiudadService,
    protected departamentoService: DepartamentoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ciudad }) => {
      this.updateForm(ciudad);

      this.departamentoService.query().subscribe((res: HttpResponse<IDepartamento[]>) => (this.departamentos = res.body || []));
    });
  }

  updateForm(ciudad: ICiudad): void {
    this.editForm.patchValue({
      id: ciudad.id,
      nombre: ciudad.nombre,
      departamentoId: ciudad.departamentoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ciudad = this.createFromForm();
    if (ciudad.id !== undefined) {
      this.subscribeToSaveResponse(this.ciudadService.update(ciudad));
    } else {
      this.subscribeToSaveResponse(this.ciudadService.create(ciudad));
    }
  }

  private createFromForm(): ICiudad {
    return {
      ...new Ciudad(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      departamentoId: this.editForm.get(['departamentoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICiudad>>): void {
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

  trackById(index: number, item: IDepartamento): any {
    return item.id;
  }
}
