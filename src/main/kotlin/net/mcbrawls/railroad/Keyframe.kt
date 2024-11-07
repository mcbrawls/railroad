package net.mcbrawls.railroad

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import org.joml.Vector2f
import org.joml.Vector3d

/**
 * A position along a camera track.
 */
data class Keyframe(
    val pos: Vector3d,
    val rotation: Vector2f,
) {
    companion object {
        /**
         * The codec for this class.
         */
        val CODEC: Codec<Keyframe> = RecordCodecBuilder.create { instance ->
            instance.group(
                Vectors.VECTOR_3D.fieldOf("position").forGetter(Keyframe::pos),
                Vectors.VECTOR_2F.fieldOf("rotation").forGetter(Keyframe::rotation),
            ).apply(instance, ::Keyframe)
        }
    }
}
