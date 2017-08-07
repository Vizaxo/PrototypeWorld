/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.prototypeWorld;

import org.terasology.math.geom.BaseVector2i;
import org.terasology.rendering.nui.Color;
import org.terasology.world.biomes.Biome;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;

public abstract class ProtoBiome implements Biome {

    protected Color previewColor;

    ProtoBiome(Color previewColor) {
        this.previewColor = previewColor;
    }

    void initialize() {}

    /**
     * Set the seed for the generator, if needed.
     * @param seed the seed
     */
    void setSeed(long seed) {}

    /**
     * Calculate the new surface height for a position, based on the global height at that position.
     * By default, don't change the height.
     * @param pos the world position of the column
     * @param height the global height at that position
     * @return the new height for the column
     */
    float getNewSurfaceHeight(BaseVector2i pos, float height) {
        return height;
    }

    /**
     * Generate a column in the biome. This should add blocks, but should not change the surface height.
     * @param chunk the chunk in which the column is located
     * @param chunkRegion the chunkRegion in which the chunk is located
     * @param worldX the global x position of the column
     * @param worldZ the global z position of the column
     */
    abstract void generateColumn(CoreChunk chunk, Region chunkRegion, int worldX, int worldZ);

    @Override
    public float getFog() {
        return 0;
    }

    @Override
    public float getHumidity() {
        return 0;
    }

    @Override
    public float getTemperature() {
        return 0;
    }

    Color getPreviewColor() {
        return previewColor;
    }
}
