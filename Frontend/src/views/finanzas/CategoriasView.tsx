import { Table } from 'antd'
import { useFindAll } from '@/hooks/core/useFindAll'
import { categoriaService } from '@/services/api'
import { queryKeys } from '@/lib/queryClient'

export default function CategoriasView() {
  const { data, isLoading } = useFindAll({
    queryKey: queryKeys.categorias,
    service: categoriaService,
  })

  return (
    <Table
      rowKey="id"
      loading={isLoading}
      dataSource={data?.data}
      columns={[
        {
          title: 'ID',
          dataIndex: 'id',
        },
        {
          title: 'Nombre',
          dataIndex: 'nombre',
        },
        {
          title: 'Tipo',
          dataIndex: 'tipo',
        },
      ]}
    />
  )
}