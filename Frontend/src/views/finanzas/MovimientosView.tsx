import { Table } from 'antd'
import { useFindAll } from '@/hooks/core/useFindAll'
import { movimientoService } from '@/services/api'
import { queryKeys } from '@/lib/queryClient'

export default function MovimientosView() {
  const { data, isLoading } = useFindAll({
    queryKey: queryKeys.movimientos,
    service: movimientoService,
  })

  return (
    <Table
      rowKey="id"
      loading={isLoading}
      dataSource={data?.data}
      columns={[
        {
          title: 'Descripción',
          dataIndex: 'descripcion',
        },
        {
          title: 'Monto',
          dataIndex: 'monto',
        },
        {
          title: 'Fecha',
          dataIndex: 'fecha',
        },
      ]}
    />
  )
}