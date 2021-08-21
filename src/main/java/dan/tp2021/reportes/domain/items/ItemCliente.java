package dan.tp2021.reportes.domain.items;

public class ItemCliente extends Item{

    //Datos calculados para el reporte.
    private Integer cantidadPedidos;
    private Integer cantidadObras;
    private Double gananciasGeneradas;

    //datos del cliente
    private Integer idCliente; //El id del cliente, no del item cliente
    private String razonSocial;
    private String cuit;
    private String mail;

}
