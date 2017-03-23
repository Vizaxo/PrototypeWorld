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
import org.terasology.math.geom.BaseVector2i;
import org.terasology.math.geom.Rect2i;
import org.terasology.math.geom.Vector2f;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.SimplexNoise;
import org.terasology.utilities.procedural.SubSampledNoise;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

public enum ProtoPlains implements ProtoBiome {
    PLAINS;

    Block grass = CoreRegistry.get(BlockManager.class).getBlock("Core:Grass");
    private Noise plainsNoise;

    @Override
    public void setSeed(long seed) {
        plainsNoise = new SubSampledNoise(new SimplexNoise(seed), new Vector2f(0.005f, 0.005f), 1);
    }

    @Override
    public String getId() {
        return "PrototypeWorld:Plains";
    }

    @Override
    public String getName() {
        return "Plains";
    }

    @Override
    public float getNewSurfaceHeight(BaseVector2i pos, float height) {
        return height + plainsNoise.noise(pos.x(), pos.y()) * 3;
    }

    @Override
    public void generateColumn(CoreChunk chunk, Region chunkRegion, int worldX, int worldZ) {
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);
        float surfaceHeight = surfaceHeightFacet.getWorld(worldX, worldZ);

        for (int chunkY = 0; chunkY < chunk.getChunkSizeY(); chunkY++) {
            if (chunk.chunkToWorldPositionY(chunkY) < surfaceHeight) {
                chunk.setBlock(ChunkMath.calcBlockPosX(worldX), chunkY, ChunkMath.calcBlockPosZ(worldZ), grass);
            }
        }
    }
}
