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

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;

public class ProtoRasterizer implements WorldRasterizer {

    @Override
    public void initialize() {
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        ProtoBiomeFacet protoBiomeFacet = chunkRegion.getFacet(ProtoBiomeFacet.class);
        ProtoBiomeRasterizer protoBiomeRasterizer = protoBiomeFacet.getRasterizer();
        protoBiomeRasterizer.generateChunk(chunk, chunkRegion);

        ProtoBiome protoBiome = chunkRegion.getFacet(ProtoBiomeFacet.class).getBiome();
        for (Vector3i position : chunkRegion.getRegion()) {
            Vector3i worldPos = ChunkMath.calcBlockPos(position);
            chunk.setBiome(worldPos.x(), worldPos.y(), worldPos.z(), protoBiome);
        }
    }
}
