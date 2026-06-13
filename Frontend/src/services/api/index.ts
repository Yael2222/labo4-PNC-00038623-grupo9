import Role from '@/models/api/entities/Role'
import Permissions from '@/models/api/entities/Permissions'

import Service from '../core/Service'

import UserService from './custom/UserService'

import cuentaService from './custom/CuentaService'
import categoriaService from './custom/CategoriaService'
import movimientoService from './custom/MovimientoService'
import transferenciaService from './custom/TransferenciaService'

export const userService = new UserService()

export { cuentaService, categoriaService, movimientoService, transferenciaService }

export const roleService = new Service<Role>({
  endpoint: 'roles',
})

export const permissionService = new Service<Permissions>({
  endpoint: 'permissions',
})