import { ICiudad } from 'app/shared/model/ciudad.model';

export interface IDepartamento {
  id?: string;
  nombre?: string;
  ciudads?: ICiudad[];
}

export class Departamento implements IDepartamento {
  constructor(public id?: string, public nombre?: string, public ciudads?: ICiudad[]) {}
}
