package dan.tp2021.reportes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.TimeZone;

import dan.tp2021.reportes.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.domain.reportes.ReporteCliente;
import dan.tp2021.reportes.domain.reportes.ReporteMaterial;
import dan.tp2021.reportes.domain.reportes.ReportePedido;
import dan.tp2021.reportes.dto.ReporteDTO;
import dan.tp2021.reportes.exceptions.SinPedidosException;
import dan.tp2021.reportes.service.ReporteClienteService;
import dan.tp2021.reportes.service.ReporteMaterialService;
import dan.tp2021.reportes.service.ReportePedidoService;

@RestController
@RequestMapping("/api/reporte")
public class ReporteRest {

    private static final Logger logger = LoggerFactory.getLogger(ReporteRest.class);

    final ReportePedidoService reportePedidoService;

    final ReporteClienteService reporteClienteService;

    final ReporteMaterialService reporteMaterialService;

    public ReporteRest(ReportePedidoService reportePedidoService, ReporteClienteService reporteClienteService, ReporteMaterialService reporteMaterialService) {
        this.reportePedidoService = reportePedidoService;
        this.reporteClienteService = reporteClienteService;
        this.reporteMaterialService = reporteMaterialService;
    }

    @PostMapping("/clientes")
    public ResponseEntity<ReporteCliente> generarReporteCliente(@RequestBody ReporteDTO datosReporte){

        Instant fechaInicio = datosReporte.fechaInicio.atStartOfDay().toInstant(ZoneOffset.ofTotalSeconds(TimeZone.getDefault().getRawOffset()/1000));
        Instant fechaFin = datosReporte.fechaFin.atTime(23,59,59).toInstant(ZoneOffset.ofTotalSeconds(TimeZone.getDefault().getRawOffset()/1000));

        if(fechaFin == null || fechaInicio == null || fechaFin.isBefore(fechaInicio)){
            //las fechas no son válidas
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas no son válidas");
        }

        ReporteCliente reporte;
        try {
            reporte = reporteClienteService.generarReporte(fechaInicio, fechaFin);
        } catch (SinPedidosException spe){
            logger.warn("generarReporteCliente: No se encontraron pedidos entre las fechas " + fechaInicio + " y " + fechaFin, spe);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, spe.getMessage(), spe);
        } catch (Exception e) {
            logger.error("generarReporteCliente: Se produjo un error al generar un reporte de clientes: " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al generar el reporte: " + e.getMessage(), e);
        }
        logger.debug("generarReporteCliente: respondiendo con cliente: " + reporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(reporte);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ReporteCliente> getReporteClienteById(@PathVariable Integer id){
        ReporteCliente reporte;
        try {
            reporte = reporteClienteService.getReporteById(id);
        } catch (ReporteNotFoundException rnfe){
            logger.warn("getReporteClienteById: No se encontró el reporte cliente con id: " + id + ". Mensaje: " + rnfe.getMessage(), rnfe);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "getReporteClienteById: No se encontró el reporte cliente con id: " + id + ". Mensaje: " + rnfe.getMessage(), rnfe);
        } catch (Exception e) {
            logger.error("getReporteClienteById: Se produjo un error al obtener el reporte de clientes con id " + id + ": " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al obtener el reporte de clientes con id " + id + ": " + e.getMessage(), e);
        }
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ReporteCliente>> getAllReporteCliente(){
        List<ReporteCliente> reportes;
        try {
            reportes = reporteClienteService.getAllReportes();
        } catch (Exception e) {
            logger.error("getAllReporteCliente: Se produjo un error al obtener todos los reportes: " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al obtener todos los reportes: " + e.getMessage(), e);
        }
        return ResponseEntity.ok(reportes);
    }

    @PostMapping("/materiales")
    public ResponseEntity<ReporteMaterial> generarReporteMaterial(@RequestBody ReporteDTO datosReporte){

        if (true) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Característica en desarrollo.");
        }

        Instant fechaInicio = datosReporte.fechaInicio.atStartOfDay().toInstant(ZoneOffset.ofTotalSeconds(TimeZone.getDefault().getRawOffset()/1000));
        Instant fechaFin = datosReporte.fechaFin.atTime(23,59,59).toInstant(ZoneOffset.ofTotalSeconds(TimeZone.getDefault().getRawOffset()/1000));

        if(fechaFin == null || fechaInicio == null || fechaFin.isBefore(fechaInicio)){
            //las fechas no son válidas
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas no son válidas");
        }

        ReporteMaterial reporte;

        try {
            reporte = reporteMaterialService.generarReporte(fechaInicio, fechaFin);
        } catch (Exception e) {
            logger.error("generarReporteMaterial: Se produjo un error al generar un reporte de materiales: " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al generar el reporte: " + e.getMessage(), e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(reporte);
    }

    @GetMapping("/materiales/{id}")
    public ResponseEntity<ReporteMaterial> getReporteMaterialById(@PathVariable("id") Integer id){

        if (true) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Característica en desarrollo.");
        }

        try {
            ReporteMaterial reporteMaterial = reporteMaterialService.getReporteById(id);
            return ResponseEntity.ok(reporteMaterial);
        } catch (ReporteNotFoundException rnfe){
            logger.warn("getReporteMaterialById: No se encontró el reporte de materiales con id: " + id + ". Mensaje: " + rnfe.getMessage(), rnfe);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el reporte de materiales con id: " + id + ". Mensaje: " + rnfe.getMessage(), rnfe);
        } catch (Exception e) {
            logger.error("getReporteMaterialById: Se produjo un error al obtener el reporte de materiales con id " + id + ": " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al obtener el reporte: " + e.getMessage(), e);
        }
    }

    @GetMapping("/materiales")
    public ResponseEntity<List<ReporteMaterial>> getAllReporteMaterial(){

        if (true) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Característica en desarrollo.");
        }

        try {
            List<ReporteMaterial> reportesMaterial = reporteMaterialService.getAllReportes();
            return ResponseEntity.ok(reportesMaterial);
        } catch (Exception e) {
            logger.error("getAllReporteMaterial: Se produjo un error al obtener la lista de reportes de materiales: " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al obtener los reportes: " + e.getMessage(), e);
        }
    }

    @PostMapping("/pedidos")
    public ResponseEntity<ReportePedido> generarReportePedidos(@RequestBody ReporteDTO datosReporte){

        Instant fechaInicio = datosReporte.fechaInicio.atStartOfDay().toInstant(ZoneOffset.ofTotalSeconds(TimeZone.getDefault().getRawOffset()/1000));
        Instant fechaFin = datosReporte.fechaFin.atTime(23,59,59).toInstant(ZoneOffset.ofTotalSeconds(TimeZone.getDefault().getRawOffset()/1000));

        if(fechaFin == null || fechaInicio == null || fechaFin.isBefore(fechaInicio)){
            //las fechas no son válidas
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas no son válidas");
        }

        ReportePedido reporte;

        try {
            reporte = reportePedidoService.generarReporte(fechaInicio, fechaFin);
        } catch (SinPedidosException spe){
            logger.warn("generarReportePedidos: No se encontraron pedidos entre las fechas " + fechaInicio + " y " + fechaFin, spe);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, spe.getMessage(), spe);
        } catch (Exception e) {
            logger.error("generarReportePedidos: Se produjo un error al generar un reporte de pedidos: " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al generar el reporte: " + e.getMessage(), e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(reporte);

    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<ReportePedido> getReportePedidoById(@PathVariable("id") Integer id){

        try {
            ReportePedido reportePedido = reportePedidoService.getReporteById(id);
            return ResponseEntity.ok(reportePedido);
        } catch (ReporteNotFoundException rnfe){
            logger.warn("getReportePedidoById: No se encontró el reporte de pedidos con id: " + id + ". Mensaje: " + rnfe.getMessage(), rnfe);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "getReportePedidoById: No se encontró el reporte de pedidos con id: " + id + ". Mensaje: " + rnfe.getMessage(), rnfe);
        } catch (Exception e) {
            logger.error("getReportePedidoById: Se produjo un error al obtener el reporte de pedidos con id " + id + ": " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al obtener el reporte: " + e.getMessage(), e);
        }
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<ReportePedido>> getAllReportePedido(){

        try {
            List<ReportePedido> reportesPedidos = reportePedidoService.getAllReportes();
            return ResponseEntity.ok(reportesPedidos);
        } catch (Exception e) {
            logger.error("getAllReportePedido: Se produjo un error al obtener la lista de reportes de pedidos: " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al obtener los reportes: " + e.getMessage(), e);
        }
    }


}
