package com.example.data.datasource

import com.example.data.service.ApiResultWrapper
import com.example.data.service.safeRemoteCall
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ApiResponseHandlerTest {

    private val dispatcher = TestCoroutineDispatcher()

    @Test
    fun `when call is successful, should emit the result as success`() {
        runBlockingTest {
            val lambdaResult = true
            val result = safeRemoteCall(dispatcher) { lambdaResult }
            assertEquals(ApiResultWrapper.Success(lambdaResult), result)
        }
    }

    @Test
    fun `when call throw IOException, should emit the result as NetworkError`() {
        runBlockingTest {
            val result = safeRemoteCall(dispatcher) { throw IOException() }
            assertEquals(ApiResultWrapper.NetworkError, result)
        }
    }

    @Test
    fun `when call throws HttpException, should emit the result as GenericError`() {
        val errorBody = "{\"errors\": [\"Unexpected parameter\"]}".toResponseBody("application/json".toMediaTypeOrNull())
        val httpException = HttpException(Response.error<Any>(422, errorBody))

        runBlockingTest {
            val result = safeRemoteCall(dispatcher) {
                throw httpException
            }
            assertEquals(ApiResultWrapper.GenericError(422, httpException.message()), result)
        }
    }

    @Test
    fun `when call throws unknown exception, should emit GenericError`() {
        runBlockingTest {
            val result = safeRemoteCall(dispatcher) {
                throw IllegalStateException()
            }
            assertEquals(ApiResultWrapper.GenericError(), result)
        }
    }

}