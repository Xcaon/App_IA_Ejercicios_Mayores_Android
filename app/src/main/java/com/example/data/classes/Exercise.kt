package com.example.data.classes

// Aqui declaramos la clase de los ejercicios
data class Exercise(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val category: String
) {
    companion object {
        val listadoEjercicios: List<Exercise> = listOf(
            Exercise(1, "Caminata ligera", "Ideal para comenzar a moverse si llevas tiempo inactivo. Puedes empezar con 10-15 minutos diarios.", "https://img.freepik.com/foto-gratis/mujer-caminando-sobre-puente-madera-tono-vintage_335224-1010.jpg", "Movilidad"),
            Exercise(2, "Marcha en el sitio", "Camina sin desplazarte, perfecto para interiores y personas con poco equilibrio.", "https://img.freepik.com/foto-gratis/cerca-hermosa-mujer-lujosa-casa-travcel_23-2149140854.jpg", "Equilibrio"),
            Exercise(3, "Estiramientos matutinos", "Serie suave de estiramientos para empezar el día, enfocada en movilidad articular.", "https://img.freepik.com/foto-gratis/tiro-medio-mujer-sonriente-estirando_23-2148785545.jpg", "Rigidez"),
            Exercise(4, "Sentadillas asistidas", "Usa una silla para realizar sentadillas seguras que fortalezcan piernas y glúteos.", "https://img.freepik.com/foto-gratis/mujer-joven-ejercitar-casa-mat_23-2148896463.jpg", "Fortalecimiento"),
            Exercise(5, "Bicicleta estática", "Cardio de bajo impacto ideal para personas con sobrepeso o problemas articulares.", "https://img.freepik.com/foto-gratis/persona-haciendo-ciclismo-indoor_23-2149270269.jpg", "Pérdida de peso"),
            Exercise(6, "Ejercicios con banda elástica", "Fortalece brazos y espalda con bandas de resistencia, adaptables a cualquier nivel.", "https://img.freepik.com/foto-gratis/vista-lateral-mujer-entrenamiento-banda-elastica_23-2149557845.jpg", "Fortalecimiento"),
            Exercise(7, "Tai Chi", "Movimientos lentos y controlados que ayudan al equilibrio y la concentración.", "https://img.freepik.com/foto-gratis/persona-practicando-tai-chi-interiores_23-2150218276.jpg", "Equilibrio"),
            Exercise(8, "Yoga suave", "Posturas simples para estirar y fortalecer suavemente todo el cuerpo.", "https://img.freepik.com/foto-gratis/mujer-haciendo-yoga-limpiar-chakra_23-2149275984.jpg", "Bienestar general"),
            Exercise(9, "Ejercicio en grupo (bailes)", "Sesiones de baile moderado para promover la actividad social y cardiovascular.", "https://img.freepik.com/foto-gratis/personas-que-toman-parte-clase-terapia-baile_23-2149346588.jpg", "Actividad social"),
            Exercise(10, "Subir escaleras", "Ejercicio sencillo que mejora la resistencia cardiovascular y la fuerza en piernas.", "https://img.freepik.com/foto-gratis/hombre-mujer-ejecutando-pasos-trabajo_23-2147758019.jpg", "Pérdida de peso"),
            Exercise(11, "Puente de glúteos", "Acostado boca arriba, fortalece el tronco inferior sin cargar las rodillas.", "https://img.freepik.com/foto-gratis/mujer-haciendo-ejercicio-yoga_23-2148108745.jpg", "Fortalecimiento"),
            Exercise(12, "Remo con banda elástica", "Ejercicio sentado que fortalece la espalda, ideal para prevenir dolores.", "https://img.freepik.com/foto-gratis/hombre-atletico-trabajando-banda-roja-estiramiento_23-2148605639.jpg", "Dolor de espalda"),
            Exercise(13, "Respiración diafragmática", "Técnica de respiración que ayuda a controlar el estrés y mejora la oxigenación.", "https://img.freepik.com/fotos-premium/seccion-media-mujer-sentada-contra-pared_1048944-3829139.jpg?w=740", "Relajación"),
            Exercise(14, "Equilibrio con una pierna", "De pie, intenta mantener el equilibrio en una pierna durante 10 segundos.", "https://img.freepik.com/foto-gratis/mujer-equilibrio-meditar_23-2147648622.jpg", "Equilibrio"),
            Exercise(15, "Trote suave en el sitio", "Aumenta el ritmo cardíaco sin desplazamientos. Adaptable a espacio pequeño.", "https://img.freepik.com/foto-gratis/corredores-entrenando-al-aire-libre_1098-3693.jpg", "Cardio ligero"),
            Exercise(16, "Movilidad de hombros", "Rotaciones suaves de brazos para evitar rigidez articular.", "https://img.freepik.com/foto-gratis/vista-frontal-terapeuta-osteopatico-comprobando-articulacion-hombro-paciente-femenino_23-2148846590.jpg", "Rigidez"),
            Exercise(17, "Estiramientos en silla", "Perfectos para personas con movilidad reducida.", "https://img.freepik.com/foto-gratis/vista-lateral-hombre-entrenando-banda-elastica_23-2149557832.jpg", "Movilidad"),
            Exercise(18, "Bailes latinos", "Ejercicio social, divertido y cardiovascular. Útil para personas activas.", "https://img.freepik.com/foto-gratis/joven-pareja-baile-bailando-tango_23-2148003968.jpg", "Actividad social"),
            Exercise(19, "Saltos suaves (jumping jacks modificados)", "Adaptables a personas sin problemas articulares.", "https://img.freepik.com/foto-gratis/vista-frontal-artistas-hip-hop-camisa-bailando-afuera_23-2148496976.jpg", "Cardio moderado"),
            Exercise(20, "Ejercicio con pelotas de goma", "Apretar y soltar una pelota ayuda a fortalecer manos y antebrazos.", "https://img.freepik.com/foto-gratis/hombre-mujer-tenencia-rosa-bola_23-2147688537.jpg", "Fortalecimiento"),
            Exercise(21, "Plancha en pared", "Plancha modificada de bajo impacto para fortalecer abdomen y brazos.", "https://content.cuerpomente.com/medio/2025/01/16/pilates-pared_4277d760_250116131054_1280x720.webp", "Core"),
            Exercise(22, "Giro de torso sentado", "Mejora movilidad de columna y flexibilidad lumbar.", "https://img.freepik.com/foto-gratis/joven-haciendo-ejercicios-deportivos-casa_1328-3040.jpg", "Espalda"),
            Exercise(23, "Paso lateral con banda", "Activa glúteos y caderas con movimientos controlados.", "https://img.freepik.com/foto-gratis/trabajando-tener-piernas-mas-fuertes-mujer-deportiva-ejercitando-musculos-sus-piernas-banda-resistencia-su-entrenamiento-cruzado-casa_662251-1381.jpg?t=st=1744732268~exp=1744735868~hmac=c2636fa1790a2f678b85148777985bad7fe4bdfd62fabe19af949cd90ea36e16&w=900", "Caderas"),
            Exercise(24, "Bicicleta en el suelo", "Ideal para abdominales sin forzar el cuello ni espalda.", "https://images.unsplash.com/photo-1586281380349-632531db7d2e", "Abdomen"),
            Exercise(25, "Tijeras con piernas", "Fortalece abdomen bajo y muslos.", "https://media.glamour.mx/photos/64dddd1c8bcbb1503ffa0cd3/16:9/w_1920,c_limit/ejercicio-de-tijeras.jpg", "Piernas"),
            Exercise(26, "Rodillas al pecho", "Estiramiento ideal para la zona lumbar.", "https://img.freepik.com/foto-gratis/ejercicios-relajacion_1385-2868.jpg", "Espalda"),
            Exercise(27, "Press de pecho con banda", "Fortalece el tren superior sin necesidad de pesas.", "https://images.unsplash.com/photo-1599058917625-3b8b2025d743", "Pecho"),
            Exercise(28, "Sombra de boxeo", "Ejercicio dinámico de brazos y core que también desestresa.", "https://img.freepik.com/foto-gratis/mujer-tiro-completo-guantes-boxeo_23-2149036397.jpg", "Cardio moderado"),
            Exercise(29, "Círculos de tobillos", "Previene rigidez y mejora la circulación en pies.", "https://img.freepik.com/fotos-premium/bolas-agujas-colores-masajes-fisioterapia-fondo-blanco-imagen-ninos_192298-906.jpg?w=996", "Movilidad"),
            Exercise(30, "Respiración consciente en posición tumbada", "Ideal para relajación total antes de dormir.", "https://img.freepik.com/foto-gratis/grupo-deportistas-ejercicio-cuerpo-muerto_1163-4995.jpg", "Relajación")
        )

    }


}
