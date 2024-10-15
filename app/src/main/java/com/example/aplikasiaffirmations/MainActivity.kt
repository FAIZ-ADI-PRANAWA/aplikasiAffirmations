package com.example.aplikasiaffirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Affirmations.model.Affirmations
import com.example.aplikasiaffirmations.data.Datasource
import com.example.aplikasiaffirmations.ui.theme.AplikasiAffirmationsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplikasiAffirmationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp() // Memanggil Composable AffirmationsApp
                }
            }
        }
    }
}

// Composable utama aplikasi
@Composable
fun AffirmationsApp() {
    // Menampilkan daftar affirmation
    AffirmationList(
        affirmationList = Datasource().loadAffirmations(),
        // Mengambil data affirmation dari Datasource
    )
}

@Composable
fun AffirmationList(affirmationList: List<Affirmations>, modifier: Modifier = Modifier) {
    // Menggunakan LazyColumn untuk menampilkan daftar secara efisien
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            // Menampilkan setiap affirmation menggunakan AffirmationCard
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmations, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            // Menampilkan gambar affirmation
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            // Menampilkan teks affirmation
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

// Preview untuk AffirmationCard
@Preview
@Composable
private fun AffirmationCardPreview() {
    AffirmationCard(Affirmations(R.string.affirmation1, R.drawable.image1))
}