package com.example.mizrahi_sarahut4p3

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mizrahi_sarahut4p3.ui.theme.Background_L


//@Preview(showBackground = true)
@Composable
fun Pantalla1(navController: NavController) {

    //val Super by remember { mutableStateOf(obtenerSunImages()) }
    //Actualizacion variable para poder usar add y remove
    val Super = remember { mutableStateListOf(*obtenerSunImages().toTypedArray()) }


    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        //Menu Sun Images
        MenuCabecera(navController)


        Box(modifier = Modifier.background(Background_L)) {
            //Cards
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                content = {
                    items(Super) { suns ->
                        ItemSun(
                            sun = suns,
                            onItemSelected = { Toast.makeText(context, it.nombre, Toast.LENGTH_SHORT).show() },
                            copiar = { Super.add(it.copy()) }, //Copia al final de la lista
                            eliminar = { Super.remove(it) } ,   //Elimina el elemento seleccionado
                        )}
                    })
                }
            }
        }


fun obtenerSunImages(): List<Sun> {
    return listOf(
        Sun( "Corona solar",R.drawable.corona_solar),
        Sun("Erupción solar", R.drawable.erupcionsolar),
        Sun("Espiculas",R.drawable.espiculas),
        Sun("Filamentos",R.drawable.filamentos),
        Sun("Magnetosfera",R.drawable.magnetosfera),
        Sun("Mancha solar",R.drawable.manchasolar),
    )
}


@Composable
fun ItemSun(sun: Sun,
            onItemSelected: (Sun)-> Unit,
            copiar: (Sun) -> Unit,
            eliminar: (Sun) -> Unit)
{
    var expanded by remember { mutableStateOf(false) }

    Card( modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()
        .clickable { onItemSelected(sun) }
    )
    {
        Column (
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = sun.img),
                contentDescription = "sunImages",
                modifier = Modifier.fillMaxWidth()
                    //IMPORTANTE
                    .aspectRatio(1f),   //Es lo unico que alinea los par de Cards. IMPORTANTE
                contentScale = ContentScale.Crop
            )

            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically, //Alinea los puntos con el texto izquierdo
                horizontalArrangement = Arrangement.SpaceBetween)   //Posiciona los puntos al final derecha
              {
                Text(text = sun.nombre, modifier = Modifier.padding(8.dp), fontWeight = FontWeight.Bold)
                //Icono 3 puntitos boton
                IconButton(
                    onClick = { expanded = true })
                {
                    Icon(Icons.Default.MoreVert, contentDescription = null)
                    //Aqui el desplegable
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        //Items
                        DropdownMenuItem(
                            text = { Text("Copiar") },
                            onClick = { copiar(sun) },
                        )
                        DropdownMenuItem(
                            text = { Text("Eliminar") },
                            onClick = { eliminar(sun) },
                        )
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun MenuCabecera(navController: NavController) {

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .padding(start = 10.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween)   //Posiciona los puntos al final derecha
        {
                Text("Sun images:", fontSize = 20.sp, modifier = Modifier.padding(6.dp))
                //Icono 3 puntitos boton
                IconButton(
                    onClick = { expanded = true })
                {
                    //Icono puntitos
                    Icon(Icons.Default.MoreVert, contentDescription = null)
                    //Desplegable
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        //Items
                        DropdownMenuItem(
                            text = { Text("Siguiente pantalla") },
                            onClick = { navController.navigate("Pantalla2") },
                        )

                    }
                }
        }
    }
}



