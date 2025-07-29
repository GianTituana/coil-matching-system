-- Sample data for the COIL Matching System
-- This file populates the database with initial test data for development and testing

-- Insert sample universities
INSERT INTO UNIVERSIDAD (nombre, pais) VALUES 
('Universidad Técnica de Berlín', 'Alemania'),
('Universidad de Edimburgo', 'Reino Unido'),
('Universidad de São Paulo', 'Brasil'),
('Instituto Tecnológico de Monterrey', 'México'),
('Universidad de Sydney', 'Australia');

-- Insert sample courses
INSERT INTO CURSO (codigo, nombre, idioma, tematica, universidad_nombre) VALUES 
-- Computer Science courses
('CS101', 'Introducción a la Programación', 'Español', 'Ciencias de la Computación', 'Universidad de São Paulo'),
('CS102', 'Fundamentos de Programación', 'Inglés', 'Ciencias de la Computación', 'Universidad Técnica de Berlín'),
('CS201', 'Algoritmos y Estructuras de Datos', 'Inglés', 'Ciencias de la Computación', 'Universidad de Edimburgo'),
('CS301', 'Inteligencia Artificial', 'Español', 'Ciencias de la Computación', 'Instituto Tecnológico de Monterrey'),
('COMP150', 'Introduction to Programming', 'Inglés', 'Ciencias de la Computación', 'Universidad de Sydney'),

-- Mathematics courses
('MATH201', 'Cálculo Avanzado', 'Inglés', 'Matemáticas', 'Universidad de Edimburgo'),
('MATH101', 'Álgebra Linear', 'Español', 'Matemáticas', 'Universidad de São Paulo'),
('MATH301', 'Estadística Aplicada', 'Español', 'Matemáticas', 'Instituto Tecnológico de Monterrey'),
('MATH250', 'Discrete Mathematics', 'Inglés', 'Matemáticas', 'Universidad de Sydney'),

-- Business courses
('BUS101', 'Fundamentos de Administración', 'Español', 'Administración', 'Universidad de São Paulo'),
('BUS201', 'International Business', 'Inglés', 'Administración', 'Universidad de Edimburgo'),
('MGT300', 'Gestión de Proyectos', 'Español', 'Administración', 'Instituto Tecnológico de Monterrey'),

-- Engineering courses
('ENG101', 'Ingeniería de Software', 'Alemán', 'Ingeniería', 'Universidad Técnica de Berlín'),
('ENG201', 'Civil Engineering Fundamentals', 'Inglés', 'Ingeniería', 'Universidad de Sydney'),
('ING301', 'Ingeniería Industrial', 'Español', 'Ingeniería', 'Instituto Tecnológico de Monterrey');

-- Insert sample professors
INSERT INTO PROFESOR (email, nombre, universidad_nombre) VALUES 
-- Universidad de São Paulo professors
('j.silva@usp.br', 'João Silva', 'Universidad de São Paulo'),
('m.santos@usp.br', 'Maria Santos', 'Universidad de São Paulo'),
('c.oliveira@usp.br', 'Carlos Oliveira', 'Universidad de São Paulo'),

-- Universidad Técnica de Berlín professors
('h.mueller@tu-berlin.de', 'Hans Müller', 'Universidad Técnica de Berlín'),
('a.schmidt@tu-berlin.de', 'Anna Schmidt', 'Universidad Técnica de Berlín'),

-- Universidad de Edimburgo professors
('m.smith@ed.ac.uk', 'Mary Smith', 'Universidad de Edimburgo'),
('j.brown@ed.ac.uk', 'James Brown', 'Universidad de Edimburgo'),

-- Instituto Tecnológico de Monterrey professors
('l.garcia@itesm.mx', 'Luis García', 'Instituto Tecnológico de Monterrey'),
('a.rodriguez@itesm.mx', 'Ana Rodríguez', 'Instituto Tecnológico de Monterrey'),

-- Universidad de Sydney professors
('r.wilson@sydney.edu.au', 'Robert Wilson', 'Universidad de Sydney'),
('s.taylor@sydney.edu.au', 'Sarah Taylor', 'Universidad de Sydney');

-- Insert professor-course relationships
INSERT INTO PROFESOR_CURSO (profesor_email, curso_codigo) VALUES 
-- João Silva teaches CS and Math
('j.silva@usp.br', 'CS101'),
('j.silva@usp.br', 'MATH101'),

-- Maria Santos teaches Business
('m.santos@usp.br', 'BUS101'),

-- Carlos Oliveira teaches CS
('c.oliveira@usp.br', 'CS101'),

-- Hans Müller teaches CS and Engineering
('h.mueller@tu-berlin.de', 'CS102'),
('h.mueller@tu-berlin.de', 'ENG101'),

-- Anna Schmidt teaches Engineering
('a.schmidt@tu-berlin.de', 'ENG101'),

-- Mary Smith teaches Math
('m.smith@ed.ac.uk', 'MATH201'),

-- James Brown teaches CS and Business
('j.brown@ed.ac.uk', 'CS201'),
('j.brown@ed.ac.uk', 'BUS201'),

-- Luis García teaches CS and Engineering
('l.garcia@itesm.mx', 'CS301'),
('l.garcia@itesm.mx', 'ING301'),

-- Ana Rodríguez teaches Math and Business
('a.rodriguez@itesm.mx', 'MATH301'),
('a.rodriguez@itesm.mx', 'MGT300'),

-- Robert Wilson teaches CS and Math
('r.wilson@sydney.edu.au', 'COMP150'),
('r.wilson@sydney.edu.au', 'MATH250'),

-- Sarah Taylor teaches Engineering
('s.taylor@sydney.edu.au', 'ENG201');