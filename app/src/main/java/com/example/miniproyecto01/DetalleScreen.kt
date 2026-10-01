package com.example.miniproyecto01


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetalleScreen(
    matricula: String,
    nombre: String,
    carrera: String,
    turno: String,
    activo: Boolean,
    onVolver: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro Confirmado",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(20.dp)) {
                DetalleItem("Matrícula", matricula)
                DetalleItem("Nombre", nombre)
                DetalleItem("Carrera", carrera)
                DetalleItem("Turno", turno)
                DetalleItem("Estatus", if (activo) "Activo" else "Inactivo")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Formulario")
        }
    }
}

@Composable
fun DetalleItem(etiqueta: String, valor: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
        Text(text = "$etiqueta: ", fontWeight = FontWeight.Bold)
        Text(text = valor)
    }
}