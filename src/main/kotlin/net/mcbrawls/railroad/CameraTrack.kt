package net.mcbrawls.railroad

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

/**
 * A camera track which can be played to a player.
 */
data class CameraTrack(
    val keyframes: List<TrackKeyframe>,
) {
    data class TrackKeyframe(
        /**
         * The time at which the keyframe is played on the track.
         */
        val seconds: Float,

        /**
         * The keyframe to be played.
         */
        val keyframe: Keyframe,
    ) {
        companion object {
            /**
             * The codec for this class.
             */
            val CODEC: Codec<TrackKeyframe> = RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.FLOAT.fieldOf("seconds").forGetter(TrackKeyframe::seconds),
                    Keyframe.CODEC.fieldOf("keyframe").forGetter(TrackKeyframe::keyframe),
                ).apply(instance, ::TrackKeyframe)
            }
        }
    }

    companion object {
        /**
         * The codec for this class.
         */
        val CODEC: Codec<CameraTrack> = RecordCodecBuilder.create { instance ->
            instance.group(
                TrackKeyframe.CODEC.listOf().fieldOf("keyframes").forGetter(CameraTrack::keyframes),
            ).apply(instance, ::CameraTrack)
        }
    }
}
