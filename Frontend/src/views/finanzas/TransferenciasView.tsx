import { Button, Card, Form, Input, InputNumber, message } from 'antd'
import SelectApi from '@/components/core/SelectApi'
import { cuentaService, transferenciaService } from '@/services/api'
import { queryKeys } from '@/lib/queryClient'
import type Cuenta from '@/models/api/entities/Cuenta'
import useCrud from '@/hooks/core/useCrud'

export default function TransferenciasView() {
  const [form] = Form.useForm()

  const crud = useCrud({
    service: transferenciaService,
    queryKey: queryKeys.transferencias,
  })

  const handleFinish = async (values: {
    origen?: Cuenta
    destino?: Cuenta
    monto: number
    descripcion?: string
  }) => {
    if (!values.origen || !values.destino) return

    if (values.origen.id === values.destino.id) {
      message.error('La cuenta de origen y destino no pueden ser la misma')
      return
    }

    await crud.create({
      payload: {
        origen: values.origen.id,
        destino: values.destino.id,
        monto: values.monto,
        descripcion: values.descripcion,
      },
    })

    message.success('Transferencia realizada correctamente')
    form.resetFields()
  }

  return (
    <Card title="Transferencias" className="max-w-xl">
      <Form form={form} layout="vertical" onFinish={handleFinish}>
        <Form.Item
          name="origen"
          label="Cuenta de origen"
          rules={[{ required: true, message: 'Selecciona la cuenta de origen' }]}
        >
          <SelectApi<Cuenta>
            service={cuentaService}
            queryKey={queryKeys.cuentas}
            queryParams={{ page: 0, size: 50 }}
            placeholder="Selecciona una cuenta"
            renderOption={(item) => `${item.alias} (${item.moneda})`}
          />
        </Form.Item>

        <Form.Item
          name="destino"
          label="Cuenta destino"
          rules={[{ required: true, message: 'Selecciona la cuenta destino' }]}
        >
          <SelectApi<Cuenta>
            service={cuentaService}
            queryKey={queryKeys.cuentas}
            queryParams={{ page: 0, size: 50 }}
            placeholder="Selecciona una cuenta"
            renderOption={(item) => `${item.alias} (${item.moneda})`}
          />
        </Form.Item>

        <Form.Item
          name="monto"
          label="Monto"
          rules={[{ required: true, message: 'Ingresa el monto a transferir' }]}
        >
          <InputNumber min={0.01} step={0.01} style={{ width: '100%' }} />
        </Form.Item>

        <Form.Item name="descripcion" label="Descripción">
          <Input placeholder="Ej. Pago de servicios" />
        </Form.Item>

        <Form.Item>
          <Button type="primary" htmlType="submit" loading={crud.isCreating}>
            Transferir
          </Button>
        </Form.Item>
      </Form>
    </Card>
  )
}
