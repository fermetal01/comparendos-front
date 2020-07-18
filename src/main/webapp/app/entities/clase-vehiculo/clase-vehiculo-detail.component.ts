import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClaseVehiculo } from 'app/shared/model/clase-vehiculo.model';

@Component({
  selector: 'jhi-clase-vehiculo-detail',
  templateUrl: './clase-vehiculo-detail.component.html',
})
export class ClaseVehiculoDetailComponent implements OnInit {
  claseVehiculo: IClaseVehiculo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claseVehiculo }) => (this.claseVehiculo = claseVehiculo));
  }

  previousState(): void {
    window.history.back();
  }
}
