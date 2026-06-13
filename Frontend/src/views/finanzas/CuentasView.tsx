import { Button, Form, Input, Modal, Select, Table } from 'antd'
import { useState } from 'react'
import { useFindAll } from '@/hooks/core/useFindAll'
import useCrud from '@/hooks/core/useCrud'
import { cuentaService } from '@/services/api'
import type Cuenta from '@/models/api/entities/Cuenta'
import { queryKeys } from '@/lib/queryClient'
import { useSession } from '@/hooks/useSession'
import { hasPermission } from '@/utils/permission.app'

const MONEDAS = ['USD', 'EUR', 'BTC']
const TIPOS = ['AHORRO', 'CORRIENTE']

export default function CuentasView() {
  const { profile } = useSession()

  const canCreate = hasPermission(
    
    profile?.role?.permissions,
    'POST:/api/finanzas/cuentas'
  )
    console.log('DEBUG canCreate', canCreate, profile?.role?.permissions)



  const { data, isLoading } = useFindAll<Cuenta>({
    queryKey: queryKeys.cuentas,
    service: cuentaService,
    queryParams: {
      page: 0,
      size: 20,
    },
  })

  const crud = useCrud<Cuenta>({
    service: cuentaService,
    queryKey: queryKeys.cuentas,
  })

  const [open, setOpen] = useState(false)
  const [form] = Form.useForm()

  const openCreate = () => {
    form.resetFields()
    setOpen(true)
  }

  const handleOk = async () => {
    const values = await form.validateFields()

    await crud.create({
      payload: {
        alias: values.alias,
        moneda: values.moneda,
        tipo: values.tipo,
      } as Cuenta,
    })

    setOpen(false)
    form.resetFields()
  }

  return (
    <div>
      {canCreate && (
        <div className="mb-3 flex justify-end">
          <Button type="primary" onClick={openCreate}>
            Crear cuenta
          </Button>
        </div>
      )}

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

      <Modal
        title="Crear cuenta"
        open={open}
        onOk={handleOk}
        onCancel={() => setOpen(false)}
        confirmLoading={crud.isCreating}
        destroyOnClose
      >
        <Form form={form} layout="vertical">
          <Form.Item
            name="alias"
            label="Alias"
            rules={[{ required: true, message: 'El alias es obligatorio' }]}
          >
            <Input placeholder="Ej. Cuenta Principal" />
          </Form.Item>

          <Form.Item
            name="moneda"
            label="Moneda"
            rules={[{ required: true, message: 'Selecciona una moneda' }]}
          >
            <Select
              options={MONEDAS.map((m) => ({ label: m, value: m }))}
              placeholder="Selecciona una moneda"
            />
          </Form.Item>

          <Form.Item
            name="tipo"
            label="Tipo de cuenta"
            rules={[{ required: true, message: 'Selecciona un tipo' }]}
          >
            <Select
              options={TIPOS.map((t) => ({ label: t, value: t }))}
              placeholder="Selecciona un tipo"
            />
          </Form.Item>
        </Form>
      </Modal>
    </div>
    
  )
}
