package dan.tp2021.reportes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import dan.tp2021.reportes.domain.items.Item;
import dan.tp2021.reportes.domain.reportes.Reporte;
import dan.tp2021.reportes.domain.reportes.ReporteClientes;
import dan.tp2021.reportes.domain.reportes.ReporteMateriales;
import dan.tp2021.reportes.domain.reportes.ReportePedidos;
import dan.tp2021.reportes.domain.reportes.TipoReporte;
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
    public ResponseEntity<ReporteClientes> generarReporteCliente(@RequestBody ReporteDTO datosReporte){

        LocalDate fechaInicio = datosReporte.fechaInicio;
        LocalDate fechaFin = datosReporte.fechaFin;

        if(fechaFin == null || fechaInicio == null || fechaFin.toEpochDay() < fechaInicio.toEpochDay()){
            //las fechas no son válidas
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas no son válidas");
        }

        ReporteClientes reporte;
        try {
            reporte = reporteClienteService.generarReporte(fechaInicio, fechaFin);
        } catch (Exception e) {
            logger.error("generarReporteCliente: Se produjo un error al generar un reporte de clientes: " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al generar el reporte: " + e.getMessage(), e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(reporte);
    }

    @PostMapping("/materiales")
    public ResponseEntity<ReporteMateriales> generarReporteMaterial(@RequestBody ReporteDTO datosReporte){

        LocalDate fechaInicio = datosReporte.fechaInicio;
        LocalDate fechaFin = datosReporte.fechaFin;

        if(fechaFin == null || fechaInicio == null || fechaFin.toEpochDay() < fechaInicio.toEpochDay()){
            //las fechas no son válidas
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas no son válidas");
        }

        ReporteMateriales reporte;

        try {
            reporte = reporteMaterialService.generarReporte(fechaInicio, fechaFin);
        } catch (Exception e) {
            logger.error("generarReporteCliente: Se produjo un error al generar un reporte de materiales: " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al generar el reporte: " + e.getMessage(), e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(reporte);
    }

    @PostMapping("/pedidos")
    public ResponseEntity<ReportePedidos> generarReportePedidos(@RequestBody ReporteDTO datosReporte){

        LocalDate fechaInicio = datosReporte.fechaInicio;
        LocalDate fechaFin = datosReporte.fechaFin;

        if(fechaFin == null || fechaInicio == null || fechaFin.toEpochDay() < fechaInicio.toEpochDay()){
            //las fechas no son válidas
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas no son válidas");
        }

        ReportePedidos reporte;

        try {
            reporte = reportePedidoService.generarReporte(fechaInicio, fechaFin);
        } catch (Exception e) {
            logger.error("generarReporteCliente: Se produjo un error al generar un reporte de pedidos: " + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error al generar el reporte: " + e.getMessage(), e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(reporte);

    }


}
