import { IPersona } from 'app/shared/model/persona.model';

export interface ITipoIdentificacion {
  id?: string;
  nombre?: string;
  personas?: IPersona[];
}

export class TipoIdentificacion implements ITipoIdentificacion {
  constructor(public id?: string, public nombre?: string, public personas?: IPersona[]) {}
}
