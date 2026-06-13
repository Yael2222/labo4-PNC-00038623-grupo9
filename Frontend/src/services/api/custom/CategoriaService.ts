import Service from '@/services/core/Service'
import Categoria from '@/models/api/entities/Categoria'

class CategoriaService extends Service<Categoria> {
  constructor() {
    super({
      endpoint: 'finanzas/categorias',
    })
  }
}

export default new CategoriaService()