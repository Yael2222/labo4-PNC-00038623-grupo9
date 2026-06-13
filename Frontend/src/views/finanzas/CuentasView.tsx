import { Table } from 'antd'
import { useFindAll } from '@/hooks/core/useFindAll'
import { cuentaService } from '@/services/api'
import type Cuenta from '@/models/api/entities/Cuenta'

export default function CuentasView() {
  const { data, isLoading } = useFindAll<Cuenta>({
    queryKey: ['cuentas'],
    service: cuentaService,
    queryParams: {
      page: 0,
      size: 20,
    },
  })

  return (
    <Table
      loading={isLoading}
      dataSource={data?.data}
      rowKey="id"
      columns={[
        {
          title: 'ID',
          dataIndex: 'id',
        },
        {
          title: 'Alias',
          dataIndex: 'alias',
        },
        {
          title: 'Moneda',
          dataIndex: 'moneda',
        },
        {
          title: 'Saldo',
          dataIndex: 'saldoBase',
        },
        {
          title: 'Tipo',
          dataIndex: 'tipo',
        },
      ]}
    />
  )
}