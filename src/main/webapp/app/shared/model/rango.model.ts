import { IAgente } from 'app/shared/model/agente.model';

export interface IRango {
  id?: string;
  nombre?: string;
  acronimo?: string;
  agentes?: IAgente[];
}

export class Rango implements IRango {
  constructor(public id?: string, public nombre?: string, public acronimo?: string, public agentes?: IAgente[]) {}
}
