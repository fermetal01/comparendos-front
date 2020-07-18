import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRango, Rango } from 'app/shared/model/rango.model';
import { RangoService } from './rango.service';

@Component({
  selector: 'jhi-rango-update',
  templateUrl: './rango-update.component.html',
})
export class RangoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [],
    acronimo: [],
  });

  constructor(protected rangoService: RangoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rango }) => {
      this.updateForm(rango);
    });
  }

  updateForm(rango: IRango): void {
    this.editForm.patchValue({
      id: rango.id,
      nombre: rango.nombre,
      acronimo: rango.acronimo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rango = this.createFromForm();
    if (rango.id !== undefined) {
      this.subscribeToSaveResponse(this.rangoService.update(rango));
    } else {
      this.subscribeToSaveResponse(this.rangoService.create(rango));
    }
  }

  private createFromForm(): IRango {
    return {
      ...new Rango(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      acronimo: this.editForm.get(['acronimo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRango>>): void {
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
