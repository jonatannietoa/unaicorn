# Documentación del Modelo de Dominio: App de Empleo (Job Swiping)

**Versión:** 1.0
**Fecha:** 2024-07-19

## 1. Concepto General

Este documento describe el modelo de dominio para una aplicación de búsqueda de empleo basada en un sistema de "swiping" o deslizamiento. El núcleo de la aplicación es un mecanismo de **doble opt-in**: la comunicación entre un candidato y una empresa solo es posible después de que ambos hayan mostrado interés mutuo, creando un "Match".

-   **Candidato**: Desliza a la derecha sobre las ofertas de trabajo que le interesan.
-   **Empresa**: A través de un reclutador, desliza a la derecha sobre los perfiles de candidatos que considera adecuados para una oferta específica.
-   **Match**: Ocurre cuando un candidato y una empresa muestran interés mutuo por la misma oportunidad, abriendo un canal de comunicación.

## 2. Entidades del Dominio

A continuación se detallan las entidades principales que componen el sistema.

### Entidad: `Compañía (Company)`

Representa la organización que publica ofertas de trabajo.

> Es el perfil público de la empresa. Contiene información general que ven los candidatos para evaluar la cultura y reputación de la organización.

#### Atributos Clave

-   `id_company`: Identificador único (Clave Primaria).
-   `name`: Nombre comercial de la empresa.
-   `description`: Texto descriptivo sobre la misión, visión y cultura de la empresa.
-   `logo_url`: URL a la imagen del logo.
-   `website`: Enlace al sitio web oficial.
-   `sector`: Industria a la que pertenece (ej. "Tecnología", "Salud", "Finanzas").
-   `location`: Ubicación de la sede principal.

#### Relaciones

-   Una **Compañía** tiene uno o más `Reclutadores`.
-   Una **Compañía** publica una o más `Ofertas de Trabajo`.

### Entidad: `Oferta de Trabajo (Job)`

Representa una vacante específica publicada por una compañía. Es el "perfil" que los candidatos ven y sobre el que deslizan.

> Contiene todos los detalles de un puesto de trabajo, incluyendo responsabilidades, requisitos y beneficios.

#### Atributos Clave

-   `id_job`: Identificador único (Clave Primaria).
-   `title`: Título del puesto (ej. "Desarrollador/a de Software Senior").
-   `description`: Descripción detallada de las responsabilidades y tareas.
-   `salary_range`: Rango salarial ofrecido.
-   `job_type`: Enumeración del tipo de contrato (ej. `FULL_TIME`, `PART_TIME`, `CONTRACT`).
-   `location_type`: Enumeración de la modalidad (ej. `ON_SITE`, `REMOTE`, `HYBRID`).
-   `required_skills`: Lista de habilidades o tecnologías requeridas.
-   `is_active`: Booleano (`true`/`false`) para indicar si la oferta sigue abierta a recibir candidatos.

#### Relaciones

-   Pertenece a **una** `Compañía`.
-   Es gestionada por **un** `Reclutador`.
-   Puede tener **muchas** `Interacciones (Match)` con diferentes candidatos.

### Entidad: `Candidato (Candidate)`

Representa al usuario que busca empleo. Su perfil es lo que las empresas ven y sobre lo que deslizan.

> Es el perfil profesional del usuario, que funciona como un CV dinámico dentro de la aplicación.

#### Atributos Clave

-   `id_candidate`: Identificador único (Clave Primaria).
-   `full_name`: Nombre completo del candidato.
-   `headline`: Un titular profesional corto y llamativo.
-   `summary`: Un párrafo de resumen profesional.
-   `cv_url`: (Opcional) Enlace a un CV tradicional en formato PDF.
-   `skills`: Lista de habilidades y competencias del candidato.
-   `experience`: Lista de experiencias laborales (puede ser una entidad separada).
-   `education`: Lista de su formación académica (puede ser una entidad separada).

#### Relaciones

-   Un **Candidato** puede tener **muchas** `Interacciones (Match)` con diferentes ofertas de trabajo.

### Entidad: `Interacción (Match)`

Esta es la entidad central que conecta todo el sistema. Registra el interés de ambas partes y determina si se produce un "match".

> Modela la relación única entre un `Candidato` y una `Oferta de Trabajo`. Se crea en el momento en que una de las dos partes muestra interés por primera vez.

#### Atributos Clave

-   `id_match`: Identificador único de la interacción (Clave Primaria).
-   `job_id`: Clave foránea que apunta a la `Oferta de Trabajo`.
-   `candidate_id`: Clave foránea que apunta al `Candidato`.
-   `candidate_interest`: Estado del swipe del candidato (`PENDING`, `LIKED`, `DISLIKED`).
-   `company_interest`: Estado del swipe de la empresa (`PENDING`, `LIKED`, `DISLIKED`).
-   `match_status`: Estado derivado que se actualiza automáticamente (`PENDING`, `MATCHED`, `NO_MATCH`).

#### Lógica de Negocio

-   `match_status` se convierte en `MATCHED` si y solo si `candidate_interest` es `LIKED` **Y** `company_interest` es `LIKED`.

## 3. Diagrama de Relaciones Simplificado

```
[Compañía] 1--< [Oferta de Trabajo] >--< [Interacción] >--< [Candidato]
     |
     | 1..*
     v
[Reclutador]
```

**Leyenda:**
- `1--<`: Relación uno a muchos.
- `>--<`: Relación muchos a muchos (a través de la tabla `Interacción`).

## 4. Flujo Básico de la Aplicación

1.  **Registro y Publicación**:
    -   Una `Compañía` se registra y añade a sus `Reclutadores`.
    -   Un `Reclutador` publica una nueva `Oferta de Trabajo`.

2.  **Acción del Candidato**:
    -   La aplicación muestra al `Candidato` una `Oferta de Trabajo`.
    -   Si al `Candidato` le interesa, desliza a la derecha.
    -   El sistema registra esta acción actualizando el campo `candidate_interest` a `LIKED` en la tabla `Interacción` correspondiente.

3.  **Acción de la Empresa**:
    -   La aplicación muestra al `Reclutador` el perfil de un `Candidato` potencial para su `Oferta de Trabajo`.
    -   Si el `Reclutador` considera que el perfil es adecuado, desliza a la derecha.
    -   El sistema registra la acción actualizando `company_interest` a `LIKED`.

4.  **¡Match!**:
    -   Un trigger o una lógica en el backend detecta que un registro de `Interacción` tiene ambos campos de interés como `LIKED`.
    -   El `match_status` cambia a `MATCHED`.
    -   El sistema envía una notificación a ambas partes y habilita la funcionalidad de chat entre ellos.
```
