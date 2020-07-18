import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMarca, Marca } from 'app/shared/model/marca.model';
import { MarcaService } from './marca.service';

@Component({
  selector: 'jhi-marca-update',
  templateUrl: './marca-update.component.html',
})
export class MarcaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [],
  });

  constructor(protected marcaService: MarcaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ marca }) => {
      this.updateForm(marca);
    });
  }

  updateForm(marca: IMarca): void {
    this.editForm.patchValue({
      id: marca.id,
      nombre: marca.nombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const marca = this.createFromForm();
    if (marca.id !== undefined) {
      this.subscribeToSaveResponse(this.marcaService.update(marca));
    } else {
      this.subscribeToSaveResponse(this.marcaService.create(marca));
    }
  }

  private createFromForm(): IMarca {
    return {
      ...new Marca(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMarca>>): void {
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
