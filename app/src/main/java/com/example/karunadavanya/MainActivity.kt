package com.example.karunadavanya

import android.media.MediaPlayer
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import com.google.firebase.database.FirebaseDatabase


data class AlertData(
    val animalName: String = "",
    val location: String = ""
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KarunadaVanyaApp()
        }
    }
}

@Composable
fun KarunadaVanyaApp() {

    var currentScreen by remember { mutableStateOf("welcome") }

    when (currentScreen) {

        "welcome" -> WelcomeScreen {
            currentScreen = "home"
        }

        "home" -> HomeScreen(
            onWildlifeClick = { currentScreen = "wiki" },

            onMovementClick = { currentScreen = "alert" },

            onAlertHistoryClick = { currentScreen = "history" },

            onSafetyClick = { currentScreen = "safety" },

            onForestSoundClick = { currentScreen = "sound" },

            onForestMapClick = { currentScreen = "forestmap" }

        )

        "wiki" -> WildlifeWikiScreen {
            currentScreen = "home"
        }

        "alert" -> MovementAlertScreen {
            currentScreen = "home"
        }

        "safety" -> SafetyGuideScreen {
            currentScreen = "home"
        }

        "sound" -> ForestSoundsScreen {
            currentScreen = "home"
        }

        "forestmap" -> ForestMapScreen {
            currentScreen = "home" }

        "history" -> AlertHistoryScreen {
            currentScreen = "home"
        }
    }
}

@Composable
fun TopBar(
    title: String,
    onBackClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 16.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Default.ArrowBack,

            contentDescription = "Back",

            modifier = Modifier
                .size(30.dp)
                .clickable {
                    onBackClick()
                },

            tint = Color.Black
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,

            fontSize = 24.sp,

            fontWeight = FontWeight.Bold,

            color = Color(0xFF1B5E20)
        )
    }
}

@Composable
fun WelcomeScreen(onEnterClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1B5E20),
                        Color(0xFF4CAF50)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // LOGO (clean look)
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "KARUNADA-VANYA",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Wildlife Education & Alert System",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = onEnterClick,
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = "Let's Go",
                    color = Color(0xFF1B5E20),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    onWildlifeClick: () -> Unit,
    onMovementClick: () -> Unit,
    onAlertHistoryClick: () -> Unit,
    onSafetyClick: () -> Unit,
    onForestSoundClick: () -> Unit,
    onForestMapClick: () -> Unit
) {

    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .padding(bottom = 80.dp)
        ) {

            Text(
                text = "Explore Karnataka Wildlife",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },

                placeholder = {
                    Text("Search animals or features")
                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            FeatureCard(
                "Wildlife Wiki",
                "Learn about animals",
                onWildlifeClick
            )

            FeatureCard(
                "Movement Alert",
                "Report animal movement",
                onMovementClick
            )

            FeatureCard(
                "Alert History",
                "View all reported alerts",
                onAlertHistoryClick
            )

            FeatureCard(
                "Safety Guide",
                "Stay safe near wildlife",
                onSafetyClick
            )

            FeatureCard(
                "Forest Sounds",
                "Listen to nature sounds",
                onForestSoundClick
            )

            FeatureCard(
                "Karnataka Forest Map",
                "Explore forests and locations",
                onForestMapClick
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {

            BottomBar(

                onHomeClick = { },

                onAlertClick = onAlertHistoryClick
            )
        }
    }
}

@Composable
fun FeatureCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {

    val icon = when (title) {

        "Wildlife Wiki" -> "🦁"

        "Movement Alert" -> "🚨"

        "Alert History" -> "📜"

        "Safety Guide" -> "🛡️"

        "Forest Sounds" -> "🔊"

        "Karnataka Forest Map" -> "🗺️"

        else -> "🌿"
    }

    val gradientColors = when (title) {

        "Wildlife Wiki" -> listOf(
            Color(0xFFFF9800),
            Color(0xFFF57C00)
        )

        "Movement Alert" -> listOf(
            Color(0xFFE53935),
            Color(0xFFC62828)
        )

        "Alert History" -> listOf(
            Color(0xFF5E35B1),
            Color(0xFF4527A0)
        )

        "Safety Guide" -> listOf(
            Color(0xFF1E88E5),
            Color(0xFF1565C0)
        )

        "Forest Sounds" -> listOf(
            Color(0xFF00897B),
            Color(0xFF00695C)
        )

        "Karnataka Forest Map" -> listOf(
            Color(0xFF6D4C41),
            Color(0xFF4E342E)
        )

        else -> listOf(
            Color(0xFF43A047),
            Color(0xFF1B5E20)
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(24.dp),

        elevation = CardDefaults.cardElevation(10.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            colors = gradientColors
                        )
                    ),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = icon,
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,

                    fontSize = 20.sp,

                    fontWeight = FontWeight.Bold,

                    color = Color(0xFF1B5E20)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitle,

                    fontSize = 14.sp,

                    color = Color.Gray
                )
            }

            Text(
                text = "➜",

                fontSize = 24.sp,

                color = Color(0xFF1B5E20)
            )
        }
    }
}

