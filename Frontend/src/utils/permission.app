import { routesConfig } from '@/config/routes.app'
import type Permissions from '@/models/api/entities/Permissions'
import type { RoutesEnum } from '@/enum/routes..app'

/**
 * Verifica si el usuario tiene un permiso puntual, dado en formato "METHOD:/path".
 * "*" siempre se considera autorizado.
 */
export function hasPermission(
  permissions: Permissions[] | undefined,
  permissionKey: string
): boolean {
  if (permissionKey === '*') return true

  const [method, path] = permissionKey.split(':')

  return !!permissions?.some(
    (permission) => permission.method === method && permission.path === path
  )
}

/**
 * Verifica si el usuario está autorizado a entrar a una ruta, según los
 * permisos definidos en `routesConfig[route].permission`.
 *
 * Si la ruta requiere "*" (o no define permisos), se permite a cualquier
 * usuario autenticado.
 */
export function isAuthorized(
  permissions: Permissions[] | undefined,
  route: RoutesEnum
): boolean {
  const routeData = routesConfig[route]

  if (!routeData) return false

  const required = routeData.permission ?? ['*']

  if (required.includes('*')) return true

  return required.some((permission) => hasPermission(permissions, permission))
}
