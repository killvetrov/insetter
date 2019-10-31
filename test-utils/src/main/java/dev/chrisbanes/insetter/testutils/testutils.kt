/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.chrisbanes.insetter.testutils

import android.graphics.Rect
import android.view.View
import android.view.WindowInsets
import androidx.annotation.RequiresApi

@RequiresApi(20)
fun createWindowInsetsInstance(systemWindowInsets: Rect = Rect()): WindowInsets {
    val constructor = WindowInsets::class.java.getConstructor(Rect::class.java)
    constructor.isAccessible = true
    return constructor.newInstance(systemWindowInsets)
}

@RequiresApi(20)
fun View.dispatchInsets(systemWindowInsets: Rect = Rect(), f: ((WindowInsets) -> WindowInsets)? = null) {
    val insets = f?.invoke(createWindowInsetsInstance(systemWindowInsets))
        ?: createWindowInsetsInstance(systemWindowInsets)
    dispatchApplyWindowInsets(insets)
}