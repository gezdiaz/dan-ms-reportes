package dan.tp2021.reportes.domain.items;

import java.time.Instant;

public class ItemPedido extends Item{

    private Double ingresosGenerados;
    private String estado;

    private Integer idPedido;
    private Instant fechaPedido;
    private Integer cantidadMateriales; //de los detalles
    private String direccionObra;
    private String tipoObra;
    private String razonSocialCliente;
    private String cuitCliente;


}
