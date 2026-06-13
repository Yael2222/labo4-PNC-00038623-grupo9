import Service from '@/services/core/Service'
import Transferencia from '@/models/api/entities/Transferencia'

class TransferenciaService extends Service<Transferencia> {
  constructor() {
    super({
      endpoint: 'finanzas/transferencias',
    })
  }
}

export default new TransferenciaService()
