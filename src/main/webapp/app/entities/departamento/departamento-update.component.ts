import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDepartamento, Departamento } from 'app/shared/model/departamento.model';
import { DepartamentoService } from './departamento.service';

@Component({
  selector: 'jhi-departamento-update',
  templateUrl: './departamento-update.component.html',
})
export class DepartamentoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [],
  });

  constructor(protected departamentoService: DepartamentoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ departamento }) => {
      this.updateForm(departamento);
    });
  }

  updateForm(departamento: IDepartamento): void {
    this.editForm.patchValue({
      id: departamento.id,
      nombre: departamento.nombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const departamento = this.createFromForm();
    if (departamento.id !== undefined) {
      this.subscribeToSaveResponse(this.departamentoService.update(departamento));
    } else {
      this.subscribeToSaveResponse(this.departamentoService.create(departamento));
    }
  }

  private createFromForm(): IDepartamento {
    return {
      ...new Departamento(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepartamento>>): void {
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
