import { ILicencia } from 'app/shared/model/licencia.model';
import { IVehiculo } from 'app/shared/model/vehiculo.model';

export interface IOrganismoTransito {
  id?: string;
  nombre?: string;
  licencias?: ILicencia[];
  vehiculos?: IVehiculo[];
}

export class OrganismoTransito implements IOrganismoTransito {
  constructor(public id?: string, public nombre?: string, public licencias?: ILicencia[], public vehiculos?: IVehiculo[]) {}
}