@Composable
fun MovementAlertScreen(onBackClick: () -> Unit) {

    var animalName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance()
    val alertRef = database.getReference("MovementAlerts")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        TopBar("Movement Alert", onBackClick)

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),

            elevation = CardDefaults.cardElevation(8.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "🚨 Report Animal Movement",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFBF360C)
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = animalName,
                    onValueChange = { animalName = it },

                    label = {
                        Text("Animal Name")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },

                    label = {
                        Text("Location")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {

                        if (animalName.isEmpty() || location.isEmpty()) {

                            message =
                                "Please enter animal name and location"

                        } else {

                            val alertId = alertRef.push().key

                            val alertData = mapOf(
                                "animalName" to animalName,
                                "location" to location
                            )

                            if (alertId != null) {

                                alertRef.child(alertId)
                                    .setValue(alertData)

                                message =
                                    "Alert Saved Successfully ✅"

                                animalName = ""
                                location = ""
                            }
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),

                    shape = RoundedCornerShape(18.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6A1B9A)
                    )
                ) {

                    Text(
                        text = "Report Alert",
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = message,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun WildlifeWikiScreen(onBackClick: () -> Unit) {
    var selectedAnimal by remember { mutableStateOf("") }
    var searchText by remember { mutableStateOf("") }

    val animalList = listOf(
        "Elephant",
        "Tiger",
        "Leopard",
        "Peacock"
    )

    val filteredAnimals = animalList.filter {
        it.contains(searchText, ignoreCase = true)
    }

    if (selectedAnimal.isNotEmpty()) {
        AnimalDetailScreen(
            animalName = selectedAnimal,
            onBackClick = {
                selectedAnimal = ""
            }
        )
        return
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F5F5)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            TopBar(
                "Wildlife Wiki",
                onBackClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = {
                    Text("Search Animal")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            filteredAnimals.forEach { animal ->
                AnimalCard(animal) {
                    selectedAnimal = animal
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

@Composable
fun AnimalCard(
    animalName: String,
    onClick: () -> Unit
) {

    val imageRes = when (animalName) {
        "Elephant" -> R.drawable.elephant
        "Tiger" -> R.drawable.tiger
        "Leopard" -> R.drawable.leopard
        "Peacock" -> R.drawable.peacock
        else -> R.drawable.elephant
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },

        shape = RoundedCornerShape(20.dp),

        elevation = CardDefaults.cardElevation(8.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = animalName,

                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(
                    text = animalName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B5E20)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Tap to view full details",
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun AnimalDetailScreen(
    animalName: String,
    onBackClick: () -> Unit
) {
    val imageRes = when (animalName) {
        "Elephant" -> R.drawable.elephant
        "Tiger" -> R.drawable.tiger
        "Leopard" -> R.drawable.leopard
        "Peacock" -> R.drawable.peacock
        else -> R.drawable.elephant
    }

    val description = when (animalName) {
        "Elephant" -> "Elephants help maintain forest balance and biodiversity. They are important for the ecosystem and commonly found near forest villages."

        "Tiger" -> "Tiger is India's national animal and a symbol of strength. Tigers are powerful predators and important for forest balance."

        "Leopard" -> "Leopards are strong wild cats often seen near villages and forest borders. They are fast, intelligent, and powerful hunters."

        "Peacock" -> "Peacock is India’s national bird, famous for its colorful feathers and beauty. It plays an important role in nature."

        else -> "Wildlife information"
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = animalName,
                fontSize = 30.sp,
                color = Color(0xFF1B5E20)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = animalName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = description,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }
        }
    }
}
@Composable
fun SafetyGuideScreen(onBackClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFE3F2FD)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            TopBar(
                "Safety Guide",
                onBackClick
            )

            Spacer(modifier = Modifier.height(20.dp))

            SafetyCard(
                title = "🐘 Elephant Safety",
                tips = "Do not go near. Inform forest officers. Avoid loud sounds and keep safe distance."
            )

            Spacer(modifier = Modifier.height(12.dp))

            SafetyCard(
                title = "🐆 Leopard Safety",
                tips = "Stay indoors at night. Protect pets and children. Avoid lonely forest roads."
            )

            Spacer(modifier = Modifier.height(12.dp))

            SafetyCard(
                title = "🐍 Snake Safety",
                tips = "Do not touch unknown snakes. Call rescue team. Wear footwear in farms."
            )

            Spacer(modifier = Modifier.height(12.dp))

            SafetyCard(
                title = "🐒 Monkey Safety",
                tips = "Do not feed monkeys. Keep food covered. Stay calm and avoid teasing."
            )

            Spacer(modifier = Modifier.height(12.dp))

            SafetyCard(
                title = "🐗 Wild Boar Safety",
                tips = "Avoid crop fields at night. Use safe fencing. Inform local forest officials."
            )

            Spacer(modifier = Modifier.height(24.dp))


        }
    }
}

@Composable
fun SafetyCard(
    title: String,
    tips: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = title,
                fontSize = 20.sp,
                color = Color(0xFF1565C0)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = tips,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ForestSoundsScreen(onBackClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFE8F5E9)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            TopBar(
                "Forest Sounds",
                onBackClick
            )

            Spacer(modifier = Modifier.height(20.dp))

            SoundCard("🐦 Bird Sound", R.raw.bird)
            Spacer(modifier = Modifier.height(12.dp))

            SoundCard("🐘 Elephant Sound", R.raw.elephant)
            Spacer(modifier = Modifier.height(12.dp))

            SoundCard("🐅 Tiger Roar", R.raw.tiger)
            Spacer(modifier = Modifier.height(12.dp))

            SoundCard("🌿 Forest Nature", R.raw.forest)

            Spacer(modifier = Modifier.height(24.dp))

        }
    }
}

var currentMediaPlayer: MediaPlayer? = null
@Composable
fun SoundCard(
    title: String,
    soundRes: Int
) {

    val context = LocalContext.current

    var mediaPlayer by remember {
        mutableStateOf<MediaPlayer?>(null)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        shape = RoundedCornerShape(20.dp),

        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = title,
                fontSize = 20.sp,
                color = Color(0xFF1B5E20)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {

                    currentMediaPlayer?.stop()
                    currentMediaPlayer?.release()

                    mediaPlayer = MediaPlayer.create(
                        context,
                        soundRes
                    )

                    currentMediaPlayer = mediaPlayer

                    mediaPlayer?.start()
                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(16.dp)
            ) {

                Text("Play Sound")
            }
        }
    }

    DisposableEffect(Unit) {

        onDispose {

            mediaPlayer?.stop()
            mediaPlayer?.release()
        }
    }
}

@Composable
fun ForestMapScreen(onBackClick: () -> Unit) {

    var selectedForest by remember {
        mutableStateOf("")
    }

    if (selectedForest.isNotEmpty()) {

        ForestDetailScreen(
            forestName = selectedForest,

            onBackClick = {
                selectedForest = ""
            }
        )

        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {

        TopBar(
            "Karnataka Forest Map",
            onBackClick
        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(
                id = R.drawable.karnataka_map
            ),

            contentDescription = "Karnataka Map",

            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.height(20.dp))

        FeatureCard(
            "Bandipur",
            "Famous for Tigers",
            { selectedForest = "Bandipur" }
        )

        FeatureCard(
            "Nagarhole",
            "Elephants & Leopards",
            { selectedForest = "Nagarhole" }
        )

        FeatureCard(
            "Dandeli",
            "Bird sanctuary",
            { selectedForest = "Dandeli" }
        )

        FeatureCard(
            "Bhadra",
            "Wildlife reserve",
            { selectedForest = "Bhadra" }
        )

        FeatureCard(
            "Bannerghatta",
            "Safari & zoo",
            { selectedForest = "Bannerghatta" }
        )
    }
}
@Composable
fun ForestDetailScreen(
    forestName: String,
    onBackClick: () -> Unit
) {

    val imageRes = when (forestName) {

        "Bandipur" -> R.drawable.bandipur

        "Nagarhole" -> R.drawable.nagarhole

        "Dandeli" -> R.drawable.dandeli

        "Bhadra" -> R.drawable.bhadra

        "Bannerghatta" -> R.drawable.bannerghatta

        else -> R.drawable.bandipur
    }

    val details = when (forestName) {

        "Bandipur" ->
            "Bandipur National Park is famous for Tigers, Elephants and Deer. It is one of the major tiger reserves in Karnataka."

        "Nagarhole" ->
            "Nagarhole is well known for Elephants, Leopards and dense forests with rich biodiversity."

        "Dandeli" ->
            "Dandeli Wildlife Sanctuary is famous for birds, black panthers and river activities."

        "Bhadra" ->
            "Bhadra Wildlife Sanctuary contains Tigers, Sloth Bears and beautiful forest landscapes."

        "Bannerghatta" ->
            "Bannerghatta National Park is famous for safari, zoo and wildlife conservation near Bangalore."

        else -> "Wildlife Forest"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {

        TopBar(
            forestName,
            onBackClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = imageRes),

            contentDescription = forestName,

            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(24.dp))
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            elevation = CardDefaults.cardElevation(8.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = forestName,

                    fontSize = 28.sp,

                    fontWeight = FontWeight.Bold,

                    color = Color(0xFF1B5E20)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = details,

                    fontSize = 18.sp,

                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "🌿 Wildlife Protected Area",

                    fontWeight = FontWeight.Bold,

                    color = Color(0xFF2E7D32)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "🐘 Rich Biodiversity\n🐅 Wildlife Conservation\n🌳 Nature Tourism",

                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun AlertHistoryScreen(onBackClick: () -> Unit) {

    val database = FirebaseDatabase.getInstance()
    val alertRef = database.getReference("MovementAlerts")

    val alertList = remember { mutableStateListOf<AlertData>() }

    LaunchedEffect(Unit) {

        alertRef.get().addOnSuccessListener { snapshot ->

            alertList.clear()

            snapshot.children.forEach {

                val animal =
                    it.child("animalName").value.toString()

                val location =
                    it.child("location").value.toString()

                alertList.add(
                    AlertData(animal, location)
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TopBar("Alert History", onBackClick)

        Spacer(modifier = Modifier.height(20.dp))

        alertList.forEach {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),

                shape = RoundedCornerShape(16.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Animal: ${it.animalName}",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Location: ${it.location}"
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    onHomeClick: () -> Unit,
    onAlertClick: () -> Unit
) {

    NavigationBar(

        containerColor = Color.White,

        tonalElevation = 10.dp
    ) {

        NavigationBarItem(

            selected = false,

            onClick = onHomeClick,

            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home"
                )
            },

            label = {
                Text("Home")
            }
        )

        NavigationBarItem(

            selected = false,

            onClick = onAlertClick,

            icon = {
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = "Alerts"
                )
            },

            label = {
                Text("Alerts")
            }
        )
    }
}