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

import java.time.LocalDate;
import java.util.List;

import javax.persistence.GeneratedValue;

import dan.tp2021.reportes.domain.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.domain.reportes.ReporteCliente;
import dan.tp2021.reportes.domain.reportes.ReporteMaterial;
import dan.tp2021.reportes.domain.reportes.ReportePedido;
import dan.tp2021.reportes.dto.ReporteDTO;
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

        LocalDate fechaInicio = datosReporte.fechaInicio;
        LocalDate fechaFin = datosReporte.fechaFin;

        if(fechaFin == null || fechaInicio == null || fechaFin.toEpochDay() < fechaInicio.toEpochDay()){
            //las fechas no son válidas
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas no son válidas");
        }

        ReporteCliente reporte;
        try {
            reporte = reporteClienteService.generarReporte(fechaInicio, fechaFin);
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
            logger.error("getReporteClienteById: Se produjo un error al obtener todos los reportes: " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al obtener todos los reportes: " + e.getMessage(), e);
        }
        return ResponseEntity.ok(reportes);
    }

    @PostMapping("/materiales")
    public ResponseEntity<ReporteMaterial> generarReporteMaterial(@RequestBody ReporteDTO datosReporte){

        LocalDate fechaInicio = datosReporte.fechaInicio;
        LocalDate fechaFin = datosReporte.fechaFin;

        if(fechaFin == null || fechaInicio == null || fechaFin.toEpochDay() < fechaInicio.toEpochDay()){
            //las fechas no son válidas
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas no son válidas");
        }

        ReporteMaterial reporte;

        try {
            reporte = reporteMaterialService.generarReporte(fechaInicio, fechaFin);
        } catch (Exception e) {
            logger.error("generarReporteCliente: Se produjo un error al generar un reporte de materiales: " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al generar el reporte: " + e.getMessage(), e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(reporte);
    }

    @PostMapping("/pedidos")
    public ResponseEntity<ReportePedido> generarReportePedidos(@RequestBody ReporteDTO datosReporte){

        LocalDate fechaInicio = datosReporte.fechaInicio;
        LocalDate fechaFin = datosReporte.fechaFin;

        if(fechaFin == null || fechaInicio == null || fechaFin.toEpochDay() < fechaInicio.toEpochDay()){
            //las fechas no son válidas
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas no son válidas");
        }

        ReportePedido reporte;

        try {
            reporte = reportePedidoService.generarReporte(fechaInicio, fechaFin);
        } catch (Exception e) {
            logger.error("generarReporteCliente: Se produjo un error al generar un reporte de pedidos: " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al generar el reporte: " + e.getMessage(), e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(reporte);

    }


}
