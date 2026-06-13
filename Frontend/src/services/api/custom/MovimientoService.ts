import Service from '@/services/core/Service'
import Movimiento from '@/models/api/entities/Movimiento'

class MovimientoService extends Service<Movimiento> {
  constructor() {
    super({
      endpoint: 'finanzas/movimientos',
    })
  }
}

export default new MovimientoService()