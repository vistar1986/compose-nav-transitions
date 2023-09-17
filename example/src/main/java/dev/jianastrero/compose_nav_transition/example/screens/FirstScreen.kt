/*
 * MIT License
 *
 * Copyright (c) 2023 Jian James Astrero
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.jianastrero.compose_nav_transition.example.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jianastrero.compose_nav_transition.composable.NavTransition
import dev.jianastrero.compose_nav_transition.composable.sharedElement
import dev.jianastrero.compose_nav_transition.element.Element
import dev.jianastrero.compose_nav_transition.element.rememberElements
import dev.jianastrero.compose_nav_transition.example.R

@Composable
fun FirstScreen(
    sharedImageElement: Element,
    sharedLabelElement: Element,
    onGotoSecondScreen: (image: Element, label: Element) -> Unit,
    modifier: Modifier = Modifier
) {
    val (image, label) = rememberElements(count = 2)

    NavTransition(
        elements = listOf(image, label),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = label.with("First Screen"),
                fontSize = label.withFontSize(24.sp),
                fontWeight = label.with(FontWeight.Bold),
                modifier = Modifier.sharedElement(label)
            )
            Image(
                painter = image.with(painterResource(id = R.drawable.sample)),
                contentDescription = "Sample Image",
                contentScale = image.with(ContentScale.Crop),
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 12.dp)
                    .clickable { onGotoSecondScreen(image, label) }
                    .sharedElement(image)
            )
        }
    }

    LaunchedEffect(sharedImageElement, sharedLabelElement) {
        image.connect(sharedImageElement)
        label.connect(sharedLabelElement)
    }
}

@Preview
@Composable
private fun FirstScreenPreview() {
    FirstScreen(
        sharedImageElement = Element.None,
        sharedLabelElement = Element.None,
        onGotoSecondScreen = { _, _ -> },
        modifier = Modifier.fillMaxSize()
    )
}
