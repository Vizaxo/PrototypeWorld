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

import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;

public class ProtoRasterizer implements WorldRasterizer {

    @Override
    public void initialize() {
        //Nothing needed
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        ProtoBiomeFacet protoBiomeFacet = chunkRegion.getFacet(ProtoBiomeFacet.class);

        //Do for all columns in the chunk
        for (int chunkX = 0; chunkX < chunk.getChunkSizeX(); chunkX++) {
            for (int chunkZ = 0; chunkZ < chunk.getChunkSizeZ(); chunkZ++) {
                //Get the biome to generate the column
                ProtoBiome protoBiome = protoBiomeFacet.get(chunkX, chunkZ);
                protoBiome.generateColumn(chunk, chunkRegion, chunk.chunkToWorldPositionX(chunkX), chunk.chunkToWorldPositionZ(chunkZ));

                //Set the biome for every block in the column, so that the rest of the game can use it
                for (int chunkY = 0; chunkY < chunk.getChunkSizeY(); chunkY++) {
                    chunk.setBiome(chunkX, chunkY, chunkZ, protoBiome);
                }
            }
        }
    }
}
