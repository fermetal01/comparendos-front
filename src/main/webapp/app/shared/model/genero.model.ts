import { IPersona } from 'app/shared/model/persona.model';

export interface IGenero {
  id?: string;
  nombre?: string;
  personas?: IPersona[];
}

export class Genero implements IGenero {
  constructor(public id?: string, public nombre?: string, public personas?: IPersona[]) {}
}
