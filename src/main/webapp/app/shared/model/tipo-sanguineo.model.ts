import { IPersona } from 'app/shared/model/persona.model';

export interface ITipoSanguineo {
  id?: string;
  tipo?: string;
  personas?: IPersona[];
}

export class TipoSanguineo implements ITipoSanguineo {
  constructor(public id?: string, public tipo?: string, public personas?: IPersona[]) {}
}
