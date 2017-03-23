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
import org.terasology.world.biomes.Biome;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

public interface ProtoBiome extends Biome {

    default void setSeed(long seed) {}

    default float getNewSurfaceHeight(BaseVector2i pos, float height) {
        return height;
    }

    void generateColumn(CoreChunk chunk, Region chunkRegion, int worldX, int worldZ);

    @Override
    default float getFog() {
        return 0;
    }

    @Override
    default float getHumidity() {
        return 0;
    }

    @Override
    default float getTemperature() {
        return 0;
    }
}
