import { IComparendo } from 'app/shared/model/comparendo.model';

export interface IAgente {
  id?: string;
  placa?: string;
  comparendos?: IComparendo[];
  personaId?: string;
  rangoId?: string;
}

export class Agente implements IAgente {
  constructor(
    public id?: string,
    public placa?: string,
    public comparendos?: IComparendo[],
    public personaId?: string,
    public rangoId?: string
  ) {}
}
