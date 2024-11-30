package tech.medivh.medivh.generate.console.core

/**
 * @author gongxuanzhangmelt@gmail.com
 */
class Result<T> private constructor(var code: Int, var message: String, var data: T? = null) {

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(200, "success", data)
        }

        fun <T> success(): Result<T> {
            return Result(200, "success")
        }

        fun fail(code: Int = 500, message: String?): Result<String> {
            return Result(code, message ?: "fail")
        }
    }

    override fun toString(): String {
        return "Result(code=$code, message='$message', data=$data)"
    }
}