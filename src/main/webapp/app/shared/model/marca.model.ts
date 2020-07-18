import { IVehiculo } from 'app/shared/model/vehiculo.model';

export interface IMarca {
  id?: string;
  nombre?: string;
  vehiculos?: IVehiculo[];
}

export class Marca implements IMarca {
  constructor(public id?: string, public nombre?: string, public vehiculos?: IVehiculo[]) {}
}
