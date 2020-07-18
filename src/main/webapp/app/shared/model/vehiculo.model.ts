import { Moment } from 'moment';
import { IComparendo } from 'app/shared/model/comparendo.model';
import { IGrua } from 'app/shared/model/grua.model';
import { IRestriccion } from 'app/shared/model/restriccion.model';
import { IPersona } from 'app/shared/model/persona.model';
import { IEntidad } from 'app/shared/model/entidad.model';

export interface IVehiculo {
  id?: string;
  placa?: string;
  linea?: string;
  modelo?: number;
  cilindraje?: number;
  color?: string;
  capacidad?: number;
  numeroMotor?: string;
  vin?: string;
  serie?: string;
  chasis?: string;
  potencia?: string;
  fechaMatricula?: Moment;
  comparendos?: IComparendo[];
  gruas?: IGrua[];
  restriccions?: IRestriccion[];
  personas?: IPersona[];
  entidads?: IEntidad[];
  marcaId?: string;
  servicioId?: string;
  claseId?: string;
  combustibleId?: string;
  organismoTransitoId?: string;
  licenciaTransitoId?: string;
  personaId?: string;
}

export class Vehiculo implements IVehiculo {
  constructor(
    public id?: string,
    public placa?: string,
    public linea?: string,
    public modelo?: number,
    public cilindraje?: number,
    public color?: string,
    public capacidad?: number,
    public numeroMotor?: string,
    public vin?: string,
    public serie?: string,
    public chasis?: string,
    public potencia?: string,
    public fechaMatricula?: Moment,
    public comparendos?: IComparendo[],
    public gruas?: IGrua[],
    public restriccions?: IRestriccion[],
    public personas?: IPersona[],
    public entidads?: IEntidad[],
    public marcaId?: string,
    public servicioId?: string,
    public claseId?: string,
    public combustibleId?: string,
    public organismoTransitoId?: string,
    public licenciaTransitoId?: string,
    public personaId?: string
  ) {}
}
