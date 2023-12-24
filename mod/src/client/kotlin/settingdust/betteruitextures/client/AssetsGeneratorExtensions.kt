package settingdust.betteruitextures.client

import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage

fun TextureImage.generateBackgroundNinePatch(
    ninePatch: NinePatch,
    originalSize: Size,
    targetSize: Size = originalSize,
    offset: Point = Point(0, 0)
): TextureImage {
    val centerSize =
        Size(
            targetSize.width - ninePatch.first.x - ninePatch.second.x,
            targetSize.height - ninePatch.first.y - ninePatch.second.y
        )
    val result = TextureImage.createNew(targetSize.width, targetSize.height, null)

    val targetRightX = targetSize.width - ninePatch.second.x
    val targetBottomY = targetSize.height - ninePatch.second.y
    val originalRightX = originalSize.width - ninePatch.second.x + offset.x
    val originalBottomY = originalSize.height - ninePatch.second.y + offset.y

    val backgroundColor = getFramePixel(0, 7 + offset.x, 7 + offset.y)

    for (x in ninePatch.first.x + offset.x until targetRightX) {
        for (y in ninePatch.first.y + offset.y until targetBottomY) {
            result.setFramePixel(0, x, y, backgroundColor)
        }
    }

    image.run {
        // Corners
        // Left Top
        copyRect(
            result.image,
            offset.x,
            offset.y,
            0,
            0,
            ninePatch.first.x,
            ninePatch.first.y,
            false,
            false
        )
        // Right Top
        copyRect(
            result.image,
            originalRightX,
            offset.y,
            targetRightX,
            0,
            ninePatch.second.x,
            ninePatch.first.y,
            false,
            false
        )
        // Left Bottom
        copyRect(
            result.image,
            offset.x,
            originalBottomY,
            0,
            targetBottomY,
            ninePatch.first.x,
            ninePatch.second.y,
            false,
            false
        )
        // Right Bottom
        copyRect(
            result.image,
            originalRightX,
            originalBottomY,
            targetRightX,
            targetBottomY,
            ninePatch.second.x,
            ninePatch.second.y,
            false,
            false
        )
        // Edges
        // Top
        copyRect(
            result.image,
            ninePatch.first.x + offset.x,
            offset.y,
            ninePatch.first.x,
            0,
            centerSize.width,
            ninePatch.first.y,
            false,
            false
        )
        // Left
        copyRect(
            result.image,
            offset.x,
            ninePatch.first.y + offset.y,
            0,
            ninePatch.first.y,
            ninePatch.first.x,
            centerSize.height,
            false,
            false
        )
        // Right
        copyRect(
            result.image,
            originalRightX,
            ninePatch.first.y + offset.y,
            targetRightX,
            ninePatch.first.y,
            ninePatch.second.x,
            centerSize.height,
            false,
            false
        )
        // Bottom
        copyRect(
            result.image,
            ninePatch.first.x + offset.x,
            originalBottomY,
            ninePatch.first.x,
            targetBottomY,
            centerSize.width,
            ninePatch.second.y,
            false,
            false
        )
    }

    //    ImageTransformer.builder(imageWidth(), imageHeight(), targetSize.width, targetSize.height)
    //        .apply {
    //            //            // Corners
    //            //            // Left Top
    //            //            copyRect(offset.x, offset.y, ninePatch.first.x, ninePatch.first.y,
    // 0, 0)
    //            //            // Right Top
    //            //            copyRect(
    //            //                originalRightX,
    //            //                offset.y,
    //            //                ninePatch.second.x,
    //            //                ninePatch.first.y,
    //            //                targetRightX,
    //            //                0
    //            //            )
    //            //            // Left Bottom
    //            //            copyRect(
    //            //                offset.x,
    //            //                originalBottomY,
    //            //                ninePatch.first.x,
    //            //                ninePatch.second.y,
    //            //                0,
    //            //                targetBottomY
    //            //            )
    //            //            // Right Bottom
    //            //            copyRect(
    //            //                originalRightX,
    //            //                originalBottomY,
    //            //                ninePatch.second.x,
    //            //                ninePatch.second.y,
    //            //                targetRightX,
    //            //                targetBottomY
    //            //            )
    //
    //            //            // Edges
    //            //            // Top
    //            //            copyRect(
    //            //                ninePatch.first.x + offset.x,
    //            //                offset.y,
    //            //                centerSize.width,
    //            //                ninePatch.first.y,
    //            //                ninePatch.first.x,
    //            //                0
    //            //            )
    //            //            // Left
    //            //            copyRect(
    //            //                offset.x,
    //            //                ninePatch.first.y + offset.y,
    //            //                ninePatch.first.x,
    //            //                centerSize.height,
    //            //                0,
    //            //                ninePatch.first.y,
    //            //            )
    //            //            // Right
    //            //            copyRect(
    //            //                originalRightX,
    //            //                ninePatch.first.y + offset.y,
    //            //                ninePatch.second.x,
    //            //                centerSize.height,
    //            //                targetRightX,
    //            //                ninePatch.first.y,
    //            //            )
    //            //            // Bottom
    //            //            copyRect(
    //            //                ninePatch.first.x + offset.x,
    //            //                originalBottomY,
    //            //                centerSize.width,
    //            //                ninePatch.second.y,
    //            //                ninePatch.first.x,
    //            //                targetBottomY
    //            //            )
    //        }
    //        .build()
    //        .apply(this, result)
    return result
}
