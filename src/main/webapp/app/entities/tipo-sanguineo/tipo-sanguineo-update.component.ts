import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITipoSanguineo, TipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';
import { TipoSanguineoService } from './tipo-sanguineo.service';

@Component({
  selector: 'jhi-tipo-sanguineo-update',
  templateUrl: './tipo-sanguineo-update.component.html',
})
export class TipoSanguineoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tipo: [],
  });

  constructor(protected tipoSanguineoService: TipoSanguineoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoSanguineo }) => {
      this.updateForm(tipoSanguineo);
    });
  }

  updateForm(tipoSanguineo: ITipoSanguineo): void {
    this.editForm.patchValue({
      id: tipoSanguineo.id,
      tipo: tipoSanguineo.tipo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tipoSanguineo = this.createFromForm();
    if (tipoSanguineo.id !== undefined) {
      this.subscribeToSaveResponse(this.tipoSanguineoService.update(tipoSanguineo));
    } else {
      this.subscribeToSaveResponse(this.tipoSanguineoService.create(tipoSanguineo));
    }
  }

  private createFromForm(): ITipoSanguineo {
    return {
      ...new TipoSanguineo(),
      id: this.editForm.get(['id'])!.value,
      tipo: this.editForm.get(['tipo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoSanguineo>>): void {
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
