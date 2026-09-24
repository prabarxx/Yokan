/*
 * Copyright (C) 2024-2025 OpenAni and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license, which can be found at the following link.
 *
 * https://github.com/open-ani/ani/blob/main/LICENSE
 */

package me.him188.ani.utils.io

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PathMoveTest {

    @Test
    fun testMoveDirectoryRecursively() {
        val tempDir = SystemPaths.createTempDirectory("moveTest")
        val sourceDir = tempDir.resolve("source")
        val targetDir = tempDir.resolve("target")

        try {
            // Setup test directory structure
            sourceDir.createDirectories()
            sourceDir.resolve("file1.txt").writeText("content1")
            sourceDir.resolve("file2.txt").writeText("content2")

            val subDir = sourceDir.resolve("subdir")
            subDir.createDirectories()
            subDir.resolve("subfile1.txt").writeText("subcontent1")

            val emptySubDir = sourceDir.resolve("emptysubdir")
            emptySubDir.createDirectories()

            // Track visited files
            val visitedFiles = mutableListOf<SystemPath>()

            // Perform the move
            sourceDir.moveDirectoryRecursively(targetDir) { visitedFiles.add(it) }

            // Verify source no longer exists
            assertFalse(sourceDir.exists())

            // Verify target structure
            assertTrue(targetDir.exists())
            assertTrue(targetDir.isDirectory())
            assertTrue(targetDir.resolve("file1.txt").exists())
            assertTrue(targetDir.resolve("file2.txt").exists())
            assertTrue(targetDir.resolve("subdir").exists())
            assertTrue(targetDir.resolve("subdir/subfile1.txt").exists())
            assertTrue(targetDir.resolve("emptysubdir").exists())

            // Verify content is preserved
            assertEquals("content1", targetDir.resolve("file1.txt").readText())
            assertEquals("content2", targetDir.resolve("file2.txt").readText())
            assertEquals("subcontent1", targetDir.resolve("subdir/subfile1.txt").readText())

            // Verify visitor was called for each file
            assertEquals(3, visitedFiles.size)
            assertTrue(visitedFiles.any { it.toString().endsWith("file1.txt") })
            assertTrue(visitedFiles.any { it.toString().endsWith("file2.txt") })
            assertTrue(visitedFiles.any { it.toString().endsWith("subfile1.txt") })
        } finally {
            // Cleanup
            tempDir.deleteRecursively()
        }
    }

    @Test
    fun testMoveDirectoryRecursively_TargetExists() {
        val tempDir = SystemPaths.createTempDirectory("moveTest")
        val sourceDir = tempDir.resolve("source")
        val targetDir = tempDir.resolve("target")

        try {
            // Setup source directory
            sourceDir.createDirectories()
            sourceDir.resolve("file1.txt").writeText("source content")

            // Setup target directory with existing file
            targetDir.createDirectories()
            targetDir.resolve("file1.txt").writeText("target content")

            // Perform the move - should replace existing files
            sourceDir.moveDirectoryRecursively(targetDir)

            // Verify source no longer exists
            assertFalse(sourceDir.exists())

            // Verify target has the source content
            assertTrue(targetDir.exists())
            assertEquals("source content", targetDir.resolve("file1.txt").readText())
        } finally {
            // Cleanup
            tempDir.deleteRecursively()
        }
    }

    @Test
    fun testMoveDirectoryRecursively_EmptySource() {
        val tempDir = SystemPaths.createTempDirectory("moveTest")
        val sourceDir = tempDir.resolve("source")
        val targetDir = tempDir.resolve("target")

        try {
            // Setup empty source directory
            sourceDir.createDirectories()

            // Perform the move
            sourceDir.moveDirectoryRecursively(targetDir)

            // Verify source no longer exists
            assertFalse(sourceDir.exists())

            // Verify target exists as an empty directory
            assertTrue(targetDir.exists())
            assertTrue(targetDir.isDirectory())
            assertEquals(0, targetDir.list().size)
        } finally {
            // Cleanup
            tempDir.deleteRecursively()
        }
    }

    @Test
    fun testMoveDirectoryRecursively_SourceNotExists() {
        val tempDir = SystemPaths.createTempDirectory("moveTest")
        val sourceDir = tempDir.resolve("nonexistent")
        val targetDir = tempDir.resolve("target")

        try {
            // Attempt to move a non-existent directory
            assertFailsWith(NoSuchFileException::class) {
                sourceDir.moveDirectoryRecursively(targetDir)
            }

            // Verify target was not created
            assertFalse(targetDir.exists())
        } finally {
            // Cleanup
            tempDir.deleteRecursively()
        }
    }

    @Test
    fun testMoveDirectoryRecursively_SourceIsFile() {
        val tempDir = SystemPaths.createTempDirectory("moveTest")
        val sourceFile = tempDir.resolve("sourcefile.txt")
        val targetDir = tempDir.resolve("target")

        try {
            // Create a file instead of directory
            sourceFile.writeText("content")

            // Attempt to move a file as directory
            assertFailsWith(IllegalArgumentException::class) {
                sourceFile.moveDirectoryRecursively(targetDir)
            }

            // Verify source still exists and target was not created
            assertTrue(sourceFile.exists())
            assertFalse(targetDir.exists())
        } finally {
            // Cleanup
            tempDir.deleteRecursively()
        }
    }

    @Test
    fun testMoveDirectoryRecursively_WithoutVisitor() {
        val tempDir = SystemPaths.createTempDirectory("moveTest")
        val sourceDir = tempDir.resolve("source")
        val targetDir = tempDir.resolve("target")

        try {
            // Setup test directory
            sourceDir.createDirectories()
            sourceDir.resolve("file1.txt").writeText("content")

            // Move without visitor
            sourceDir.moveDirectoryRecursively(targetDir, null)

            // Verify move was successful
            assertFalse(sourceDir.exists())
            assertTrue(targetDir.resolve("file1.txt").exists())
        } finally {
            // Cleanup
            tempDir.deleteRecursively()
        }
    }

    @Test
    fun testMoveDirectoryRecursively_NestedStructure() {
        val tempDir = SystemPaths.createTempDirectory("moveTest")
        val sourceDir = tempDir.resolve("source")
        val targetDir = tempDir.resolve("target")

        try {
            // Create a complex nested structure
            sourceDir.createDirectories()
            sourceDir.resolve("level1").createDirectories()
            sourceDir.resolve("level1/level2").createDirectories()
            sourceDir.resolve("level1/level2/level3").createDirectories()
            sourceDir.resolve("level1/file1.txt").writeText("level1")
            sourceDir.resolve("level1/level2/file2.txt").writeText("level2")
            sourceDir.resolve("level1/level2/level3/file3.txt").writeText("level3")

            // Perform the move
            sourceDir.moveDirectoryRecursively(targetDir)

            // Verify the nested structure was preserved
            assertTrue(targetDir.exists())
            assertTrue(targetDir.resolve("level1").exists())
            assertTrue(targetDir.resolve("level1/level2").exists())
            assertTrue(targetDir.resolve("level1/level2/level3").exists())
            assertEquals("level1", targetDir.resolve("level1/file1.txt").readText())
            assertEquals("level2", targetDir.resolve("level1/level2/file2.txt").readText())
            assertEquals("level3", targetDir.resolve("level1/level2/level3/file3.txt").readText())
        } finally {
            // Cleanup
            tempDir.deleteRecursively()
        }
    }

    @Test
    fun testMoveDirectoryRecursively_SpecialFileNames() {
        val tempDir = SystemPaths.createTempDirectory("moveTest")
        val sourceDir = tempDir.resolve("source")
        val targetDir = tempDir.resolve("target")

        try {
            // Create files with special characters
            sourceDir.createDirectories()
            sourceDir.resolve("file with spaces.txt").writeText("spaces")
            sourceDir.resolve("file-with-dashes.txt").writeText("dashes")
            sourceDir.resolve("file_with_underscores.txt").writeText("underscores")
            sourceDir.resolve("file.with.dots.txt").writeText("dots")

            // Perform the move
            sourceDir.moveDirectoryRecursively(targetDir)

            // Verify special filenames were handled correctly
            assertEquals("spaces", targetDir.resolve("file with spaces.txt").readText())
            assertEquals("dashes", targetDir.resolve("file-with-dashes.txt").readText())
            assertEquals("underscores", targetDir.resolve("file_with_underscores.txt").readText())
            assertEquals("dots", targetDir.resolve("file.with.dots.txt").readText())
        } finally {
            // Cleanup
            tempDir.deleteRecursively()
        }
    }
}