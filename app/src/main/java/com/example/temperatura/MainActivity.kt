package com.example.temperatura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.temperatura.ui.theme.TemperaturaTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemperaturaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConversorTemperatura ()
                }
            }
        }
    }
}


@Composable
fun ConversorTemperatura() {
    var temperaturaFahrenheit by remember {
        mutableStateOf("")
    }

    var temperaturaCelsius by remember {
        mutableStateOf("")
    }

    var ResultadoCtoF by remember {
        mutableStateOf("")
    }

    var ResultadoFtoC by remember {
        mutableStateOf("")
    }

    Column (modifier = Modifier.padding(16.dp)) {
        Text(text = "Conversor de temperatura",
            fontSize = 26.sp
        )

        Text(text = "Digite a temperatura em graus Fahrenheit",
            fontSize = 14.sp
        )

        OutlinedTextField(
            value = temperaturaFahrenheit,
            onValueChange = { temperaturaFahrenheit = it },
            label = {
                Text(text = "Graus Fahrenheit")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Text(text = "Digite a temperatura em graus Celsius",
            fontSize = 14.sp
        )

        OutlinedTextField(
            value = temperaturaCelsius,
            onValueChange = { temperaturaCelsius = it },
            label = {
                Text(text = "Graus Celsius")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (temperaturaFahrenheit.isNotBlank()) {
                val tempF = temperaturaFahrenheit.toDouble()
                ResultadoFtoC = "${String.format("%.0f", fahrenheitParaCelsius(tempF))} °C"
            }
            if (temperaturaCelsius.isNotBlank()) {
                val tempC = temperaturaCelsius.toDouble()
                ResultadoCtoF = "${String.format("%.0f", celsiusParaFahrenheit(tempC))} °F"
            }
        }) {
            Text(text = "Converter")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (ResultadoFtoC.isNotBlank() || ResultadoCtoF.isNotBlank()) {
            Text(
                text = "Conversão de Fahrenheit para Celsius: $ResultadoFtoC"
            )
            Text(
                text = "Conversão de Celsius para Fahrenheit: $ResultadoCtoF"
            )
        }
    }
}

fun celsiusParaFahrenheit(temperaturaCelsius: Double): Double {
    return temperaturaCelsius * 1.8 + 32
}

fun fahrenheitParaCelsius(temperaturaFahrenheit: Double): Double {
    return (temperaturaFahrenheit - 32) / 1.8
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TemperaturaTheme {
        Greeting("Android")
    }
}