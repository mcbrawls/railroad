package net.mcbrawls.railroad

import net.mcbrawls.railroad.Vectors.cubicInterpolate

fun interface Interpolation {
    fun apply(delta: Float, keyframe: Keyframe, targetKeyframe: Keyframe): Keyframe

    companion object {
        val LINEAR: Interpolation = Interpolation { delta, keyframe, targetKeyframe ->
            val pos = keyframe.pos.lerp(targetKeyframe.pos, delta.toDouble())
            val rotation = keyframe.rotation.lerp(targetKeyframe.rotation, delta)
            Keyframe(pos, rotation)
        }

        val CUBIC: Interpolation = Interpolation { delta, keyframe, targetKeyframe ->
            val pos = keyframe.pos.cubicInterpolate(targetKeyframe.pos, delta)
            val rotation = keyframe.rotation.cubicInterpolate(targetKeyframe.rotation, delta)
            Keyframe(pos, rotation)
        }
    }
}
