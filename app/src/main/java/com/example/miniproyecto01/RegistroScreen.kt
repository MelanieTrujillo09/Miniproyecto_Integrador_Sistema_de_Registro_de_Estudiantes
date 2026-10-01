package com.example.miniproyecto01


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.miniproyecto01.data.PreferencesManager

val carreras = listOf("Ingeniería de Software", "Ingeniería Industrial", "Contaduría", "Administración")

@Composable
fun RegistroScreen(onNavigateToDetalle: (String, String, String, String, Boolean) -> Unit) {
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }

    var matricula by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var carrera by remember { mutableStateOf("Seleccionar Carrera") }
    var turno by remember { mutableStateOf("Matutino") }
    var activo by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }

    // Carga el último registro guardado al abrir la pantalla
    LaunchedEffect(Unit) {
        matricula = preferencesManager.getLastMatricula()
        nombre = preferencesManager.getLastNombre()
        val carreraGuardada = preferencesManager.getLastCarrera()
        if (carreraGuardada.isNotEmpty()) carrera = carreraGuardada
        turno = preferencesManager.getLastTurno()
        activo = preferencesManager.getLastEstatus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = "Sistema de Registro de Estudiantes",
            fontSize = 22.sp,
            style = MaterialTheme.typography.headlineSmall
        )

        HorizontalDivider()

        // Matrícula
        OutlinedTextField(
            value = matricula,
            onValueChange = { matricula = it },
            label = { Text("Matrícula") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Nombre completo
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre completo") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Carrera - DropdownMenu
        Text("Carrera:", style = MaterialTheme.typography.titleMedium)
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = carrera,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                carreras.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            carrera = opcion
                            expanded = false
                        }
                    )
                }
            }
        }

        // Turno - RadioButton
        Text("Turno:", style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = (turno == "Matutino"),
                onClick = { turno = "Matutino" }
            )
            Text("Matutino")

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = (turno == "Vespertino"),
                onClick = { turno = "Vespertino" }
            )
            Text("Vespertino")
        }

        // Estatus - Switch
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Estatus Activo:", style = MaterialTheme.typography.titleMedium)
            Switch(
                checked = activo,
                onCheckedChange = { activo = it }
            )
        }

        HorizontalDivider()

        // Botón para registrar y navegar
        Button(
            onClick = {
                preferencesManager.saveLastRegistro(matricula, nombre, carrera, turno, activo)
                onNavigateToDetalle(matricula, nombre, carrera, turno, activo)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = matricula.isNotBlank() && nombre.isNotBlank()
        ) {
            Text("Registrar y Ver Detalle")
        }
    }
}