import { IVehiculo } from 'app/shared/model/vehiculo.model';

export interface ICombustible {
  id?: string;
  nombre?: string;
  vehiculos?: IVehiculo[];
}

export class Combustible implements ICombustible {
  constructor(public id?: string, public nombre?: string, public vehiculos?: IVehiculo[]) {}
}
