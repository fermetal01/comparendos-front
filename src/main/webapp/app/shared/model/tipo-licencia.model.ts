import { ILicencia } from 'app/shared/model/licencia.model';

export interface ITipoLicencia {
  id?: string;
  tipo?: string;
  licencias?: ILicencia[];
}

export class TipoLicencia implements ITipoLicencia {
  constructor(public id?: string, public tipo?: string, public licencias?: ILicencia[]) {}
}
