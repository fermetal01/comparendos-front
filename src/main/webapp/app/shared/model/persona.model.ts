import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { IAgente } from 'app/shared/model/agente.model';
import { IVehiculo } from 'app/shared/model/vehiculo.model';
import { IComparendo } from 'app/shared/model/comparendo.model';

export interface IPersona {
  id?: string;
  nombres?: string;
  apellidos?: string;
  fechaNacimiento?: Moment;
  numeroId?: string;
  direccion?: string;
  telefono?: string;
  celular?: string;
  email?: string;
  usuarios?: IUser[];
  agentes?: IAgente[];
  comparendos?: IComparendo[];
  ciudadId?: string;
  generoId?: string;
  tipoSanguineoId?: string;
  tipoIdId?: string;
  licenciaId?: string;
  vehiculos?: IVehiculo[];
}

export class Persona implements IPersona {
  constructor(
    public id?: string,
    public nombres?: string,
    public apellidos?: string,
    public fechaNacimiento?: Moment,
    public numeroId?: string,
    public direccion?: string,
    public telefono?: string,
    public celular?: string,
    public email?: string,
    public usuarios?: IUser[],
    public agentes?: IAgente[],
    public comparendos?: IComparendo[],
    public ciudadId?: string,
    public generoId?: string,
    public tipoSanguineoId?: string,
    public tipoIdId?: string,
    public licenciaId?: string,
    public vehiculos?: IVehiculo[]
  ) {}
}
