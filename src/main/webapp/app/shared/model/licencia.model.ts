import { Moment } from 'moment';
import { IPersona } from 'app/shared/model/persona.model';
import { IVehiculo } from 'app/shared/model/vehiculo.model';
import { IComparendo } from 'app/shared/model/comparendo.model';
import { IRestriccion } from 'app/shared/model/restriccion.model';

export interface ILicencia {
  id?: string;
  fechaExpedicion?: Moment;
  vigencia?: Moment;
  serial?: string;
  personas?: IPersona[];
  vehiculos?: IVehiculo[];
  comparendos?: IComparendo[];
  restriccions?: IRestriccion[];
  tipoLicenciaId?: string;
  categoriaId?: string;
  servicioId?: string;
  organismoTransitoId?: string;
}

export class Licencia implements ILicencia {
  constructor(
    public id?: string,
    public fechaExpedicion?: Moment,
    public vigencia?: Moment,
    public serial?: string,
    public personas?: IPersona[],
    public vehiculos?: IVehiculo[],
    public comparendos?: IComparendo[],
    public restriccions?: IRestriccion[],
    public tipoLicenciaId?: string,
    public categoriaId?: string,
    public servicioId?: string,
    public organismoTransitoId?: string
  ) {}
}
