import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICombustible, Combustible } from 'app/shared/model/combustible.model';
import { CombustibleService } from './combustible.service';

@Component({
  selector: 'jhi-combustible-update',
  templateUrl: './combustible-update.component.html',
})
export class CombustibleUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [],
  });

  constructor(protected combustibleService: CombustibleService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ combustible }) => {
      this.updateForm(combustible);
    });
  }

  updateForm(combustible: ICombustible): void {
    this.editForm.patchValue({
      id: combustible.id,
      nombre: combustible.nombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const combustible = this.createFromForm();
    if (combustible.id !== undefined) {
      this.subscribeToSaveResponse(this.combustibleService.update(combustible));
    } else {
      this.subscribeToSaveResponse(this.combustibleService.create(combustible));
    }
  }

  private createFromForm(): ICombustible {
    return {
      ...new Combustible(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICombustible>>): void {
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
