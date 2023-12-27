package settingdust.betteruitextures.client

import net.mehvahdjukaar.moonlight.api.resources.ResType
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicTexturePack
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import settingdust.betteruitextures.client.GenericAssetsGenerator.removeElementBackground

data object FabricWaystonesAssetsGenerator : DynamicAssetsGenerator() {

    override val modId = "fwaystones"
    private val WAYSTONE = Identifier(modId, "gui/waystone")
    private val WAYSTONE_CONFIG = Identifier(modId, "gui/waystone_config")

    override fun regenerateDynamicAssets(
        manager: ResourceManager,
        dynamicPack: DynamicTexturePack
    ) {
        dynamicPack.removeResource(ResType.TEXTURES.getPath(WAYSTONE))
        dynamicPack.removeResource(ResType.TEXTURES.getPath(WAYSTONE_CONFIG))
        dynamicPack.addAndCloseTexture(WAYSTONE, Waystone.generate(manager), false)
        dynamicPack.addAndCloseTexture(WAYSTONE_CONFIG, Config.generate(manager), false)
    }

    private object Waystone {
        private const val WINDOW_WIDTH = 177
        private const val WINDOW_HEIGHT = 176
        private const val TEXTURE_SIZE = 256

        fun generate(manager: ResourceManager): TextureImage {
            val original = TextureImage.open(manager, Identifier(modId, "gui/waystone"))
            val image = original.makeCopy()

            // Background
            image.removeElementBackground(
                WINDOW_WIDTH,
                WINDOW_HEIGHT,
                GenericAssetsGenerator.StandaloneWindow.NINE_PATCH
            )

            val background =
                GenericAssetsGenerator.StandaloneWindow.generateBackground(
                    manager,
                    Size(WINDOW_WIDTH, WINDOW_HEIGHT)
                )

            background.applyOverlay(image)

            return background
        }
    }

    private object Config {
        private const val WINDOW_WIDTH = 177
        private const val WINDOW_HEIGHT = 176
        private const val TEXTURE_SIZE = 256

        fun generate(manager: ResourceManager): TextureImage {
            val original = TextureImage.open(manager, Identifier(modId, "gui/waystone_config"))
            val image = original.makeCopy()

            image.removeElementBackground(
                WINDOW_WIDTH,
                WINDOW_HEIGHT,
                GenericAssetsGenerator.StandaloneWindow.NINE_PATCH
            )

            val transformed =
                GenericAssetsGenerator.StandaloneWindow.generateBackground(
                    manager,
                    Size(WINDOW_WIDTH, WINDOW_HEIGHT)
                )

            transformed.applyOverlay(image)

            return transformed
        }
    }
}
