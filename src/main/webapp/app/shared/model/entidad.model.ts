import { IComparendo } from 'app/shared/model/comparendo.model';
import { IVehiculo } from 'app/shared/model/vehiculo.model';

export interface IEntidad {
  id?: string;
  nombre?: string;
  direccion?: string;
  telefono?: string;
  celular?: string;
  email?: string;
  comparendos?: IComparendo[];
  ciudadId?: string;
  vehiculos?: IVehiculo[];
}

export class Entidad implements IEntidad {
  constructor(
    public id?: string,
    public nombre?: string,
    public direccion?: string,
    public telefono?: string,
    public celular?: string,
    public email?: string,
    public comparendos?: IComparendo[],
    public ciudadId?: string,
    public vehiculos?: IVehiculo[]
  ) {}
}
