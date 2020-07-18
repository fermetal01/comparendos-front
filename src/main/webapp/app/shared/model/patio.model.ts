import { IComparendo } from 'app/shared/model/comparendo.model';

export interface IPatio {
  id?: string;
  nombre?: string;
  numero?: string;
  direccion?: string;
  comparendos?: IComparendo[];
}

export class Patio implements IPatio {
  constructor(
    public id?: string,
    public nombre?: string,
    public numero?: string,
    public direccion?: string,
    public comparendos?: IComparendo[]
  ) {}
}
