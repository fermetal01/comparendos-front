import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGenero, Genero } from 'app/shared/model/genero.model';
import { GeneroService } from './genero.service';

@Component({
  selector: 'jhi-genero-update',
  templateUrl: './genero-update.component.html',
})
export class GeneroUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [],
  });

  constructor(protected generoService: GeneroService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ genero }) => {
      this.updateForm(genero);
    });
  }

  updateForm(genero: IGenero): void {
    this.editForm.patchValue({
      id: genero.id,
      nombre: genero.nombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const genero = this.createFromForm();
    if (genero.id !== undefined) {
      this.subscribeToSaveResponse(this.generoService.update(genero));
    } else {
      this.subscribeToSaveResponse(this.generoService.create(genero));
    }
  }

  private createFromForm(): IGenero {
    return {
      ...new Genero(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGenero>>): void {
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
