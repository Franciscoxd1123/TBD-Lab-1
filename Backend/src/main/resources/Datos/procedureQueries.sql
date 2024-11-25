CREATE OR REPLACE PROCEDURE reporte_usuarios_mas_activos()
LANGUAGE plpgsql
AS $$
BEGIN
    -- Reportar los usuarios más activos en general
    RAISE NOTICE 'Usuarios con más operaciones en total:';
    PERFORM * FROM (
        SELECT 
            usuario,
            COUNT(*) AS total_operaciones
        FROM log_auditoria
        GROUP BY usuario
        ORDER BY total_operaciones DESC
        LIMIT 10
    ) AS reporte_general;

    -- Reportar los detalles de operaciones por usuario
    RAISE NOTICE 'Detalle de operaciones por usuario:';
    PERFORM * FROM (
        SELECT 
            usuario,
            operacion,
            COUNT(*) AS total_por_operacion
        FROM log_auditoria
        GROUP BY usuario, operacion
        ORDER BY usuario, total_por_operacion DESC
    ) AS reporte_detallado;
END;
$$;