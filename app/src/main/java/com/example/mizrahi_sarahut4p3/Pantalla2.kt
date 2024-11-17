package com.example.mizrahi_sarahut4p3


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mizrahi_sarahut4p3.ui.theme.Background_L
import com.example.mizrahi_sarahut4p3.ui.theme.Purple40
import com.example.mizrahi_sarahut4p3.ui.theme.Purple80
import com.example.mizrahi_sarahut4p3.ui.theme.PurpleGrey80


@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun Pantalla2(navController: NavController) {

    val planetas = listOf(
        Planeta("Mercurio", "0,382", "0,387", "5400"),
        Planeta("Venus", "0,949", "0,723", "5250"),
        Planeta("Tierra", "1", "1", "5520"),
        Planeta("Marte", "0,53", "1,542", "3960"),
        Planeta("Júpiter", "11,2", "5,203", "1350"),
        Planeta("Saturno", "9,41", "9,539", "700"),
        Planeta("Urano", "3,38", "19,81", "1200"),
        Planeta("Neptuno", "3,81", "30,07", "500"),
        Planeta("Plutón", "???", "39,44", "5?")
    )

    //Variable de estado que indica si el menú está expandido o no (true o false).
    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    //Variable que guarda el texto de la opción seleccionada, para mostrarla en el TextField
    var opcionSelec1 by remember { mutableStateOf<Planeta?>(null) }
    var opcionSelec2 by remember { mutableStateOf<Planeta?>(null) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier
            .background(Background_L)
            .fillMaxWidth()
            .padding(20.dp))
        {
            Text("El Sol", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
        ) {

            Text("Compara planetas", fontSize = 20.sp, modifier = Modifier.padding(16.dp),fontWeight = FontWeight.Bold)

            //TextFields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                //Primer textfield
                ExposedDropdownMenuBox(
                    expanded = expanded1,//Define si el menú está desplegado o no.
                    onExpandedChange = {expanded1 = !expanded1},//Cambia el valor de expanded cada vez que se hace clic en el TextField
                    modifier = Modifier.weight(1f).height(10.dp)  //Controla el espacio de la caja del textfield
                ) {
                    TextField(
                        modifier = Modifier
                            .menuAnchor(), //Asegura que el menú esté alineado con el campo de texto
                        readOnly = true,                  //Evita que el usuario escriba directamente en el campo
                        value = opcionSelec1?.nombre ?: "",       //Muestra la opción seleccionada
                        onValueChange = {},
                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                            focusedContainerColor = Purple40,
                            unfocusedContainerColor = Purple40
                        )
                    )
                            //Desplegable dentro del Textfield
                            ExposedDropdownMenu(
                                expanded = expanded1,
                                onDismissRequest = {expanded1 = false}//El menú aparece cuando es true, y desaparece cuando es false
                                //Cierra el menú cuando el usuario hace clic fuera de él.

                            ) {
                                //Añadir las Opciones del Menú
                                //forEachIndexed para crear los indices
                                planetas.forEach { planeta ->
                                    DropdownMenuItem(
                                        text = { Text(planeta.nombre) },
                                        onClick = {
                                            opcionSelec1 = planeta
                                            expanded1 = false
                                        },
                                        //para asegurarte de que el menú desplegable tenga el mismo espaciado que el su textField
                                        //para cambiarlo usar PaddingValues
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding

                                    )
                                }
                    }
                }

                //Segundo textfield
                ExposedDropdownMenuBox(
                    expanded = expanded2,//Define si el menú está desplegado o no.
                    onExpandedChange = {expanded2 = !expanded2},//Cambia el valor de expanded cada vez que se hace clic en el TextField
                            modifier = Modifier.weight(1f).height(10.dp) //Controla el espacio de la caja del textfield
                ) {
                    TextField(
                        modifier = Modifier.menuAnchor(), //Asegura que el menú esté alineado con el campo de texto
                        readOnly = true,                  //Evita que el usuario escriba directamente en el campo
                        value = opcionSelec2?.nombre ?: "",       //Muestra la opción seleccionada
                        onValueChange = {},
                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                            focusedContainerColor = Purple40,
                            unfocusedContainerColor = Purple40
                        )
                    )
                    //Desplegable dentro del Textfield
                    ExposedDropdownMenu(
                        expanded = expanded2,
                        onDismissRequest = {expanded2 = false}//El menú aparece cuando es true, y desaparece cuando es false
                        //Cierra el menú cuando el usuario hace clic fuera de él.

                    ) {
                        //Añadir las Opciones del Menú
                        //forEachIndexed para crear los indices
                        planetas.forEach { planeta ->
                            DropdownMenuItem(
                                text = { Text(planeta.nombre) },
                                onClick = {
                                    opcionSelec2 = planeta
                                    expanded2 = false
                                },
                                //para asegurarte de que el menú desplegable tenga el mismo espaciado que el su textField
                                //para cambiarlo usar PaddingValues
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding

                            )

                        }
                    }
                }

            }

            Spacer(modifier = Modifier.size(30.dp))

            //Datos
            Text("Diámetro",color = Purple80,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp)

            Row (modifier = Modifier.fillMaxWidth().background(PurpleGrey80),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(
                    text = opcionSelec1?.diametro ?: "",
                    color = Purple80,
                    fontSize = 16.sp
                )
              
                Text(
                    text = opcionSelec2?.diametro ?: "",
                    color = Purple80,
                    fontSize = 16.sp
                )
            }


            Text("Distancia al Sol",color = Purple80,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp)

            Row (modifier = Modifier.fillMaxWidth().background(PurpleGrey80),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(
                    text = opcionSelec1?.distanciaAlSol ?: "",
                    color = Purple80,
                    fontSize = 16.sp
                )
                Text(
                    text = opcionSelec2?.distanciaAlSol ?: "",
                    color = Purple80,
                    fontSize = 16.sp
                )
            }

            Text("Densidad", color = Purple80,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp)

            Row (modifier = Modifier.fillMaxWidth().background(PurpleGrey80),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(
                    text = opcionSelec1?.densidad ?: "",
                    color = Purple80,
                    fontSize = 16.sp
                )
                Text(
                    text = opcionSelec2?.densidad ?: "",
                    color = Purple80,
                    fontSize = 16.sp
                )
            }
        }
    }
}