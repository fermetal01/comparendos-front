import { IPersona } from 'app/shared/model/persona.model';
import { IComparendo } from 'app/shared/model/comparendo.model';
import { IEntidad } from 'app/shared/model/entidad.model';

export interface ICiudad {
  id?: string;
  nombre?: string;
  personas?: IPersona[];
  comparendos?: IComparendo[];
  entidads?: IEntidad[];
  departamentoId?: string;
}

export class Ciudad implements ICiudad {
  constructor(
    public id?: string,
    public nombre?: string,
    public personas?: IPersona[],
    public comparendos?: IComparendo[],
    public entidads?: IEntidad[],
    public departamentoId?: string
  ) {}
}
