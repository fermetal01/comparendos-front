import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAgente, Agente } from 'app/shared/model/agente.model';
import { AgenteService } from './agente.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';
import { IRango } from 'app/shared/model/rango.model';
import { RangoService } from 'app/entities/rango/rango.service';

type SelectableEntity = IPersona | IRango;

@Component({
  selector: 'jhi-agente-update',
  templateUrl: './agente-update.component.html',
})
export class AgenteUpdateComponent implements OnInit {
  isSaving = false;
  personas: IPersona[] = [];
  rangos: IRango[] = [];

  editForm = this.fb.group({
    id: [],
    placa: [],
    personaId: [],
    rangoId: [],
  });

  constructor(
    protected agenteService: AgenteService,
    protected personaService: PersonaService,
    protected rangoService: RangoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ agente }) => {
      this.updateForm(agente);

      this.personaService.query().subscribe((res: HttpResponse<IPersona[]>) => (this.personas = res.body || []));

      this.rangoService.query().subscribe((res: HttpResponse<IRango[]>) => (this.rangos = res.body || []));
    });
  }

  updateForm(agente: IAgente): void {
    this.editForm.patchValue({
      id: agente.id,
      placa: agente.placa,
      personaId: agente.personaId,
      rangoId: agente.rangoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const agente = this.createFromForm();
    if (agente.id !== undefined) {
      this.subscribeToSaveResponse(this.agenteService.update(agente));
    } else {
      this.subscribeToSaveResponse(this.agenteService.create(agente));
    }
  }

  private createFromForm(): IAgente {
    return {
      ...new Agente(),
      id: this.editForm.get(['id'])!.value,
      placa: this.editForm.get(['placa'])!.value,
      personaId: this.editForm.get(['personaId'])!.value,
      rangoId: this.editForm.get(['rangoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAgente>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
