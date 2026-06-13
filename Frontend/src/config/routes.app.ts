import type { RoleName } from '@/enum/role'
import { RoutesEnum } from '@/enum/routes..app'

type RouteConfig = {
  auth: boolean
  roles: RoleName[]
  permission: string[]
  title: string
  search: boolean
}

export const routesConfig: Record<RoutesEnum, RouteConfig> = {
  [RoutesEnum.ROOT]: {
    auth: true,
    roles: [],
    permission: ['*'],
    title: 'Inicio',
    search: false,
  },
  [RoutesEnum.LOGIN]: {
    auth: false,
    roles: [],
    permission: ['*'],
    title: 'Login',
    search: false,
  },
  [RoutesEnum.DASHBOARD]: {
    auth: true,
    roles: ['*'],
    permission: ['*'],
    title: 'Dashboard',
    search: true,
  },
  [RoutesEnum.ROLES]: {
    auth: true,
    roles: ['*'],
    permission: ['*'],
    title: 'Roles',
    search: true,
  },
  [RoutesEnum.PERMISSIONS]: {
    auth: true,
    roles: ['*'],
    permission: ['*'],
    title: 'Permisos',
    search: true,
  },
  [RoutesEnum.FINANZAS]: {

    auth: true,

    roles: ["*"],

    permission: ["GET:/api/finanzas/cuentas"],

    title: "Finanzas",

    search: true

  },
  [RoutesEnum.CUENTAS]: {
  auth: true,
  roles: ['*'],
  permission: ['GET:/api/finanzas/cuentas'],
  title: 'Cuentas',
  search: true,
},

[RoutesEnum.MOVIMIENTOS]: {
  auth: true,
  roles: ['*'],
  permission: ['GET:/api/finanzas/movimientos'],
  title: 'Movimientos',
  search: true,
},

[RoutesEnum.CATEGORIAS]: {
  auth: true,
  roles: ['*'],
  permission: ['GET:/api/finanzas/categorias'],
  title: 'Categorías',
  search: true,
},

[RoutesEnum.TRANSFERENCIAS]: {
  auth: true,
  roles: ['*'],
  permission: ['POST:/api/finanzas/transferencias'],
  title: 'Transferencias',
  search: true,
},
} as const
