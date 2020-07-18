import { ILicencia } from 'app/shared/model/licencia.model';
import { IVehiculo } from 'app/shared/model/vehiculo.model';

export interface IRestriccion {
  id?: string;
  tipo?: string;
  licencias?: ILicencia[];
  vehiculos?: IVehiculo[];
}

export class Restriccion implements IRestriccion {
  constructor(public id?: string, public tipo?: string, public licencias?: ILicencia[], public vehiculos?: IVehiculo[]) {}
}
