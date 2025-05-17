package com.example.quersaber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quersaber.ui.theme.QuerSaberTheme

object AppColors {
    val Background = Color(0xFF3C375C)
    val Title = Color(0xFFE3B54D)
    val Card = Color(0xFF746688)
    val Text = Color(0xFFDFD3A7)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuerSaberTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppColors.Background
                ) {
                    SimpleQuestionApp()
                }
            }
        }
    }
}

@Composable
fun SimpleQuestionApp() {
    val questions = remember {
        listOf(
            "Qual é o seu filme favorito?",
            "Qual é a sua série favorita?",
            "Qual é o seu livro favorito?",
            "Um filme que você odeia?",
            "Qual música poderia ouvir pelo resto da vida?",
            "Um fato que ninguém sabe sobre você?",
            "Um sonho que você ainda quer realizar?",
            "E um sonho que já realizou?",
            "Para qual time torce?",
            "Qual é/era matéria preferida na escola?",
            "Uma viagem que marcou a sua vida?",
            "Qual é a viagem dos seus sonhos?",
            "Qual foi a coisa mais importante que já aconteceu na sua vida?",
            "Qual foi o maior mico que você já pagou?",
            "Qual série poderia assistir o dia todo?",
            "Qual profissão gostaria de ter?",
            "Se você ganhasse na loteria, qual seria a primeira coisa que faria com o dinheiro?",
            "Prefere viajar para a praia ou para o campo?",
            "O que achou de mim quando me viu pela primeira vez?"
        )
    }

    var currentQuestion by remember { mutableStateOf("Clique no botão para uma pergunta") }
    val updatedQuestions by rememberUpdatedState(questions)

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "QuerSaber?",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = AppColors.Title
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = AppColors.Card),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .heightIn(min = 80.dp),
                contentAlignment = Alignment.Center
            ) {
                AnimatedContent(
                    targetState = currentQuestion,
                    transitionSpec = {
                        fadeIn(tween(300)) togetherWith fadeOut(tween(300))
                    },
                    label = "question_transition"
                ) { question ->
                    Text(
                        text = question,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = AppColors.Text,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { currentQuestion = updatedQuestions.random() },
            modifier = Modifier.height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.Title,
                contentColor = AppColors.Background
            )
        ) {
            Text(
                text = "Nova Pergunta",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SimpleQuestionAppPreview() {
    QuerSaberTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppColors.Background
        ) {
            SimpleQuestionApp()
        }
    }
}
