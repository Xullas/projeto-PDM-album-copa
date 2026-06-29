package com.pdm.grupo2.albumfigurinhas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.grupo2.albumfigurinhas.R

data class CountryItem(val id: String, val name: String, val flagRes: Int)

@Composable
fun CompetitionScreen(
    onTeamSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val countries = listOf(
        CountryItem("brazil", "Brasil", R.drawable.flag_brasil),
        CountryItem("argentina", "Argentina", R.drawable.flag_argentina),
        CountryItem("france", "França", R.drawable.flag_franca),
        CountryItem("spain", "Espanha", R.drawable.flag_espanha),
        CountryItem("portugal", "Portugal", R.drawable.flag_portugal),
        CountryItem("netherlands", "Holanda", R.drawable.flag_holanda),
        CountryItem("mexico", "México", R.drawable.flag_mexico),
        CountryItem("colombia", "Colômbia", R.drawable.flag_colombia),
        CountryItem("ecuador", "Equador", R.drawable.flag_equador)
    )

    val scrollState = rememberScrollState()

    // O Scroll agora engloba este Box principal
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Image(
            painter = painterResource(id = R.drawable.bkg_inicial),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

        // 1ª Camada interna: A Imagem de Fundo acompanha o tamanho total do Scroll.
        // Usamos ContentScale.FillBounds para que ela estique até o final da lista de bandeiras,
        // eliminando completamente o fundo vinho backup.
        Image(
            painter = painterResource(id = R.drawable.bkg_inicial),
            contentDescription = "Fundo Abstrato Copa 2026",
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(
                    translationY = -with(LocalDensity.current) { 120.dp.toPx() }
                ),
            contentScale = ContentScale.Crop
        )

        // 2ª Camada interna: O Conteúdo estruturado
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Cabeçalho Superior (Fundo Preto)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.8f))
                    .padding(top = 56.dp, bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "COPA DO MUNDO",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "FIFA 2026",
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_taca_copa),
                    contentDescription = "Taça da FIFA",
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .height(350.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(120.dp))

            // Contêiner Inferior de SELEÇÕES (Fundo Preto)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.8f))
                    .padding(top = 16.dp, bottom = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "SELEÇÕES",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                val chunks = countries.chunked(3)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    chunks.forEach { rowCountries ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            rowCountries.forEach { country ->
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(80.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color.White.copy(alpha = 0.15f))
                                        .clickable { onTeamSelect(country.id) }
                                        .padding(6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = country.flagRes),
                                        contentDescription = country.name,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(8.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 917)
@Composable
fun CompetitionScreenPreview() {
    CompetitionScreen(onTeamSelect = {})
}