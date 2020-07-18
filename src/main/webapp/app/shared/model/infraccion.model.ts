import { IComparendo } from 'app/shared/model/comparendo.model';

export interface IInfraccion {
  id?: string;
  codigo?: string;
  descripcion?: string;
  comparendos?: IComparendo[];
}

export class Infraccion implements IInfraccion {
  constructor(public id?: string, public codigo?: string, public descripcion?: string, public comparendos?: IComparendo[]) {}
}
