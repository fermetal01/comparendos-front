import { ILicencia } from 'app/shared/model/licencia.model';

export interface ICategoria {
  id?: string;
  tipo?: string;
  licencias?: ILicencia[];
}

export class Categoria implements ICategoria {
  constructor(public id?: string, public tipo?: string, public licencias?: ILicencia[]) {}
}
