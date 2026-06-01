package com.ozcarmel.multiplicationtable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiplicationTableApp()
        }
    }
}

private enum class AppLanguage { Hebrew, English }
private enum class PracticeMode { Ordered, Mixed }

private data class Copy(
    val direction: LayoutDirection,
    val title: String,
    val subtitle: String,
    val languageButton: String,
    val repeatedPrefix: String,
    val groupsWord: String,
    val commutative: String,
    val cluePrefix: String,
    val practiceTitle: String,
    val practiceCardTitle: String,
    val ordered: String,
    val mixed: String,
    val reveal: String,
    val clear: String,
    val times: String,
    val equals: String,
    val answerLabel: String,
    val checkAnswer: String
)

private val HebrewCopy = Copy(
    direction = LayoutDirection.Rtl,
    title = "לוח הכפל בקלות",
    subtitle = "לוח 10x10 אינטראקטיבי עם תרגול לילדים.",
    languageButton = "English",
    repeatedPrefix = "קבוצות שוות:",
    groupsWord = "קבוצות שוות של",
    commutative = "חוק החילוף מאפשר לנו להפוך את הגורמים ולקבל אותה תשובה:",
    cluePrefix = "רמז:",
    practiceTitle = "תרגול",
    practiceCardTitle = "תרגול כפולות של",
    ordered = "לפי סדר",
    mixed = "מעורבב",
    reveal = "גלה",
    clear = "נקה",
    times = "כפול",
    equals = "שווה",
    answerLabel = "תשובה",
    checkAnswer = "בדיקה"
)

private val EnglishCopy = Copy(
    direction = LayoutDirection.Ltr,
    title = "Multiplication Made Easy",
    subtitle = "An interactive 10x10 multiplication table with practice for kids.",
    languageButton = "עברית",
    repeatedPrefix = "Equal groups:",
    groupsWord = "groups of",
    commutative = "The commutative fact lets us swap the factors and keep the same answer:",
    cluePrefix = "Clue:",
    practiceTitle = "Practice",
    practiceCardTitle = "Practice",
    ordered = "In order",
    mixed = "Mixed",
    reveal = "Reveal",
    clear = "Clear",
    times = "times",
    equals = "equals",
    answerLabel = "Answer",
    checkAnswer = "Check"
)

