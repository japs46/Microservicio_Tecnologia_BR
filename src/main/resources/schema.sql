CREATE TABLE IF NOT EXISTS tecnologia (
    id SERIAL PRIMARY KEY,               -- Clave primaria autogenerada
    nombre VARCHAR(50) NOT NULL UNIQUE,  -- El nombre no se puede repetir y es obligatorio
    descripcion VARCHAR(90) NOT NULL     -- La descripción es obligatoria
);