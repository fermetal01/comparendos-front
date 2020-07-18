import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'ciudad',
        loadChildren: () => import('./ciudad/ciudad.module').then(m => m.ComparendosCiudadModule),
      },
      {
        path: 'departamento',
        loadChildren: () => import('./departamento/departamento.module').then(m => m.ComparendosDepartamentoModule),
      },
      {
        path: 'persona',
        loadChildren: () => import('./persona/persona.module').then(m => m.ComparendosPersonaModule),
      },
      {
        path: 'genero',
        loadChildren: () => import('./genero/genero.module').then(m => m.ComparendosGeneroModule),
      },
      {
        path: 'tipo-identificacion',
        loadChildren: () => import('./tipo-identificacion/tipo-identificacion.module').then(m => m.ComparendosTipoIdentificacionModule),
      },
      {
        path: 'tipo-sanguineo',
        loadChildren: () => import('./tipo-sanguineo/tipo-sanguineo.module').then(m => m.ComparendosTipoSanguineoModule),
      },
      {
        path: 'licencia',
        loadChildren: () => import('./licencia/licencia.module').then(m => m.ComparendosLicenciaModule),
      },
      {
        path: 'tipo-licencia',
        loadChildren: () => import('./tipo-licencia/tipo-licencia.module').then(m => m.ComparendosTipoLicenciaModule),
      },
      {
        path: 'categoria',
        loadChildren: () => import('./categoria/categoria.module').then(m => m.ComparendosCategoriaModule),
      },
      {
        path: 'servicio',
        loadChildren: () => import('./servicio/servicio.module').then(m => m.ComparendosServicioModule),
      },
      {
        path: 'restriccion',
        loadChildren: () => import('./restriccion/restriccion.module').then(m => m.ComparendosRestriccionModule),
      },
      {
        path: 'organismo-transito',
        loadChildren: () => import('./organismo-transito/organismo-transito.module').then(m => m.ComparendosOrganismoTransitoModule),
      },
      {
        path: 'agente',
        loadChildren: () => import('./agente/agente.module').then(m => m.ComparendosAgenteModule),
      },
      {
        path: 'rango',
        loadChildren: () => import('./rango/rango.module').then(m => m.ComparendosRangoModule),
      },
      {
        path: 'vehiculo',
        loadChildren: () => import('./vehiculo/vehiculo.module').then(m => m.ComparendosVehiculoModule),
      },
      {
        path: 'marca',
        loadChildren: () => import('./marca/marca.module').then(m => m.ComparendosMarcaModule),
      },
      {
        path: 'clase-vehiculo',
        loadChildren: () => import('./clase-vehiculo/clase-vehiculo.module').then(m => m.ComparendosClaseVehiculoModule),
      },
      {
        path: 'combustible',
        loadChildren: () => import('./combustible/combustible.module').then(m => m.ComparendosCombustibleModule),
      },
      {
        path: 'comparendo',
        loadChildren: () => import('./comparendo/comparendo.module').then(m => m.ComparendosComparendoModule),
      },
      {
        path: 'estado-comparendo',
        loadChildren: () => import('./estado-comparendo/estado-comparendo.module').then(m => m.ComparendosEstadoComparendoModule),
      },
      {
        path: 'infraccion',
        loadChildren: () => import('./infraccion/infraccion.module').then(m => m.ComparendosInfraccionModule),
      },
      {
        path: 'patio',
        loadChildren: () => import('./patio/patio.module').then(m => m.ComparendosPatioModule),
      },
      {
        path: 'grua',
        loadChildren: () => import('./grua/grua.module').then(m => m.ComparendosGruaModule),
      },
      {
        path: 'entidad',
        loadChildren: () => import('./entidad/entidad.module').then(m => m.ComparendosEntidadModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ComparendosEntityModule {}
