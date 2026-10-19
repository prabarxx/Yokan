/*
 * Copyright (C) 2024-2026 OpenAni and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license, which can be found at the following link.
 *
 * https://github.com/open-ani/ani/blob/main/LICENSE
 */

package me.him188.ani.utils.coroutines

import kotlinx.coroutines.CancellationException

/**
 * Same as [runCatching], but re-throws [CancellationException] instead of capturing it into the
 * returned [Result].
 *
 * [runCatching] also catches [CancellationException], which turns a coroutine cancellation into a
 * `Result.failure` and breaks structured concurrency (e.g. a `withTimeout` or a UI-triggered
 * cancellation would be swallowed, and fallback code would keep running). Use this variant in
 * blocks that call suspending functions.
 */
public suspend inline fun <T> runSuspendCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (e: CancellationException) {
    throw e
} catch (e: Throwable) {
    Result.failure(e)
}
