import { IComparendo } from 'app/shared/model/comparendo.model';

export interface IEstadoComparendo {
  id?: string;
  tipo?: string;
  comparendos?: IComparendo[];
}

export class EstadoComparendo implements IEstadoComparendo {
  constructor(public id?: string, public tipo?: string, public comparendos?: IComparendo[]) {}
}