@Composable
fun MultiplicationTableApp() {
    var language by rememberSaveable { mutableStateOf(AppLanguage.Hebrew) }
    var selectedA by rememberSaveable { mutableStateOf(3) }
    var selectedB by rememberSaveable { mutableStateOf(4) }
    var selectedPractice by rememberSaveable { mutableStateOf(1) }
    var practiceMode by rememberSaveable { mutableStateOf(PracticeMode.Ordered) }
    val answers = remember { mutableStateMapOf<Int, String>() }
    val results = remember { mutableStateMapOf<Int, Boolean>() }
    val copy = if (language == AppLanguage.Hebrew) HebrewCopy else EnglishCopy

    CompositionLocalProvider(LocalLayoutDirection provides copy.direction) {
        MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Sky
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Header(copy = copy) {
                        language = if (language == AppLanguage.Hebrew) AppLanguage.English else AppLanguage.Hebrew
                    }

                    MultiplicationTable(
                        selectedA = selectedA,
                        selectedB = selectedB,
                        copy = copy,
                        onSelect = { row, column ->
                            selectedA = row
                            selectedB = column
                        }
                    )

                    InsightPanel(
                        selectedA = selectedA,
                        selectedB = selectedB,
                        copy = copy,
                        language = language
                    )

                    PracticePanel(
                        selectedPractice = selectedPractice,
                        practiceMode = practiceMode,
                        answers = answers,
                        results = results,
                        copy = copy,
                        onPracticeChange = {
                            selectedPractice = it
                            answers.clear()
                            results.clear()
                        },
                        onModeChange = {
                            practiceMode = it
                            answers.clear()
                            results.clear()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun Header(copy: Copy, onLanguageToggle: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = copy.title,
                color = Ink,
                fontSize = 34.sp,
                fontWeight = FontWeight.Black,
                textAlign = if (copy.direction == LayoutDirection.Rtl) TextAlign.Right else TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = copy.subtitle,
                color = Muted,
                fontSize = 16.sp,
                textAlign = if (copy.direction == LayoutDirection.Rtl) TextAlign.Right else TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        GameButton(text = copy.languageButton, selected = true, onClick = onLanguageToggle)
    }
}

@Composable
private fun MultiplicationTable(
    selectedA: Int,
    selectedB: Int,
    copy: Copy,
    onSelect: (Int, Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            TableRow {
                AxisCell("×")
                (1..10).forEach { AxisCell(it.toString()) }
            }
            (1..10).forEach { row ->
                TableRow {
                    AxisCell(row.toString())
                    (1..10).forEach { column ->
                        val isSelected = row == selectedA && column == selectedB
                        val isMirror = row == selectedB && column == selectedA
                        val isActive = row == selectedA || column == selectedB
                        TableCell(
                            value = row * column,
                            selected = isSelected,
                            mirror = isMirror,
                            active = isActive,
                            contentDescription = "$row ${copy.times} $column ${copy.equals} ${row * column}",
                            onClick = { onSelect(row, column) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TableRow(content: @Composable () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(5.dp), content = { content() })
}

@Composable
private fun AxisCell(text: String) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(Ink, RoundedCornerShape(7.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White, fontWeight = FontWeight.Black)
    }
}

@Composable
private fun TableCell(
    value: Int,
    selected: Boolean,
    mirror: Boolean,
    active: Boolean,
    contentDescription: String,
    onClick: () -> Unit
) {
    val background = when {
        selected -> Coral
        mirror -> SoftYellow
        active -> Mint
        else -> Color.White
    }
    val foreground = if (selected) Color.White else Ink

    Button(
        onClick = onClick,
        modifier = Modifier.size(48.dp),
        shape = RoundedCornerShape(7.dp),
        colors = ButtonDefaults.buttonColors(containerColor = background, contentColor = foreground),
        border = BorderStroke(1.dp, if (selected) CoralDark else PaleBorder),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text = value.toString(), fontWeight = FontWeight.Black, fontSize = 15.sp)
    }
}

@Composable
private fun InsightPanel(selectedA: Int, selectedB: Int, copy: Copy, language: AppLanguage) {
    val product = selectedA * selectedB
    val repeatedAddition = List(selectedB) { selectedA }.joinToString(" + ")
    val mirror = "$selectedB × $selectedA = $product"
    val clue = clueFor(selectedA, selectedB, language)

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                EquationChip(selectedA.toString())
                EquationChip("×")
                EquationChip(selectedB.toString())
                EquationChip("=")
                EquationChip(product.toString(), highlight = true)
            }

            Text(
                text = "${copy.repeatedPrefix} $selectedB ${copy.groupsWord}",
                color = Muted,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = textAlignFor(copy),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = repeatedAddition,
                color = Ink,
                fontSize = 16.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "${copy.commutative} $mirror",
                color = Muted,
                fontSize = 16.sp,
                textAlign = textAlignFor(copy),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "${copy.cluePrefix} $clue",
                color = Muted,
                fontSize = 16.sp,
                textAlign = textAlignFor(copy),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun EquationChip(text: String, highlight: Boolean = false) {
    Box(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .size(46.dp)
            .background(if (highlight) Gold else Mint, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Ink, fontSize = 25.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
private fun PracticePanel(
    selectedPractice: Int,
    practiceMode: PracticeMode,
    answers: MutableMap<Int, String>,
    results: MutableMap<Int, Boolean>,
    copy: Copy,
    onPracticeChange: (Int) -> Unit,
    onModeChange: (PracticeMode) -> Unit
) {
    val order = if (practiceMode == PracticeMode.Ordered) {
        (1..10).toList()
    } else {
        listOf(7, 2, 9, 4, 1, 10, 5, 3, 8, 6)
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = WarmPanel),
        border = BorderStroke(1.dp, Gold),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(copy.practiceTitle, color = Ink, fontSize = 26.sp, fontWeight = FontWeight.Black)

            NumberChooser(selected = selectedPractice, onSelect = onPracticeChange)

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                GameButton(copy.ordered, practiceMode == PracticeMode.Ordered) {
                    onModeChange(PracticeMode.Ordered)
                }
                GameButton(copy.mixed, practiceMode == PracticeMode.Mixed) {
                    onModeChange(PracticeMode.Mixed)
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                GameButton(copy.reveal, selected = false, accent = Gold) {
                    order.forEach { multiplier ->
                        answers[multiplier] = (selectedPractice * multiplier).toString()
                        results[multiplier] = true
                    }
                }
                GameButton(copy.clear, selected = false, accent = Coral) {
                    answers.clear()
                    results.clear()
                }
            }

            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(2.dp, Ink),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(9.dp)
                ) {
                    Text(
                        text = "${copy.practiceCardTitle} $selectedPractice",
                        color = Ink,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    order.forEach { multiplier ->
                        PracticeRow(
                            selectedPractice = selectedPractice,
                            multiplier = multiplier,
                            answerText = answers[multiplier].orEmpty(),
                            result = results[multiplier],
                            copy = copy,
                            onAnswerChange = {
                                answers[multiplier] = it.filter(Char::isDigit).take(3)
                                results.remove(multiplier)
                            },
                            onCheck = {
                                results[multiplier] = answers[multiplier].orEmpty().toIntOrNull() == selectedPractice * multiplier
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NumberChooser(selected: Int, onSelect: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .background(Mint, RoundedCornerShape(8.dp))
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        (1..10).chunked(5).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { number ->
                    GameButton(text = number.toString(), selected = selected == number) {
                        onSelect(number)
                    }
                }
            }
        }
    }
}

@Composable
private fun PracticeRow(
    selectedPractice: Int,
    multiplier: Int,
    answerText: String,
    result: Boolean?,
    copy: Copy,
    onAnswerChange: (String) -> Unit,
    onCheck: () -> Unit
) {
    val rowColor = when (result) {
        true -> SuccessSoft
        false -> ErrorSoft
        null -> Color(0xFFF7FFFD)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(rowColor, RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "$selectedPractice × $multiplier =",
            modifier = Modifier.weight(1f),
            color = Ink,
            fontWeight = FontWeight.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Left
        )
        OutlinedTextField(
            value = answerText,
            onValueChange = onAnswerChange,
            modifier = Modifier.width(76.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { if (answerText.isNotBlank()) onCheck() }),
            textStyle = MaterialTheme.typography.titleMedium.copy(
                color = Ink,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            ),
            label = null
        )
        GameButton(text = "⌕", selected = false, compact = true, enabled = answerText.isNotBlank()) {
            onCheck()
        }
        Text(
            text = when (result) {
                true -> "✓"
                false -> "✕"
                null -> ""
            },
            modifier = Modifier.width(24.dp),
            color = if (result == true) Success else CoralDark,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun GameButton(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    accent: Color = Ink,
    compact: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val background = if (selected) Ink else Color.White
    val content = if (selected) Color.White else accent
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = background,
            contentColor = content,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        border = if (enabled) BorderStroke(2.dp, if (selected) Ink else PaleBorder) else null,
        modifier = modifier.height(if (compact) 40.dp else 44.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        Text(text = text, fontWeight = FontWeight.Black, fontSize = if (compact) 18.sp else 14.sp)
    }
}

private fun textAlignFor(copy: Copy): TextAlign {
    return if (copy.direction == LayoutDirection.Rtl) TextAlign.Right else TextAlign.Left
}

private fun clueFor(a: Int, b: Int, language: AppLanguage): String {
    return if (language == AppLanguage.Hebrew) {
        when {
            a == b -> "זה מספר ריבועי: שני הגורמים שווים."
            a == 10 || b == 10 -> "כפולות של 10 מוסיפות אפס."
            a == 5 || b == 5 -> "כפולות של 5 מסתיימות ב-0 או 5."
            a == 9 || b == 9 -> "בכפולות של 9 יש תבנית ספרות יפה."
            a == 2 || b == 2 -> "כפול 2 הוא זוגות."
            else -> "אפשר להפוך את הגורמים ולקבל אותה תשובה."
        }
    } else {
        when {
            a == b -> "This is a square number: both factors are equal."
            a == 10 || b == 10 -> "Multiples of 10 add a zero."
            a == 5 || b == 5 -> "Multiples of 5 end in 0 or 5."
            a == 9 || b == 9 -> "Multiples of 9 have a useful digit pattern."
            a == 2 || b == 2 -> "Times 2 means pairs."
            else -> "You can swap the factors and keep the same answer."
        }
    }
}

private val Sky = Color(0xFFF6FBFF)
private val Ink = Color(0xFF14324A)
private val Muted = Color(0xFF53677A)
private val Mint = Color(0xFFD9F3F0)
private val PaleBorder = Color(0xFFC9DBE8)
private val Gold = Color(0xFFF6C945)
private val SoftYellow = Color(0xFFFFF3BA)
private val WarmPanel = Color(0xFFFFFDF7)
private val Coral = Color(0xFFFF6B5A)
private val CoralDark = Color(0xFFC94736)
private val ErrorSoft = Color(0xFFFFF5F2)
private val Success = Color(0xFF1B8A4A)
private val SuccessSoft = Color(0xFFEFFAF3)

@Preview(showBackground = true)
@Composable
private fun PreviewApp() {
    MultiplicationTableApp()
}
