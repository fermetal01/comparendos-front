import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrganismoTransito, OrganismoTransito } from 'app/shared/model/organismo-transito.model';
import { OrganismoTransitoService } from './organismo-transito.service';

@Component({
  selector: 'jhi-organismo-transito-update',
  templateUrl: './organismo-transito-update.component.html',
})
export class OrganismoTransitoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [],
  });

  constructor(
    protected organismoTransitoService: OrganismoTransitoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organismoTransito }) => {
      this.updateForm(organismoTransito);
    });
  }

  updateForm(organismoTransito: IOrganismoTransito): void {
    this.editForm.patchValue({
      id: organismoTransito.id,
      nombre: organismoTransito.nombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const organismoTransito = this.createFromForm();
    if (organismoTransito.id !== undefined) {
      this.subscribeToSaveResponse(this.organismoTransitoService.update(organismoTransito));
    } else {
      this.subscribeToSaveResponse(this.organismoTransitoService.create(organismoTransito));
    }
  }

  private createFromForm(): IOrganismoTransito {
    return {
      ...new OrganismoTransito(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganismoTransito>>): void {
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
