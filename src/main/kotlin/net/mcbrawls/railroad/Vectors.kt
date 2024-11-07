package net.mcbrawls.railroad

import com.mojang.serialization.Codec
import org.joml.Vector2f
import org.joml.Vector3d

object Vectors {
    val VECTOR_3D: Codec<Vector3d> = Codec.DOUBLE.listOf().xmap(
        { list -> Vector3d(list[0], list[1], list[2]) },
        { vect -> listOf(vect.x, vect.y, vect.z) }
    )

    val VECTOR_2F: Codec<Vector2f> = Codec.FLOAT.listOf().xmap(
        { list -> Vector2f(list[0], list[1]) },
        { vect -> listOf(vect.x, vect.y) }
    )

    fun Vector3d.cubicInterpolate(end: Vector3d, delta: Float): Vector3d {
        val start = this

        val t2 = delta * delta
        val t3 = t2 * delta

        val m0 = (end.sub(start)).mul(0.5)

        val h0 = 2 * t3 - 3 * t2 + 1
        val h1 = -2 * t3 + 3 * t2
        val h2 = t3 - 2 * t2 + delta
        val h3 = t3 - t2

        return Vector3d(
            start.x * h0 + end.x * h1 + m0.x * h2 + m0.x * h3,
            start.y * h0 + end.y * h1 + m0.y * h2 + m0.y * h3,
            start.z * h0 + end.z * h1 + m0.z * h2 + m0.z * h3
        )
    }

    fun Vector2f.cubicInterpolate(end: Vector2f, delta: Float): Vector2f {
        val start = this

        val t2 = delta * delta
        val t3 = t2 * delta

        val m0 = (end.sub(start)).mul(0.5f)

        val h0 = 2 * t3 - 3 * t2 + 1
        val h1 = -2 * t3 + 3 * t2
        val h2 = t3 - 2 * t2 + delta
        val h3 = t3 - t2

        return Vector2f(
            start.x * h0 + end.x * h1 + m0.x * h2 + m0.x * h3,
            start.y * h0 + end.y * h1 + m0.y * h2 + m0.y * h3
        )
    }
}
