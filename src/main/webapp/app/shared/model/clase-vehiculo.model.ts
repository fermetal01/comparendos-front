import { IVehiculo } from 'app/shared/model/vehiculo.model';

export interface IClaseVehiculo {
  id?: string;
  nombre?: string;
  vehiculos?: IVehiculo[];
}

export class ClaseVehiculo implements IClaseVehiculo {
  constructor(public id?: string, public nombre?: string, public vehiculos?: IVehiculo[]) {}
}
