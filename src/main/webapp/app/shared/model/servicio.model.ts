import { ILicencia } from 'app/shared/model/licencia.model';
import { IVehiculo } from 'app/shared/model/vehiculo.model';

export interface IServicio {
  id?: string;
  tipo?: string;
  licencias?: ILicencia[];
  vehiculos?: IVehiculo[];
}

export class Servicio implements IServicio {
  constructor(public id?: string, public tipo?: string, public licencias?: ILicencia[], public vehiculos?: IVehiculo[]) {}
}
