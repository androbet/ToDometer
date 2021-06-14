/*
 * Copyright 2021 Sergio Belda Galbis
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

package com.sergiobelda.androidtodometer.usecase

import com.sergiobelda.androidtodometer.model.TagColors
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.model.TaskState
import com.sergiobelda.androidtodometer.repository.TaskRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetTaskUseCaseTest {

    @MockK
    private val taskRepository = mockk<TaskRepository>()

    private val getTaskUseCase = GetTaskUseCase(taskRepository)

    @Test
    fun testGetTaskUseCase() = runBlocking {
        val task = Task(1, "Name", "Description", TaskState.DOING, 1, TagColors.GRAY)

        coEvery { taskRepository.getTask(1) } returns flow {
            emit(task)
        }

        assertEquals(task, getTaskUseCase(1).firstOrNull())
    }
}
