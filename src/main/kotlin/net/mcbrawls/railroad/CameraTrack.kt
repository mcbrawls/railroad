package net.mcbrawls.railroad

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.mcbrawls.railroad.codec.ExtraCodecs
import kotlin.time.Duration

/**
 * A camera track which can be played to a player.
 */
data class CameraTrack(
    val keyframes: List<TrackKeyframe>,
) {
    /**
     * The total duration of the track.
     */
    val duration: Duration = keyframes.lastOrNull()?.time ?: Duration.ZERO

    data class TrackKeyframe(
        /**
         * The time at which the keyframe is played on the track.
         */
        val time: Duration,

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
                    ExtraCodecs.DURATION.fieldOf("time").forGetter(TrackKeyframe::time),
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
