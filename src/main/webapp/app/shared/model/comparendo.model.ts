import { Moment } from 'moment';
import { IInfraccion } from 'app/shared/model/infraccion.model';

export interface IComparendo {
  id?: string;
  fechaHora?: Moment;
  direccion?: string;
  observaciones?: string;
  codigoInmovilizacion?: string;
  infracciones?: IInfraccion[];
  estadoId?: string;
  vehiculoId?: string;
  licenciaTransitoId?: string;
  licenciaConduccionId?: string;
  agenteId?: string;
  ciudadId?: string;
  infractorId?: string;
  testigoId?: string;
  patioId?: string;
  gruaId?: string;
  entidadId?: string;
}

export class Comparendo implements IComparendo {
  constructor(
    public id?: string,
    public fechaHora?: Moment,
    public direccion?: string,
    public observaciones?: string,
    public codigoInmovilizacion?: string,
    public infracciones?: IInfraccion[],
    public estadoId?: string,
    public vehiculoId?: string,
    public licenciaTransitoId?: string,
    public licenciaConduccionId?: string,
    public agenteId?: string,
    public ciudadId?: string,
    public infractorId?: string,
    public testigoId?: string,
    public patioId?: string,
    public gruaId?: string,
    public entidadId?: string
  ) {}
}
