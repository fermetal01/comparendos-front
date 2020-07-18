import { IComparendo } from 'app/shared/model/comparendo.model';

export interface IGrua {
  id?: string;
  codigo?: string;
  comparendos?: IComparendo[];
  vehiculoId?: string;
}

export class Grua implements IGrua {
  constructor(public id?: string, public codigo?: string, public comparendos?: IComparendo[], public vehiculoId?: string) {}
}
