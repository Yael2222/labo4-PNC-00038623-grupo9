import Service from '@/services/core/Service'
import Cuenta from '@/models/api/entities/Cuenta'

class CuentaService extends Service<Cuenta> {
  constructor() {
    super({
      endpoint: 'finanzas/cuentas',
    })
  }
}

export default new CuentaService()